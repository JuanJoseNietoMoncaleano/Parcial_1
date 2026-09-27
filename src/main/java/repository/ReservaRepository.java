package repository;

import co.edu.uniquindio.Reserva;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository {
    void guardar(Reserva reserva);
    Optional<Reserva> buscarPorCodigo(String codigo);
    List<Reserva> listarTodas();
}