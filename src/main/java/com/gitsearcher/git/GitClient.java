package com.gitsearcher.git;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.Validate.notEmpty;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */

@Component
public class GitClient {

    @Autowired
    private GitOAuthProvider gitOAuthProvider;

    public List<SearchRepository> searchRepositories(final String query) {
        notEmpty(query, "query can not be empty");
        final RepositoryService repositoryService = new RepositoryService(gitOAuthProvider.getGitHubClient());
        try {
            return repositoryService.searchRepositories(query);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Repository getRepository(final String repositoryGeneratedId){
        notEmpty(repositoryGeneratedId, "repositoryGeneratedId can not be empty");
        final RepositoryService repositoryService = new RepositoryService(gitOAuthProvider.getGitHubClient());
        try {
            return repositoryService.getRepository(RepositoryId.createFromId(repositoryGeneratedId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contributor> getContributors(final String repositoryGeneratedId){
        notEmpty(repositoryGeneratedId, "repositoryGeneratedId can not be empty");
        final RepositoryService repositoryService = new RepositoryService(gitOAuthProvider.getGitHubClient());
        try {
            return repositoryService.getContributors(RepositoryId.createFromId(repositoryGeneratedId), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<RepositoryCommit> getRepositoryCommits(final String repositoryGeneratedId){
        notEmpty(repositoryGeneratedId, "repositoryGeneratedId can not be empty");
        final CommitService commitService = new CommitService(new GitHubClient());
        final List<RepositoryCommit> repositoryCommits = new ArrayList<>();
        commitService.pageCommits(RepositoryId.createFromId(repositoryGeneratedId), 1)
                .forEachRemaining(repositoryCommits::addAll);
        return repositoryCommits;
    }

    public RepositoryCommit getRepositoryCommit(final String repositoryGeneratedId, final String sha){
        notEmpty(repositoryGeneratedId, "repositoryGeneratedId can not be empty");
        notEmpty(sha, "sha can not be empty");
        final CommitService commitService = new CommitService(new GitHubClient());
        try {
            return commitService.getCommit(RepositoryId.createFromId(repositoryGeneratedId), sha);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
