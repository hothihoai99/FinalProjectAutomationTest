package com.hoaitester.projectHRM;

import com.hoaitester.drivers.DriverManager;
import com.hoaitester.helpers.PropertiesHelper;
import com.hoaitester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;


public class DashboardPage {

    private String dashboardPageUrl = "/admin/";
    private By menuDashboard = By.xpath("//p[normalize-space()='Dashboard']");
    private By totalInvoicesAwaitingPayment = By.xpath("");
    private By totalConvertedLeads = By.xpath("");
    private By totalProjectsInProgress = By.xpath("");
    private By totalTasksNotFinished = By.xpath("");

    public void verifyNavigateToDashboardPage() {
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(menuDashboard);
        WebUI.assertEquals(WebUI.getCurrentURL(), "https://admin-demo.nopcommerce.com/admin/", "The dashboard page URL not match.");
    }
}
