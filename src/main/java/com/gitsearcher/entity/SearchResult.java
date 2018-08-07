package com.gitsearcher.entity;

import com.gitsearcher.misc.ContributionHonor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */


@Entity
@Table(name = "search_result")
public class SearchResult extends AbstractEntity{

    @OneToOne
    private Repository repository;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Contributor> contributors = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<RepositoryCommit> commits = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<ContributorStat> contributorStats = new ArrayList<>();

    @Column(name = "earliest_commit")
    private LocalDateTime earliestCommit;

    @Column(name = "latest_commit")
    private LocalDateTime latestCommit;

    @Column(name = "total_commits")
    private Integer totalCommits;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "key", updatable = false)
    @Column(name = "value", updatable = false)
    @CollectionTable(name = "contribution_stat", joinColumns = @JoinColumn(name = "id"))
    @MapKeyEnumerated(EnumType.STRING)
    private Map<ContributionHonor, ContributorStat> stats = new EnumMap<>(ContributionHonor.class);

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

    public List<ContributorStat> getContributorStats() {
        return contributorStats;
    }

    public void setContributorStats(List<ContributorStat> contributorStats) {
        this.contributorStats = contributorStats;
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
}
