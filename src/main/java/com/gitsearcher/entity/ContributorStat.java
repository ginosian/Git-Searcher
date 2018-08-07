package com.gitsearcher.entity;

import javax.persistence.*;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */


@Entity
@Table(name = "contributor_stat")
public class ContributorStat extends AbstractEntity{

    @OneToOne
    @MapsId
    private Contributor contributor;

    @OneToOne
    @MapsId
    private SearchResult searchResult;

    @Column(name = "additions")
    private Integer additions;

    @Column(name = "deletions")
    private Integer deletions;

    @Column(name = "total")
    private Integer total;

    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public Integer getAdditions() {
        return additions;
    }

    public void setAdditions(Integer additions) {
        this.additions = additions;
    }

    public Integer getDeletions() {
        return deletions;
    }

    public void setDeletions(Integer deletions) {
        this.deletions = deletions;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Transient
    public void addAddition(int addition){
        this.additions += addition;
    }

    @Transient
    public void addDelition(int delition){
        this.deletions += delition;
    }

    @Transient
    public void addTotal(int total){
        this.total += total;
    }

}
