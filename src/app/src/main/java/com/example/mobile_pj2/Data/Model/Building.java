package com.example.mobile_pj2.Data.Model;

import android.graphics.Point;

public class Building {
        private String buildingName;
        private int peopleInside;
        private String intro;
        private Point topLeft;
        private Point topRight;
        private Point bottomLeft;
        private Point bottomRight;

        public Building(String buildingName, Point topLeft, Point topRight, Point bottomLeft, Point bottomRight){
            this.buildingName = buildingName;
            this.peopleInside = 0;
            this.intro = "";
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
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

        public boolean isInside(Point person) {

            int x = person.x;
            int y = person.y;
            int a = (topLeft.x - bottomLeft.x)*(y - bottomLeft.y) - (topLeft.y - bottomLeft.y)*(x - bottomLeft.x);
            int b = (topRight.x - topLeft.x)*(y - topLeft.y) - (topRight.y - topLeft.y)*(x - topLeft.x);
            int c = (bottomRight.x - topRight.x)*(y - topRight.y) - (bottomRight.y - topRight.y)*(x - topRight.x);
            int d = (bottomLeft.x - bottomRight.x)*(y - bottomRight.y) - (bottomLeft.y - bottomRight.y)*(x - bottomRight.x);
            if((a > 0 && b > 0 && c > 0 && d > 0) || (a < 0 && b < 0 && c < 0 && d < 0))
                return true;
            return false;
            }

}
