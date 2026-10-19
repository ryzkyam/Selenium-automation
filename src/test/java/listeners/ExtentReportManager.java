package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        if (extent == null) {
            String fileName = System.getProperty("user.dir") + "/target/ExtentReport.html";
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

            // Konfigurasi Tampilan
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setDocumentTitle("Automation Test Execution Report");
            htmlReporter.config().setReportName("DanaRutin Automation Result");

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("QA Engineer", "Reza Rizki Ramadhan");
            extent.setSystemInfo("Environment", "Staging");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }
}