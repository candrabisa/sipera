package com.kgp.salamat.admin.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseRelawanAdmin{

	@SerializedName("relawan")
	private List<RelawanItem> relawan;

	@SerializedName("error")
	private boolean error;

	@SerializedName("status")
	private int status;

	public void setRelawan(List<RelawanItem> relawan){
		this.relawan = relawan;
	}

	public List<RelawanItem> getRelawan(){
		return relawan;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}