package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.Constants;
import oncall.ErrorMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputView {
    public String getOncallDate() {
        System.out.print(Constants.GET_ONCALL_DATE_COMMENT);
        String input = Console.readLine();

        validateDateSplit(input);

        return input;
    }

    private void validateDateSplit(String input) {
        List<String> monthAndDate = Arrays.stream(input.split(",")).toList();

        if (monthAndDate.size() != 2) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR);
        }
    }

    public List<String> getWeekdayWorkers() {
        System.out.print(Constants.GET_WEEKDAY_WORKER_COMMENT);
        String input = Console.readLine();

        validateWorkerSplit(input);
        return List.of(input.split(","));
    }

    public List<String> getWeekEndWorkers() {
        System.out.print(Constants.GET_WEEKEND_WORKER_COMMENT);
        String input = Console.readLine();

        validateWorkerSplit(input);
        return List.of(input.split(","));
    }

    private void validateWorkerSplit(String input) {
        if (!input.contains(",")) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR);
        }
    }
}
