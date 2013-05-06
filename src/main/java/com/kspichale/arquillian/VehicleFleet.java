package com.kspichale.arquillian;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kspichale.arquillian.entity.Vehicle;

@Stateless
public class VehicleFleet {

	@PersistenceContext
	EntityManager em;

	public void add(Vehicle vehicle) {
		em.persist(vehicle);
	}

	public List<Vehicle> getAvailableVehicles() {
		final List<Vehicle> resultList = this.em.createNamedQuery(Vehicle.FIND_ALL_AVAILABLE).getResultList();
		return resultList;
	}

	public void setAvailability(long vehicleId, boolean available) {
		final Vehicle vehicle = em.find(Vehicle.class, vehicleId);
		vehicle.setAvailable(available);
	}

	public boolean isAvailable(long vehicleId) {
		final Vehicle vehicle = em.find(Vehicle.class, vehicleId);
		return vehicle.isAvailable();
	}
}
