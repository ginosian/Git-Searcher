package com.gitsearcher.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitsearcher.client.api.AbstractApiResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public class SearchResource extends AbstractApiResource {

    private final Logger logger = LoggerFactory.getLogger(SearchResource.class);

    private ObjectMapper objectMapper;

    public SearchResource(Client client, WebTarget rootTarget, ObjectMapper objectMapper) {
        super(client, rootTarget, "/search");
        this.objectMapper = objectMapper;
    }
}
