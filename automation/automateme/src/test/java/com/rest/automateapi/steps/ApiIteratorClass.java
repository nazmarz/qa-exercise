package com.rest.automateapi.steps;

import java.util.Date;

public class ApiIteratorClass {

	public Date unixEpochToStandardTime (String dueDateFromApi) {
		Date date = new Date(Long.parseLong(dueDateFromApi.trim())*1000L);
		return date;
	}
	
}
