package oncall;

import java.util.List;

public class Constants {
    /** 비상 근무 명단에 사용되는 문구 */
    public final static String GET_ONCALL_DATE_COMMENT = "비상 근무를 배정할 월과 시작 요일을 입력하세요>";
    public final static String GET_WEEKDAY_WORKER_COMMENT = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요>";
    public final static String GET_WEEKEND_WORKER_COMMENT = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>";

    public final static Integer START_MONTH = 1;
    public final static Integer END_MONTH = 12;
    public final static List<String> DATE_LIST = List.of("월", "화", "수", "목", "금", "토", "일");
    public final static Integer MIN_WORKER_COUNT = 5;
    public final static Integer MAX_WORKER_COUNT = 35;
    public final static Integer MAX_NAME_LENGTH = 5;
}
