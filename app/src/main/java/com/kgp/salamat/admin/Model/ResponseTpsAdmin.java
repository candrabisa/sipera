package com.kgp.salamat.admin.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseTpsAdmin{

	@SerializedName("tps")
	private List<TpsItem> tps;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<TpsItem> getTps(){
		return tps;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}