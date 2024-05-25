package com.bookmantasty.travelmaps.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InstructionDto {
    private Double distance;
    private Double heading;
    private Integer sign;
    private List<Integer> interval;
    private String text;
    private Integer time;
    @JsonProperty("street_name")
    private String streetName;
    @JsonProperty("last_heading")
    private Double lastHeading;

    public InstructionDto() {
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getHeading() {
        return heading;
    }

    public void setHeading(Double heading) {
        this.heading = heading;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public List<Integer> getInterval() {
        return interval;
    }

    public void setInterval(List<Integer> interval) {
        this.interval = interval;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Double getLastHeading() {
        return lastHeading;
    }

    public void setLastHeading(Double lastHeading) {
        this.lastHeading = lastHeading;
    }
}
