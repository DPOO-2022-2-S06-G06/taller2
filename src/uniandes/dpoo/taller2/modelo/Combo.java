package uniandes.dpoo.taller2.modelo;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Producto

{
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
		
	
	private String nombreCombo;

	private double descuento;
	
	private List<ProductoMenu> itemsCombo;
	
	private int precio;
	
	public Combo(String pnombre, double pdescuento)
	{
		
		nombreCombo = pnombre;
		descuento = pdescuento;
		itemsCombo = new ArrayList<>();

	}
	
	public void agregarItemACombo(ProductoMenu itemCombo)
	{
		
		itemsCombo.add(itemCombo);
		precio += (int)(itemCombo.getPrecio()*((100-descuento)/100));

	}
	
	public String getNombre()
	{
		return nombreCombo;
	}
	
	public int getPrecio()
	{
		return precio;
	}
	
	public String generarTextoFactura()
	{
		String Factura = nombreCombo + " $ " +String.valueOf(precio);
		return Factura;
	}
	
	public String toString()
	{
		return nombreCombo + " (" + precio + ")";
	}


}
