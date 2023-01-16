package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.AdminController;
import controller.FindStudController;
import database.Database;

@Configuration
public class ControllerConfig {
	@Autowired
	private Database database;

	@Bean
	public FindStudController findStudController() {
		FindStudController controller = new FindStudController();
		controller.setDatabase(database);
		return controller;
	}
	
	@Bean
	public AdminController adminController() {
		return new AdminController();
	}
}
