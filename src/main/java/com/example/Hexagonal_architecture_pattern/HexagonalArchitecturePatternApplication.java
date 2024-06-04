package com.example.Hexagonal_architecture_pattern;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.Hexagonal_architecture_pattern")
public class HexagonalArchitecturePatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexagonalArchitecturePatternApplication.class, args);
	}
}
