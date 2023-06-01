package org.itstep.data;

public class ButtonCurrentPostsNav {
    public static final String on = "on";
    public static final String off = "off";
    private int mean;
    private String active;

    public ButtonCurrentPostsNav(int mean, String active) {
        this.mean = mean;
        this.active = active;
    }

    public int getMean() {
        return mean;
    }

    public void setMean(int mean) {
        this.mean = mean;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ButtonCurrentPostsNav{" +
                "mean=" + mean +
                ", active='" + active + '\'' +
                '}';
    }
}
