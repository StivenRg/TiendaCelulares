package co.edu.uptc.modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Tienda{
	private ArrayList <Producto>       productos;
	private HashMap <String, Vendedor> vendedores;
	private ArrayList <Venta>          ventas;

	public Tienda (){
		productos  = new ArrayList <>();
		vendedores = new HashMap <>();
		ventas     = new ArrayList <>();
	}

	public void agregarVendedor (Vendedor vendedor){
		vendedores.put(vendedor.getNombre(), vendedor);
	}

	public void agregarProductos (String[] paramDatos){
		for (String locLinea : paramDatos){
			String[] locDato = locLinea.strip().split("\\|");
			try{
				locDato[0] = locDato[0].strip();
				locDato[1] = locDato[1].strip();
				locDato[2] = locDato[2].strip();
				locDato[3] = locDato[3].strip();
				locDato[4] = locDato[4].strip();
				Producto producto = new Producto(locDato[0], locDato[1], locDato[2], Double.parseDouble(locDato[3]), Integer.parseInt(locDato[4]));
				productos.add(producto);
			} catch (ArrayIndexOutOfBoundsException e){
				System.err.println("Error en el formato de inventario" + locLinea);
			}
		}
	}

	public void agregarVenta (Venta venta){
		ventas.add(venta);
	}

	public HashMap <String, Vendedor> getVendedores (){
		return vendedores;
	}

	public ArrayList <Producto> getProductos (){
		return productos;
	}

	public ArrayList <Venta> getVentas (){
		return ventas;
	}
}