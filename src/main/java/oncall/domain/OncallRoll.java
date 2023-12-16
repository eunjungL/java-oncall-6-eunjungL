package oncall.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OncallRoll {
    private final DateInfo dateInfo;
    private final Map<Integer, String> roll;

    public OncallRoll(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
        this.roll = new HashMap<>();
    }

    public void setRoll(Integer date, String name) {
        roll.put(date, name);
    }

    public void duplicateTradeOff(Workers workers) {
        for (int i = 2; i < dateInfo.getLastDate(); i++) {
            if (roll.get(i-1).equals(roll.get(i))) {
                tradeOff(i, workers);
            }
        }
    }

    private void tradeOff(Integer i, Workers workers) {
        if (dateInfo.isHoliday(i)) {
            int idx = workers.getIndexByName(DayType.WEEKEND, roll.get(i));

            roll.put(i, workers.getWeekendWorker(idx+1));

            for (int j = i+1; j < dateInfo.getLastDate(); j++) {
                if (dateInfo.isHoliday(j) && roll.get(j).equals(workers.getWeekendWorker(idx+1))) {
                    roll.put(j, workers.getWeekendWorker(idx));
                    break;
                }
            }

            return;
        }

        if (!dateInfo.isHoliday(i)){
            int idx = workers.getIndexByName(DayType.WEEKDAY, roll.get(i));

            roll.put(i, workers.getWeekdayWorker(idx+1));

            for (int j = i+1; j < dateInfo.getLastDate(); j++) {
                if (dateInfo.isHoliday(j) && roll.get(j).equals(workers.getWeekdayWorker(idx+1))) {
                    roll.put(j, workers.getWeekdayWorker(idx));
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        List<String> result = new ArrayList<>();

        Map<Integer, String> calendar = dateInfo.getCalendar();
        for (Map.Entry<Integer, String> r : roll.entrySet()) {
            String formattingResult = "%d월 %d일 %s %s";

            if (dateInfo.isHoliday(r.getKey()) && !DayType.WEEKEND.isType(calendar.get(r.getKey()))) {
                formattingResult = "%d월 %d일 (휴일) %s %s";
            }

            result.add(String.format(formattingResult, dateInfo.getMonth(), r.getKey(), calendar.get(r.getKey()), r.getValue()));
        }

        return String.join("\n", result);
    }
}
