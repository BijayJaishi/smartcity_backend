package com.bijay.smartcity_backend.firebase_models;

/**
 * Created by Bijay on 8/10/2018.
 */

public class Modeladdcompo {

   String  sector_name,sector_id,categoryid;

    public Modeladdcompo() {
    }


    public Modeladdcompo(String sector_name, String sector_id,String categoryid) {
        this.sector_name = sector_name;
        this.sector_id = sector_id;
        this.categoryid=categoryid;

    }

    public String getSector_name() {
        return sector_name;
    }

    public void setSector_name(String sector_name) {
        this.sector_name = sector_name;
    }

    public String getSector_id() {
        return sector_id;
    }

    public void setSector_id(String sector_id) {
        this.sector_id = sector_id;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }
}
