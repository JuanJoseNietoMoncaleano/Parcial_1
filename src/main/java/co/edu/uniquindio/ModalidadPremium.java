package co.edu.uniquindio;

public class ModalidadPremium extends ModalidadAlquiler {
    private String tipoCobertura;
    private int cantidadConductoresAdicionales;
    private String caracteristicasEspeciales;

    public ModalidadPremium(String codigo, String nombre, String descripcion,
                            int duracionMinimaDias, double valorDiario, EstadoModalidad estado,
                            String tipoCobertura, int cantidadConductoresAdicionales, String caracteristicasEspeciales) {
        super(codigo, nombre, descripcion, duracionMinimaDias, valorDiario, estado);
        this.tipoCobertura = tipoCobertura;
        this.cantidadConductoresAdicionales = cantidadConductoresAdicionales;
        this.caracteristicasEspeciales = caracteristicasEspeciales;
    }

    public String getTipoCobertura() { return tipoCobertura; }
    public void setTipoCobertura(String tipoCobertura) { this.tipoCobertura = tipoCobertura; }

    public int getCantidadConductoresAdicionales() { return cantidadConductoresAdicionales; }
    public void setCantidadConductoresAdicionales(int cantidadConductoresAdicionales) {
        this.cantidadConductoresAdicionales = cantidadConductoresAdicionales;
    }

    public String getCaracteristicasEspeciales() { return caracteristicasEspeciales; }
    public void setCaracteristicasEspeciales(String caracteristicasEspeciales) {
        this.caracteristicasEspeciales = caracteristicasEspeciales;
    }

    @Override
    public String toString() {
        return "[Premium] " + nombre + " - $" + valorDiario + "/día (Cobertura: " + tipoCobertura + ")";
    }
}
