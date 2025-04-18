package fr.milleis.test.backend;

import fr.milleis.test.backend.exception.BusinessException;
import fr.milleis.test.backend.model.Categorie;
import fr.milleis.test.backend.model.ModePaiement;
import fr.milleis.test.backend.model.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}