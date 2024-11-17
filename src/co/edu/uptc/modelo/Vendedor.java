package co.edu.uptc.modelo;

import co.edu.uptc.negocio.Inventario;

import javax.swing.*;
import java.util.ArrayList;

public class Vendedor{
	private        String            nombre;
	private        long              numeroTelefono;
	private        long              numeroID;
	private        String            tipoID;
	private        long              numeroCuenta;
	private        String            tipoCuenta;
	private final  String            CODIGO_VENDEDOR;
	private static int               serial          = 1;
	private final  ArrayList <Venta> VENTAS_VENDEDOR = new ArrayList <>();

	public Vendedor (String paramNombre, long paramNumeroTelefono, long paramNumeroID, String paramTipoID, long paramNumeroCuenta, String paramTipoCuenta){
		this.nombre          = paramNombre.toUpperCase();
		this.numeroTelefono  = paramNumeroTelefono;
		this.numeroID        = paramNumeroID;
		this.tipoID          = paramTipoID.toUpperCase();
		this.numeroCuenta    = paramNumeroCuenta;
		this.tipoCuenta      = paramTipoCuenta.toUpperCase();
		this.CODIGO_VENDEDOR = String.format("VEN%d", serial).toUpperCase();
		serial++;
	}

	public String getNombre (){
		return nombre;
	}

	public void setNombre (String paramNombre){
		nombre = paramNombre.toUpperCase();
	}

	public long getNumeroTelefono (){
		return numeroTelefono;
	}

	public void setNumeroTelefono (long paramNumeroTelefono){
		numeroTelefono = paramNumeroTelefono;
	}

	public long getNumeroID (){
		return numeroID;
	}

	public void setNumeroID (long paramNumeroID){
		numeroID = paramNumeroID;
	}

	public String getTipoID (){
		return tipoID;
	}

	public void setTipoID (String paramTipoID){
		tipoID = paramTipoID.toUpperCase();
	}

	public long getNumeroCuenta (){
		return numeroCuenta;
	}

	public void setNumeroCuenta (long paramNumeroCuenta){
		numeroCuenta = paramNumeroCuenta;
	}

	public String getTipoCuenta (){
		return tipoCuenta;
	}

	public void setTipoCuenta (String paramTipoCuenta){
		tipoCuenta = paramTipoCuenta.toUpperCase();
	}

	public ArrayList <Venta> getVentasVendedor (){
		return VENTAS_VENDEDOR;
	}

	public void agregarVenta (String paramCodigoProducto, int paramCantidad){
		Producto locProducto = obtenerProducto(paramCodigoProducto);
		if (locProducto == null) return;

		if (locProducto.getCantidad() <= paramCantidad){
			System.err.println("No queda stock suficiente para la venta: " + paramCodigoProducto + " (" + paramCantidad + ")");
			return;
		}

		Venta venta = new Venta();
		venta.setCodigoVendedor(CODIGO_VENDEDOR);
		venta.setCodigoProducto(locProducto.getCodigo());
		venta.setCantidad(paramCantidad);
		VENTAS_VENDEDOR.add(venta);
		Inventario.descontarProducto(locProducto, paramCantidad);
	}

	private static Producto obtenerProducto (String paramCodigoProducto){
		for (Producto locProducto : Inventario.getProductos()){
			if (locProducto.getCodigo().equals(paramCodigoProducto)){
				return locProducto;
			}
		}
		JOptionPane.showMessageDialog(null, "Producto no encontrado.", "Producto no encontrado", JOptionPane.INFORMATION_MESSAGE);
		return null;
	}

	public String getCodigoVendedor (){
		return CODIGO_VENDEDOR;
	}

	public double getTotalComision (){
		double totalComision = 0;
		for (Venta locVenta : VENTAS_VENDEDOR){
			totalComision += locVenta.getProducto().getPrecio() * locVenta.getCantidad() * 0.05;
		}
		return totalComision;
	}

	public int getCantidadCelularesVendidos (){
		int cantidadCelularesVendidos = 0;
		for (Venta locVenta : VENTAS_VENDEDOR){
			cantidadCelularesVendidos += locVenta.getCantidad();
		}
		return cantidadCelularesVendidos;
	}
}