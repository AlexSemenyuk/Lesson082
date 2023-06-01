package org.itstep.data;

import java.util.Arrays;

public class CurrentPostsNav {
    public static final String on = "on";
    public static final String off = "off";
    public static final int amountButtonsOfNav = 3;
    private String newer;
    private ButtonCurrentPostsNav [] buttons;
    private String older;

    public CurrentPostsNav(String newer, ButtonCurrentPostsNav[] buttons, String older) {
        this.newer = newer;
        this.buttons = buttons;
        this.older = older;
    }

    public String getNewer() {
        return newer;
    }

    public void setNewer(String newer) {
        this.newer = newer;
    }

    public ButtonCurrentPostsNav[] getButtons() {
        return buttons;
    }

    public void setButtons(ButtonCurrentPostsNav[] buttons) {
        this.buttons = buttons;
    }

    public String getOlder() {
        return older;
    }

    public void setOlder(String older) {
        this.older = older;
    }

    @Override
    public String toString() {
        return "CurrentPostsNav{" +
                "newer='" + newer + '\'' +
                ", buttons=" + Arrays.toString(buttons) +
                ", older='" + older + '\'' +
                '}';
    }
}
