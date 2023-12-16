package oncall.domain;

import oncall.CalendarGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OncallRollTest {

    @DisplayName("중복 상관 없이 근무표 배정 테스트")
    @Test
    void setOncallRoll() {
        DateInfo dateInfo = new DateInfo(5, "월", 31, CalendarGenerator.generateCalendar(5, "월"));
        Workers workers = new Workers(
                List.of("준팍","도밥","고니","수아","루루","글로","솔로스타","우코","슬링키","참새","도리"),
                List.of("수아","루루","글로","솔로스타","우코","슬링키","참새","도리","준팍","도밥","고니")
        );

        OncallRoll oncallRoll = new OncallRoll(dateInfo);
        oncallRoll.setOncallRoll(workers);

        assertThat(oncallRoll.toString()).contains(
                "5월 1일 월 준팍\n" +
                        "5월 2일 화 도밥\n" +
                        "5월 3일 수 고니\n" +
                        "5월 4일 목 수아\n" +
                        "5월 5일 금(휴일) 수아\n" +
                        "5월 6일 토 루루\n" +
                        "5월 7일 일 글로\n" +
                        "5월 8일 월 루루\n" +
                        "5월 9일 화 글로\n" +
                        "5월 10일 수 솔로스타\n" +
                        "5월 11일 목 우코\n" +
                        "5월 12일 금 슬링키\n" +
                        "5월 13일 토 솔로스타\n" +
                        "5월 14일 일 우코\n" +
                        "5월 15일 월 참새\n" +
                        "5월 16일 화 도리\n" +
                        "5월 17일 수 준팍\n" +
                        "5월 18일 목 도밥\n" +
                        "5월 19일 금 고니\n" +
                        "5월 20일 토 슬링키\n" +
                        "5월 21일 일 참새\n" +
                        "5월 22일 월 수아\n" +
                        "5월 23일 화 루루\n" +
                        "5월 24일 수 글로\n" +
                        "5월 25일 목 솔로스타\n" +
                        "5월 26일 금 우코\n" +
                        "5월 27일 토 도리\n" +
                        "5월 28일 일 준팍\n" +
                        "5월 29일 월 슬링키\n" +
                        "5월 30일 화 참새\n" +
                        "5월 31일 수 도리"
        );
    }

    @DisplayName("근무표 중복 처리 테스트")
    @Test
    void duplicateTradeOff() {
        DateInfo dateInfo = new DateInfo(5, "월", 31, CalendarGenerator.generateCalendar(5, "월"));
        Workers workers = new Workers(
                List.of("준팍","도밥","고니","수아","루루","글로","솔로스타","우코","슬링키","참새","도리"),
                List.of("수아","루루","글로","솔로스타","우코","슬링키","참새","도리","준팍","도밥","고니")
        );

        OncallRoll oncallRoll = new OncallRoll(dateInfo);
        oncallRoll.setOncallRoll(workers);
        oncallRoll.duplicateTradeOff(workers);

        assertThat(oncallRoll.toString()).contains(
                "5월 1일 월 준팍\n" +
                        "5월 2일 화 도밥\n" +
                        "5월 3일 수 고니\n" +
                        "5월 4일 목 수아\n" +
                        "5월 5일 금(휴일) 루루\n" +
                        "5월 6일 토 수아\n" +
                        "5월 7일 일 글로\n" +
                        "5월 8일 월 루루\n" +
                        "5월 9일 화 글로\n" +
                        "5월 10일 수 솔로스타\n" +
                        "5월 11일 목 우코\n" +
                        "5월 12일 금 슬링키\n" +
                        "5월 13일 토 솔로스타\n" +
                        "5월 14일 일 우코\n" +
                        "5월 15일 월 참새\n" +
                        "5월 16일 화 도리\n" +
                        "5월 17일 수 준팍\n" +
                        "5월 18일 목 도밥\n" +
                        "5월 19일 금 고니\n" +
                        "5월 20일 토 슬링키\n" +
                        "5월 21일 일 참새\n" +
                        "5월 22일 월 수아\n" +
                        "5월 23일 화 루루\n" +
                        "5월 24일 수 글로\n" +
                        "5월 25일 목 솔로스타\n" +
                        "5월 26일 금 우코\n" +
                        "5월 27일 토 도리\n" +
                        "5월 28일 일 준팍\n" +
                        "5월 29일 월 슬링키\n" +
                        "5월 30일 화 참새\n" +
                        "5월 31일 수 도리"
        );
    }
}