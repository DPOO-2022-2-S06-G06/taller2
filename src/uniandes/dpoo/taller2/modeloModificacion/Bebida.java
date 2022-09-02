package uniandes.dpoo.taller2.modeloModificacion;

public class Bebida implements Producto

{
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
		
	private String nombre;

	private int precioBase;
	
	private int calorias;

	public Bebida(String pnombre, int pprecioBase,int pcalorias)
	{
		nombre = pnombre;
		precioBase = pprecioBase;
		calorias = pcalorias;
	}
	
	public String getNombre() 
	{
		return nombre;
	}

	public int getPrecio() 
	{
		return precioBase;
	}

	public String generarTextoFactura() 
	{
		String Factura = nombre + " $ " + String.valueOf(precioBase) + " cal: " + String.valueOf(calorias);
		return Factura;
	}

	@Override
	public int getCalorias() 
	{
		return calorias;
	}

}
