package co.edu.uniquindio;

public class ModalidadFactory {

    public static ModalidadAlquiler crearModalidad(String tipo, String codigo, String nombre,
                                                   String descripcion, int duracionMinimaDias,
                                                   double valorDiario, EstadoModalidad estado,
                                                   String tipoCobertura, int conductores, String caracteristicas) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de modalidad no puede ser nulo.");
        }

        switch (tipo.trim().toUpperCase()) {
            case "ECONOMICA":
            case "ECONÓMICA":
                return new ModalidadEconomica(codigo, nombre, descripcion, duracionMinimaDias, valorDiario, estado);

            case "EJECUTIVA":
                return new ModalidadEjecutiva(codigo, nombre, descripcion, duracionMinimaDias, valorDiario, estado);

            case "PREMIUM":
                return new ModalidadPremium(codigo, nombre, descripcion, duracionMinimaDias, valorDiario, estado,
                        tipoCobertura, conductores, caracteristicas);

            default:
                throw new IllegalArgumentException("Tipo de modalidad desconocido: " + tipo);
        }
    }
}
