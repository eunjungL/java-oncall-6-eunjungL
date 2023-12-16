package oncall.domain;

import oncall.Constants;
import oncall.ErrorMessage;

import java.util.List;
import java.util.Set;

public class Workers {
    private final List<String> weekdayWorkers;
    private final List<String> weekendWorkers;

    public Workers(List<String> weekdayWorkers, List<String> weekendWorkers) {
        validateWorkerNameDuplicate(weekdayWorkers);
        validateWorkerNameDuplicate(weekendWorkers);
        validateWorkerNumber(weekdayWorkers);
        validateWorkerNumber(weekendWorkers);
        validateWorkerNickNameLength(weekdayWorkers);
        validateWorkerNickNameLength(weekendWorkers);

        this.weekdayWorkers = weekdayWorkers;
        this.weekendWorkers = weekendWorkers;
    }

    private void validateWorkerNameDuplicate(List<String> workers) {
        if (Set.copyOf(workers).size() != workers.size()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR);
        }
    }

    private void validateWorkerNumber(List<String> workers) {
        if (workers.size() < Constants.MIN_WORKER_COUNT || workers.size() > Constants.MAX_WORKER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR);
        }
    }

    private void validateWorkerNickNameLength(List<String> workers) {
        for (String worker : workers) {
            if (worker.length() > Constants.MAX_NAME_LENGTH) {
                throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR);
            }
        }
    }

    public String getWorkerByIndex(DayType type, Integer index) {
        if (type.equals(DayType.WEEKDAY)) {
            return weekdayWorkers.get(index % weekdayWorkers.size());
        }

        return weekendWorkers.get(index % weekendWorkers.size());
    }

    public String getWeekendWorker(Integer index) {
        return weekendWorkers.get(index % weekendWorkers.size());
    }

    public Integer getIndexByName(DayType type, String name) {
        if (type.equals(DayType.WEEKEND)) {
            return weekendWorkers.indexOf(name);
        }

        return weekdayWorkers.indexOf(name);
    }
}
