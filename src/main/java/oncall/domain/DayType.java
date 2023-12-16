package oncall.domain;

import java.util.List;

public enum DayType {
    WEEKDAY(List.of("월", "화", "수", "목", "금")),
    WEEKEND(List.of("토", "일"));

    private final List<String> dayOfDate;

    DayType(List<String> dayOfDate) {
        this.dayOfDate = dayOfDate;
    }

    public Boolean isType(String day) {
        return dayOfDate.contains(day);
    }
}
