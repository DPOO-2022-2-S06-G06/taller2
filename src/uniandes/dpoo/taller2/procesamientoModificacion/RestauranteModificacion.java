package uniandes.dpoo.taller2.procesamientoModificacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.taller2.modeloModificacion.Ingrediente;
import uniandes.dpoo.taller2.modeloModificacion.Pedido;
import uniandes.dpoo.taller2.modeloModificacion.ProductoMenu;
import uniandes.dpoo.taller2.modeloModificacion.Bebida;
import uniandes.dpoo.taller2.modeloModificacion.ComboModificacion;

public class RestauranteModificacion 

{
	
	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	private static List<Ingrediente> ingredientes;
	
	private static List<ProductoMenu> menuBase;
	
	private static List<ComboModificacion> combos;
	
	private static  List<Bebida> bebidas;
	
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
	
	public static List<ComboModificacion> getCombos()
	{
		return combos;
	}
	
	public static List<Bebida> getBebidas()
	{
		return bebidas;
	}
	
	public static void cargarInformacionRestaurante(String archivo1,String archivo2,String archivo3,String archivo4) throws FileNotFoundException, IOException
	{
		ingredientes = cargarIngredientes(archivo1);
		menuBase = cargarMenu(archivo2);
		bebidas = cargarBebidas(archivo4);
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
			int pcaloriasIngredientes = Integer.parseInt(partes[2]);
			
			Ingrediente elIngrediente = new Ingrediente(nombreIngrediente,precioadicional,pcaloriasIngredientes);
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
			int pcaloriasMenuProducto = Integer.parseInt(partes[2]);
			
			ProductoMenu elMenu = new ProductoMenu(nombreMenu,costoBase,pcaloriasMenuProducto);
			listaMenu.add(elMenu);

			linea = br.readLine(); // Leer la siguiente línea
		}
		
		
		br.close();
		
		return listaMenu;
	}
	
	private static List<ComboModificacion> cargarCombos(String nombreArchivo) throws FileNotFoundException, IOException
	{
		List<ComboModificacion> listaCombos = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		String linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(";");
			String nombreCombo = partes[0];
			int descuento = Integer.parseInt(partes[1].replace("%", ""));
			
			ComboModificacion elcombomodificado = new ComboModificacion(nombreCombo,descuento);
			
			String producto1Menu = partes[2];
			String producto2Menu = partes[3];
			String producto3Menu = partes[4];
			
			List<ProductoMenu> menu = RestauranteModificacion.getMenuBase();
			List<Bebida> bebidasLista = RestauranteModificacion.getBebidas();
			
			for (ProductoMenu productoMenu : menu)
			{
				if (productoMenu.getNombre().equals(producto1Menu) |productoMenu.getNombre().equals(producto2Menu))
				{
					elcombomodificado.agregarItemACombo(productoMenu);
				}
			}
			
			for (Bebida bebida : bebidasLista)
			{
				if (bebida.getNombre().equals(producto3Menu))
				{
					elcombomodificado.agregarItemACombo(bebida);
				}
			}
			
			listaCombos.add(elcombomodificado);
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
	
	private static List<Bebida> cargarBebidas(String nombreArchivo) throws FileNotFoundException, IOException
	{
		List<Bebida> listabebidas = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		String linea = br.readLine();
		while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
		{
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(";");
			String nombreBebida = partes[0];
			int precioBebida = Integer.parseInt(partes[1].replace("%", ""));
			int pcaloriasBebida = Integer.parseInt(partes[2]);
			
			Bebida bebida = new Bebida(nombreBebida,precioBebida,pcaloriasBebida);
			
			listabebidas.add(bebida);
			linea = br.readLine(); // Leer la siguiente línea
		}
		
		
		br.close();
		
		return listabebidas;
	}
	
	public static void comprarPedidos() throws FileNotFoundException, IOException
	{
		for (Map.Entry<String, Pedido> pedido : pedidos.entrySet()) 
		{
		boolean yaExiste = pedidoEnCurso.equals(pedido.getValue());
		if (yaExiste == true)
			{
			System.out.println("------------------Este pedido ya fue realizado por alguien mas en este restaurante------------------");
			}
		}
	}
	
}
