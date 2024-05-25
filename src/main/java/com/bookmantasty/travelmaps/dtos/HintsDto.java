package com.bookmantasty.travelmaps.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HintsDto {
    @JsonProperty("visited_nodes.sum")
    private Integer visitedNodesSum;
    @JsonProperty("visited_nodes.average")
    private Integer visitedNodesAverage;

    public HintsDto() {
    }

    public Integer getVisitedNodesSum() {
        return visitedNodesSum;
    }

    public void setVisitedNodesSum(Integer visitedNodesSum) {
        this.visitedNodesSum = visitedNodesSum;
    }

    public Integer getVisitedNodesAverage() {
        return visitedNodesAverage;
    }

    public void setVisitedNodesAverage(Integer visitedNodesAverage) {
        this.visitedNodesAverage = visitedNodesAverage;
    }
}
