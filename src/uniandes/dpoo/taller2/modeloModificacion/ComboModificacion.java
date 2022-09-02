package uniandes.dpoo.taller2.modeloModificacion;

import java.util.ArrayList;
import java.util.List;

public class ComboModificacion implements Producto

{
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
		
	
	private String nombreCombo;

	private double descuento;
	
	private List<Producto> itemsCombo;
	
	private int precio;
	
	private int calorias;
	
	public ComboModificacion(String pnombre, double pdescuento)
	{
		
		nombreCombo = pnombre;
		descuento = pdescuento;
		itemsCombo = new ArrayList<>();

	}
	
	public void agregarItemACombo(Producto itemCombo)
	{
		
		itemsCombo.add(itemCombo);
		precio += (int)(itemCombo.getPrecio()*((100-descuento)/100));
		calorias += (int)(itemCombo.getCalorias());

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
		String Factura = nombreCombo + " $ " +String.valueOf(precio)+ " cal: " +String.valueOf(calorias);
		return Factura;
	}
	
	public String toString()
	{
		return nombreCombo + " (" + precio + ")" + " (" + calorias + ")";
	}

	@Override
	public int getCalorias() 
	{
		return calorias;
	}


}
