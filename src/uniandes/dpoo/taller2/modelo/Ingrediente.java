package uniandes.dpoo.taller2.modelo;

public class Ingrediente

{
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	private String nombre;

	private int CostoAdicional;
	
	public Ingrediente(String pnombre, int pCostoAdicional)
	{
		this.nombre = pnombre;
		this.CostoAdicional = pCostoAdicional;
		
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
	
}
