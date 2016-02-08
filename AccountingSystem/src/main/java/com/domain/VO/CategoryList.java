package com.domain.VO;

import com.domain.Database.Detail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by semri on 1/17/2016.
 */
public class CategoryList {

    String name;
    List<Detail> details = new ArrayList<Detail>();

    public CategoryList(String name){
        this.name = name;
    }

    public void addDetail(Detail detail){
        this.details.add(detail);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }
}
