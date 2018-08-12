package com.bijay.smartcity_backend.firebase_models;

/**
 * Created by Bijay on 8/12/2018.
 */

public class Modeladdelement {
    String element_name,element_address,element_phone,element_open,element_close,element_desc,element_id,element_photo,catename;

    public Modeladdelement() {
    }

    public Modeladdelement(String element_name, String element_address, String element_phone, String element_open, String element_close, String element_desc, String element_id, String element_photo,String catename) {
        this.element_name = element_name;
        this.element_address = element_address;
        this.element_phone = element_phone;
        this.element_open = element_open;
        this.element_close = element_close;
        this.element_desc = element_desc;
        this.element_id = element_id;
        this.element_photo = element_photo;
        this.catename=catename;

    }

    public String getElement_name() {
        return element_name;
    }

    public void setElement_name(String element_name) {
        this.element_name = element_name;
    }

    public String getElement_address() {
        return element_address;
    }

    public void setElement_address(String element_address) {
        this.element_address = element_address;
    }

    public String getElement_phone() {
        return element_phone;
    }

    public void setElement_phone(String element_phone) {
        this.element_phone = element_phone;
    }

    public String getElement_open() {
        return element_open;
    }

    public void setElement_open(String element_open) {
        this.element_open = element_open;
    }

    public String getElement_close() {
        return element_close;
    }

    public void setElement_close(String element_close) {
        this.element_close = element_close;
    }

    public String getElement_desc() {
        return element_desc;
    }

    public void setElement_desc(String element_desc) {
        this.element_desc = element_desc;
    }

    public String getElement_id() {
        return element_id;
    }

    public void setElement_id(String element_id) {
        this.element_id = element_id;
    }

    public String getElement_photo() {
        return element_photo;
    }

    public void setElement_photo(String element_photo) {
        this.element_photo = element_photo;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }
}
