package oncall.controller;

import oncall.domain.DateInfo;
import oncall.domain.OncallRoll;
import oncall.domain.Workers;
import oncall.service.OncallService;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.List;

public class OncallController {
    private final InputView inputView;
    private final OutputView outputView;
    private final OncallService oncallService;

    public OncallController(InputView inputView, OutputView outputView, OncallService oncallService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.oncallService = oncallService;
    }

    public void run() {
        DateInfo dateInfo = getDateInfo();
        Workers workers = getWorkers();

        getOncallRoll(dateInfo, workers);
    }

    private DateInfo getDateInfo() {
        while (true) {
            try {
                String dateInfo = inputView.getOncallDate();

                return oncallService.getDateInfo(dateInfo);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private Workers getWorkers() {
        while (true) {
            try {
                List<String> weekdayWorkers = inputView.getWeekdayWorkers();
                List<String> weekendWorkers = inputView.getWeekEndWorkers();

                return oncallService.getWorkers(weekdayWorkers, weekendWorkers);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void getOncallRoll(DateInfo dateInfo, Workers workers) {
        OncallRoll oncallRoll = oncallService.makeOncallRoll(dateInfo, workers);

        outputView.printOncallRoll(oncallRoll.toString());
    }
}
