package co.edu.uptc.modelo;

import java.util.ArrayList;

public class Vendedor{
	private        String            nombre;
	private        long              numeroTelefono;
	private        long              numeroID;
	private        String            tipoID;
	private        long              numeroCuenta;
	private        String            tipoCuenta;
	private        String            codigoVendedor;
	private static int               serial         = 1;
	private        ArrayList <Venta> ventasVendedor = new ArrayList <>();

	public Vendedor (String paramNombre, long paramNumeroTelefono, long paramNumeroID, String paramTipoID, long paramNumeroCuenta, String paramTipoCuenta){
		this.nombre         = paramNombre.toUpperCase();
		this.numeroTelefono = paramNumeroTelefono;
		this.numeroID       = paramNumeroID;
		this.tipoID         = paramTipoID.toUpperCase();
		this.numeroCuenta   = paramNumeroCuenta;
		this.tipoCuenta     = paramTipoCuenta.toUpperCase();
		//Se genera el codigo del vendedor
		this.codigoVendedor = String.format("VEN%d", serial).toUpperCase();
		//Incrementa el número de vendedor
		serial++;
	}

	public String getNombre (){
		return nombre;
	}

	//Métodos setter y getter
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

	public String getCodigoVendedor (){
		return codigoVendedor;
	}

	public ArrayList <Venta> getVentasVendedor (){
		return ventasVendedor;
	}

	/// Metodo encargado de agregar una venta a un vendedor
	///
	/// @param paramCodigoProducto: Código del producto
	/// @param paramCantidad: Cantidad de producto
	public void agregarVenta (String paramCodigoProducto, int paramCantidad){
		Venta venta = new Venta(codigoVendedor, paramCodigoProducto, paramCantidad);
		ventasVendedor.add(venta);
	}
}