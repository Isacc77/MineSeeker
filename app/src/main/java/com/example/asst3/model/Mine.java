package com.example.asst3.model;


/**
 * Mine class
 * basic logic:
 * status == 0 untapped      not mine default
 * status == 1 untapped          mine
 * status == 2 tapped            mine & 1-> 2 show image
 * status == 3 tapped two times  mine & 2-> 3 show image & show int
 * status == 4 not mine tapped more than one times & no response
 */



public class Mine {

    private int status;

    public Mine(int status) {

        this.status = status;

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



}
