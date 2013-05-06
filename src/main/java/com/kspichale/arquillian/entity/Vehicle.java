package com.kspichale.arquillian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "vehicles")
@NamedQuery(name = Vehicle.FIND_ALL_AVAILABLE, query = "select v from Vehicle v")
public class Vehicle {

	public static final String FIND_ALL_AVAILABLE = "com.kspichale.arquillian.entity.Vehicle.findAllAvailable";

	@Id
	@GeneratedValue
	@Column(name = "veh_id")
	private long id;

	@Column(name = "veh_available")
	private boolean available = true;

	@Column(name = "veh_name")
	private String name;

	public Vehicle() {
		// Arg-less constructor for JPA contract
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
