package com.gitsearcher.git;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/6/18<br/>
 */

@Component
public class GitOAuthProvider {

    private GitHubClient gitHubClient;

    public static String ACCESS_TOKEN_ID = "";

    private String scheme = "https";

    private int port = 443;

    private String gitHubApiHost = "api.github.com";

    private String gitHubHost = "github.com";

    @PostConstruct
    public void initClient(){
        gitHubClient = new GitHubClient();
    }

    public GitHubClient getGitHubClient() {
        return gitHubClient;
    }

    public void setGitHubClient(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public String getGitHubBaseUrl() {
        return getBaseUrl(gitHubHost);
    }

    public String getGitHubApiBaseUrl() {
        return getBaseUrl(gitHubApiHost);
    }

    private String getBaseUrl(String host) {
        String optionalPort = ":" + port;
        if("https".equals(scheme) && port == 443) {
            optionalPort = "";
        }
        return scheme + "://" + host + optionalPort + "/";
    }


    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getGitHubApiHost() {
        return gitHubApiHost;
    }

    public void setGitHubApiHost(String gitHubApiHost) {
        this.gitHubApiHost = gitHubApiHost;
    }

    public String getGitHubHost() {
        return gitHubHost;
    }

    public void setGitHubHost(String gitHubHost) {
        this.gitHubHost = gitHubHost;
    }
}
