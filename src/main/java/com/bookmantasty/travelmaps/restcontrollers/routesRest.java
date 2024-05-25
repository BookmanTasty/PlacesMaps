package com.bookmantasty.travelmaps.restcontrollers;

import com.bookmantasty.travelmaps.dtos.PlaceDto;
import com.bookmantasty.travelmaps.dtos.RouteResponse;
import com.bookmantasty.travelmaps.models.RoutesMap;
import com.bookmantasty.travelmaps.services.RoutesService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/routes")
public class routesRest {

    private final RoutesService routesService;

    @Inject
    public routesRest(RoutesService routesService) {
        this.routesService = routesService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<RouteResponse> storeReoutes(List<PlaceDto> placeDtos) {
        return routesService.storeRoutes(placeDtos);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RoutesMap getRoutes(@PathParam("id") UUID id) {
        return routesService.getRoutes(id);
    }

}
