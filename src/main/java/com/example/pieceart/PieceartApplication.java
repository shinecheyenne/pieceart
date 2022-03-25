package com.example.pieceart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //@WebFilter 사용에 필요한 어노테이션
public class PieceartApplication {

	public static void main(String[] args) {
		SpringApplication.run(PieceartApplication.class, args);
	}

}
