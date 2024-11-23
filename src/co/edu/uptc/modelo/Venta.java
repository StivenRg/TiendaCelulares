package co.edu.uptc.modelo;

public class Venta{
	private String codigoVendedor;
	private String codigoProducto;
	private int    cantidad;

	public Venta (String paramCodigoVendedor, String paramCodigoProducto, int paramCantidad){
		codigoVendedor = paramCodigoVendedor.toUpperCase();
		codigoProducto = paramCodigoProducto.toUpperCase();
		cantidad       = paramCantidad;
	}

	public Venta (){
	}

	public String getCodigoProducto (){
		return codigoProducto;
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

	public int getCantidad (){
		return cantidad;
	}

	public void setCantidad (int paramCantidad){
		cantidad = paramCantidad;
	}
}