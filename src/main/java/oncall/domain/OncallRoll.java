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

    public void setOncallRoll(Workers workers) {
        int dayOffset = 0;
        int endOffset = 0;

        for (int i = 1; i <= dateInfo.getLastDate(); i++) {
            if (!dateInfo.isHoliday(i)) {
                setRoll(i, workers.getWorkerByIndex(DayType.WEEKDAY, dayOffset));
                dayOffset += 1;
            }

            if (dateInfo.isHoliday(i)) {
                setRoll(i, workers.getWorkerByIndex(DayType.WEEKEND, endOffset));
                endOffset += 1;
            }
        }
    }

    private void setRoll(Integer date, String name) {
        roll.put(date, name);
    }

    public void duplicateTradeOff(Workers workers) {
        for (int i = 2; i < dateInfo.getLastDate(); i++) {
            if (roll.get(i-1).equals(roll.get(i)) && dateInfo.isHoliday(i)) {
                tradeOff(DayType.WEEKEND, i, workers);
            }

            if (roll.get(i-1).equals(roll.get(i)) && !dateInfo.isHoliday(i)) {
                tradeOff(DayType.WEEKDAY, i, workers);
            }
        }
    }

    private void tradeOff(DayType type, Integer i, Workers workers) {
        int idx = workers.getIndexByName(type, roll.get(i));

        roll.put(i, workers.getWeekendWorker(idx+1));

        for (int j = i+1; j < dateInfo.getLastDate(); j++) {
            if (dateInfo.isHoliday(j) && roll.get(j).equals(workers.getWeekendWorker(idx+1))) {
                roll.put(j, workers.getWeekendWorker(idx));
                break;
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
                formattingResult = "%d월 %d일 %s(휴일) %s";
            }

            result.add(String.format(formattingResult, dateInfo.getMonth(), r.getKey(), calendar.get(r.getKey()), r.getValue()));
        }

        return String.join("\n", result);
    }
}
