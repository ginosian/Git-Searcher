package com.gitsearcher.service.impl;

import com.gitsearcher.git.GitClient;
import com.gitsearcher.rest.endpoint.dto.RepositoryAnalyticsDto;
import com.gitsearcher.service.GitService;
import com.google.common.collect.Lists;
import org.eclipse.egit.github.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */

@Service
public class GitServiceImpl implements GitService{

    @Autowired
    private GitClient gitClient;

    @Override
    public List<SearchRepository> searchRepositories(String query) {
        final List<SearchRepository> repositories = gitClient.searchRepositories(query);
        return repositories;
    }

    @Override
    public RepositoryAnalyticsDto analytics(String repositoryGeneratedId) {
        final Repository repository = gitClient.getRepository(repositoryGeneratedId);
        final List<Contributor> contributors = gitClient.getContributors(repositoryGeneratedId);
        final Map<User, List<RepositoryCommit>> commits = recalculateData(searchRepositoryCommits(repositoryGeneratedId));
        final RepositoryAnalyticsDto repositoryAnalyticsDto = new RepositoryAnalyticsDto();
        repositoryAnalyticsDto.setRepository(repository);
        repositoryAnalyticsDto.setContributors(contributors);
        repositoryAnalyticsDto.setCommits(commits);
        return repositoryAnalyticsDto;
    }

    private List<RepositoryCommit> searchRepositoryCommits(final String repositoryGeneratedId){
        final List<RepositoryCommit> repositoryCommits = gitClient.getRepositoryCommits(repositoryGeneratedId);
       return repositoryCommits
                .stream()
                .map(commit -> CompletableFuture.supplyAsync(() -> getCommitWithAnalytics(repositoryGeneratedId, commit.getSha())))
                .map(this::getRepositoryCommit)
                .collect(Collectors.toList());
    }

    private RepositoryCommit getCommitWithAnalytics(final String repositoryGeneratedId, final String sha){
       return gitClient.getRepositoryCommit(repositoryGeneratedId, sha);
    }

    private RepositoryCommit getRepositoryCommit( CompletableFuture<RepositoryCommit> repositoryCommitCompletableFuture){
        try {
            return repositoryCommitCompletableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<User, List<RepositoryCommit>> recalculateData(List<RepositoryCommit> repositoryCommits){
        final Map<User, List<RepositoryCommit>> userCommitsMap= new HashMap<>();
        repositoryCommits.forEach(repositoryCommit -> {
            final User commiter = repositoryCommit.getCommitter();
            final List<RepositoryCommit> value = userCommitsMap.get(commiter);
            if(value == null){
                userCommitsMap.put(commiter, Lists.newArrayList(repositoryCommit));
            } else {
                value.add(repositoryCommit);
            }
        });

        return userCommitsMap;
    }
}
