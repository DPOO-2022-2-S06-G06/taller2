package uniandes.dpoo.taller2.modelo;

public class ProductoMenu implements Producto

{
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	private String nombre;

	private int precioBase;
	
	public ProductoMenu(String pnombre, int pprecioBase)
	{
		nombre = pnombre;
		precioBase = pprecioBase;

	}
	
	// ************************************************************************
	// Metodos
	// ************************************************************************
	
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
		String Factura = nombre + " $ " + String.valueOf(precioBase);
		return Factura;
	}
	
	@Override
	public String toString()
	{
		return nombre + " (" + precioBase + ")";
	}
	
}
