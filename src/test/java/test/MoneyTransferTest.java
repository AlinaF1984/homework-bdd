package test;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import page.DashboardPage;
import page.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static page.DashboardPage.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

 public  class MoneyTransferTest {
        int begBalance1;
        int begBalance2;
        int endBalance1;
        int endBalance2;
        int sum;
       DashboardPage dashboardPage;
    @BeforeEach
    void SetUp() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
        begBalance1 = dashboardPage.getBalance(dashboardPage.getcard1());
        begBalance2 = dashboardPage.getBalance(dashboardPage.getcard2());
    }

    @Test
    @DisplayName("Перевод денег сo второй карты на первую")
    void shouldTransferMoneyFromSecondToFirstCard() {
        sum = 100;
        var topUpPage = dashboardPage.clickTopUp(dashboardPage.getcard1());
        var cardNum = DataHelper.getFirstCardInfo().getNumber();
        var dashboardPage2 = topUpPage.successfulTopUp(Integer.toString(sum), cardNum);
        endBalance1 = dashboardPage2.getBalance(dashboardPage.getcard1());
        endBalance2 = dashboardPage2.getBalance(dashboardPage.getcard2());
        assertEquals(begBalance1 + sum, endBalance1);
        assertEquals(begBalance2 - sum, endBalance2);
    }

    @Test
    @DisplayName("Перевод денег с первой карты на вторую")
    void shouldTransferMoneyFromFirstToSecondCard() {
        sum = 100;
        var topUpPage = dashboardPage.clickTopUp(dashboardPage.getcard2());
        var cardNum = DataHelper.getFirstCardInfo().getNumber();
        var dashboardPage2 = topUpPage.successfulTopUp(Integer.toString(sum), cardNum);
        endBalance1 = dashboardPage2.getBalance(dashboardPage.getcard1());
        endBalance2 = dashboardPage2.getBalance(dashboardPage.getcard2());
        assertEquals(begBalance1 - sum, endBalance1);
        assertEquals(begBalance2 + sum, endBalance2);
    }

   @Test
   @DisplayName("Не должен переводить больше, чем есть на карте")
   void shouldNotTransferMoreThanAvailable() {
       sum = begBalance1 + 100;
       var topUpPage = dashboardPage.clickTopUp(dashboardPage.getcard2());
       var cardNum = DataHelper.getFirstCardInfo().getNumber();
        topUpPage.unsuccessfulTopUp(Integer.toString(sum), cardNum);
    }
    
}