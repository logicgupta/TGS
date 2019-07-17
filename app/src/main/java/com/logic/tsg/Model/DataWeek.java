package com.logic.tsg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataWeek {


    @SerializedName("index")
    @Expose
    private String index;
    @SerializedName("vry")
    @Expose
    private int vry;
    @SerializedName("vyb")
    @Expose
    private int vyb;
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
    private int ir;
    @SerializedName("iy")
    @Expose
    private int iy;
    @SerializedName("ib")
    @Expose
    private int ib;
    @SerializedName("electricity_consumption")
    @Expose
    private int electricityConsumption;
    @SerializedName("currentbilling")
    @Expose
    private int currentbilling;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getVry() {
        return vry;
    }

    public void setVry(int vry) {
        this.vry = vry;
    }

    public int getVyb() {
        return vyb;
    }

    public void setVyb(int vyb) {
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

    public int getIr() {
        return ir;
    }

    public void setIr(int ir) {
        this.ir = ir;
    }

    public int getIy() {
        return iy;
    }

    public void setIy(int iy) {
        this.iy = iy;
    }

    public int getIb() {
        return ib;
    }

    public void setIb(int ib) {
        this.ib = ib;
    }

    public int getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(int electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    public int getCurrentbilling() {
        return currentbilling;
    }

    public void setCurrentbilling(int currentbilling) {
        this.currentbilling = currentbilling;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}