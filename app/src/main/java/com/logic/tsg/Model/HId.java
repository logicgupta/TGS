package com.logic.tsg.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HId {
    @SerializedName("HardwareId")
    @Expose
    String HardwareId;

    public HId() {
    }

    public HId(String hardwareId) {
        HardwareId = hardwareId;
    }

    public String getHardwareId() {
        return HardwareId;
    }

    public void setHardwareId(String hardwareId) {
        HardwareId = hardwareId;
    }
}
