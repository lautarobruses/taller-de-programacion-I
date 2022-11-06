package negocio;

import modelo.*;
import vista.interfaces.IVistaAdministrador;
import vista.ventanas.VentanaEntidad;
import vista.ventanas.VentanaLogin;
import vista.ventanas.VentanaPromocion;
import java.awt.event.*;

public class ControladorAdministrador implements ActionListener, WindowListener {
    private Administrador modelo;
    private IVistaAdministrador vista;

    public ControladorAdministrador(Administrador modelo, IVistaAdministrador vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.setActionListener(this);
        this.vista.setKeyListener();
        this.vista.setListSelectionListener();
        this.vista.setWindowListener(this);
        this.vista.setNombreLocal(Cerveceria.getInstance().getNombreDelLocal());
        this.vista.setRemuneracion(Cerveceria.getInstance().getRemuneracionBasica());
        this.vista.inicializarListas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Inicio" -> vista.cambiarPagina(0);
            case "Entidades" -> vista.cambiarPagina(1);
            case "Promociones" -> vista.cambiarPagina(2);
            case "Estadisticas" -> vista.cambiarPagina(3);
            case "Editar Cerveceria" -> Cerveceria.getInstance().setNombreDelLocal(vista.getNombreLocal());
            case "Editar Remuneracion" -> Cerveceria.getInstance().setRemuneracionBasica(vista.getRemuneracion());
            case "Cerrar Sesion" -> creaOtraVentana("Login");
            case "Agregar" -> creaOtraVentana("Agregar Entidad");
            case "Modificar" -> creaOtraVentana("Modificar Entidad");
            case "Eliminar" -> {
                switch (vista.getTipoEntidadSeleccionada()) {
                    case "Operarios" -> {
                        Operario operario = vista.getOperarioSeleccionado();
                        Cerveceria.getInstance().eliminarOperario(operario);
                        vista.actualizaLista("Operarios");
                    }
                    case "Mozos" -> {
                        Mozo mozo = vista.getMozoSeleccionado();
                        Cerveceria.getInstance().eliminarMozo(mozo);
                        vista.actualizaLista("Mozos");
                    }
                    case "Productos en venta" -> {
                        try {
                            Producto producto = vista.getProductoSeleccionado();
                            Cerveceria.getInstance().eliminarProducto(producto);
                            vista.actualizaLista("Productos en venta");
                        } catch (Exception ex) {
                            vista.lanzarVentanaEmergente(ex.getMessage());
                        }
                    }
                    case "Mesas del local" -> {
                        Mesa mesa = vista.getMesaSeleccionado();
                        Cerveceria.getInstance().eliminarMesa(mesa);
                        vista.actualizaLista("Mesas del local");
                    }
                }
            }
            case "Activar Promocion" -> {
                switch (vista.getTipoPromocionSeleccionada()) {
                    case "Productos en promocion" -> {
                        PromocionProducto productoEnPromocion = vista.getProductoEnPromocionSeleccionado();
                        productoEnPromocion.setActiva(true);
                    }
                    case "promociones temporales" -> {
                        PromocionTemporal promocionTemporal = vista.getPromocionTemporalSeleccionada();
                        promocionTemporal.setActiva(true);
                    }
                }
            }
            case "Desactivar Promocion" -> { //TODO CHEQUEAR
                switch (vista.getTipoPromocionSeleccionada()) {
                    case "Productos en promocion" -> {
                        PromocionProducto productoEnPromocion = vista.getProductoEnPromocionSeleccionado();
                        productoEnPromocion.setActiva(false);

                    }
                    case "promociones temporales" -> {
                        PromocionTemporal promocionTemporal = vista.getPromocionTemporalSeleccionada();
                        promocionTemporal.setActiva(false);

                    }
                }
            }
            case "Nueva Promocion" -> creaOtraVentana("Nueva Promocion");
            case "Eliminar Promocion" -> {
                switch (vista.getTipoPromocionSeleccionada()) {
                    case "Productos en promocion" -> {
                        PromocionProducto productoEnPromocion = vista.getProductoEnPromocionSeleccionado();
                        //TODO ELIMINAR PROMOCION
                        vista.actualizaLista("Productos en promocion");
                    }
                    case "promociones temporales" -> {
                        PromocionTemporal promocionTemporal = vista.getPromocionTemporalSeleccionada();
                        //TODO ELIMINAR PROMOCION
                        vista.actualizaLista("Promociones temporales");
                    }
                }
            }
            case "Generar Estadistica" -> { //TODO GENERAR ESTADISTICAS

            }
        }
    }

    public void creaOtraVentana(String ventana) {
        switch (ventana) {
            case "Login" -> {
                VentanaLogin ventanaLogin = new VentanaLogin();
                ControladorLogin controladorLogin = new ControladorLogin(ventanaLogin);
                ventanaLogin.ejecutar();
                vista.cerrarVentana();
            }
            case "Agregar Entidad" -> {
                VentanaEntidad ventanaEntidad = new VentanaEntidad();
                ventanaEntidad.setAccion("Crear");
                switch (vista.getTipoEntidadSeleccionada()) {
                    case "Operarios" -> ventanaEntidad.setEntidad("Operario");
                    case "Mozos" -> ventanaEntidad.setEntidad("Mozo");
                    case "Productos en venta" -> ventanaEntidad.setEntidad("Producto");
                    case "Mesas del local" -> ventanaEntidad.setEntidad("Mesa");
                }
                ControladorEntidad controladorEntidad = new ControladorEntidad(ventanaEntidad);
                ventanaEntidad.setWindowListener(this);
                ventanaEntidad.ejecutar();
            }
            case "Modificar Entidad" -> {
                VentanaEntidad ventanaEntidad = new VentanaEntidad();
                ventanaEntidad.setAccion("Modificar");
                switch (vista.getTipoEntidadSeleccionada()) {
                    case "Operarios" -> {
                        ventanaEntidad.setEntidad("Operario");
                        Operario operario = vista.getOperarioSeleccionado();
                        ventanaEntidad.setDatosOperario(operario);
                    }
                    case "Mozos" -> {
                        ventanaEntidad.setEntidad("Mozo");
                        Mozo mozo = vista.getMozoSeleccionado();
                        ventanaEntidad.setDatosMozo(mozo);
                    }
                    case "Productos en venta" -> {
                        ventanaEntidad.setEntidad("Producto");
                        Producto producto = vista.getProductoSeleccionado();
                        ventanaEntidad.setDatosProducto(producto);
                    }
                    case "Mesas del local" -> {
                        ventanaEntidad.setEntidad("Mesa");
                        Mesa mesa = vista.getMesaSeleccionado();
                        ventanaEntidad.setDatosMesa(mesa);
                    }
                }
                ControladorEntidad controladorEntidad = new ControladorEntidad(ventanaEntidad);
                ventanaEntidad.setWindowListener(this);
                ventanaEntidad.ejecutar();
            }
            case "Nueva Promocion" -> {
                switch (vista.getTipoPromocionSeleccionada()) {
                    case "Productos en promocion" -> {
                        VentanaPromocion ventanaPromocion = new VentanaPromocion();
                        ventanaPromocion.setPromocion("Producto en Promocion");
                        ControladorPromocion controladorPromocion = new ControladorPromocion(ventanaPromocion);
                        ventanaPromocion.ejecutar();
                    }
                    case "Promociones temporales" -> {
                        VentanaPromocion ventanaPromocion = new VentanaPromocion();
                        ventanaPromocion.setPromocion("Promocion Temporal");
                        ControladorPromocion controladorPromocion = new ControladorPromocion(ventanaPromocion);
                        ventanaPromocion.ejecutar();
                    }
                }
            }
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //TODO PERSISTIR
    }

    @Override
    public void windowClosed(WindowEvent e) {
        vista.inicializarListas();
    }

    //METODOS NO USADOS
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
