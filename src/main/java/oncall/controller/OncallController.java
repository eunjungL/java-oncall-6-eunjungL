package oncall.controller;

import oncall.domain.DateInfo;
import oncall.service.OncallService;
import oncall.view.InputView;

public class OncallController {
    private final InputView inputView;
    private final OncallService oncallService;

    public OncallController(InputView inputView, OncallService oncallService) {
        this.inputView = inputView;
        this.oncallService = oncallService;
    }

    public void run() {
        DateInfo dateInfo = getDateInfo();
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


}
