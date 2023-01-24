package test;

import static io.restassured.RestAssured.get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAssuredTest {

    @Autowired
    Environment environment;

    @Test
    public void validRequest() {
        String port = environment.getProperty("local.server.port");
        RestAssured.port = Integer.parseInt(port);
        Response response = get("/suggestions?q=Londo&latitude=43.70011&longitude=-79.4163");
        ResponseBody body = response.body();
        String s = body.asString();
        assertEquals("""
                        [\
                        {"name":"London, ON, Canada","latitude":42.98339,"longitude":-81.23304,"score":0.9},\
                        {"name":"London, KY, USA","latitude":37.12898,"longitude":-84.08326,"score":0.5},\
                        {"name":"London, OH, USA","latitude":39.88645,"longitude":-83.44825,"score":0.5},\
                        {"name":"Londontowne, MD, USA","latitude":38.93345,"longitude":-76.54941,"score":0.3},\
                        {"name":"Londonderry, NH, USA","latitude":42.86509,"longitude":-71.37395,"score":0.3}\
                        ]"""
                ,
                body.asString());
    }

}
