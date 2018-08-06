package com.gitsearcher.rest.endpoint.dto;

import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */
public class RepositoryAnalyticsDto {

    private Repository repository;
    private List<Contributor> contributors;
    private Map<Integer, List<RepositoryCommit>> commits;
    private LocalDateTime created;

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }

    public Map<Integer, List<RepositoryCommit>> getCommits() {
        return commits;
    }

    public void setCommits(Map<Integer, List<RepositoryCommit>> commits) {
        this.commits = commits;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
