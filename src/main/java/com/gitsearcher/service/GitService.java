package com.gitsearcher.service;

import com.gitsearcher.rest.endpoint.dto.RepositoryAnalyticsDto;
import org.eclipse.egit.github.core.SearchRepository;

import java.util.List;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public interface GitService {

    List<SearchRepository> searchRepositories(String query);

    RepositoryAnalyticsDto analytics(String repositoryGeneratedId);
}
