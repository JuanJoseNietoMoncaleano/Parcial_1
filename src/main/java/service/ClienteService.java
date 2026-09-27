package service;

import co.edu.uniquindio.Cliente;
import repository.ClienteRepository;
import repository.ClienteRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService() {
        this.clienteRepository = new ClienteRepositoryImpl();
    }

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (cliente.getDocumentoIdentidad() == null || cliente.getDocumentoIdentidad().trim().isEmpty()) {
            throw new IllegalArgumentException("El documento de identidad es obligatorio.");
        }
        clienteRepository.guardar(cliente);
    }

    public Optional<Cliente> buscarPorTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return Optional.empty();
        }
        return clienteRepository.buscarPorTelefono(telefono.trim());
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.listarTodos();
    }

    /**
     * Determina si el número de teléfono corresponde a un número perfecto.
     * Limpia caracteres no numéricos antes de evaluar.
     */
    public boolean esTelefonoPerfecto(String telefono) {
        if (telefono == null) {
            return false;
        }

        // Extraer únicamente los dígitos numéricos
        String digitos = telefono.replaceAll("\\D", "");
        if (digitos.isEmpty()) {
            return false;
        }

        try {
            long numero = Long.parseLong(digitos);
            return esNumeroPerfecto(numero);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Algoritmo de verificación de número perfecto O(sqrt(n))
     */
    public boolean esNumeroPerfecto(long n) {
        if (n <= 1) {
            return false;
        }

        long suma = 1; // 1 siempre es divisor propio de cualquier n > 1
        long limite = (long) Math.sqrt(n);

        for (long i = 2; i <= limite; i++) {
            if (n % i == 0) {
                suma += i;
                long par = n / i;
                if (par != i) {
                    suma += par;
                }
            }
        }

        return suma == n;
    }
}