//package com.example.demo.User;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//
//import org.junit.jupiter.api.MethodOrderer;
////import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.json.*;
//import static io.restassured.RestAssured.*;
//import io.restassured.http.ContentType;
//
//
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//public class UserTestApi {
//
//
//    @Test
//    @Order(2)
//    public void testSignUp() {
//    String jsonBody= "{\"name\":\"test\",   \"active\": true, \"address\":\"Sample\", \"email\":\"xyz@gmail.com\", \"password\":\"123sample\"}";
//    String response = given().header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
//            .body(jsonBody)
//            .when()
//            .post("http://localhost:9001/users/")
//            .then()
//            .assertThat().statusCode(equalTo(201)).extract().response().asString();
//
//    }
//    
//    @Test
//    @Order(4)
//    public void testGetAll() throws JSONException {
//    	 String jsonBody="{ \"username\": \"himanshu.nainwal@stl.tech\", \"password\":\"123\"}";
//       String tokenn =  given()
//               .header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
//               .body(jsonBody)
//               .when()
//               .post("http://localhost:9001/auth/login")
//               .then().assertThat().statusCode(equalTo(200)).extract().response().asString();
//       
//       System.out.println("Hi there Token"+ tokenn);
//       
//       JSONObject json;
//       json = new JSONObject(tokenn);
//       
//               
//        System.out.println(json.getString("token"));
//    
//       
//       String result = given()
//             .header("Authorization", "Bearer " + json.getString("token")).contentType(ContentType.JSON).accept(ContentType.JSON)
//             .when()
//             .get("http://localhost:9001/users/")
//             .then()
//             .assertThat().statusCode(equalTo(200))
//             .extract().response().asString() ;
//    	System.out.println("Hi this is result "+ result);
//
//        
//    }
//
//    
//    
////    @Test
////    @Order(2)
////  public void testLogin() {
////        String jsonBody="{ \"username\":\"sheetal@gmail.com\", \"password\":\"qwert\"}";
////        String token=given()
////                .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
////                .body(jsonBody)
////                .when()
////                .post("http://localhost:9001")
////                .then()
////                .assertThat().statusCode(200)
////                .extract().response().asString();
////                
////                
////    }
////
////    
////    @Test
////    @Order(3)
////    public void testGetPatients() {
////        
////       
////        String result = given()
////                 .when()
////                    .get("http://localhost:8989/api/patientt/fetch")
////                    .then()
////                    .assertThat().statusCode(200)
////                    .extract().response().asString() ;
////    }
////    
////    
////    @Test
////    @Order(4)
////    public void testGetDoctorList() {
////         String res = given()
////                    .when()
////                    .get("http://localhost:8989/api/patientt/")
////                    .then()
////                    .assertThat().statusCode(200)
////                    .extract().response().asString() ;
////        
////    }
////    
////    @Test
////    @Order(5)
////    public void testSinglePatient() {
////        String res= given()
////                .when()
////                .get("http://localhost:8989/api/patientt/1")
////                .then()
////                .assertThat().statusCode(200)
////                .extract().response().asString() ;
////    }
////    
////    @Test
////    @Order(6)
////    public void testDeletePatient() throws JSONException  {
////           String jsonBody="{ \"username\":\"sheetal@gmail.com\", \"password\":\"qwert\"}";
////           String tokenn =  given()
////                   .header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
////                   .body(jsonBody)
////                   .when()
////                   .post("http://localhost:8989/api/v1/auth/login")
////                   .then()
////                   .assertThat().statusCode(200)
////                   .extract().response().asString(); 
////           
////           System.out.println(tokenn);
////           
////           JSONObject json;
////           json = new JSONObject(tokenn);
////            
////            System.out.println(json.getString("token"));
////          
////           
////           String result = given()
////                   .header("Authorization", "Bearer " + json.getString("token")).contentType(ContentType.JSON).accept(ContentType.JSON)
////                   .when()
////                   .delete("http://localhost:8989/api/patientt/3")
////                   .then()
////                   .assertThat().statusCode(200)
////                   .extract().response().asString() ;
////    }
////    
//    
//    
//    
//    
//    
//    
//
//}