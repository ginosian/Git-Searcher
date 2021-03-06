package com.gitsearcher.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */


@Entity
@Table(name = "repository_commit")
public class RepositoryCommit extends AbstractEntity{

    @OneToOne
    private Contributor contributor;

    @Column(name = "sha")
    private String sha;

    @Column(name = "url")
    private String url;

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

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
