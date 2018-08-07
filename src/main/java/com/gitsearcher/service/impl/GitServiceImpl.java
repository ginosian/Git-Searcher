package com.gitsearcher.service.impl;

import com.gitsearcher.entity.SearchResult;
import com.gitsearcher.git.GitClient;
import com.gitsearcher.service.GitService;
import com.gitsearcher.service.component.GitModelConverter;
import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */

@Service
public class GitServiceImpl implements GitService {

    @Autowired
    private GitClient gitClient;

    @Autowired
    private GitModelConverter converter;

    @Override
    public List<SearchRepository> searchRepositories(String query, int page) {
        final List<SearchRepository> repositories = gitClient.searchRepositories(query);
        return repositories;
    }

    @Override
    public SearchResult searchRepository(String repositoryGeneratedId) {
        final Repository repository = gitClient.getRepository(repositoryGeneratedId);
        final List<Contributor> contributors = gitClient.getContributors(repositoryGeneratedId);
        final List<RepositoryCommit> repositoryCommits = searchRepositoryCommits(repositoryGeneratedId);
        final SearchResult searchResult = new SearchResult();
        searchResult.setRepository(converter.convert(repository));
        searchResult.setContributors(converter.convertContributorList(contributors));
        searchResult.setCommits(converter.convertCommitList(repositoryCommits));
        return searchResult;
    }

    private List<RepositoryCommit> searchRepositoryCommits(final String repositoryGeneratedId) {
        final List<RepositoryCommit> repositoryCommits = gitClient.getRepositoryCommits(repositoryGeneratedId);
        return repositoryCommits
                .stream()
                .map(commit -> CompletableFuture.supplyAsync(() -> getCommitWithAnalytics(repositoryGeneratedId, commit.getSha())))
                .map(this::getRepositoryCommit)
                .collect(Collectors.toList());
    }

    private RepositoryCommit getCommitWithAnalytics(final String repositoryGeneratedId, final String sha) {
        return gitClient.getRepositoryCommit(repositoryGeneratedId, sha);
    }

    private RepositoryCommit getRepositoryCommit(CompletableFuture<RepositoryCommit> repositoryCommitCompletableFuture) {
        try {
            return repositoryCommitCompletableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
