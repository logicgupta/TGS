package com.logic.tsg.Model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class HardwareId {

        @SerializedName("HardwareId")
        @Expose
        private List<String> hardwareId ;


        public HardwareId(List<String> hardwareId) {
            this.hardwareId = hardwareId;
        }

        public List<String> getHardwareId() {
            return hardwareId;
        }

        public void setHardwareId(List<String> hardwareId) {
            this.hardwareId = hardwareId;
        }

    }
