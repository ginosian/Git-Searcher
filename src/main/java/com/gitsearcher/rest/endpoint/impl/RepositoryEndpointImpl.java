package com.gitsearcher.rest.endpoint.impl;

import com.gitsearcher.rest.endpoint.RepositoryEndpoint;
import com.gitsearcher.rest.endpoint.dto.RepositoryAnalyticsDto;
import com.gitsearcher.service.GitService;
import org.eclipse.egit.github.core.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public class RepositoryEndpointImpl implements RepositoryEndpoint {

    //TODO testing yet, replace with service later
    @Autowired
    private GitService gitService;

    @Override
    public List<SearchRepository> search(final String query, final Integer page) {
        return gitService.searchRepositories(query);
    }

    @Override
    public RepositoryAnalyticsDto analytics(final String repositoryGeneratedId) {
        return gitService.analytics(repositoryGeneratedId);
    }
}
