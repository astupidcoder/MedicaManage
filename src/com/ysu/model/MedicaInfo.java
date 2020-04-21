package com.ysu.model;

import java.util.Date;

public class MedicaInfo {

	private int medicaId;
	private String name;
	private int classId;
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	private double price;
	private float volume;
	private Date pdate;
	private String producer;
	
	
	public MedicaInfo(int medicaId, String name, int classId, double price, float volume, Date pdate, String producer) {
		super();
		this.medicaId = medicaId;
		this.name = name;
		this.classId = classId;
		this.price = price;
		this.volume = volume;
		this.pdate = pdate;
		this.producer = producer;
	}
	public int getMedicaId() {
		return medicaId;
	}
	public void setMedicaId(int medicaId) {
		this.medicaId = medicaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public MedicaInfo() {
		// TODO Auto-generated constructor stub
	}

}
