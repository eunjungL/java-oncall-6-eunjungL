package oncall;

import oncall.controller.OncallController;
import oncall.service.OncallService;
import oncall.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OncallService oncallService = new OncallService();
        OncallController oncallController = new OncallController(inputView, oncallService);

        oncallController.run();
    }
}
