package com.gitsearcher.rest.endpoint.Component;

import com.gitsearcher.entity.SearchResult;
import com.gitsearcher.rest.endpoint.dto.RepositoryAnalyticsDto;
import com.gitsearcher.rest.endpoint.dto.RepositoryDto;
import org.eclipse.egit.github.core.SearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/7/18<br/>
 */

@Component
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

    public RepositoryDto convert(final SearchRepository searchRepository){
        final RepositoryDto repositoryDto = new RepositoryDto();
        repositoryDto.setGitGeneratedId(searchRepository.generateId());
        repositoryDto.setDescription(searchRepository.getDescription());
        repositoryDto.setName(searchRepository.getName());
        repositoryDto.setCreatorName(searchRepository.getOwner());
        repositoryDto.setUrl(searchRepository.getUrl());
        return repositoryDto;
    }

    public List<RepositoryDto> convert(final List<SearchRepository> repositories){
        return repositories.stream().map(this::convert).collect(Collectors.toList());
    }


}
