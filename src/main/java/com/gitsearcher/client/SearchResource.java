package com.gitsearcher.client;

import com.gitsearcher.client.api.AbstractApiResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.WebTarget;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public class SearchResource extends AbstractApiResource {

    private final Logger logger = LoggerFactory.getLogger(InfoResource.class);

    public SearchResource(final WebTarget webTarget) {
        super("api", webTarget);
    }
}
