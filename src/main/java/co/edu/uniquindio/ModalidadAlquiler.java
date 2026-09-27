package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.List;

public abstract class ModalidadAlquiler {
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected int duracionMinimaDias;
    protected double valorDiario;
    protected EstadoModalidad estado;
    protected List<String> beneficios;

    public ModalidadAlquiler(String codigo, String nombre, String descripcion,
                             int duracionMinimaDias, double valorDiario, EstadoModalidad estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMinimaDias = duracionMinimaDias;
        this.valorDiario = valorDiario;
        this.estado = estado;
        this.beneficios = new ArrayList<>();
    }

    public void agregarBeneficio(String beneficio) {
        if (beneficio != null && !beneficio.trim().isEmpty()) {
            this.beneficios.add(beneficio);
        }
    }

    public double calcularCostoModalidad(int dias) {
        return this.valorDiario * dias;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getDuracionMinimaDias() { return duracionMinimaDias; }
    public void setDuracionMinimaDias(int duracionMinimaDias) { this.duracionMinimaDias = duracionMinimaDias; }

    public double getValorDiario() { return valorDiario; }
    public void setValorDiario(double valorDiario) { this.valorDiario = valorDiario; }

    public EstadoModalidad getEstado() { return estado; }
    public void setEstado(EstadoModalidad estado) { this.estado = estado; }

    public List<String> getBeneficios() { return beneficios; }
}