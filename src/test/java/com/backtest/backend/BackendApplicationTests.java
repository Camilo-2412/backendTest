package com.backtest.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backtest.backend.model.Client;
import com.backtest.backend.service.ClientService;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private ClientService clientService;

	@BeforeEach
	void setUp() {
		// Inicializamos el servicio antes de cada prueba
		clientService = new ClientService();
	}

	@Test
	void testBuscarClienteExitoso() {
		Client cliente = clientService.buscarCliente("C", "23445322");
		assertNotNull(cliente, "El cliente no debe ser null");
		assertEquals("Juan", cliente.getPrimerNombre(), "El primer nombre debe ser Juan");
		assertEquals("Carlos", cliente.getSegundoNombre(), "El segundo nombre debe ser Carlos");
	}

	@Test
	void testBuscarClienteNoExistente() {
		Client cliente = clientService.buscarCliente("C", "99999999");
		assertNull(cliente, "El cliente debe ser null ya que no existe");
	}

	@Test
	void testBuscarClienteConTipoDocumentoInvalido() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			clientService.buscarCliente("X", "23445322");
		});
		assertEquals("Tipo o número de documento inválido", exception.getMessage(),
				"El mensaje de error debe ser el esperado");
	}

	@Test
	void testBuscarClienteConNumeroDocumentoInvalido() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			clientService.buscarCliente("C", "");
		});
		assertEquals("Tipo o número de documento inválido", exception.getMessage(),
				"El mensaje de error debe ser el esperado");
	}
}
