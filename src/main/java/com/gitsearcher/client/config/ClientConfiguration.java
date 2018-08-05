package com.gitsearcher.client.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.filter.EncodingFilter;
import org.glassfish.jersey.message.GZipEncoder;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */
public class ClientConfiguration {

    private ClientConfiguration() {
        throw new IllegalStateException("Class cannot be initialized");
    }

    public static WebTarget createWebTarget(final String baseUrl) {

        return ClientBuilder.newClient(
                new ClientConfig()
                        .property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true)
                        .register(GZipEncoder.class)
                        .register(EncodingFilter.class)
                        .register(new JacksonJsonProvider(
                                        new ObjectMapper()
                                                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                                )
                        )
        ).target(baseUrl);
    }
}
