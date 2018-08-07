package com.gitsearcher.service;

import com.gitsearcher.entity.SearchResult;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */
public interface AnalyticsService {

    SearchResult calculateStats(final SearchResult searchResult);
}
