package co.edu.uptc.modelo;

import co.edu.uptc.negocio.Inventario;

public class Venta{
	private String codigoProducto;
	private String codigoVendedor;
	private int    cantidad;

	public Venta (){
	}

	public void setCodigoProducto (String paramCodigoProducto){
		codigoProducto = paramCodigoProducto.toUpperCase();
	}

	public String getCodigoVendedor (){
		return codigoVendedor;
	}

	public void setCodigoVendedor (String paramCodigoVendedor){
		codigoVendedor = paramCodigoVendedor.toUpperCase();
	}

	public String getCodigoProducto (){
		return codigoProducto;
	}

	public void setCantidad (int paramCantidad){
		cantidad = paramCantidad;
	}

	public int getCantidad (){
		return cantidad;
	}

	public Producto getProducto (){
		for (Producto locProducto : Inventario.getProductos()){
			if (locProducto.getCodigo().equals(codigoProducto)){
				return locProducto;
			}
		}
		return null;
	}
}