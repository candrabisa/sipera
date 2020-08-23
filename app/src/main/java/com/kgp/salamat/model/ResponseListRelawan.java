package com.kgp.salamat.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseListRelawan{

	@SerializedName("relawan")
	private List<RelawanItem> relawan;

	@SerializedName("error")
	private boolean error;

	@SerializedName("status")
	private int status;

	public List<RelawanItem> getRelawan(){
		return relawan;
	}

	public boolean isError(){
		return error;
	}

	public int getStatus(){
		return status;
	}
}