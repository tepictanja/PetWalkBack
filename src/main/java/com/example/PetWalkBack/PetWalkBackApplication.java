package com.example.PetWalkBack;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.service.ApiInfo;

import java.util.ArrayList;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PetWalkBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetWalkBackApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		return mapper;
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("Asset Management", "Description of our application", "1.0.0", "", null, "", "", new ArrayList<>());
	}

}
