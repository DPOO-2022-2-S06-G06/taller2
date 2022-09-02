package uniandes.dpoo.taller2.modeloModificacion;

public class Ingrediente

{
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	private String nombre;

	private int CostoAdicional;
	
	private int calorias;
	
	public Ingrediente(String pnombre, int pCostoAdicional, int pCalorias)
	{
		this.nombre = pnombre;
		this.CostoAdicional = pCostoAdicional;
		calorias = pCalorias;
		
	// ************************************************************************
	// Metodos
	// ************************************************************************

	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public int getCostoAdicional()
	{
		return CostoAdicional;
	}
	
	public int getCalorias()
	{
		return calorias;
	}
	
}
