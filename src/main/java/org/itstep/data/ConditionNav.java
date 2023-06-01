package org.itstep.data;

public enum ConditionNav {
    ON (1, "On"),
    OFF (2, "Off");
    public final int num;
    public final String mean;

    ConditionNav (int num, String mean) {	// Конструктор
        this.num = num;
        this.mean = mean;
    }

    public int num() {
        return num;
    }

    @Override
    public String toString() {
        return String.format("%d: %s", num, mean);
    }
}

