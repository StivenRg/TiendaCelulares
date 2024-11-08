package co.edu.uptc.negocio;

public class Producto{
	private String marca;
	private String linea;
	private String codigo;
	private double precio;
	private int    cantidad;

	public Producto (String marca, String linea, String codigo, double precio, int cantidad){
		this.marca    = marca;
		this.linea    = linea;
		this.codigo   = codigo;
		this.precio   = precio;
		this.cantidad = cantidad;
	}

	public Producto (){
	}

	public String getMmarca (){
		return marca;
	}

	public void setMarca (String paramMarca){
		marca = paramMarca;
	}

	public String getLinea (){
		return linea;
	}

	public void setLinea (String paramLinea){
		linea = paramLinea;
	}

	public String getCodigo (){
		return codigo;
	}

	public void setCodigo (String paramCodigo){
		codigo = paramCodigo;
	}

	public double getPrecio (){
		return precio;
	}

	public void setPrecio (double paramPrecio){
		precio = paramPrecio;
	}

	public int getCantidad (){
		return cantidad;
	}

	public void agregarCantidad (int paramCantidad){
		cantidad += paramCantidad;
	}
}
