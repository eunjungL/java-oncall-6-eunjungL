package oncall.domain;

import oncall.Constants;
import oncall.ErrorMessage;

public class DateInfo {
    private final Integer month;
    private final String startDay;

    public DateInfo(Integer month, String startDay) {
        validateMonthRange(month);
        validateStartDay(startDay);

        this.month = month;
        this.startDay = startDay;
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
}
