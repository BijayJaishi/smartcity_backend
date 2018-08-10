package com.bijay.smartcity_backend;

/**
 * Created by Bijay on 8/10/2018.
 */

public class Modeladdcompo {

   String  sector_name,sector_id;

    public Modeladdcompo() {
    }

    public Modeladdcompo(String sector_name, String sector_id) {
        this.sector_name = sector_name;
        this.sector_id = sector_id;
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



}
