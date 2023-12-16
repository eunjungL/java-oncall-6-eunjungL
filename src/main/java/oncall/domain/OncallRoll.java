package oncall.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OncallRoll {
    private final DateInfo dateInfo;
    private final Map<Integer, String> roll;
    public Boolean weekdayTradeOff;
    public Boolean weekendTradeOff;
    public Integer weekdayOffset;
    public Integer weekendOffset;

    public OncallRoll(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
        this.roll = new HashMap<>();
    }

    public void setRoll(Integer date, String name) {
        roll.put(date, name);
    }

    @Override
    public String toString() {
        List<String> result = new ArrayList<>();

        Map<Integer, String> calendar = dateInfo.getCalendar();
        for (Map.Entry<Integer, String> r : roll.entrySet()) {
            result.add(String.format("%d월 %d일 %s %s", dateInfo.getMonth(), r.getKey(), calendar.get(r.getKey()), r.getValue()));
        }

        return String.join("\n", result);
    }
}
