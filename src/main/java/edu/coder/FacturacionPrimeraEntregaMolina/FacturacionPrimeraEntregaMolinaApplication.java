package edu.coder.FacturacionPrimeraEntregaMolina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FacturacionPrimeraEntregaMolinaApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(FacturacionPrimeraEntregaMolinaApplication.class);

	@Autowired
	private DaoFactory daoFactory;

	public static void main(String[] args) {
		SpringApplication.run(FacturacionPrimeraEntregaMolinaApplication.class, args);
		logger.info("Aplicación levantada");
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			// Se crea un Cliente
			Cliente cliente = new Cliente("Mario", "Gomez", 45678569);
			// Se crean 2 Domicilios
			Domicilio domicilioParticular = new Domicilio("Marquez", 542, "Palermo", "Buenos Aires", "1001");
			Domicilio domicilioLaboral = new Domicilio("Callao", 6472, "Recoleta", "Buenos Aires", "1002");
			//Se asigna el cliente a cada domicilio
			domicilioParticular.setCliente(cliente);
			domicilioLaboral.setCliente(cliente);
			//Se asignan los domicilios al cliente
			List<Domicilio> domicilios = new ArrayList<Domicilio>();
			domicilios.add(domicilioParticular);
			domicilios.add(domicilioLaboral);
			cliente.setDomicilio(domicilios);
			//Se crea el cliente
			daoFactory.create(cliente);
			//Busco el cliente guardado
			Cliente clienteGuardado = daoFactory.getCliente(cliente);
			imprimirCliente(clienteGuardado, "Cliente Guardado: ");
			//Modifico el cliente guardado
			modificarCliente(clienteGuardado);
			//Busco el cliente modificado
			Cliente clienteModificado = daoFactory.getCliente(clienteGuardado);
			imprimirCliente(clienteModificado, "Cliente Modificado: ");

		} catch (Exception e) {
			// Manejo de excepciones
			logger.error("Ocurrió un error durante la ejecución: ", e);
		}

	}


	private void modificarCliente(Cliente clienteGuardado) {
		//Modifico un dato del cliente
		clienteGuardado.setNombre("Marcelo");
		clienteGuardado.setApellido("Diaz");
		daoFactory.update(clienteGuardado);
	}

	private void imprimirCliente(Cliente clienteGuardado, String mensaje) {
		System.out.println("----------------------------");
		System.out.println(mensaje + clienteGuardado.toString());
	}

}

