package com.bookmantasty.travelmaps.clients;


import com.bookmantasty.travelmaps.dtos.GraphHopperResponseDto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

    @Path("1/route")
@RegisterRestClient
public interface GraphHopperClient {

    @GET
    GraphHopperResponseDto getRoute(@QueryParam("point") String start,
                                    @QueryParam("point") String end,
                                    @QueryParam("instructions") boolean instructions,
                                    @QueryParam("vehicle") String vehicle,
                                    @QueryParam("points_encoded") boolean pointsEncoded,
                                    @QueryParam("key") String apiKey);
}
