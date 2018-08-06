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
@Table(name = "contributor")
public class Contributor extends AbstractEntity{

    @Column(name = "git_id")
    private Integer gitId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    public Integer getGitId() {
        return gitId;
    }

    public void setGitId(Integer gitId) {
        this.gitId = gitId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
