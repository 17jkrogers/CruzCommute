package com.group25.cruzcommute;

public class Route {
    private String routeNum, congestion;
    private int slowdown;

    public Route(String num, String con, int slow){
        routeNum = num;
        congestion = con;
        slowdown = slow;
    }

    public String getRouteNum(){
        return routeNum;
    }

    public String getCongestion() {
        return congestion;
    }

    public int getSlowdown(){
        return slowdown;
    }

    public void setRouteNum(String newRoute){
        routeNum = newRoute;
    }

    public void setCongestion(String newCong){
        congestion = newCong;
    }

    public void setSlowdown(int newSlow){
        slowdown = newSlow;
    }

    @Override
    public String toString(){
        return "" + routeNum + " " + slowdown + " " + congestion;
    }
}
