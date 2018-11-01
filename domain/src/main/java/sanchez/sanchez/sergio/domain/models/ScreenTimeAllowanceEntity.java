package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Screen Time Allowance Entity
 */
public final class ScreenTimeAllowanceEntity implements Serializable {

    private int remainingTime;
    private List<TimeAllowancePerDayEntity> timeAllowancePerDay;

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public List<TimeAllowancePerDayEntity> getTimeAllowancePerDay() {
        return timeAllowancePerDay;
    }

    public void setTimeAllowancePerDay(List<TimeAllowancePerDayEntity> timeAllowancePerDay) {
        this.timeAllowancePerDay = timeAllowancePerDay;
    }

    public static class TimeAllowancePerDayEntity implements Serializable {

        private int dayNumber;
        private String dayName;
        private int time;

        public TimeAllowancePerDayEntity(){}

        public TimeAllowancePerDayEntity(int dayNumber, String dayName, int time) {
            this.dayNumber = dayNumber;
            this.dayName = dayName;
            this.time = time;
        }

        public int getDayNumber() {
            return dayNumber;
        }

        public void setDayNumber(int dayNumber) {
            this.dayNumber = dayNumber;
        }

        public String getDayName() {
            return dayName;
        }

        public void setDayName(String dayName) {
            this.dayName = dayName;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }

}
