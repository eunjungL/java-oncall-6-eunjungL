package oncall.domain;

import oncall.Constants;
import oncall.ErrorMessage;

import java.util.List;
import java.util.Map;

public class DateInfo {
    private final Integer month;
    private final String startDay;
    private final Integer lastDate;
    private final Map<Integer, String> calendar;

    public DateInfo(Integer month, String startDay, Integer lastDate, Map<Integer, String> calendar) {
        validateMonthRange(month);
        validateStartDay(startDay);

        this.month = month;
        this.startDay = startDay;
        this.lastDate = lastDate;
        this.calendar = calendar;
    }

    private void validateMonthRange(Integer month) {
        if (month < Constants.START_MONTH || month > Constants.END_MONTH) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR);
        }
    }

    private void validateStartDay(String startDay) {
        if (!Constants.DATE_LIST.contains(startDay)) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR);
        }
    }

    public Boolean isHoliday(Integer date) {
        if (List.of("토", "일").contains(calendar.get(date))) {
            return true;
        }

        return LegalHoliday.getLegalHoliday(month)
                .isHoliday(date);
    }

    public Integer getLastDate() {
        return lastDate;
    }

    public Integer getMonth() {
        return month;
    }

    public Map<Integer, String> getCalendar() {
        return calendar;
    }
}
