package com.gitsearcher.rest.endpoint.dto;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/7/18<br/>
 */
public class RepositoryDto {

    private String gitGeneratedId;
    private String description;
    private String name;
    private String creatorName;
    private String url;

    public String getGitGeneratedId() {
        return gitGeneratedId;
    }

    public void setGitGeneratedId(String gitGeneratedId) {
        this.gitGeneratedId = gitGeneratedId;
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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
