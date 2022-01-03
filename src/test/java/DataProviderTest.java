import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataProviderTest {

    //Object[][] is not always the return type
    // number of tests will be one if we use for loop

    @Test(dataProvider = "getData1")
    public void dpTest(Map<String,String> data){
            System.out.println(data.get("username"));
        System.out.println("password = " + data.get("password"));
    }

    @DataProvider
    public Object[][] getData1(){
       Object[][] data = new Object[3][1];
       Map<String,String> map1 = new HashMap<>();
        map1.put("username","sdfsf");
        map1.put("password","sdfsffghf");
        map1.put("email","sdfsffdhdf");

        Map<String,String> map2 = new HashMap<>();
        map2.put("username","sdfsfghf");
        map2.put("password","sdfhgdfsffghf");
        map2.put("email","sdfsffdfgdhdf");

        Map<String,String> map3 = new HashMap<>();
        map3.put("username","sdfdgsf");
        map3.put("password","sdfsffghf");
        map3.put("email","sdfsffdfgdhdf");

        data[0][0] = map1;
        data[1][0]=  map2;
        data[2][0] = map3;
       return data;
    };

    @DataProvider
    public Object[][] getData(){
        //first dim basically number of times you want to execute
        //second dim indicates number of parameters to the method
        return new Object[][]{  //3*2
                {"abcd","dfghsd","email0","address","responsecode"},
                {"efgh","dsfgdfgsg","email6","address","responsecode"},
                {"ijkl","dfgdfgdg","emaildg","address","responsecode"}
        };

        // map --> usernmae-abcd, password-sfdjsdf
        // map2
        //map3
        //list --> map1,map2,map3
    }
}
