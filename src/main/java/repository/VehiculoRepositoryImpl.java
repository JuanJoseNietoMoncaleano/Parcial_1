package repository;

package repository;

import co.edu.uniquindio.EmpresaRentCar;
import co.edu.uniquindio.Vehiculo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VehiculoRepositoryImpl implements VehiculoRepository {
    private final EmpresaRentCar empresa;

    public VehiculoRepositoryImpl() {
        this.empresa = EmpresaRentCar.getInstance();
    }

    @Override
    public void guardar(Vehiculo vehiculo) {
        if (vehiculo != null && buscarPorPlaca(vehiculo.getPlaca()).isEmpty()) {
            empresa.getVehiculos().add(vehiculo);
        }
    }

    @Override
    public Optional<Vehiculo> buscarPorPlaca(String placa) {
        return empresa.getVehiculos().stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst();
    }

    @Override
    public List<Vehiculo> listarTodos() {
        return empresa.getVehiculos();
    }

    @Override
    public List<Vehiculo> listarDisponibles() {
        return empresa.getVehiculos().stream()
                .filter(Vehiculo::isDisponible)
                .collect(Collectors.toList());
    }
}
