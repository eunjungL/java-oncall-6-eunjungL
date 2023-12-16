package oncall.service;

import oncall.CalendarGenerator;
import oncall.ErrorMessage;
import oncall.domain.DateInfo;
import oncall.domain.OncallRoll;
import oncall.domain.Workers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OncallService {
    public DateInfo getDateInfo(String input) {
        Integer month = splitMonth(input);
        String date = splitDate(input);

        return new DateInfo(month, date, CalendarGenerator.getLastDate(month), CalendarGenerator.generateCalendar(month, date));
    }

    private Integer splitMonth(String input) {
        Map<Integer, String> result = new HashMap<>();

        List<String> monthAndDate = Arrays.stream(input.split(",")).toList();
        validMonthIsInteger(monthAndDate.get(0));

        return Integer.parseInt(monthAndDate.get(0));
    }

    private String splitDate(String input) {
        Map<Integer, String> result = new HashMap<>();

        List<String> monthAndDate = Arrays.stream(input.split(",")).toList();

        return monthAndDate.get(1);
    }

    private void validMonthIsInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR);
        }
    }

    public Workers getWorkers(List<String> weekdayWorkers, List<String> weekendWorkers) {
        return new Workers(weekdayWorkers, weekendWorkers);
    }

    public OncallRoll makeOncallRoll(DateInfo dateInfo, Workers workers) {
        OncallRoll oncallRoll = new OncallRoll(dateInfo);

        int dayOffset = 0;
        int endOffset = 0;
        for (int i = 1; i <= dateInfo.getLastDate(); i++) {
            if (!dateInfo.isHoliday(i)) {
                oncallRoll.setRoll(i, workers.getWeekdayWorker(dayOffset));
                dayOffset += 1;
            }
            if (dateInfo.isHoliday(i)) {
                oncallRoll.setRoll(i, workers.getWeekendWorker(endOffset));
                endOffset += 1;
            }
        }

        oncallRoll.duplicateTradeOff(workers);

        return oncallRoll;
    }
}
