package com.bookmantasty.travelmaps.dtos;

import java.util.List;

public class GraphHopperResponseDto {
    private HintsDto hints;
    private InfoDto info;
    private List<PathDto> paths;

    public GraphHopperResponseDto() {
    }

    public HintsDto getHints() {
        return hints;
    }

    public void setHints(HintsDto hints) {
        this.hints = hints;
    }

    public InfoDto getInfo() {
        return info;
    }

    public void setInfo(InfoDto info) {
        this.info = info;
    }

    public List<PathDto> getPaths() {
        return paths;
    }

    public void setPaths(List<PathDto> paths) {
        this.paths = paths;
    }
}
