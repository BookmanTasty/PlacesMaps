package com.bookmantasty.travelmaps.dtos;

import com.bookmantasty.travelmaps.models.RoutesMap;


import java.util.UUID;

public class RouteResponse {
    private String fileName;
    private UUID uuid;


    public RouteResponse() {
    }

    public RouteResponse(RoutesMap routesMap) {
        this.fileName = routesMap.getFileName();
        this.uuid = routesMap.getId();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
