package com.gitsearcher.endpoint.endpoint;

import com.gitsearcher.endpoint.endpoint.dto.InfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Marta Ginosyan<br/>
 * Date: gitsearcher<br/>
 * Date: 8/5/18<br/>
 */

@SwaggerDefinition(tags = {@Tag(name = "Info", description = "Application info")})
@Api(tags = {"Info"})
@Path("/info")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface InfoEndpoint {

    @GET
    @Path("")
    InfoDto info();
}
