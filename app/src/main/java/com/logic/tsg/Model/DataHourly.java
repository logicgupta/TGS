package com.logic.tsg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataHourly {

    @SerializedName("vry")
    @Expose
    private int vry;
    @SerializedName("vyb")
    @Expose
    private double vyb;
    @SerializedName("vbr")
    @Expose
    private int vbr;
    @SerializedName("vrn")
    @Expose
    private int vrn;
    @SerializedName("vyn")
    @Expose
    private int vyn;
    @SerializedName("vbn")
    @Expose
    private int vbn;
    @SerializedName("ir")
    @Expose
    private double ir;
    @SerializedName("iy")
    @Expose
    private double iy;
    @SerializedName("ib")
    @Expose
    private double ib;
    @SerializedName("electricity_consumption")
    @Expose
    private double electricityConsumption;
    @SerializedName("hour")
    @Expose
    private int hour;
    @SerializedName("time")
    @Expose
    private int time;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("currentbilling")
    @Expose
    private double currentbilling;

    public int getVry() {
        return vry;
    }

    public void setVry(int vry) {
        this.vry = vry;
    }

    public double getVyb() {
        return vyb;
    }

    public void setVyb(double vyb) {
        this.vyb = vyb;
    }

    public int getVbr() {
        return vbr;
    }

    public void setVbr(int vbr) {
        this.vbr = vbr;
    }

    public int getVrn() {
        return vrn;
    }

    public void setVrn(int vrn) {
        this.vrn = vrn;
    }

    public int getVyn() {
        return vyn;
    }

    public void setVyn(int vyn) {
        this.vyn = vyn;
    }

    public int getVbn() {
        return vbn;
    }

    public void setVbn(int vbn) {
        this.vbn = vbn;
    }

    public double getIr() {
        return ir;
    }

    public void setIr(double ir) {
        this.ir = ir;
    }

    public double getIy() {
        return iy;
    }

    public void setIy(double iy) {
        this.iy = iy;
    }

    public double getIb() {
        return ib;
    }

    public void setIb(double ib) {
        this.ib = ib;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(double electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public double getCurrentbilling() {
        return currentbilling;
    }

    public void setCurrentbilling(double currentbilling) {
        this.currentbilling = currentbilling;
    }

}