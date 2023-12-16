package oncall.service;

import oncall.ErrorMessage;
import oncall.domain.DateInfo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OncallService {
    public DateInfo getDateInfo(String input) {
        Integer month = splitMonth(input);
        String date = splitDate(input);

        return new DateInfo(month, date);
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
}