package com.gitsearcher.service;

import com.gitsearcher.entity.SearchResult;
import org.eclipse.egit.github.core.SearchRepository;

import java.util.List;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public interface GitService {

    List<SearchRepository> searchRepositories(String query, int page);

    SearchResult searchRepository(String repositoryGeneratedId);
}
