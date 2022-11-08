package vista.ventanas;

import modelo.Cerveceria;
import modelo.Pedido;
import modelo.Producto;
import vista.interfaces.IVistaPedido;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 * Clase que representa la interfaz cuando se crea un pedido.<br>
 */
public class VentanaPedido extends JFrame implements IVistaPedido, ActionListener, ItemListener {
    private JPanel panelPrincipal;
    private JLabel accionPedidoLabel;
    private JPanel panelCentral;
    private JLabel productoLabel;
    private JComboBox<Producto> productoComboBox;
    private JComboBox<String> cantidadComboBox;
    private JButton cancelarButton;
    private JButton accionButton;
    private JPanel productoPane;
    private JPanel cantidadPane;
    private JLabel cantidadLabel;
    //MODELOS
    private DefaultComboBoxModel<Producto> modeloProductos = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> modeloCantidad = new DefaultComboBoxModel<>();

    /**
     * Agrega los ActionListener a los diferentes componentes de la ventana.<br>
     *
     * <b>pre</b> controlador distinto de null.<br>
     * <b>post</b> Se ha asignado un ActionListener a los componentes que lo necesiten.<br>
     *
     * @param controlador Es la clase que recibe los eventos de acción de la ventana.
     */
    @Override
    public void setActionListener(ActionListener controlador) {
        this.accionButton.addActionListener(controlador);
        this.cancelarButton.addActionListener(controlador);
        this.cantidadComboBox.addActionListener(this);
    }

    /**
     * Agrega un ItemListener a los diferentes JComboBox de la ventana, para notificar ItemEvents cuando cambia el elemento seleccionado en el JComboBox.
     *
     * <b>pre</b> Deben existir componentes JComboBox dentro de la ventana.<br>
     * <b>post</b> Se han agregado los ItemListener a los JComboBox especificados.<br>
     */
    @Override
    public void setItemListener() {
        this.productoComboBox.addItemListener(this);
    }

    /**
     * Agrega un WindowListener a la ventana, para notificar WindowEvent que ocurran desde esta ventana.<br>
     *
     * <b>pre</b> controlador distinto de null.<br>
     * <b>post</b> Se ha asignado un WindowListener a la ventana<br>
     * @param controlador Es la clase que recibe los WindowEvent de la ventana.
     */
    @Override
    public void setWindowListener(WindowListener controlador) {
        this.addWindowListener(controlador);
    }

    /**
     * Establece las caracteristicas principales que defininen a la ventana.<br>
     *
     * <b>post</b> Se ejecuta la ventana.<br>
     */
    @Override
    public void ejecutar() {
        setTitle("Cerveceria - Grupo 1");
        pack(); //Coloca los componentes
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        setSize(800,440); //Dimensiones del JFrame
        setResizable(false); //No redimensionable
        setLocationRelativeTo(null);
        setModelos();
    }

    /**
     * Oculta y cierra la ventana.<br>
     *
     * <b>post</b> Se detiene la ejecucion de la ventana.<br>
     */
    @Override
    public void cerrarVentana() {
        setVisible(false); //Oculto la ventana
        dispose(); //Cierro la ventana
    }

    /**
     * Lanza una pequena ventana con un mensaje y boton de confirmacion.<br>
     *
     * <b>pre</b> mensaje distinto de null.<br>
     * <b>post</b> Se abre un JFrame con un mensaje.<br>
     * @param mensaje Es el mensaje que se desea mostrar en la ventana.<br>
     */
    @Override
    public void lanzarVentanaEmergente(String mensaje) {
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, mensaje);
    }

    /**
     * Establece los modelos a los diferentes componentes que lo necesiten.<br>
     *
     * <b>pre</b> Los modelos deben estar instanciados.<br>
     * <b>post</b> Los componentes quedan con el modelo acorde establecido.<br>
     */
    @Override
    public void setModelos() {
        this.productoComboBox.setModel(modeloProductos);
        this.cantidadComboBox.setModel(modeloCantidad);
    }

    /**
     * Inicializa el DefaultComboBoxModel dependiendo del parametro recibido, pora su visualizacion en la JComboBox.<br>
     *
     * <b>pre</b> El DefaultComboBoxModel ya ha sido seteado a su JComboBox.<br>
     * <b>post</b> Se pueden visualizar los productos almacenados en el sistema en el JComboBox.<br>
     */
    @Override
    public void inicializaComboBox() {
        HashMap<Integer, Producto> productos = Cerveceria.getInstance().getProductos();

        modeloProductos.addElement(null);
        productos.forEach((nro, producto) -> modeloProductos.addElement(producto));
    }

    /**
     * Devuelve el producto seleccionado en la JComboBox.<br>
     *
     * @return producto seleccionado.<br>
     */
    @Override
    public Producto getProductoSeleccionado() {
        return modeloProductos.getElementAt(productoComboBox.getSelectedIndex());
    }

    /**
     * Devuelve la cantidad seleccionada correspondiente al producto seleccionado en la JComboBox.<br>
     *
     * @return cantidad seleccionada.<br>
     */
    @Override
    public int getCantidadSeleccionada() {
        return Integer.parseInt(modeloCantidad.getElementAt(cantidadComboBox.getSelectedIndex()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.accionButton.setEnabled(true);

        if (productoComboBox.getSelectedIndex() == -1 || cantidadComboBox.getSelectedIndex() == -1) {
            this.accionButton.setEnabled(false);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        this.cantidadComboBox.setEnabled(false);
        Producto productoSeleccionado = modeloProductos.getElementAt(productoComboBox.getSelectedIndex());

        modeloCantidad.removeAllElements();
        if (productoSeleccionado != null) {
            modeloCantidad.addElement(null);

            if (productoSeleccionado.getStockInicial() != 0) {
                this.cantidadComboBox.setEnabled(true);

                for (int i = 1; i <= productoSeleccionado.getStockInicial(); i++) {
                    modeloCantidad.addElement(String.valueOf(i));
                }
            } else {
                lanzarVentanaEmergente("El producto seleccionado NO tiene stock disponible");
            }
        }
    }
}
