package com.kgp.salamat.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseSpinnerTps{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	@SerializedName("spinner")
	private List<SpinnerItem> spinner;

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	public List<SpinnerItem> getSpinner(){
		return spinner;
	}
}