package co.edu.uptc.modelo;

import java.util.ArrayList;

public class Venta{
	private        String            codigoVendedor;
	private        String            codigoProducto;
	private        short             cantidad;

	public Venta (String paramCodigoVendedor, String paramCodigoProducto, short paramCantidad){
		codigoVendedor = paramCodigoVendedor;
		codigoProducto = paramCodigoProducto;
		cantidad       = paramCantidad;
	}

	public Venta (){
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

	public void setCodigoProducto (String paramCodigoProducto){
		codigoProducto = paramCodigoProducto.toUpperCase();
	}

	public short getCantidad (){
		return cantidad;
	}

	public void setCantidad (short paramCantidad){
		cantidad = paramCantidad;
	}
}