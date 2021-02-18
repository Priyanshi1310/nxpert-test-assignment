package com.nxpert.consultant;

public class Consultant {
	int id;
	String customer_name;
	String consultant_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getConsultant_name() {
		return consultant_name;
	}
	public void setConsultant_name(String consultant_name) {
		this.consultant_name = consultant_name;
	}
	public Consultant() {
	}
	public Consultant(int id, String customer_name, String consultant_name) {
		this.id = id;
		this.customer_name = customer_name;
		this.consultant_name = consultant_name;
	}
	@Override
	public String toString() {
		return "Consultant [id=" + id + ", customer_name=" + customer_name + ", consultant_name=" + consultant_name
				+ "]";
	}
	
	
	
	

}
