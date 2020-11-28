package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import shapescontroller.Circle;
import shapescontroller.Shape;
import shapescontroller.ShapesFactory;

import org.springframework.boot.SpringApplication;
 //import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PainteBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PainteBackEndApplication.class, args);
		
		Circle c = new Circle("circle",0,0,1,2,"red","10");
		System.out.println(c.id);
		
		ShapesFactory factory = new ShapesFactory();
		Circle c1=(Circle) factory.getShape("circle",0,0,1,2,"blue","10");
		System.out.println(c1.id);
		
	}

}

