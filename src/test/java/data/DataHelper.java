package data;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;


import  lombok.Value;
import org.jetbrains.annotations.NotNull;
import page.TransferPage;

import static page.DashboardPage.*;
public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }


    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002");
    }

    public static CardInfo getInvalidCardInfo() {
        Faker faker = new Faker();
        return new CardInfo(faker.business().creditCardNumber());
    }

    @Value
    public static class CardInfo {
        String number;
    }


}