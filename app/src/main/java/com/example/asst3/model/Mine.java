package com.example.asst3.model;

public class Mine {

    /**
     * status ==0  tapped        not mine & show int
     * status == 1 tapped            mine & show image
     * status == 2 tapped two times  mine & show image & show int
     */


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
