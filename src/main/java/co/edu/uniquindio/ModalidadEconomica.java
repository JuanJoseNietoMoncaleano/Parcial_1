package co.edu.uniquindio;

public class ModalidadEconomica extends ModalidadAlquiler {

    public ModalidadEconomica(String codigo, String nombre, String descripcion,
                              int duracionMinimaDias, double valorDiario, EstadoModalidad estado) {
        super(codigo, nombre, descripcion, duracionMinimaDias, valorDiario, estado);
    }

    @Override
    public String toString() {
        return "[Económica] " + nombre + " - $" + valorDiario + "/día (Mín: " + duracionMinimaDias + " días)";
    }
}

