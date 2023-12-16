package oncall;

import oncall.controller.OncallController;
import oncall.service.OncallService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        OncallService oncallService = new OncallService();
        OncallController oncallController = new OncallController(inputView, outputView, oncallService);

        oncallController.run();
    }
}
