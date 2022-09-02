package uniandes.dpoo.taller2.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pedido 

{
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	private static int numeroPedidos;
	
	private  int idPedido;
	
	private  String NombreCliente;
	
	private  String DireccionCliente;
	
	private List<Producto> itemsPedido;
	
	// ************************************************************************
	// Metodos
	// ************************************************************************
	
	public Pedido(String pnombreCliente, String pdireccionCliente)
	
	{
		NombreCliente = pnombreCliente;
		DireccionCliente = pdireccionCliente;
		itemsPedido = new ArrayList<>();
		idPedido = (int)(Math. random()*100+1); 
		numeroPedidos += 1;
	}
	
	public int getIdPedido()
	{
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem)
	{
		itemsPedido.add(nuevoItem);
	}
	
	private int getPrecioNetoPedido()
	{
		int PrecioNeto = 0;
		for (Producto item : itemsPedido)
		{
			int precio = item.getPrecio();
			PrecioNeto += precio;
		}
		
		return PrecioNeto;
	}
	
	private int getPrecioIvaPedido()
	{
		
		int PrecioNeto = getPrecioNetoPedido();
		int PrecioIva = (int)(PrecioNeto*(0.19));
		
		return PrecioIva;
	}
	
	private int getPrecioTotalPedido()
	{
		int PrecioTotal = 0;
		int PrecioNeto = getPrecioNetoPedido();
		int PrecioIva = getPrecioIvaPedido();
		PrecioTotal = PrecioNeto+PrecioIva;
		
		return PrecioTotal;
	}
	
	public String generarTextoFactura()
	{
		String Factura = "Factura: ";
		
		Factura += String.valueOf(idPedido) + "\n";
		
		Factura += "Nombre: " + NombreCliente + ": " + "\n";
		
		Factura += "Direccion: " + DireccionCliente + ": " + "\n";
		
		Factura += "Numero de pedido: " + String.valueOf(numeroPedidos) + ": " + "\n";
		
		for (Producto item : itemsPedido)
		{
			Factura += item.generarTextoFactura() + "\n";
		}
		
		int PrecioNeto = getPrecioNetoPedido();
		Factura += "Precio Neto: " + String.valueOf(PrecioNeto) + "\n";
		
		int  PrecioIVA = getPrecioIvaPedido();
		Factura += "Precio IVA: " + String.valueOf(PrecioIVA) + "\n";
		
		int PrecioTotal = getPrecioTotalPedido();
		Factura += "Precio Total: " + String.valueOf(PrecioTotal) + "\n";
		
		return Factura;
	}
	
	public  void guardarFactura() throws IOException
	{
		
		String Factura = generarTextoFactura();

		File file = new File("./data/" + String.valueOf(idPedido) + ".txt"); 
		if(file.createNewFile()) System.out.println("File created: " + file.getName()); 
		else System.out.println("File already exists."); 
		BufferedWriter writer = new BufferedWriter(new FileWriter("./data/" + String.valueOf(idPedido) + ".txt"));
		
		writer.write(Factura);
		writer.close();
	}
	
}
