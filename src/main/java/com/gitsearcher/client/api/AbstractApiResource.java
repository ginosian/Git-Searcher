package com.gitsearcher.client.api;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public class AbstractApiResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected final Client client;
    protected final WebTarget rootTarget;

    protected AbstractApiResource(final Client client, final WebTarget rootTarget, final String pathSuffix) {
        if (client == null || rootTarget == null || StringUtils.isEmpty(pathSuffix)) {
            throw new IllegalStateException("Failed to construct ApiResource instance. Supplied " +
                    "client:" + (client == null) + ", " +
                    "rootTarget:" + (rootTarget == null) + ", " +
                    "pathSuffix:" + StringUtils.isEmpty(pathSuffix));
        }

        String finalPathSuffix = pathSuffix;
        if (!"/".equals(pathSuffix.substring(0, 1))) {
            finalPathSuffix = "/" + pathSuffix;
        }

        this.client = client;
        this.rootTarget = rootTarget.path(finalPathSuffix);
    }


    // region GET
    public <T> T doGet(final String path, final Class<T> entityType) {
        Response response = null;
        try {
            response = doGet(path);
            return response.readEntity(entityType);
        } finally {
            closeResponse(response);
        }
    }

    public <T> T doGet(final String path, final GenericType<T> entityType) {
        Response response = null;

        try {
            response = doGet(path);
            return response.readEntity(entityType);
        } finally {
            closeResponse(response);
        }
    }

    public Response doGet(final String path, Map<String, String> queryParams) {
        WebTarget target = rootTarget.path(path);
        if (queryParams != null) {
            for (Map.Entry<String, String> e : queryParams.entrySet())
                target = target.queryParam(e.getKey(), e.getValue());
        }
        logger.debug("Doing GET request to {}", target.getUri());
        return target.request().get();
    }

    public <T> T doGet(final String path, final Class<T> entityType, Map<String, String> queryParams) {
        Response response = null;
        try {
            response = doGet(path, queryParams);
            return response.readEntity(entityType);
        } finally {
            closeResponse(response);
        }
    }

    public <T> T doGet(final String path, final GenericType<T> entityType, Map<String, String> queryParams) {
        Response response = null;
        try {
            response = doGet(path, queryParams);
            return response.readEntity(entityType);
        } finally {
            closeResponse(response);
        }
    }

    private Response doGet(final String path) {
        WebTarget target = rootTarget.path(path);
        logger.debug("Doing GET request to {}", target.getUri());
        return target.request().get();
    }
    // endregion

   // region POST
    public  <T> T doPost(String path, Object data, Class<T> entityType) {
        Response response = null;
        try {
            response = doPost(path, data);
            return response.readEntity(entityType);
        } finally {
            closeResponse(response);
        }
    }

    public  <T> T doPost(final String path, final Object data, final GenericType<T> entityType) {
        Response response = null;
        try {
            response = doPost(path, data);
            return response.readEntity(entityType);
        } finally {
            closeResponse(response);
        }
    }

    protected Response doPost(final String path, final Object data) {
        WebTarget target = rootTarget.path(path);
        logger.debug("Doing POST request to {}", target.getUri());
        return target.request().post(Entity.entity(data, MediaType.APPLICATION_JSON), Response.class);
    }

    // endregion

    // region PUT
    public  <T> T doPut(String path, Object data, Class<T> entityType) {
        Response response = null;

        try {
            response = doPut(path, data);
            return response.readEntity(entityType);
        } finally {
            closeResponse(response);
        }
    }

    public  <T> T doPut(final String path, final Object data, final GenericType<T> entityType) {
        Response response = null;

        try {
            response = doPut(path, data);
            return response.readEntity(entityType);
        } finally {
            closeResponse(response);
        }
    }

    private Response doPut(final String path, final Object data) {
        WebTarget target = rootTarget.path(path);
        logger.debug("Doing PUT request to {}", target.getUri());
        Invocation.Builder request = target.request();
        if (data == null) {
            request = request.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);
        }
        return request.put(Entity.entity(data, MediaType.APPLICATION_JSON), Response.class);
    }

    // endregion

    // region DELETE
    public  <T> T doDelete(String path, Class<T> entityType) {
        Response response = null;
        try {
            response = doDelete(path);
            return response.readEntity(entityType);
        } finally {
            closeResponse(response);
        }
    }

    public  <T> T doDelete(final String path, final GenericType<T> entityType) {
        Response response = null;
        try {
            response = doDelete(path);
            return response.readEntity(entityType);
        } finally {
            closeResponse(response);
        }
    }

    private Response doDelete(final String path) {
        WebTarget target = rootTarget.path(path);
        logger.debug("Doing DELETE request to {}", target.getUri());
        return target.request().delete();
    }

    // endregion

    private void closeResponse(final Response r) {
        try {
            if (r != null) {
                r.close();
            }
        } catch (Exception e) {
            logger.debug("Error when closing the response object", e);
        }
    }
}
