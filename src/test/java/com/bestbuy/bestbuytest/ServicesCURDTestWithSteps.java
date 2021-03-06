package com.bestbuy.bestbuytest;

import com.bestbuy.stepsinfo.ServicesSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

//@RunWith(SerenityRunner.class)

public class ServicesCURDTestWithSteps extends TestBase {
    static String name = "Camera Recycling" + TestUtils.getRandomValue();
    static long servicesId;

    @Steps
    ServicesSteps serviceSteps;
    @Title("This test will create a new services and verify its generated")
    @Test

    public void test001() {
        servicesId = serviceSteps.createNewServices(name).log().all().statusCode(201).extract().response()
                .body().jsonPath().getLong("id");
        System.out.println("services ID is" + servicesId);
    }
    @Title("This test will get the services information by ID")
    @Test

    public void test002() {
        serviceSteps.getServicesById(servicesId).log().all().statusCode(200);

    }

    @Title("This test will update the services information and verify the updated information")
    @Test

    public void test003(){

        name = name+"_Changed";

        serviceSteps.updateServices(servicesId,name).statusCode(200);
        serviceSteps.getServicesById(servicesId).body("name",equalTo(name));

    }
    @Title("This test will delete the categories information and verify the categories is deleted ")
    @Test

    public void test004(){
        serviceSteps.deleteServicesById(servicesId).statusCode(200);
        serviceSteps.getServicesById(servicesId).statusCode(404);
    }
}
