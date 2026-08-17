package com.hoaitester.listeners;

import com.hoaitester.Utils.LogUtils;
import com.hoaitester.helpers.CaptureHelper;
import com.hoaitester.reports.ExtentReportManager;
import com.hoaitester.reports.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static int test_total;
    private static int test_passed_total;
    private static int test_Failed_total;
    private static int test_skipped_total;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }


    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("Setup môi trường onStart: " + result.getStartDate());

    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("Kết thúc bộ test: " + result.getEndDate());
        LogUtils.info("Test total: " + test_total);
        LogUtils.info("Test pass total: " + test_passed_total);
        LogUtils.info("Test Fail total: " + test_Failed_total);
        LogUtils.info("Test Skip total: " + test_skipped_total);
        CaptureHelper.stopRecord();
        //Gửi mail(đính kèm file log và file report)
        //Xuấ report
        ExtentReportManager.getExtentReports().flush();

    }

    @Override
    public void onTestStart(ITestResult result) {
//        System.out.println("Bắt đầu chạy test case: " + result.getName());
        test_total++;
        CaptureHelper.startRecord("Videosuite_1");
        LogUtils.info("Bắt đầu chạy test case" + result.getName());
        //Bắt đầu ghi 1 TCs mới vào Extent Report(nếu ko có name và description thì nó sẽ lấy tên hàm)
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test case " + result.getName() + " is passed.");
        test_passed_total++;
        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " is failed.");
        LogUtils.error(result.getThrowable());
        test_Failed_total++;
//        CaptureHelper.captureScreenshot(result.getName());
        //Extent Report
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");
        ExtentTestManager.addScreenshot(result.getName());



    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("Test case " + result.getName() + " is skipped.");
        LogUtils.error(result.getThrowable());
        test_skipped_total++;
        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());


    }
}
