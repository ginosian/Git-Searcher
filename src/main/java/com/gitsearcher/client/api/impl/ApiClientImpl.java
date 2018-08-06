package com.gitsearcher.client.api.impl;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.gitsearcher.client.InfoResource;
import com.gitsearcher.client.SearchResource;
import com.gitsearcher.client.api.ApiClient;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.filter.EncodingFilter;
import org.glassfish.jersey.message.GZipEncoder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */

public class ApiClientImpl implements ApiClient{

    private static final String BASE_URL = "https://localhost:8080";

    private final Client client;

    private final InfoResource infoResource;
    private final SearchResource searchResource;

    public ApiClientImpl(final String apiUrl) {
        final ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule("apiErrorMapper", Version.unknownVersion());
        mapper.registerModule(module);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ClientConfig cc = new ClientConfig();
        cc.property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);

        cc.register(GZipEncoder.class);
        cc.register(EncodingFilter.class);
        cc.register(new JacksonJsonProvider(mapper));

        client = ClientBuilder.newClient(cc);

        final WebTarget rootTarget = client.target(BASE_URL);

        infoResource = new InfoResource(client, rootTarget, mapper);
        searchResource = new SearchResource(client, rootTarget, mapper);

    }

    @Override
    public InfoResource info() {
        return infoResource;
    }

    @Override
    public SearchResource search() {
        return searchResource;
    }

    @Override
    public void close() throws IOException {

    }
}
