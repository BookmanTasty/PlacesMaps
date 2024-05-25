package com.bookmantasty.travelmaps.dtos;


import java.util.List;

public class InfoDto {

    private List<String> copyrights;
    private Integer took;

    public InfoDto() {
    }

    public List<String> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<String> copyrights) {
        this.copyrights = copyrights;
    }

    public Integer getTook() {
        return took;
    }

    public void setTook(Integer took) {
        this.took = took;
    }
}
