package oncall.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WorkersTest {
    @DisplayName("이름 중복 예외 테스트")
    @Test
    void validateDuplicateNameTest() {
        List<String> weekdayWorkers = List.of("수아","수아","글로","고니","도밥","준팍");
        List<String> weekendWorkers = List.of("수아","준택","글로","고니","도밥","준팍");
        assertThatThrownBy(() -> new Workers(weekdayWorkers, weekendWorkers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름 글자수 초과 테스트")
    @Test
    void validateNameLengthTest() {
        List<String> weekdayWorkers = List.of("수아","준택준택준택","글로","고니","도밥","준팍");
        List<String> weekendWorkers = List.of("수아","준택","글로","고니","도밥","준팍");
        assertThatThrownBy(() -> new Workers(weekdayWorkers, weekendWorkers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사람 수 미만 테스트")
    @Test
    void validateWorkerNumberTest() {
        List<String> weekdayWorkers = List.of("수아","준택","글로");
        List<String> weekendWorkers = List.of("수아","준택","글로");
        assertThatThrownBy(() -> new Workers(weekdayWorkers, weekendWorkers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}