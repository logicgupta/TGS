package com.logic.tsg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Header {

    @SerializedName("current_load")
    @Expose
    private double currentLoad;
    @SerializedName("electricity_consumption")
    @Expose
    private double electricityConsumption;
    @SerializedName("currentbilling")
    @Expose
    private double currentbilling;
    @SerializedName("current_meter_reading")
    @Expose
    private double currentMeterReading;
    @SerializedName("last_date")
    @Expose
    private String lastDate;
    @SerializedName("current_balance")
    @Expose
    private int currentBalance;
    @SerializedName("carbon_footprint")
    @Expose
    private int carbonFootprint;
    @SerializedName("carbon_emission")
    @Expose
    private double carbonEmission;
    @SerializedName("next_billdate")
    @Expose
    private String nextBilldate;
    @SerializedName("current_in_red")
    @Expose
    private double currentInRed;
    @SerializedName("current_in_blue")
    @Expose
    private double currentInBlue;
    @SerializedName("current_in_yellow")
    @Expose
    private double currentInYellow;
    @SerializedName("voltage_in_vry")
    @Expose
    private int voltageInVry;
    @SerializedName("voltage_in_vyb")
    @Expose
    private double voltageInVyb;
    @SerializedName("voltage_in_vbr")
    @Expose
    private int voltageInVbr;
    @SerializedName("voltage_in_vrn")
    @Expose
    private int voltageInVrn;
    @SerializedName("voltage_in_vyn")
    @Expose
    private int voltageInVyn;
    @SerializedName("voltage_in_vbn")
    @Expose
    private int voltageInVbn;

    public double getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(double currentLoad) {
        this.currentLoad = currentLoad;
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

    public double getCurrentMeterReading() {
        return currentMeterReading;
    }

    public void setCurrentMeterReading(double currentMeterReading) {
        this.currentMeterReading = currentMeterReading;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getCarbonFootprint() {
        return carbonFootprint;
    }

    public void setCarbonFootprint(int carbonFootprint) {
        this.carbonFootprint = carbonFootprint;
    }

    public double getCarbonEmission() {
        return carbonEmission;
    }

    public void setCarbonEmission(double carbonEmission) {
        this.carbonEmission = carbonEmission;
    }

    public String getNextBilldate() {
        return nextBilldate;
    }

    public void setNextBilldate(String nextBilldate) {
        this.nextBilldate = nextBilldate;
    }

    public double getCurrentInRed() {
        return currentInRed;
    }

    public void setCurrentInRed(double currentInRed) {
        this.currentInRed = currentInRed;
    }

    public double getCurrentInBlue() {
        return currentInBlue;
    }

    public void setCurrentInBlue(double currentInBlue) {
        this.currentInBlue = currentInBlue;
    }

    public double getCurrentInYellow() {
        return currentInYellow;
    }

    public void setCurrentInYellow(double currentInYellow) {
        this.currentInYellow = currentInYellow;
    }

    public int getVoltageInVry() {
        return voltageInVry;
    }

    public void setVoltageInVry(int voltageInVry) {
        this.voltageInVry = voltageInVry;
    }

    public double getVoltageInVyb() {
        return voltageInVyb;
    }

    public void setVoltageInVyb(double voltageInVyb) {
        this.voltageInVyb = voltageInVyb;
    }

    public int getVoltageInVbr() {
        return voltageInVbr;
    }

    public void setVoltageInVbr(int voltageInVbr) {
        this.voltageInVbr = voltageInVbr;
    }

    public int getVoltageInVrn() {
        return voltageInVrn;
    }

    public void setVoltageInVrn(int voltageInVrn) {
        this.voltageInVrn = voltageInVrn;
    }

    public int getVoltageInVyn() {
        return voltageInVyn;
    }

    public void setVoltageInVyn(int voltageInVyn) {
        this.voltageInVyn = voltageInVyn;
    }

    public int getVoltageInVbn() {
        return voltageInVbn;
    }

    public void setVoltageInVbn(int voltageInVbn) {
        this.voltageInVbn = voltageInVbn;
    }

}
