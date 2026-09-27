package co.edu.uniquindio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String codigoReserva;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int diasContratados;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private ModalidadAlquiler modalidad;
    private List<ServicioAdicional> serviciosContratados;
    private double porcentajeDescuento;
    private double valorTotal;

    // Constructor accesible mediante el Builder
    Reserva(String codigoReserva, LocalDate fechaInicio, LocalDate fechaFin,
            Cliente cliente, Vehiculo vehiculo, ModalidadAlquiler modalidad,
            List<ServicioAdicional> serviciosContratados, double porcentajeDescuento) {

        this.codigoReserva = codigoReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.modalidad = modalidad;
        this.serviciosContratados = serviciosContratados != null ? serviciosContratados : new ArrayList<>();
        this.porcentajeDescuento = porcentajeDescuento;

        // Cálculo de los días efectivos
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        this.diasContratados = dias <= 0 ? 1 : (int) dias;

        // Liquidación automática del valor total
        this.valorTotal = calcularTotal();
    }

    /**
     * Fórmula de liquidación:
     * [(Tarifa Vehículo + Valor Diario Modalidad) * Días] + Sumatoria(Servicios) - Descuento
     */
    public double calcularTotal() {
        double costoBaseDiario = vehiculo.getTarifaDiaria() + modalidad.getValorDiario();
        double subtotalVehiculoModalidad = costoBaseDiario * diasContratados;

        double subtotalServicios = 0.0;
        for (ServicioAdicional servicio : serviciosContratados) {
            subtotalServicios += servicio.getPrecio();
        }

        double subtotalBruto = subtotalVehiculoModalidad + subtotalServicios;
        double valorDescuento = subtotalBruto * (porcentajeDescuento / 100.0);

        return subtotalBruto - valorDescuento;
    }

    // Punto de entrada al Builder
    public static ReservaBuilder builder() {
        return new ReservaBuilder();
    }

    // Getters y Setters
    public String getCodigoReserva() { return codigoReserva; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public int getDiasContratados() { return diasContratados; }
    public Cliente getCliente() { return cliente; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public ModalidadAlquiler getModalidad() { return modalidad; }
    public List<ServicioAdicional> getServiciosContratados() { return serviciosContratados; }
    public double getPorcentajeDescuento() { return porcentajeDescuento; }
    public double getValorTotal() { return valorTotal; }

    @Override
    public String toString() {
        return "Reserva #" + codigoReserva + " | " + cliente.getNombreCompleto() +
                " | " + vehiculo.getPlaca() + " | Total: $" + valorTotal;
    }
}
