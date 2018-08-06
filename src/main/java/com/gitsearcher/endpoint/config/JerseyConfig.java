package com.gitsearcher.endpoint.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.gitsearcher.endpoint.endpoint.impl.InfoEndpointImpl;
import com.gitsearcher.endpoint.endpoint.impl.SearchEndpointImpl;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */

@Component
@ApplicationPath("")
public class JerseyConfig extends ResourceConfig{

    @Value("${api.mountPath}")
    private String mountPath;

    public JerseyConfig() {

        configEndpoints();
        configSwagger();
    }

    private void configEndpoints(){
        this.register(MultiPartFeature.class);
        this.register(JacksonJsonProvider.class);
        this.register(ObjectMapperResolver.class);
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        this.property("jersey.config.beanValidation.enableOutputValidationErrorEntity.server", false);
        this.property("jersey.config.disableAutoDiscovery", true);
        EncodingFilter.enableFor(this, new Class[]{GZipEncoder.class});
        // Endpoints
        register(InfoEndpointImpl.class);
        register(SearchEndpointImpl.class);
    }

    private void configSwagger(){
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setSchemes( new String[]{ "http" } );
        beanConfig.setHost( "localhost:9001" );
        beanConfig.setBasePath( "/api" );
        beanConfig.setDescription( "REST API services for accessing the pcg application"  );
        beanConfig.setTitle( "RESTAPI" );
        beanConfig.setVersion( "1.0.1" );
        beanConfig.setResourcePackage( "com.gitsearcher.endpoint.endpoint" );
        beanConfig.setScan(true);

        register( io.swagger.jaxrs.listing.ApiListingResource.class );
        register( io.swagger.jaxrs.listing.SwaggerSerializers.class );
    }

}
