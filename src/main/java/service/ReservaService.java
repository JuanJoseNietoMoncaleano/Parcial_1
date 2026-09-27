package service;

import co.edu.uniquindio.Reserva;
import repository.ReservaRepository;
import repository.ReservaRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService() {
        this.reservaRepository = new ReservaRepositoryImpl();
    }

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void registrarReserva(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }
        reservaRepository.guardar(reserva);
    }

    public List<Reserva> listarTodas() {
        return reservaRepository.listarTodas();
    }

    /**
     * Recorre las reservas registradas, identifica aquellas realizadas
     * dentro del periodo consultado (inclusivo) y acumula el valor total generado.
     */
    public double calcularIngresosPorPeriodo(LocalDate fechaInicioPeriodo, LocalDate fechaFinPeriodo) {
        if (fechaInicioPeriodo == null || fechaFinPeriodo == null) {
            throw new IllegalArgumentException("El rango de fechas es obligatorio.");
        }
        if (fechaFinPeriodo.isBefore(fechaInicioPeriodo)) {
            throw new IllegalArgumentException("La fecha final no puede ser previa a la inicial.");
        }

        return reservaRepository.listarTodas().stream()
                .filter(reserva -> !reserva.getFechaInicio().isBefore(fechaInicioPeriodo)
                        && !reserva.getFechaInicio().isAfter(fechaFinPeriodo))
                .mapToDouble(Reserva::getValorTotal)
                .sum();
    }

    /**
     * Retorna las reservas que coinciden dentro del periodo consultado.
     */
    public List<Reserva> filtrarReservasPorPeriodo(LocalDate desde, LocalDate hasta) {
        return reservaRepository.listarTodas().stream()
                .filter(r -> !r.getFechaInicio().isBefore(desde) && !r.getFechaInicio().isAfter(hasta))
                .collect(Collectors.toList());
    }
}
