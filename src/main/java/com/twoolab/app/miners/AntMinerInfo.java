package com.twoolab.app.miners;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yeesheng on 23/02/2018
 * @project antmonitorapp
 */
public class AntMinerInfo {

    private long id;
    private String hostIp;
    private String model;
    private String hashRate;
    private String idealHashRate;
    private AntMinerTemp pcbTemperature;
    private AntMinerTemp chipTemperature;
    private String miningAccepted;
    private String miningRejected;
    private String hardwareErrors;
    private String workerName;
    private static final AtomicLong counter = new AtomicLong(100);

    public AntMinerInfo(String aHostIp, String aModel, String aHashRate, String aIdealHashRate,
                        AntMinerTemp aPcbTemp, AntMinerTemp aChipTemp, String accepted,
                        String rejected, String errors, String workerName) {
        this.hostIp = aHostIp;
        this.model = aModel;
        this.hashRate = aHashRate;
        this.idealHashRate = aIdealHashRate;
        this.pcbTemperature = aPcbTemp;
        this.chipTemperature = aChipTemp;
        this.miningAccepted = accepted;
        this.miningRejected = rejected;
        this.hardwareErrors = errors;
        this.id = counter.incrementAndGet();
        this.workerName = workerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHashRate() {
        return hashRate;
    }

    public void setHashRate(String hashRate) {
        this.hashRate = hashRate;
    }

    public String getIdealHashRate() {
        return idealHashRate;
    }

    public void setIdealHashRate(String idealHashRate) {
        this.idealHashRate = idealHashRate;
    }

    public AntMinerTemp getPcbTemperature() {
        return pcbTemperature;
    }

    public void setPcbTemperature(AntMinerTemp pcbTemperature) {
        this.pcbTemperature = pcbTemperature;
    }

    public AntMinerTemp getChipTemperature() {
        return chipTemperature;
    }

    public void setChipTemperature(AntMinerTemp chipTemperature) {
        this.chipTemperature = chipTemperature;
    }

    public String getMiningAccepted() {
        return miningAccepted;
    }

    public void setMiningAccepted(String miningAccepted) {
        this.miningAccepted = miningAccepted;
    }

    public String getMiningRejected() {
        return miningRejected;
    }

    public void setMiningRejected(String miningRejected) {
        this.miningRejected = miningRejected;
    }

    public String getHardwareErrors() {
        return hardwareErrors;
    }

    public void setHardwareErrors(String hardwareErrors) {
        this.hardwareErrors = hardwareErrors;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public static final class AntMinerTemp {
        private Integer temp1;
        private Integer temp2;
        private Integer temp3;

        public AntMinerTemp(Integer t1, Integer t2, Integer t3) {
            temp1 = t1;
            temp2 = t2;
            temp3 = t3;
        }

        public Integer getTemp1() {
            return temp1;
        }

        public void setTemp1(Integer temp1) {
            this.temp1 = temp1;
        }

        public Integer getTemp2() {
            return temp2;
        }

        public void setTemp2(Integer temp2) {
            this.temp2 = temp2;
        }

        public Integer getTemp3() {
            return temp3;
        }

        public void setTemp3(Integer temp3) {
            this.temp3 = temp3;
        }
    }
}
