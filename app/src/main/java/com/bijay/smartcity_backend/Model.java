package com.bijay.smartcity_backend;

/**
 * Created by Bijay on 7/22/2018.
 */

class Modeladdcate {

    String name,id;

    public Modeladdcate() {

    }

    public Modeladdcate(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [Id = "+id+", Item_Name = "+name+"]";
    }

}
