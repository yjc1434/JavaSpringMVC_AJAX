package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import database.Database;

@Configuration
public class DataConfig {
	
	@Bean
	public Database Database() {
		Database db = new Database();
		db.connectDB("insert (차영준,20172560,소프트웨어대학,컴퓨터공학부)");
		return db;
	}

}
