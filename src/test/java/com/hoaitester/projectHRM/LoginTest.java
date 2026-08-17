package com.hoaitester.projectHRM;

import com.hoaitester.common.Basetest;
import com.hoaitester.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends Basetest {
    LoginPage loginPage;
    @Test
    public void TestLoginSuccess(){
        loginPage = new LoginPage();
        loginPage.loginHRM("admin@yourstore.com","admin" );
        loginPage.verifyLoginSuccess();


    }
}
