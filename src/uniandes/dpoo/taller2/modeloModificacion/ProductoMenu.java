package uniandes.dpoo.taller2.modeloModificacion;

public class ProductoMenu implements Producto

{
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	private String nombre;

	private int precioBase;
	
	private int calorias;
	
	public ProductoMenu(String pnombre, int pprecioBase, int pCalorias)
	{
		nombre = pnombre;
		precioBase = pprecioBase;
		calorias = pCalorias;

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
		String Factura = nombre + " $ " + String.valueOf(precioBase) + " cal: " + String.valueOf(calorias);
		return Factura;
	}
	
	@Override
	public String toString()
	{
		return nombre + " (" + precioBase + ")";
	}

	@Override
	public int getCalorias() 
	{
		
		return calorias;
	}
	
}
