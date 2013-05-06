package com.kspichale.arquillian;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.kspichale.arquillian.entity.Vehicle;

/**
 * Requires transaction-type="JTA"
 */
@RunWith(Arquillian.class)
public class VehicleFleetIT {

	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive archive = ShrinkWrap.create(JavaArchive.class).addClass(VehicleFleet.class).addClass(Vehicle.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(new ByteArrayAsset("<beans/>".getBytes()), ArchivePaths.create("beans.xml"));
		System.out.println(archive);
		return archive;
	}

	@Inject
	VehicleFleet vehicleService;

	@Test
	public void createNewVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setName("vehicle1");
		vehicle.setAvailable(true);
		vehicleService.add(vehicle);

		List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();
		assertThat(availableVehicles).containsOnly(vehicle);
	}
}
