package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    public static SelenideElement card1 = $("div[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    public static SelenideElement card2 = $("div[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private static SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection topUpButtons = $$("button[data-test-id=action-deposit]");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public static SelenideElement getHeading() {
        return heading;
    }

    public static SelenideElement getcard1() {
        return card1;
    }

    public static SelenideElement getcard2() {
        return card2;
    }

    public TransferPage clickTopUp(SelenideElement card) {

        card.find("button[data-test-id=action-deposit]").click();
        return new TransferPage();
    }

    public int getBalance(SelenideElement card) {
        String[] text = card.innerText().split(" ");
        return Integer.parseInt(text[5]);
    }

}