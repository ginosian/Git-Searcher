package com.gitsearcher.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */


@Entity
@Table(name = "repository")
public class Repository extends AbstractEntity{

    @Column(name = "git_id")
    private Long gitId;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    public Long getGitId() {
        return gitId;
    }

    public void setGitId(Long gitId) {
        this.gitId = gitId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
