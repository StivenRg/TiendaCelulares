package co.edu.uptc.modelo;

public class Vendedor{
	private String nombre;
	private long   numeroTelefono;
	private long   numeroID;
	private String tipoID;
	private long   numeroCuenta;
	private String tipoCuenta;

	public Vendedor (String nombre, long numeroTelefono, long numeroID, String tipoID, long numeroCuenta, String tipoCuenta){
		this.nombre         = nombre;
		this.numeroTelefono = numeroTelefono;
		this.numeroID       = numeroID;
		this.tipoID         = tipoID;
		this.numeroCuenta   = numeroCuenta;
		this.tipoCuenta     = tipoCuenta;
	}

	public Vendedor (){
	}

	public String nombre (){
		return nombre;
	}

	public void setNombre (String paramNombre){
		nombre = paramNombre;
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
		tipoID = paramTipoID;
	}

	public long setNumeroCuenta (){
		return numeroCuenta;
	}

	public void setNumeroCuenta (long paramNumeroCuenta){
		numeroCuenta = paramNumeroCuenta;
	}

	public String getTipoCuenta (){
		return tipoCuenta;
	}

	public void setTipoCuenta (String paramTipoCuenta){
		tipoCuenta = paramTipoCuenta;
	}
}
