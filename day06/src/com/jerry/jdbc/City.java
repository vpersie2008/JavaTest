package com.jerry.jdbc;

public class City {

    public String name;
    public int id;
    public String countryCode;
    public String district;

    public City() {
    }

    public City(String name, int id, String countryCode, String district) {
        this.name = name;
        this.id = id;
        this.countryCode = countryCode;
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                '}' + "\n";
    }



}
