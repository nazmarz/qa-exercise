package com.rest.automateapi.steps;

import static io.restassured.RestAssured.given;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.response.Response;

import com.rest.automateapi.steps.Task;
import com.rest.automateapi.steps.ApiIteratorClass;

public class RestAssuredTests extends ServerDetails {
	
	public static final Logger logger = LoggerFactory.getLogger(RestAssuredTests.class);
	
	ApiIteratorClass apiIteratorClass = new ApiIteratorClass();
	String fakeApiUrl = "/fake-api-call.php";

	/**
	 * GET Call to "fake-api-call.php" to check service status
	 * If Successful then Status Code should be 200.
	 * "application/json\r\n" 	
	 */
	@Test
	public void givenFakeApi_a_getStatusCode_ServiceStatusTest() {
		logger.info("[TEST CASE]: Test service status of the API endpoint (only one in this case)---");
		logger.info("[TEST STARTS]");
		
		given().
			when().log().all().
				get(fakeApiUrl).
			then().log().all().
				contentType("text/html; charset=UTF-8");
		
		int responseStatusCode = given().
				log().all().
				get(fakeApiUrl).getStatusCode();
		
		try{
			Assert.assertEquals(responseStatusCode, 200);
			logger.info("[TEST PASSED] YAY! ... Status Code: " + responseStatusCode);
		}catch(AssertionError e){
			logger.info("[TEST FAILED] :( ... Status Code: " + responseStatusCode);
			throw e;
		}

	     
	}
	
	/**
	 * The original fake data (also found in backup_todo.list) has 5
	 * tasks without category. This test checks if this count matches with the fake api.
	 */
	@Test
	public void givenFakeApi_b_getTasks_NoCategoryTest() {
		logger.info("[TEST CASE]: Find how many tasks do not have \"category\" assigned---");
		logger.info("[TEST STARTS]");
		
		Response response = given().
				when().get(fakeApiUrl).
				then().contentType("text/html; charset=UTF-8").extract().response();
		// GSON
		List<Task> taskResponses = response.jsonPath().getList("$", Task.class);
		// Count no. of tasks with empty category
		int count=0;
		for (Task task: taskResponses) {
			logger.info("[TEST PASSED] Category codes present: {}: " + task.category);
			if (task.category.equals("")) {
				count++;
			}
		}
		
		try{
			Assert.assertEquals(count, 5);
			logger.info("[TEST PASSED] YAY! ... Tasks without category assigned: {}: " + count);
		}catch(AssertionError e){
			logger.info("[TEST FAILED] :( ... Tasks without category assigned: {}: " + count);
			throw e;
		}
	}	
	
	/**
	 * The original fake data (also found in backup_todo.list) has 5
	 * tasks without category. This test checks if this count matches with the fake api.
	 */
	@Test
	public void givenFakeApi_c_getTasks_aggregateTaskNamesTest() {
		logger.info("[TEST CASE]: Aggregate and print only \"task names\"---");
		logger.info("[TEST STARTS]");
		
		Response response = given().
				when().get(fakeApiUrl).
				then().contentType("text/html; charset=UTF-8").extract().response();
		// GSON
		List<Task> taskResponses = response.jsonPath().getList("$", Task.class);

		for (Task task: taskResponses) {
			logger.info("Tasks : {}", task.taskName);
		}

	}
	
	/**
	 * The original fake data (also found in backup_todo.list) has 5
	 * tasks without category. This test checks if this count matches with the fake api.
	 */
	@Test
	public void givenFakeApi_getTasks_descDueDatesTest() {
		logger.info("[TEST CASE]: Read API response and print tasks in descending \"due date\" order");
		logger.info("[TEST STARTS]");
		
		Response response = given().
				when().get(fakeApiUrl).
				then().contentType("text/html; charset=UTF-8").extract().response();
		
		// GSON
		List<Task> taskResponses = response.jsonPath().getList("$", Task.class);
		Map<Date, String> descDueDateTasks = new TreeMap<Date, String>(Collections.reverseOrder());
		
		for (Task task: taskResponses) {
			if (!task.dueDate.trim().equals("")) {
				descDueDateTasks.put(apiIteratorClass.unixEpochToStandardTime(task.dueDate), task.taskName);
			}			
		}
		for (Map.Entry<Date, String> m:descDueDateTasks.entrySet()) {
			logger.info("Task date: " + m.getKey() + "Task name: " + m.getValue());
		}
	}
	
	/**
	 * The original fake data (also found in backup_todo.list) has 5
	 * tasks without category. This test checks if this count matches with the fake api.
	 */
	@Test
	public void givenFakeApi_getTasks_validateTasksTest() {
		logger.info("[TEST CASE]: Aggregate and print only \"task names\"---");
		logger.info("[TEST STARTS]");
		
		Response response = given().
				when().get(fakeApiUrl).
				then().contentType("text/html; charset=UTF-8").extract().response();
		// GSON
		List<Task> taskResponses = response.jsonPath().getList("$", Task.class);
		int taskCount = 0;
		for (@SuppressWarnings("unused") Task task: taskResponses) {			
			taskCount++;			
		}
		logger.info("No. of tasks: " + taskCount);
		System.out.println("No. of tasks: " + taskCount);

	}
	
	
}
