package vista.interfaces;

import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import java.awt.event.WindowListener;

public interface IVistaAdministrador extends IVista{

    void setWindowListener(WindowListener controlador);

    void setModelos();

    void inicializarListas();

    String getTipoEntidadSeleccionada();

    Operario getOperarioSeleccionado();

    Mozo getMozoSeleccionado();

    Producto getProductoSeleccionado();

    Mesa getMesaSeleccionado();

    String getTipoPromocionSeleccionada();

    String getPromocionSeleccionada();

    void cambiarPagina(int pagina);
}