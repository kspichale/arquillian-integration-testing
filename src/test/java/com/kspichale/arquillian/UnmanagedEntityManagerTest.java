package com.kspichale.arquillian;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.kspichale.arquillian.entity.Vehicle;

/**
 * Requires transaction-type="RESOURCE_LOCAL"
 */
public class UnmanagedEntityManagerTest {

	private VehicleFleet service;
	private EntityTransaction transaction;

	@Before
	public void initializeDependencies() {
		service = new VehicleFleet();
		service.em = Persistence.createEntityManagerFactory("integration-testing").createEntityManager();
		this.transaction = service.em.getTransaction();
	}

	@Test
	public void readOperationDoesNotRequireAnTransaction() {
		List<Vehicle> availableVehicles = this.service.getAvailableVehicles();
		assertNotNull(availableVehicles);
	}

	@Test
	public void createNewVehicleWithUserManagedTransaction() {
		Vehicle vehicle = new Vehicle();
		vehicle.setName("vehicle1");
		vehicle.setAvailable(true);
		this.service.add(vehicle);
		this.transaction.begin();
		List<Vehicle> availableVehicles = this.service.getAvailableVehicles();
		this.transaction.commit();
		assertThat(availableVehicles).containsOnly(vehicle);
	}
}
