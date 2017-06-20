package br.com.gs.kartrank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.gs.kartrank.service.CorridaService;

@SpringBootApplication
public class Aplicacao {

    public static void main(String[] args) {
	SpringApplication.run(Aplicacao.class, args);
    }

    @Bean
    public CommandLineRunner demo(CorridaService corridaService) {
	return (args) -> {
	   
	};
    }

}
