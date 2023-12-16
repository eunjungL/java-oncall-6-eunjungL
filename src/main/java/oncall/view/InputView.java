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
        System.out.println(Constants.GET_ONCALL_DATE_COMMENT);
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
}
