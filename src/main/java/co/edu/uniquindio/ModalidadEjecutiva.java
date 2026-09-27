package co.edu.uniquindio;

public class ModalidadEjecutiva extends ModalidadAlquiler {

    public ModalidadEjecutiva(String codigo, String nombre, String descripcion,
                              int duracionMinimaDias, double valorDiario, EstadoModalidad estado) {
        super(codigo, nombre, descripcion, duracionMinimaDias, valorDiario, estado);
    }

    @Override
    public String toString() {
        return "[Ejecutiva] " + nombre + " - $" + valorDiario + "/día (Mín: " + duracionMinimaDias + " días)";
    }
}