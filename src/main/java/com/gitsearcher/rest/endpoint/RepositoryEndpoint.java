package com.gitsearcher.rest.endpoint;

import com.gitsearcher.rest.endpoint.dto.RepositoryAnalyticsDto;
import io.swagger.annotations.*;
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
@Path("/repositories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RepositoryEndpoint {

    @ApiOperation(
            value = "Searches repositories in github by query.",
            notes = "A fuzzy search by query text.",
            response = SearchRepository.class
    )
    @GET
    @Path("/search")
    List<SearchRepository> search(
            @QueryParam("query") final String query,
            @DefaultValue("0") @QueryParam("page") final Integer page
    );

    @GET
    @Path("/{repositoryGeneratedId}/analytics")
    RepositoryAnalyticsDto analytics(
            @PathParam("repositoryGeneratedId") final String repositoryGeneratedId
    );
}
