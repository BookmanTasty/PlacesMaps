package com.bookmantasty.travelmaps.models;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "maps")
public class RoutesMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Type(JsonType.class)
    @Column(name = "places" ,columnDefinition = "json")
    private List<Coordinates> places = new LinkedList<>();
    @Type(JsonType.class)
    @Column(name = "routes" ,columnDefinition = "json")
    private List<Coordinates> routes = new LinkedList<>();
    @Column(name = "file_name")
    private String fileName;
    private BigDecimal distance = BigDecimal.ZERO;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public RoutesMap() {
    }

    public RoutesMap(UUID id, List<Coordinates> places, List<Coordinates> routes) {
        this.id = id;
        this.places = places;
        this.routes = routes;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Coordinates> getPlaces() {
        return places;
    }

    public void setPlaces(List<Coordinates> places) {
        this.places = places;
    }

    public List<Coordinates> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Coordinates> routes) {
        this.routes = routes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void addPlace(Coordinates coordinates) {
        this.places.add(coordinates);
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public void addDistance(BigDecimal distance) {
        this.distance = this.distance.add(distance);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}