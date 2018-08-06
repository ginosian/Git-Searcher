package com.gitsearcher.git;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

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
        final RepositoryService repositoryService = new RepositoryService(gitOAuthProvider.getGitHubClient());
        try {
            return repositoryService.searchRepositories(query);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Repository getRepository(final String repositoryGeneratedId){
        final RepositoryService repositoryService = new RepositoryService(gitOAuthProvider.getGitHubClient());
        try {
            return repositoryService.getRepository(RepositoryId.createFromId(repositoryGeneratedId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<RepositoryCommit> getRepositoryCommits(final String repositoryGeneratedId){
        final CommitService commitService = new CommitService(new GitHubClient());
        try {
            return commitService.getCommits(RepositoryId.createFromId(repositoryGeneratedId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public RepositoryCommit getRepositoryCommit(final String repositoryGeneratedId, final String sha){
        final CommitService commitService = new CommitService(new GitHubClient());
        try {
            return commitService.getCommit(RepositoryId.createFromId(repositoryGeneratedId), sha);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
