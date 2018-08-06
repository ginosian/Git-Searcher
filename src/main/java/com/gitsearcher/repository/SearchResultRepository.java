package com.gitsearcher.repository;

import com.gitsearcher.entity.SearchResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */
public interface SearchResultRepository  extends JpaRepository<SearchResult, String> {
}
