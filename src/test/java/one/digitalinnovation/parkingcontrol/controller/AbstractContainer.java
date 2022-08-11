package one.digitalinnovation.parkingcontrol.controller;

import org.testcontainers.containers.MariaDBContainer;

public abstract class AbstractContainer {

	static final MariaDBContainer<?> MARIADB_CONTAINER;
	
	static {
		MARIADB_CONTAINER = new MariaDBContainer<>("mariadb:latest");
		MARIADB_CONTAINER.start();
		System.setProperty("spring.datasource.url", MARIADB_CONTAINER.getJdbcUrl());
		System.setProperty("spring.datasource.username", MARIADB_CONTAINER.getUsername());
		System.setProperty("spring.datasource.password", MARIADB_CONTAINER.getPassword());
	}
}
