package com.gitsearcher.git.service.impl;

import com.gitsearcher.git.GitClient;
import com.gitsearcher.git.service.GitService;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<SearchRepository> searchRepository(String query) {

        final List<SearchRepository> repositories = gitClient.searchRepositories(query);
        final SearchRepository searchRepository = repositories.get(0);
        final Repository repository = gitClient.getRepository(searchRepository.generateId());
        final List<RepositoryCommit> repositoryCommits = gitClient.getRepositoryCommits(searchRepository.generateId());
        final RepositoryCommit repositoryCommit = repositoryCommits.get(0);
        final RepositoryCommit repositoryCommit1 = gitClient.getRepositoryCommit(searchRepository.generateId(), repositoryCommit.getSha());
        return repositories;
    }
}
