import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.Test;

public class ExtentReportDemo {

    @Test
    public void extentTest(){

        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
        extent.attachReporter(spark);

        //First test
        ExtentTest test = extent.createTest("MyFirstTest");
        test.log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");

        ExtentTest test1 = extent.createTest("MySecondTest");
        test1.log(Status.PASS, "This is a logging event for MySecondTest, and it passed!");


        //After every test case --> check whether it is pass or fail and need to log in the report

        //After suite
        extent.flush();



    }
}
