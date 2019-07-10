package com.logic.tsg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataWeek {

    @SerializedName("index")
    @Expose
    private int index;
    @SerializedName("vry")
    @Expose
    private int vry;
    @SerializedName("vyb")
    @Expose
    private double vyb;
    @SerializedName("vbr")
    @Expose
    private double vbr;
    @SerializedName("vrn")
    @Expose
    private double vrn;
    @SerializedName("vbn")
    @Expose
    private double vbn;
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
    @SerializedName("currentbilling")
    @Expose
    private double currentbilling;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

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

    public double getVbr() {
        return vbr;
    }

    public void setVbr(double vbr) {
        this.vbr = vbr;
    }

    public double getVrn() {
        return vrn;
    }

    public void setVrn(double vrn) {
        this.vrn = vrn;
    }

    public double getVbn() {
        return vbn;
    }

    public void setVbn(double vbn) {
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

    public double getCurrentbilling() {
        return currentbilling;
    }

    public void setCurrentbilling(double currentbilling) {
        this.currentbilling = currentbilling;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}