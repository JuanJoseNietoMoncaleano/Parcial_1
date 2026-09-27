package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.List;

public class EmpresaRentCar {
    private String nit;
    private String nombreComercial;
    private String direccion;
    private String telefono;
    private String correo;
    private String paginaWeb;

    // Listas maestras del sistema
    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<ServicioAdicional> serviciosAdicionales;
    private List<ModalidadAlquiler> modalidades;
    private List<Reserva> reservas;

    // Instancia estática única (Patrón Singleton)
    private static EmpresaRentCar instance;

    // Constructor privado para evitar instanciación con operador new
    private EmpresaRentCar() {
        this.nit = "901.845.123-4";
        this.nombreComercial = "RentCar S.A.S.";
        this.direccion = "Carrera 14 # 21-00, Armenia, Quindío";
        this.telefono = "3117894561";
        this.correo = "contacto@rentcar.com.co";
        this.paginaWeb = "www.rentcar.com.co";
        this.modalidades = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.serviciosAdicionales = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    // Punto de acceso global a la instancia única
    public static EmpresaRentCar getInstance() {
        if (instance == null) {
            instance = new EmpresaRentCar();
        }
        return instance;
    }

    // Getters y setters institucionales
    public String getNit() { return nit; }
    public String getNombreComercial() { return nombreComercial; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getPaginaWeb() { return paginaWeb; }

    // Acceso a listas maestras
    public List<Cliente> getClientes() { return clientes; }
    public List<Vehiculo> getVehiculos() { return vehiculos; }
    public List<ServicioAdicional> getServiciosAdicionales() { return serviciosAdicionales; }
    public List<ModalidadAlquiler> getModalidades() { return modalidades; }
    public List<Reserva> getReservas() { return reservas; }
    }
