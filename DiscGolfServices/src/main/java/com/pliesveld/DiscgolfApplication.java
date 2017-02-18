package com.pliesveld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pliesveld.discgolf.datalayer.PlayerRepository;

@SpringBootApplication
public class DiscgolfApplication implements CommandLineRunner {

	@Autowired
	PlayerRepository playerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DiscgolfApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
