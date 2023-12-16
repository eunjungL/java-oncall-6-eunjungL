package oncall.domain;

import java.util.Arrays;
import java.util.List;

public enum LegalHoliday {
    JANUARY(1, List.of(1)),
    FEBRUARY(2,List.of()),
    MARCH(3, List.of(1)),
    APRIL(4, List.of()),
    MAY(5, List.of(5)),
    JUNE(6, List.of(6)),
    JULY(7, List.of()),
    AUGUST(8, List.of(15)),
    SEPTEMBER(9, List.of()),
    OCTOBER(10, List.of(3, 9)),
    NOVEMBER(11, List.of()),
    DECEMBER(12, List.of(25));

    private final Integer month;
    private final List<Integer> holiday;

    LegalHoliday(Integer month, List<Integer> holiday) {
        this.month = month;
        this.holiday = holiday;
    }

    public static LegalHoliday getLegalHoliday(Integer month) {
        return Arrays.stream(LegalHoliday.values())
                .filter(legalHoliday -> legalHoliday.month.equals(month))
                .findAny()
                .orElseThrow();
    }

    public Boolean isHoliday(Integer date) {
        return holiday.contains(date);
    }
}
