package service;

import co.edu.uniquindio.Vehiculo;
import repository.VehiculoRepository;
import repository.VehiculoRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;

    public VehiculoService() {
        this.vehiculoRepository = new VehiculoRepositoryImpl();
    }

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null || vehiculo.getPlaca() == null || vehiculo.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException("La placa del vehículo es obligatoria.");
        }
        vehiculoRepository.guardar(vehiculo);
    }

    public List<Vehiculo> listarVehiculosDisponibles() {
        return vehiculoRepository.listarDisponibles();
    }

    public List<Vehiculo> listarTodos() {
        return vehiculoRepository.listarTodos();
    }

    public Optional<Vehiculo> buscarPorPlaca(String placa) {
        return vehiculoRepository.buscarPorPlaca(placa);
    }
}