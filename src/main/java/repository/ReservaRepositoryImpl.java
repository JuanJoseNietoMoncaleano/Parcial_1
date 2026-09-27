package repository;

package repository;

import co.edu.uniquindio.EmpresaRentCar;
import co.edu.uniquindio.Reserva;
import java.util.List;
import java.util.Optional;

public class ReservaRepositoryImpl implements ReservaRepository {
    private final EmpresaRentCar empresa;

    public ReservaRepositoryImpl() {
        this.empresa = EmpresaRentCar.getInstance();
    }

    @Override
    public void guardar(Reserva reserva) {
        if (reserva != null && buscarPorCodigo(reserva.getCodigoReserva()).isEmpty()) {
            empresa.getReservas().add(reserva);
            // Alquilado el vehículo, se marca como no disponible temporalmente
            reserva.getVehiculo().setDisponible(false);
        }
    }

    @Override
    public Optional<Reserva> buscarPorCodigo(String codigo) {
        return empresa.getReservas().stream()
                .filter(r -> r.getCodigoReserva().equalsIgnoreCase(codigo))
                .findFirst();
    }

    @Override
    public List<Reserva> listarTodas() {
        return empresa.getReservas();
    }
}