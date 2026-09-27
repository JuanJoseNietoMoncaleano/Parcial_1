package repository;

package repository;

import co.edu.uniquindio.Cliente;
import co.edu.uniquindio.EmpresaRentCar;
import java.util.List;
import java.util.Optional;

public class ClienteRepositoryImpl implements ClienteRepository {
    private final EmpresaRentCar empresa;

    public ClienteRepositoryImpl() {
        this.empresa = EmpresaRentCar.getInstance();
    }

    @Override
    public void guardar(Cliente cliente) {
        if (cliente != null && buscarPorDocumento(cliente.getDocumentoIdentidad()).isEmpty()) {
            empresa.getClientes().add(cliente);
        }
    }

    @Override
    public Optional<Cliente> buscarPorDocumento(String documento) {
        return empresa.getClientes().stream()
                .filter(c -> c.getDocumentoIdentidad().equalsIgnoreCase(documento))
                .findFirst();
    }

    @Override
    public Optional<Cliente> buscarPorTelefono(String telefono) {
        return empresa.getClientes().stream()
                .filter(c -> c.getTelefono().equalsIgnoreCase(telefono))
                .findFirst();
    }

    @Override
    public List<Cliente> listarTodos() {
        return empresa.getClientes();
    }
}