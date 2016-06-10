package com.peitersenprojects.firebase.model;

import java.util.Date;

/**
 * Created by denpei on 6/9/16.
 */
public class LiquidInput {

    private int amount;
    private String type;
    private Date timeStamp;
    private boolean lactating;

    public LiquidInput() {

    }

    public LiquidInput(int amount, String type, Date timeStamp, boolean lactating) {
        this.amount = amount;
        this.type = type;
        this.timeStamp = timeStamp;
        this.lactating = lactating;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isLactating() {
        return lactating;
    }

    public void setLactating(boolean lactating) {
        this.lactating = lactating;
    }
}
