package com.gitsearcher.endpoint.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.eclipse.egit.github.core.SearchRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */

@SwaggerDefinition(tags = {@Tag(name = "Info", description = "Application info")})
@Api(tags = {"Info"})
@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SearchEndpoint {

    @GET
    @Path("")
    List<SearchRepository> searchRepositories(
            @QueryParam("query") final String query,
            @DefaultValue("0") @QueryParam("page") final Integer page
    );
}
