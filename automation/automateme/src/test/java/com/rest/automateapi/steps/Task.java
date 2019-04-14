package com.rest.automateapi.steps;

import com.google.gson.annotations.SerializedName;

public class Task {
	 
    String id;
    String status;
    
    @SerializedName("task name")
    String taskName;
    String category;
    
    @SerializedName("due date")
    String dueDate;

}