package com.example.mobile_pj2.Data.Model;

public class Building implements Comparable{
    private String buildingName;
    private int peopleInside;
    private String intro;
    private GeoPoint topLeft;
    private GeoPoint topRight;
    private GeoPoint bottomLeft;
    private GeoPoint bottomRight;
    private double distance;
    private boolean inside;

    public Building(String buildingName, GeoPoint topLeft, GeoPoint topRight, GeoPoint bottomLeft, GeoPoint bottomRight){
        this.buildingName = buildingName;
        this.peopleInside = 0;
        this.intro = "";
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        this.inside = false;
    }

    public boolean getInside() {
        return inside;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
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

    public void setDistance(double distance){
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void calculateDistanse(GeoPoint person) {

        double x = person.x;
        double y = person.y;
        double center_x = (topLeft.x+topRight.x+bottomLeft.x+bottomRight.x)/4;
        double center_y = (topLeft.y+topRight.y+bottomLeft.y+bottomRight.y)/4;
        this.distance = Math.sqrt(Math.pow((x-center_x),2)+Math.pow((y-center_y),2))*100000;
    }

    public boolean isInside(GeoPoint person) {

        double x = person.x;
        double y = person.y;
//            int a = (topLeft.x - bottomLeft.x) * (y - bottomLeft.y) - (topLeft.y - bottomLeft.y) * (x - bottomLeft.x);
//            int b = (topRight.x - topLeft.x) * (y - topLeft.y) - (topRight.y - topLeft.y) * (x - topLeft.x);
//            int c = (bottomRight.x - topRight.x) * (y - topRight.y) - (bottomRight.y - topRight.y) * (x - topRight.x);
//            int d = (bottomLeft.x - bottomRight.x) * (y - bottomRight.y) - (bottomLeft.y - bottomRight.y) * (x - bottomRight.x);
//            if ((a > 0 && b > 0 && c > 0 && d > 0) || (a < 0 && b < 0 && c < 0 && d < 0))
//                return true;

        double lowLongtitude = Math.min(Math.min(topLeft.x, topRight.x), Math.min(bottomRight.x, bottomLeft.x));
        double highLongtitude = Math.max(Math.max(topLeft.x, topRight.x), Math.max(bottomRight.x, bottomLeft.x));

        double lowLatitude = Math.min(Math.min(topLeft.y, topRight.y), Math.min(bottomRight.y, bottomLeft.y));
        double highLatitude = Math.max(Math.max(topLeft.y, topRight.y), Math.max(bottomRight.y, bottomLeft.y));

        if(x > lowLongtitude && x < highLongtitude && y > lowLatitude && y < highLatitude) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Building) {

            return (int)(this.distance - ((Building) o).getDistance());
        }
        else return 0;
    }
}