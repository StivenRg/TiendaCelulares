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
	private final  String            codigoVendedor;
	private static int               serial         = 1;
	private final  ArrayList <Venta> ventasVendedor = new ArrayList <>();

	public Vendedor (String paramNombre, long paramNumeroTelefono, long paramNumeroID, String paramTipoID, long paramNumeroCuenta, String paramTipoCuenta){
		nombre         = paramNombre.toUpperCase();
		numeroTelefono = paramNumeroTelefono;
		numeroID       = paramNumeroID;
		tipoID         = paramTipoID.toUpperCase();
		numeroCuenta   = paramNumeroCuenta;
		tipoCuenta     = paramTipoCuenta.toUpperCase();
		codigoVendedor = "VEN" + serial;
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
		return ventasVendedor;
	}

	public void agregarVenta (String paramCodigoProducto, int paramCantidad){
		Producto locProducto = obtenerProducto(paramCodigoProducto);
		if (locProducto == null) return;

		if (locProducto.getCantidad() <= paramCantidad){
			JOptionPane.showMessageDialog(null, "No queda stock suficiente para la venta.", "Stock insuficiente", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Venta venta = new Venta();
		venta.setCodigoVendedor(codigoVendedor);
		venta.setCodigoProducto(locProducto.getCodigo());
		venta.setCantidad(paramCantidad);
		ventasVendedor.add(venta);
		Inventario.descontarProducto(locProducto, paramCantidad);
		JOptionPane.showMessageDialog(null, "Venta registrada con exito.", "Venta registrada", JOptionPane.INFORMATION_MESSAGE);
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
		return codigoVendedor;
	}
}