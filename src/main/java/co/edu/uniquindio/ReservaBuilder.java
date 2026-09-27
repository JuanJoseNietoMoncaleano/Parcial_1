package co.edu.uniquindio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReservaBuilder {
    private String codigoReserva;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private ModalidadAlquiler modalidad;
    private List<ServicioAdicional> serviciosContratados = new ArrayList<>();
    private double porcentajeDescuento = 0.0;

    public ReservaBuilder codigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
        return this;
    }

    public ReservaBuilder fechas(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        return this;
    }

    public ReservaBuilder cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public ReservaBuilder vehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        return this;
    }

    public ReservaBuilder modalidad(ModalidadAlquiler modalidad) {
        this.modalidad = modalidad;
        return this;
    }

    public ReservaBuilder agregarServicio(ServicioAdicional servicio) {
        if (servicio != null) {
            this.serviciosContratados.add(servicio);
        }
        return this;
    }

    public ReservaBuilder servicios(List<ServicioAdicional> servicios) {
        if (servicios != null) {
            this.serviciosContratados = new ArrayList<>(servicios);
        }
        return this;
    }

    public ReservaBuilder porcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
        return this;
    }

    public Reserva build() {
        // Validaciones de integridad antes de construir el objeto
        if (codigoReserva == null || codigoReserva.trim().isEmpty()) {
            throw new IllegalStateException("El código de la reserva es obligatorio.");
        }
        if (cliente == null) {
            throw new IllegalStateException("Debe asignar un cliente a la reserva.");
        }
        if (vehiculo == null) {
            throw new IllegalStateException("Debe asignar un vehículo a la reserva.");
        }
        if (modalidad == null) {
            throw new IllegalStateException("Debe seleccionar una modalidad de alquiler.");
        }
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalStateException("Las fechas de inicio y fin son obligatorias.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        int diasTotales = dias <= 0 ? 1 : (int) dias;

        if (diasTotales < modalidad.getDuracionMinimaDias()) {
            throw new IllegalArgumentException("La modalidad " + modalidad.getNombre() +
                    " exige una duración mínima de " + modalidad.getDuracionMinimaDias() + " días.");
        }

        return new Reserva(codigoReserva, fechaInicio, fechaFin, cliente, vehiculo,
                modalidad, serviciosContratados, porcentajeDescuento);
    }
}