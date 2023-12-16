package oncall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarGenerator {
    public static Map<Integer, String> generateCalendar(Integer month, String startDay) {
        int lastDate = getLastDate(month);
        int dayStartOffset = getDayStartOffset(startDay);

        Map<Integer, String> calendar = new HashMap<>();
        for (int i = 0; i < lastDate; i++) {
            calendar.put(i+1, Constants.DATE_LIST.get((dayStartOffset + i) % 7));
        }

        return calendar;
    }

    public static Integer getLastDate(Integer month) {
        if (List.of(4, 6, 9, 11).contains(month)) {
            return 30;
        }

        if (month.equals(2)) {
            return 28;
        }

        return 31;
    }

    private static Integer getDayStartOffset(String startDay) {
        return Constants.DATE_LIST.indexOf(startDay);
    }
}
