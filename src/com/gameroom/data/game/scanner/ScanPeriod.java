package com.gameroom.data.game.scanner;

import com.gameroom.ui.Main;

import java.util.concurrent.TimeUnit;

/**
 * Created by LM on 07/01/2017.
 */
public enum ScanPeriod {
    TEN_MINUTES(0, 10), HALF_HOUR(0, 30), HOUR(1, 0), FIVE_HOURS(5, 0), TEN_HOURS(10, 0), START_ONLY(-1, -1), NEVER(-2,-2);
    private final static int ONLY_START_CONSTANT = -1;
    private final static int NEVER_CONSTANT = -2;

    private int hours;
    private int minutes;

    ScanPeriod(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public static ScanPeriod fromString(String s){
        for(ScanPeriod period : ScanPeriod.values()){
            if(s.equals(period.toString())){
                return period;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        if (minutes > 0) {
            if (minutes == 1) {
                s = minutes + " " + Main.getString("minute") + " " + s;
            } else {
                s = minutes + " " + Main.getString("minutes") + " " + s;
            }
        }
        if (hours > 0) {
            if (hours == 1) {
                s = hours + " " + Main.getString("hour") + " " + s;
            } else {
                s = hours + " " + Main.getString("hours") + " " + s;
            }
        }
        if (minutes == ONLY_START_CONSTANT || hours == ONLY_START_CONSTANT) {
            s = Main.getString("only_at_start");
        }else if(minutes == NEVER_CONSTANT ||hours == NEVER_CONSTANT){
            s = Main.getString("never");
        }
        return s.trim();
    }

    public long toMillis() {
        if (minutes == ONLY_START_CONSTANT || hours == ONLY_START_CONSTANT) {
            return ONLY_START_CONSTANT;
        }else if(minutes == NEVER_CONSTANT ||hours == NEVER_CONSTANT){
            return NEVER_CONSTANT;
        }

        return TimeUnit.MINUTES.toMillis(minutes) + TimeUnit.HOURS.toMillis(hours);
    }
}
