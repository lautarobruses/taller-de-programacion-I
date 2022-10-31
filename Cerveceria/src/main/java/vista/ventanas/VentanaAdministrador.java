package vista.ventanas;

import modelo.*;
import vista.interfaces.IVistaAdministrador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaAdministrador extends JFrame implements IVistaAdministrador, ActionListener, KeyListener {
    private String entidadSeleccionada;
    private String promocionSeleccionada;
    private JPanel panelPrincipal;
    private JPanel panelIzquierdo;
    private JButton estadisticasButton;
    private JButton promocionesButton;
    private JButton entidadesButton;
    private JLabel ImagenCerveza;
    private JButton inicioButton;
    private JTabbedPane panelCentral;
    private JPanel inicioPanel;
    private JLabel bienvenidoLabel;
    private JButton cerrarSesionButton;
    private JLabel nombreApellidoLabel;
    private JPanel entidadesPanel;
    private JLabel entidadesLabel;
    private JList listaEntidades;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton modificarButton;
    private JButton editarTituloButton;
    private JLabel remuneracionBasicaLabel;
    private JButton editarRemuneracionButton;
    private JPanel remuneracionPanel;
    private JPanel checkBoxPanel;
    private JCheckBox operariosCheckBox;
    private JButton nuevaPromocionButton;
    private JButton eliminarPromocionButton;
    private JButton desactivarButton;
    private JButton activarButton;
    private JPanel promocionesPanel;
    private JLabel promocionesLabel;
    private JList listaPromocionesTemporales;
    private JPanel estadisticasPanel;
    private JCheckBox mesasDelLocalCheckBox;
    private JCheckBox productosEnVentaCheckBox;
    private JCheckBox mozosCheckBox;
    private JButton generarEstadisticasButton;
    private JLabel estadisticasLabel;
    private JCheckBox productosEnPromocionCheckBox;
    private JCheckBox promocionesTemporalesCheckBox;
    private JTextField cerveceriaTextField;
    private JTextField remuneracionTextField;
    //MODELOS PARA LISTAS
    DefaultListModel<Operario> modeloOperario = new DefaultListModel<>();
    DefaultListModel<Mozo> modeloMozo = new DefaultListModel<>();
    DefaultListModel<Producto> modeloProducto = new DefaultListModel<>();
    DefaultListModel<Mesa> modeloMesa = new DefaultListModel<>();
    DefaultListModel<ProductoEnPromocion> modeloProductoEnPromocion = new DefaultListModel<>();
    DefaultListModel<PromocionTemporal> modeloPromocionGeneral = new DefaultListModel<>();

    @Override
    public void setActionListener(ActionListener controlador) {
        //BUTTONS
        this.inicioButton.addActionListener(controlador);
        this.entidadesButton.addActionListener(controlador);
        this.promocionesButton.addActionListener(controlador);
        this.estadisticasButton.addActionListener(controlador);
        this.cerrarSesionButton.addActionListener(controlador);
        this.editarTituloButton.addActionListener(controlador);
        this.editarTituloButton.addActionListener(this);
        this.editarRemuneracionButton.addActionListener(controlador);
        this.editarRemuneracionButton.addActionListener(this);
        this.agregarButton.addActionListener(controlador);
        this.agregarButton.addActionListener(this);
        this.modificarButton.addActionListener(controlador);
        this.modificarButton.addActionListener(this);
        this.eliminarButton.addActionListener(controlador);
        this.eliminarButton.addActionListener(this);
        this.nuevaPromocionButton.addActionListener(controlador);
        this.eliminarPromocionButton.addActionListener(controlador);
        this.activarButton.addActionListener(controlador);
        this.desactivarButton.addActionListener(controlador);
        this.generarEstadisticasButton.addActionListener(controlador);
        //CHECKBOXS
        this.operariosCheckBox.addActionListener(this);
        this.mozosCheckBox.addActionListener(this);
        this.productosEnVentaCheckBox.addActionListener(this);
        this.mesasDelLocalCheckBox.addActionListener(this);
        this.productosEnPromocionCheckBox.addActionListener(this);
        this.promocionesTemporalesCheckBox.addActionListener(this);
    }

    @Override
    public void setKeyListener() {
        this.cerveceriaTextField.addKeyListener(this);
        this.remuneracionTextField.addKeyListener(this);
    }

    @Override
    public void setWindowListener(WindowListener controlador) {
        this.addWindowListener(controlador);
    }

    @Override
    public void ejecutar() {
        setTitle("Cerveceria - Grupo 1");
        pack(); //Coloca los componentes
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1280,720); //Dimensiones del JFrame
        setResizable(false); //No redimensionable
        setLocationRelativeTo(null);
    }

    @Override
    public void cerrarVentana() {
        setVisible(false); //Oculto la ventana
        dispose(); //Cierro la ventana
    }

    @Override
    public void lanzarVentanaEmergente(String mensaje) {
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, mensaje);
    }

    @Override
    public void setModelos() {

    }

    @Override
    public void setModeloEntidad() {
        switch (this.entidadSeleccionada) {
            case "Operarios" -> this.listaEntidades.setModel(modeloOperario);
            case "Mozos" -> this.listaEntidades.setModel(modeloMozo);
            case "Productos en venta" -> this.listaEntidades.setModel(modeloProducto);
            case "Mesas del local" -> this.listaEntidades.setModel(modeloMesa);
        }
    }

    @Override
    public void inicializarListas() {

    }

    @Override
    public String getNombreLocal() {
        return cerveceriaTextField.getText();
    }

    @Override
    public Double getRemuneracion() {
        return Double.parseDouble(remuneracionTextField.getText());
    }

    @Override
    public String getTipoEntidadSeleccionada() {
        return entidadSeleccionada;
    }

    @Override
    public Operario getOperarioSeleccionado() {
        return (this.listaEntidades == null ? null : (Operario) this.listaEntidades.getSelectedValue());
    }

    @Override
    public Mozo getMozoSeleccionado() {
        return (this.listaEntidades == null ? null : (Mozo) this.listaEntidades.getSelectedValue());
    }

    @Override
    public Producto getProductoSeleccionado() {
        return (this.listaEntidades == null ? null : (Producto) this.listaEntidades.getSelectedValue());
    }

    @Override
    public Mesa getMesaSeleccionado() {
        return (this.listaEntidades == null ? null : (Mesa) this.listaEntidades.getSelectedValue());
    }

    @Override
    public String getTipoPromocionSeleccionada() {
        return promocionSeleccionada;
    }

    @Override
    public String getPromocionSeleccionada() {
        return promocionSeleccionada;
    }

    @Override
    public void cambiarPagina(int pagina) {
        panelCentral.setSelectedIndex(pagina);

        this.inicioButton.setBackground(Color.decode("#D9D9D9"));
        this.entidadesButton.setBackground(Color.decode("#D9D9D9"));
        this.promocionesButton.setBackground(Color.decode("#D9D9D9"));
        this.estadisticasButton.setBackground(Color.decode("#D9D9D9"));

        switch (pagina) {
            case 0 -> this.inicioButton.setBackground(Color.decode("#FFFFFF"));
            case 1 -> this.entidadesButton.setBackground(Color.decode("#FFFFFF"));
            case 2 -> this.promocionesButton.setBackground(Color.decode("#FFFFFF"));
            case 3 -> this.estadisticasButton.setBackground(Color.decode("#FFFFFF"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //BOTONES
        switch (e.getActionCommand()) {
            case "Editar Cerveceria" -> editarTituloButton.setEnabled(false);
            case "Editar Remuneracion" -> editarRemuneracionButton.setEnabled(false);
            default -> {
                //ENTIDADES
                if (this.operariosCheckBox.isSelected() || this.mozosCheckBox.isSelected() || this.productosEnVentaCheckBox.isSelected() || this.mesasDelLocalCheckBox.isSelected()) {
                    this.agregarButton.setEnabled(true);
                    this.modificarButton.setEnabled(true);
                    this.eliminarButton.setEnabled(true);

                    if (this.operariosCheckBox.isSelected()) {
                        this.entidadSeleccionada = "Operario";
                    }

                    if (this.mozosCheckBox.isSelected()) {
                        this.entidadSeleccionada = "Mozo";
                    }

                    if (this.productosEnVentaCheckBox.isSelected()) {
                        this.entidadSeleccionada = "Productos en venta";
                    }

                    if (this.mesasDelLocalCheckBox.isSelected()) {
                        this.entidadSeleccionada = "Mesas del local";
                    }

                    setModeloEntidad();
                }

                //PROMOCIONES
                if (this.productosEnPromocionCheckBox.isSelected() || this.promocionesTemporalesCheckBox.isSelected()) {
                    this.activarButton.setEnabled(true);
                    this.desactivarButton.setEnabled(true);
                    this.nuevaPromocionButton.setEnabled(true);
                    this.eliminarPromocionButton.setEnabled(true);

                    if (this.productosEnPromocionCheckBox.isSelected()) {
                        this.promocionSeleccionada = "Productos en promocion";
                    }

                    if (this.promocionesTemporalesCheckBox.isSelected()) {
                        this.promocionSeleccionada = "Promociones temporales";
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String nombreLocal = Cerveceria.getInstance().getNombreDelLocal();
        String remuneracionBasica = String.valueOf(Cerveceria.getInstance().getRemuneracionBasica());

        if (cerveceriaTextField.getText().equals(nombreLocal)) {
            editarTituloButton.setEnabled(false);
        } else {
            editarTituloButton.setEnabled(true);
        }

        if (remuneracionTextField.getText().equals(remuneracionBasica)) {
            editarRemuneracionButton.setEnabled(false);
        } else {
            editarRemuneracionButton.setEnabled(true);
        }
    }

    //METODOS NO USADOS
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
