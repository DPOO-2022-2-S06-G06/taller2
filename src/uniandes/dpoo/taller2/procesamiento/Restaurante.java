package uniandes.dpoo.taller2.procesamiento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.Pedido;
import uniandes.dpoo.taller2.modelo.ProductoMenu;
import uniandes.dpoo.taller2.modelo.Combo;

public class Restaurante 

{
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	private static List<Ingrediente> ingredientes;
	
	private static List<ProductoMenu> menuBase;
	
	private static List<Combo> combos;
	
	private static  Map<String,Pedido> pedidos;
	
	private static Pedido pedidoEnCurso;
	// ************************************************************************
		// Metodos
		// ************************************************************************

	public  static List<ProductoMenu> getMenuBase()
	{
		return menuBase;
	}
	
	public static List<Ingrediente> getIngredientes()
	{
		return ingredientes;
	}
	
	public static List<Combo> getCombos()
	{
		return combos;
	}
	
	public static void cargarInformacionRestaurante(String archivo1,String archivo2,String archivo3) throws FileNotFoundException, IOException
	{
		ingredientes = cargarIngredientes(archivo1);
		menuBase = cargarMenu(archivo2);
		combos = cargarCombos(archivo3);
		pedidos = new HashMap<String, Pedido>();
	}
	
	private static List<Ingrediente> cargarIngredientes(String nombreArchivo) throws FileNotFoundException, IOException
	{
		List<Ingrediente> listaIngredientes = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		String linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(";");
			String nombreIngrediente = partes[0];
			int precioadicional = Integer.parseInt(partes[1]);
			
			Ingrediente elIngrediente = new Ingrediente(nombreIngrediente,precioadicional);
			listaIngredientes.add(elIngrediente);

			linea = br.readLine(); // Leer la siguiente línea
		}
		
		
		br.close();
		
		return listaIngredientes;
	}
	
	
	private static List<ProductoMenu> cargarMenu(String nombreArchivo) throws FileNotFoundException, IOException
	{
		List<ProductoMenu> listaMenu = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		String linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(";");
			String nombreMenu = partes[0];
			int costoBase = Integer.parseInt(partes[1]);
			
			ProductoMenu elMenu = new ProductoMenu(nombreMenu,costoBase);
			listaMenu.add(elMenu);

			linea = br.readLine(); // Leer la siguiente línea
		}
		
		
		br.close();
		
		return listaMenu;
	}
	
	private static List<Combo> cargarCombos(String nombreArchivo) throws FileNotFoundException, IOException
	{
		List<Combo> listaCombos = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		String linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(";");
			String nombreCombo = partes[0];
			int descuento = Integer.parseInt(partes[1].replace("%", ""));
			
			Combo elcombo = new Combo(nombreCombo,descuento);
			
			String producto1Menu = partes[2];
			String producto2Menu = partes[3];
			String producto3Menu = partes[4];
			
			List<ProductoMenu> menu = Restaurante.getMenuBase();
			
			for (ProductoMenu productoMenu : menu)
			{
				if (productoMenu.getNombre().equals(producto1Menu) |productoMenu.getNombre().equals(producto2Menu) |productoMenu.getNombre().equals(producto3Menu))
				{
					elcombo.agregarItemACombo(productoMenu);
				}
			}
			
			listaCombos.add(elcombo);
			linea = br.readLine(); // Leer la siguiente línea
		}
		
		
		br.close();
		
		return listaCombos;
	}
	
	public static void IniciarPedido(String nombreCliente,String direccionCliente)
	{
		Pedido pedidoOrden = new Pedido(nombreCliente,direccionCliente);
		pedidoEnCurso = pedidoOrden;
	}
	
	public static Pedido getPedidoEnCurso()
	{
		return pedidoEnCurso;
	}
	
	public static void cerrarYGuardarPedido() throws IOException
	{
		pedidoEnCurso.guardarFactura();
		pedidos.put(String.valueOf(pedidoEnCurso.getIdPedido()),pedidoEnCurso);
		pedidoEnCurso = null;
	}
	
	public static String ConsultarPedido(int idPedido) throws FileNotFoundException, IOException
	{
		
		BufferedReader br = new BufferedReader(new FileReader("./data/" + String.valueOf(idPedido) + ".txt"));
		String linea = br.readLine();
		String Factura = "";
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea
			Factura += linea + "\n";
			
			linea = br.readLine(); // Leer la siguiente línea
		}
		
		System.out.println("La informacion del pedido es: ");
		
		Pedido pedidoSolcitado = pedidos.get(String.valueOf(idPedido));
		String textoPedido = pedidoSolcitado.generarTextoFactura();
		System.out.println(textoPedido);
		
		
		br.close();
		
		return Factura;
	}
	
}
