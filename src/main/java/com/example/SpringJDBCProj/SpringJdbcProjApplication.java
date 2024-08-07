package com.example.SpringJDBCProj;

import com.example.SpringJDBCProj.model.Alien;
import com.example.SpringJDBCProj.repository.AlienRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringJdbcProjApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context=SpringApplication.run(SpringJdbcProjApplication.class, args);
		Alien alien = context.getBean(Alien.class);
		alien.setId(23);
		alien.setName("Mahabir");
		alien.setTech("Java");
//		alien.toString();

		AlienRepository alienRepository = context.getBean(AlienRepository.class); //creating the object of AlienRepository
		alienRepository.save(alien);

//		get all the Alien from the table
		System.out.println(alienRepository.findAll());
	}

}
