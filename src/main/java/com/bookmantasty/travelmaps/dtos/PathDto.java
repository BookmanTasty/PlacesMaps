package com.bookmantasty.travelmaps.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PathDto {
    private Double distance;
    private Double weight;
    private Integer time;
    private Integer transfers;
    private Boolean points_encoded;
    private List<Double> bbox;
    private PointsDto points;
    private List<InstructionDto> instructions;
    private List<Object> legs;
    private DetailsDto details;
    private Double ascend;
    private Double descend;
    @JsonProperty("snapped_waypoints")
    private PointsDto snappedWaypoints;

    public PathDto() {
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getTransfers() {
        return transfers;
    }

    public void setTransfers(Integer transfers) {
        this.transfers = transfers;
    }

    public Boolean getPoints_encoded() {
        return points_encoded;
    }

    public void setPoints_encoded(Boolean points_encoded) {
        this.points_encoded = points_encoded;
    }

    public List<Double> getBbox() {
        return bbox;
    }

    public void setBbox(List<Double> bbox) {
        this.bbox = bbox;
    }

    public PointsDto getPoints() {
        return points;
    }

    public void setPoints(PointsDto points) {
        this.points = points;
    }

    public List<InstructionDto> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<InstructionDto> instructions) {
        this.instructions = instructions;
    }

    public List<Object> getLegs() {
        return legs;
    }

    public void setLegs(List<Object> legs) {
        this.legs = legs;
    }

    public DetailsDto getDetails() {
        return details;
    }

    public void setDetails(DetailsDto details) {
        this.details = details;
    }

    public Double getAscend() {
        return ascend;
    }

    public void setAscend(Double ascend) {
        this.ascend = ascend;
    }

    public Double getDescend() {
        return descend;
    }

    public void setDescend(Double descend) {
        this.descend = descend;
    }

    public PointsDto getSnappedWaypoints() {
        return snappedWaypoints;
    }

    public void setSnappedWaypoints(PointsDto snappedWaypoints) {
        this.snappedWaypoints = snappedWaypoints;
    }
}
