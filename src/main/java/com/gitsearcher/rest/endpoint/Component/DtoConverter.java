package com.gitsearcher.rest.endpoint.Component;

import com.gitsearcher.entity.SearchResult;
import com.gitsearcher.rest.endpoint.dto.RepositoryAnalyticsDto;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/7/18<br/>
 */
public class DtoConverter {

    public RepositoryAnalyticsDto convert(final SearchResult searchResult){
        final RepositoryAnalyticsDto repositoryAnalyticsDto = new RepositoryAnalyticsDto();
        repositoryAnalyticsDto.setRepository(searchResult.getRepository());
        repositoryAnalyticsDto.setContributors(searchResult.getContributors());
        repositoryAnalyticsDto.setCommits(searchResult.getCommits());
        repositoryAnalyticsDto.setTotalCommits(searchResult.getTotalCommits());
        repositoryAnalyticsDto.setStats(searchResult.getStats());
        repositoryAnalyticsDto.setSearchDate(searchResult.getCreated());
        return repositoryAnalyticsDto;
    }
}
