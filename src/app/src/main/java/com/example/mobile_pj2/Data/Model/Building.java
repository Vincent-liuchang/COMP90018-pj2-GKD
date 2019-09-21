package com.example.mobile_pj2.Data.Model;

public class Building {
    private String buildingName;
    private int peopleInside;
    private String intro;

    public Building(String buildingName){
        this.buildingName = buildingName;
        this.peopleInside = 0;
        this.intro = "";
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getPeopleInside() {
        return peopleInside;
    }

    public void setPeopleInside(int peopleInside) {
        this.peopleInside = peopleInside;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
