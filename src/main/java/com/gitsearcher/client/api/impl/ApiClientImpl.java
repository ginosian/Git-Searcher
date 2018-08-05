package com.gitsearcher.client.api.impl;

import com.gitsearcher.client.InfoResource;
import com.gitsearcher.client.SearchResource;
import com.gitsearcher.client.api.ApiClient;
import com.gitsearcher.client.config.ClientConfiguration;

import javax.ws.rs.client.WebTarget;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public class ApiClientImpl implements ApiClient{

    private static final String BASE_URL = "https://validator.production.kube.navads.eu/rest";

    private final InfoResource infoResource;
    private final SearchResource searchResource;

    public ApiClientImpl(final String apiUrl) {

        final WebTarget rootTarget = ClientConfiguration.createWebTarget(BASE_URL);

        infoResource = new InfoResource(rootTarget);
        searchResource = new SearchResource(rootTarget);
    }

    public InfoResource info() {
        return infoResource;
    }

    public SearchResource search() {
        return searchResource;
    }
}
