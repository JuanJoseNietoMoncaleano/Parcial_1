package repository;

package repository;

import co.edu.uniquindio.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    void guardar(Cliente cliente);
    Optional<Cliente> buscarPorDocumento(String documento);
    Optional<Cliente> buscarPorTelefono(String telefono);
    List<Cliente> listarTodos();
}
