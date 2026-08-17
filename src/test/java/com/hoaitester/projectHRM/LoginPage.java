package com.hoaitester.projectHRM;

import com.hoaitester.drivers.DriverManager;
import com.hoaitester.helpers.PropertiesHelper;
import com.hoaitester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {

    //Khai báo các element dạng đối tượng By (phương thức tìm kiếm)
    private By headerPage = By.xpath("//h1[normalize-space()='Admin area demo']");
    private By inputEmail = By.xpath("//input[@id='Email']");
    private By inputPassword = By.xpath("//input[@id='Password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Log in']");
    private By errorMessage = By.xpath("//div[@class='message-error validation-summary-errors']");
    private By errorMessageEmailPasswordNull = By.xpath("//span[@id='Email-error']");

    public void verifyNavigateToTheLoginPage() {
        WebUI.waitForPageLoaded();
        //Title, URL, Header
        SoftAssert softAssert = new SoftAssert();
        Assert.assertEquals(WebUI.getCurrentURL(), PropertiesHelper.getValue("url"), "Fail. The Login page url not match.");
        softAssert.assertEquals(WebUI.getElementText(headerPage), PropertiesHelper.getValue("LOGIN_PAGE_HEADER_TEXT"), "Fail. The Login page header not match.");
        softAssert.assertAll();
    }
    public void verifyLoginSuccess() {
        new DashboardPage().verifyNavigateToDashboardPage();
        Assert.assertTrue(WebUI.getCurrentURL().contains("/admin/"), "FAIL. Không chuyển hướng sang trang Dashboard");
        Assert.assertFalse(WebUI.getCurrentURL().contains("/admin/"), "FAIL. Vẫn đang ở trang Login");
    }

    public void verifyLoginFail(String message) {
        WebUI.waitForPageLoaded();
        WebUI.assertEquals(WebUI.getElementText(errorMessage), message, "Invalid Login");
        WebUI.assertContains(WebUI.getCurrentURL(), "/login", "FAIL. không còn ở trang Login");

    }

    public void verifyLoginFailWithEmailPasswordNull() {
        Assert.assertTrue(WebUI.checkElementExist(errorMessageEmailPasswordNull), "The username field is required.");
//        Assert.assertTrue(WebUI.checkElementExist(alertPasswordRequiredMessage), "Fail. The Password Error Message is not present");
//        Assert.assertEquals(WebUI.getCurrentURL(), "https://admin-demo.nopcommerce.com/login", "The Current LOGIN_URL is not correct");
    }


    //Các hàm xử lý cho chính trang này
    public DashboardPage loginHRM(String email, String password) {
        //https://crm.anhtester.com/admin/authentication
        WebUI.openURL(PropertiesHelper.getValue("url")); //Gọi từ class ConfigData dạng biến static
        verifyNavigateToTheLoginPage();
        WebUI.cleartext(inputEmail);
        WebUI.cleartext(inputPassword);
        WebUI.setText(inputEmail, email);
        WebUI.setText(inputPassword, password);
        WebUI.clicktext(buttonLogin);
        return new DashboardPage();
    }

//    public DashboardPage loginCRM_AdminRole() {
//        WebUI.openURL(ConfigData.LOGIN_URL);
//        verifyNavigateToTheLoginPage();
//        setEmail(ConfigData.EMAIL_ADMIN);
//        setPassword(ConfigData.PASSWORD_ADMIN);
//        clickLoginButton();
//        verifyLoginSuccess();
//
//        return new DashboardPage();
//    }
}

