package com.backtest.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backtest.backend.model.Client;

@Service
public class ClientService {

    private final List<Client> clientes;

    public ClientService() {
        clientes = new ArrayList<>();
        clientes.add(new Client("C", "23445322", "Juan", "Carlos", "Perez", "Lopez", "123456789", "Av. Siempre Viva",
                "Bogotá"));
        clientes.add(
                new Client("P", "67890", "Ana", "Lucia", "Garcia", "Moreno", "987654321", "Calle Falsa", "Medellín"));
        clientes.add(
                new Client("C", "11223", "Luis", "Antonio", "Gomez", "Sánchez", "555123456", "Calle Real", "Cali"));
    }

    // Método para buscar cliente por tipo de documento y número de documento
    public Client buscarCliente(String tipoDocumento, String numeroDocumento) {
        // Validar tipo de documento
        if (!tipoDocumento.matches("(?i)[CP]") || numeroDocumento == null
                || numeroDocumento.isEmpty()) {
            throw new IllegalArgumentException("Tipo o número de documento inválido");
        }
        return clientes.stream()
                .filter(c -> c.getTipoDocumento().equalsIgnoreCase(tipoDocumento)
                        && c.getNumeroDocumento().equals(numeroDocumento))
                .findFirst()
                .orElse(null); // Retorna null si no se encuentra el cliente
    }
}
