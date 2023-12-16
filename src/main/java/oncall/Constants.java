package oncall;

import java.util.List;

public class Constants {
    /** 비상 근무 명단에 사용되는 문구 */
    public final static String GET_ONCALL_DATE_COMMENT = "비상 근무를 배정할 월과 시작 요일을 입력하세요>";

    public final static Integer START_MONTH = 1;
    public final static Integer END_MONTH = 12;
    public final static List<String> DATE_LIST = List.of("월", "화", "수", "목", "금", "토", "일");
}
