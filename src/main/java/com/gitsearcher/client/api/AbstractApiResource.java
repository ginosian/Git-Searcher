package com.gitsearcher.client.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public class AbstractApiResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final WebTarget webTarget;

    public AbstractApiResource(final String pathSuffix,
            final WebTarget webTarget) {
        hasText(pathSuffix, "Path suffix should not be empty");
        notNull(webTarget, "web target should by provided");

        this.webTarget = webTarget.path(pathSuffix.charAt(0) == '/' ? pathSuffix : "/" + pathSuffix);
    }

    public <T> T get(final String path, final Class<T> entityType) {
        return get(path, entityType, null);
    }

    public <T> T get(final String path, final Class<T> entityType, Map<String, String> queryParams) {
        WebTarget target = webTarget.path(path);
        if (queryParams != null) {
            for (final Map.Entry<String, String> entry : queryParams.entrySet()) {
                target = target.queryParam(entry.getKey(), entry.getValue());
            }
        }
        logger.debug("Doing GET request to {}", target.getUri());
        final Response response = target.request().get(Response.class);
        return response.readEntity(entityType);
    }

    public <T> T post(final String path, final Object data, final Class<T> entityType) {
        final WebTarget target = webTarget.path(path);
        logger.debug("Doing POST request to {}", target.getUri());
        final Response response = target.request().post(Entity.entity(data, MediaType.APPLICATION_JSON), Response.class);
        return response.readEntity(entityType);
    }

    public <T> T put(final String path, final Object data, final Class<T> entityType) {
        final WebTarget target = webTarget.path(path);
        logger.debug("Doing PUT request to {}", target.getUri());
        final Response response = target.request().put(Entity.entity(data, MediaType.APPLICATION_JSON), Response.class);
        return response.readEntity(entityType);
    }

    public void delete(final String path) {
        final WebTarget target = webTarget.path(path);
        logger.debug("Doing DELETE request to {}", target.getUri());
        final Response response = target.request().delete(Response.class);
    }
}
