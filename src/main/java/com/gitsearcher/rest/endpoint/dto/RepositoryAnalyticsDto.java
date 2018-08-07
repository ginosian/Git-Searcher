package com.gitsearcher.rest.endpoint.dto;


import com.gitsearcher.entity.Contributor;
import com.gitsearcher.entity.ContributorStat;
import com.gitsearcher.entity.Repository;
import com.gitsearcher.entity.RepositoryCommit;
import com.gitsearcher.misc.ContributionHonor;

import java.time.LocalDateTime;
import java.util.EnumMap;
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
    private List<RepositoryCommit> commits;
    private Integer totalCommits;
    private Map<ContributionHonor, ContributorStat> stats = new EnumMap<>(ContributionHonor.class);
    private LocalDateTime earliestCommit;
    private LocalDateTime latestCommit;
    private LocalDateTime searchDate;

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

    public List<RepositoryCommit> getCommits() {
        return commits;
    }

    public void setCommits(List<RepositoryCommit> commits) {
        this.commits = commits;
    }

    public Integer getTotalCommits() {
        return totalCommits;
    }

    public void setTotalCommits(Integer totalCommits) {
        this.totalCommits = totalCommits;
    }

    public Map<ContributionHonor, ContributorStat> getStats() {
        return stats;
    }

    public void setStats(Map<ContributionHonor, ContributorStat> stats) {
        this.stats = stats;
    }

    public LocalDateTime getEarliestCommit() {
        return earliestCommit;
    }

    public void setEarliestCommit(LocalDateTime earliestCommit) {
        this.earliestCommit = earliestCommit;
    }

    public LocalDateTime getLatestCommit() {
        return latestCommit;
    }

    public void setLatestCommit(LocalDateTime latestCommit) {
        this.latestCommit = latestCommit;
    }

    public LocalDateTime getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDateTime searchDate) {
        this.searchDate = searchDate;
    }
}
