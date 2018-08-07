package com.gitsearcher.rest.endpoint.impl;

import com.gitsearcher.rest.endpoint.Component.DtoConverter;
import com.gitsearcher.rest.endpoint.RepositoryEndpoint;
import com.gitsearcher.rest.endpoint.dto.RepositoryAnalyticsDto;
import com.gitsearcher.rest.endpoint.dto.RepositoryDto;
import com.gitsearcher.service.AnalyticsService;
import com.gitsearcher.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public class RepositoryEndpointImpl implements RepositoryEndpoint {

    @Autowired
    private GitService gitService;

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private DtoConverter converter;

    @Override
    public List<RepositoryDto> search(final String query, final Integer page) {
        return converter.convert(gitService.searchRepositories(query, page));
    }

    @Override
    public RepositoryAnalyticsDto analytics(final String repositoryGeneratedId) {
        return converter.convert(analyticsService.calculateStats(gitService.searchRepository(repositoryGeneratedId)));
    }
}
