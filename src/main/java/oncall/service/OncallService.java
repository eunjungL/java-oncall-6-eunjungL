package oncall.service;

import oncall.CalendarGenerator;
import oncall.ErrorMessage;
import oncall.domain.DateInfo;
import oncall.domain.OncallRoll;
import oncall.domain.Workers;

import java.util.Arrays;
import java.util.List;

public class OncallService {
    public DateInfo getDateInfo(String input) {
        Integer month = splitMonth(input);
        String date = splitDate(input);

        return new DateInfo(month, date);
    }

    private Integer splitMonth(String input) {
        List<String> monthAndDate = Arrays.stream(input.split(",")).toList();
        validMonthIsInteger(monthAndDate.get(0));

        return Integer.parseInt(monthAndDate.get(0));
    }

    private String splitDate(String input) {
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

        oncallRoll.setOncallRoll(workers);
        oncallRoll.duplicateTradeOff(workers);

        return oncallRoll;
    }
}
