package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper.CardInfo;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    public final SelenideElement errorMessage = $("[data-test-id='error-notification'] .notification__content");
    private final SelenideElement headerTransferPage = $(byText("Пополнение карты"));
    private final SelenideElement amountField = $("[data-test-id='amount'] input");
    private final SelenideElement fromField = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");

    public TransferPage() {
        headerTransferPage.shouldBe(visible);
    }

    public DashboardPage validTransfer(String amount, CardInfo cardNumber) {
        amountField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        amountField.setValue(amount);
        fromField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        fromField.setValue(cardNumber.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public String invalidAmountTransfer(String amount, CardInfo cardNumber) {
        amountField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        amountField.setValue(amount);
        fromField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        fromField.setValue(cardNumber.getNumber());
        transferButton.click();
        return errorMessage.shouldBe(visible).text();
    }

    public String emptyCardNumberTransfer(String amount) {
        amountField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        amountField.setValue(amount);
        fromField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        transferButton.click();
        return errorMessage.shouldBe(visible).text();
    }

    public String invalidCardNumberTransfer(String amount, CardInfo cardNumber) {
        amountField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        amountField.setValue(amount);
        fromField.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        fromField.setValue(cardNumber.getNumber());
        transferButton.click();
        return errorMessage.shouldBe(visible).text();
    }
}