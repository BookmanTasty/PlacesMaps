package com.bookmantasty.travelmaps.services;

import com.bookmantasty.travelmaps.dtos.PlaceDto;
import com.bookmantasty.travelmaps.dtos.RouteResponse;
import com.bookmantasty.travelmaps.models.RoutesMap;

import java.util.List;
import java.util.UUID;

public interface RoutesService {
    List<RouteResponse> storeRoutes(List<PlaceDto> placeDtos);
    RoutesMap getRoutes(UUID id);
}