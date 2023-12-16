package oncall.domain;

import oncall.CalendarGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateInfoTest {
    @DisplayName("잘못된 월 입력 시 예외 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 40, 13})
    void monthErrorTest(Integer month) {
        assertThatThrownBy(() ->
                new DateInfo(month, "월", 31, CalendarGenerator.generateCalendar(1, "월"))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("잘못된 요일 입력 시 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"dnjf", "월월", "회"})
    void dayErrorTest(String day) {
        assertThatThrownBy(() ->
                new DateInfo(1, day, 31, CalendarGenerator.generateCalendar(1, "월"))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}