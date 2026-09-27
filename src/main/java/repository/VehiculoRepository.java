package repository;

package repository;

import co.edu.uniquindio.Vehiculo;
import java.util.List;
import java.util.Optional;

public interface VehiculoRepository {
    void guardar(Vehiculo vehiculo);
    Optional<Vehiculo> buscarPorPlaca(String placa);
    List<Vehiculo> listarTodos();
    List<Vehiculo> listarDisponibles();
}