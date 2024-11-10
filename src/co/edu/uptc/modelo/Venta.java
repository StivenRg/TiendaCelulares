package co.edu.uptc.modelo;

public class Venta{
	private Producto producto;
	private int      cantidad;

	public Venta (){
	}

	public void setProducto (Producto paramProducto){
		producto = paramProducto;
	}

	public Producto getProducto (){
		return producto;
	}

	public void setCantidad (int paramCantidad){
		cantidad = paramCantidad;
	}

	public int getCantidad (){
		return cantidad;
	}

	public String getCodigoProducto (){
		return producto.getCodigo();
	}
}