package controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import co.edu.uniquindio.*;
import service.ClienteService;
import service.ReservaService;
import service.VehiculoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainController {

    // --- Servicios de Negocio (Capa Service) ---
    private final ClienteService clienteService = new ClienteService();
    private final VehiculoService vehiculoService = new VehiculoService();
    private final ReservaService reservaService = new ReservaService();

    // Catálogo de servicios adicionales disponibles
    private final List<ServicioAdicional> catalogoServicios = new ArrayList<>();

    // --- Controles FXML: Pestaña Clientes ---
    @FXML private TextField txtDocumento;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtEdad;
    @FXML private TextField txtBuscarTelefono;
    @FXML private Label lblResultadoPerfecto;

    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, String> colDocCliente;
    @FXML private TableColumn<Cliente, String> colNombreCliente;
    @FXML private TableColumn<Cliente, String> colTelCliente;
    @FXML private TableColumn<Cliente, String> colCorreoCliente;
    @FXML private TableColumn<Cliente, Integer> colEdadCliente;
    @FXML private TableColumn<Cliente, LocalDate> colFechaRegistro;

    // --- Controles FXML: Pestaña Nueva Reserva ---
    @FXML private TextField txtCodigoReserva;
    @FXML private ComboBox<Cliente> cmbClientes;
    @FXML private ComboBox<Vehiculo> cmbVehiculos;
    @FXML private ComboBox<ModalidadAlquiler> cmbModalidades;
    @FXML private DatePicker dpInicio;
    @FXML private DatePicker dpFin;
    @FXML private TextField txtDescuento;
    @FXML private CheckBox chkGps;
    @FXML private CheckBox chkSillaBebe;
    @FXML private CheckBox chkConductor;
    @FXML private Label lblTotalLiquidado;

    @FXML private TableView<Reserva> tblReservas;
    @FXML private TableColumn<Reserva, String> colCodReserva;
    @FXML private TableColumn<Reserva, String> colClienteReserva;
    @FXML private TableColumn<Reserva, String> colVehiculoReserva;
    @FXML private TableColumn<Reserva, String> colModalidadReserva;
    @FXML private TableColumn<Reserva, Integer> colDiasReserva;
    @FXML private TableColumn<Reserva, LocalDate> colInicioReserva;
    @FXML private TableColumn<Reserva, LocalDate> colFinReserva;
    @FXML private TableColumn<Reserva, Double> colTotalReserva;

    // --- Controles FXML: Pestaña Reporte de Ingresos ---
    @FXML private DatePicker dpReporteDesde;
    @FXML private DatePicker dpReporteHasta;
    @FXML private Label lblResultadoIngresos;

    @FXML private TableView<Reserva> tblReporteReservas;
    @FXML private TableColumn<Reserva, String> colRepCodigo;
    @FXML private TableColumn<Reserva, String> colRepCliente;
    @FXML private TableColumn<Reserva, String> colRepVehiculo;
    @FXML private TableColumn<Reserva, LocalDate> colRepFecha;
    @FXML private TableColumn<Reserva, Double> colRepTotal;

    @FXML
    public void initialize() {
        configurarColumnasTablas();
        cargarDatosInicialesFlotaYModalidades();
        actualizarTablasYCombos();
    }

    private void configurarColumnasTablas() {
        // Configuración tabla clientes
        colDocCliente.setCellValueFactory(new PropertyValueFactory<>("documentoIdentidad"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colTelCliente.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreoCliente.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        colEdadCliente.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));

        // Configuración tabla reservas
        colCodReserva.setCellValueFactory(new PropertyValueFactory<>("codigoReserva"));
        colClienteReserva.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCliente().getNombreCompleto()));
        colVehiculoReserva.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getVehiculo().getPlaca()));
        colModalidadReserva.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getModalidad().getNombre()));
        colDiasReserva.setCellValueFactory(new PropertyValueFactory<>("diasContratados"));
        colInicioReserva.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFinReserva.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colTotalReserva.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

        // Configuración tabla reporte ingresos
        colRepCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoReserva"));
        colRepCliente.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCliente().getNombreCompleto()));
        colRepVehiculo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getVehiculo().getPlaca()));
        colRepFecha.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colRepTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
    }

    private void cargarDatosInicialesFlotaYModalidades() {
        // Vehículos de prueba
        vehiculoService.registrarVehiculo(new Vehiculo("KJH-123", "Toyota", "Corolla", 2024, "Sedán", 120000.0));
        vehiculoService.registrarVehiculo(new Vehiculo("QWE-456", "Renault", "Duster", 2023, "SUV", 150000.0));
        vehiculoService.registrarVehiculo(new Vehiculo("MNB-789", "BMW", "Serie 3", 2025, "Premium Sedán", 260000.0));

        // Modalidades usando Factory Method
        EmpresaRentCar empresa = EmpresaRentCar.getInstance();
        ModalidadAlquiler eco = ModalidadFactory.crearModalidad(
                "ECONOMICA", "MOD-01", "Económica", "Plan estándar", 1, 30000.0, EstadoModalidad.DISPONIBLE, null, 0, null);
        eco.agregarBeneficio("Seguro Básico");

        ModalidadAlquiler ejec = ModalidadFactory.crearModalidad(
                "EJECUTIVA", "MOD-02", "Ejecutiva", "Viajes de negocios", 2, 60000.0, EstadoModalidad.DISPONIBLE, null, 0, null);
        ejec.agregarBeneficio("Kilometraje Ilimitado");
        ejec.agregarBeneficio("Asistencia 24/7");

        ModalidadAlquiler prem = ModalidadFactory.crearModalidad(
                "PREMIUM", "MOD-03", "Premium VIP", "Servicio exclusivo", 3, 110000.0, EstadoModalidad.DISPONIBLE,
                "Todo Riesgo Plus", 2, "Entrega y recogida en aeropuerto");

        empresa.getModalidades().add(eco);
        empresa.getModalidades().add(ejec);
        empresa.getModalidades().add(prem);

        // Servicios adicionales
        catalogoServicios.add(new ServicioAdicional("SRV-1", "GPS Satelital", "Navegador", 20000.0, true));
        catalogoServicios.add(new ServicioAdicional("SRV-2", "Silla para Bebé", "Seguridad infantil", 15000.0, true));
        catalogoServicios.add(new ServicioAdicional("SRV-3", "Conductor Adicional", "Permiso extra", 35000.0, true));
    }

    private void actualizarTablasYCombos() {
        tblClientes.setItems(FXCollections.observableArrayList(clienteService.listarClientes()));
        tblReservas.setItems(FXCollections.observableArrayList(reservaService.listarTodas()));

        cmbClientes.setItems(FXCollections.observableArrayList(clienteService.listarClientes()));
        cmbVehiculos.setItems(FXCollections.observableArrayList(vehiculoService.listarVehiculosDisponibles()));
        cmbModalidades.setItems(FXCollections.observableArrayList(EmpresaRentCar.getInstance().getModalidades()));
    }

    // --- ACCIÓN: Registrar Cliente ---
    @FXML
    public void registrarCliente() {
        try {
            String doc = txtDocumento.getText();
            String nombre = txtNombre.getText();
            String tel = txtTelefono.getText();
            String correo = txtCorreo.getText();
            int edad = Integer.parseInt(txtEdad.getText());

            Cliente nuevo = new Cliente(doc, nombre, tel, correo, edad, LocalDate.now());
            clienteService.registrarCliente(nuevo);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Registro Exitoso", "Cliente registrado correctamente.");
            limpiarCamposCliente();
            actualizarTablasYCombos();
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Datos", "La edad debe ser un número entero válido.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    // --- ACCIÓN: Consultar Teléfono y Validar Número Perfecto ---
    @FXML
    public void consultarTelefonoPerfecto() {
        String telefonoBuscado = txtBuscarTelefono.getText();
        if (telefonoBuscado == null || telefonoBuscado.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Ingrese un número de teléfono.");
            return;
        }

        Optional<Cliente> encontrado = clienteService.buscarPorTelefono(telefonoBuscado);
        boolean esPerfecto = clienteService.esTelefonoPerfecto(telefonoBuscado);

        String estadoNumero = esPerfecto
                ? "es un NÚMERO PERFECTO."
                : "NO es un número perfecto.";

        if (encontrado.isPresent()) {
            lblResultadoPerfecto.setText("Cliente: " + encontrado.get().getNombreCompleto() + " | El teléfono " + estadoNumero);
        } else {
            lblResultadoPerfecto.setText("Teléfono (" + telefonoBuscado + ") no pertenece a ningún cliente, pero " + estadoNumero);
        }
    }

    // --- ACCIÓN: Crear Reserva con Patrón Builder ---
    @FXML
    public void crearReserva() {
        try {
            String codigo = txtCodigoReserva.getText();
            Cliente cliente = cmbClientes.getValue();
            Vehiculo vehiculo = cmbVehiculos.getValue();
            ModalidadAlquiler modalidad = cmbModalidades.getValue();
            LocalDate inicio = dpInicio.getValue();
            LocalDate fin = dpFin.getValue();
            double descuento = Double.parseDouble(txtDescuento.getText());

            List<ServicioAdicional> extras = new ArrayList<>();
            if (chkGps.isSelected()) extras.add(catalogoServicios.get(0));
            if (chkSillaBebe.isSelected()) extras.add(catalogoServicios.get(1));
            if (chkConductor.isSelected()) extras.add(catalogoServicios.get(2));

            // Construcción mediante Patrón Builder
            Reserva nuevaReserva = Reserva.builder()
                    .codigoReserva(codigo)
                    .cliente(cliente)
                    .vehiculo(vehiculo)
                    .modalidad(modalidad)
                    .fechas(inicio, fin)
                    .servicios(extras)
                    .porcentajeDescuento(descuento)
                    .build();

            reservaService.registrarReserva(nuevaReserva);

            lblTotalLiquidado.setText("Total Liquidado: $" + String.format("%.2f", nuevaReserva.getValorTotal()));
            mostrarAlerta(Alert.AlertType.INFORMATION, "Reserva Creada",
                    "Reserva #" + codigo + " generada exitosamente. Total: $" + nuevaReserva.getValorTotal());

            limpiarCamposReserva();
            actualizarTablasYCombos();
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Datos", "El descuento debe ser un valor numérico.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al Reservar", e.getMessage());
        }
    }

    // --- ACCIÓN: Calcular Ingresos por Periodo ---
    @FXML
    public void calcularIngresos() {
        try {
            LocalDate desde = dpReporteDesde.getValue();
            LocalDate hasta = dpReporteHasta.getValue();

            if (desde == null || hasta == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Atención", "Seleccione un rango de fechas completo.");
                return;
            }

            double total = reservaService.calcularIngresosPorPeriodo(desde, hasta);
            List<Reserva> filtradas = reservaService.filtrarReservasPorPeriodo(desde, hasta);

            lblResultadoIngresos.setText("Total Ingresos Periodo: $" + String.format("%.2f", total));
            tblReporteReservas.setItems(FXCollections.observableArrayList(filtradas));
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error en Reporte", e.getMessage());
        }
    }

    private void limpiarCamposCliente() {
        txtDocumento.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtEdad.clear();
    }

    private void limpiarCamposReserva() {
        txtCodigoReserva.clear();
        cmbClientes.getSelectionModel().clearSelection();
        cmbVehiculos.getSelectionModel().clearSelection();
        cmbModalidades.getSelectionModel().clearSelection();
        dpInicio.setValue(null);
        dpFin.setValue(null);
        txtDescuento.setText("0");
        chkGps.setSelected(false);
        chkSillaBebe.setSelected(false);
        chkConductor.setSelected(false);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
