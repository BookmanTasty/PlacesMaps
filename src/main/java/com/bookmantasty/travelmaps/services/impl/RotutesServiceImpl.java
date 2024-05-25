package com.bookmantasty.travelmaps.services.impl;

import com.bookmantasty.travelmaps.clients.GraphHopperClient;
import com.bookmantasty.travelmaps.dtos.GraphHopperResponseDto;
import com.bookmantasty.travelmaps.dtos.PlaceDto;
import com.bookmantasty.travelmaps.dtos.RouteResponse;
import com.bookmantasty.travelmaps.models.Coordinates;
import com.bookmantasty.travelmaps.models.RoutesMap;
import com.bookmantasty.travelmaps.repositories.RoutesMapRepository;
import com.bookmantasty.travelmaps.services.RoutesService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class RotutesServiceImpl implements RoutesService {

    @RestClient
    GraphHopperClient graphHopperClient;

    @ConfigProperty(name = "maps.api.key")
    String apiKey;

    private final RoutesMapRepository routesMapRepository;

    @Inject
    public RotutesServiceImpl(RoutesMapRepository routesMapRepository) {
        this.routesMapRepository = routesMapRepository;
    }


    @Override
    @Transactional
    public List<RouteResponse> storeRoutes(List<PlaceDto> placeDtos) {
        Map<String, List<PlaceDto>> placesByFileName = placeDtos.stream().collect(Collectors.groupingBy(PlaceDto::getFileName));
        List<RoutesMap> routesMaps = new ArrayList<>();
        for (Map.Entry<String, List<PlaceDto>> entry : placesByFileName.entrySet()) {
            List<PlaceDto> places = entry.getValue();
            RoutesMap routesMap = new RoutesMap();
            routesMap.setFileName(entry.getKey());
            PlaceDto lastPlace = null;
            for (PlaceDto placeDto : places) {
                routesMap.getPlaces().add(new Coordinates(placeDto.getLatitude(), placeDto.getLongitude(),placeDto.getName()));
                if (lastPlace != null) {
                    String start = lastPlace.getLatitude() + "," + lastPlace.getLongitude();
                    String end = placeDto.getLatitude() + "," + placeDto.getLongitude();
                    GraphHopperResponseDto routeResponse = graphHopperClient.getRoute(start, end, false, "car", false, apiKey);
                    List<Coordinates> coordinates = routeResponse.getPaths().stream()
                            .flatMap(pathDto -> pathDto.getPoints().getCoordinates().stream())
                            .map(point -> new Coordinates( point.get(1),point.getFirst()))
                            .toList();
                    routesMap.getRoutes().addAll(coordinates);
                    List<BigDecimal> distances = routeResponse.getPaths().stream()
                            .map(pathDto -> BigDecimal.valueOf(pathDto.getDistance()))
                            .toList();
                    routesMap.addDistance(distances.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
                }
                lastPlace = placeDto;
            }
            routesMaps.add(routesMap);
        }
        routesMapRepository.persist(routesMaps);
        return routesMaps.stream().map(RouteResponse::new).toList();
    }

    @Override
    public RoutesMap getRoutes(UUID id) {
        return routesMapRepository.findById(id);
    }
}

