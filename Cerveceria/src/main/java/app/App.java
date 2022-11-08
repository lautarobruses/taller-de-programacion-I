package app;

import modelo.Cerveceria;
import modelo.Operario;
import modelo.Producto;
import negocio.ControladorLogin;
import vista.ventanas.VentanaLogin;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Cerveceria cerveceria = Cerveceria.getInstance();

        //MOZOS
        try {
            cerveceria.agregarMozo("Lautaro", 18,1);
            cerveceria.agregarMozo("Ignacio", 25,1);
            cerveceria.agregarMozo("Tomas", 33,1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //MESAS
        try {
            cerveceria.agregarMesa(2);
            cerveceria.agregarMesa(6);
            cerveceria.agregarMesa(7);
            cerveceria.agregarMesa(8);
            cerveceria.agregarMesa(3);
            cerveceria.agregarMesa(4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //PRODUCTOS
        Producto p1 = new Producto("Coca-Cola",150,200,5);
        Producto p2 = new Producto("Agua",100,150,9);
        Producto p3 = new Producto("Sprite",250,300,100);

        cerveceria.getProductos().put(p1.getIdProducto(), p1);
        cerveceria.getProductos().put(p2.getIdProducto(), p2);
        cerveceria.getProductos().put(p3.getIdProducto(), p3);

        //OPERARIOS
        Operario yop = new Operario("Lautaro Bruses","123","123", true);
        Cerveceria.getInstance().getOperarios().add(yop);

        //PROMOCIONES
        ArrayList<String> diasSemana = new ArrayList<>();
        diasSemana.add("Lunes");
        diasSemana.add("Miercoles");

        Cerveceria.getInstance().agregarPromocionProducto(diasSemana, true, p1, true, false);
        Cerveceria.getInstance().agregarPromocionProducto(diasSemana, true, p2, true, false);

        Cerveceria.getInstance().agregarPromocionTemporal(diasSemana, true, "Pappy Hour", "Efectivo", 50, true);

        //EJECUCION NORMAL
        VentanaLogin ventanaLogin = new VentanaLogin();
        ControladorLogin controladorLogin = new ControladorLogin(ventanaLogin);
        ventanaLogin.ejecutar();
    }
}
