package uniandes.dpoo.taller2.consolaModificacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import uniandes.dpoo.taller2.modeloModificacion.Bebida;
import uniandes.dpoo.taller2.modeloModificacion.ComboModificacion;
import uniandes.dpoo.taller2.modeloModificacion.Ingrediente;
import uniandes.dpoo.taller2.modeloModificacion.Pedido;
import uniandes.dpoo.taller2.modeloModificacion.Producto;
import uniandes.dpoo.taller2.modeloModificacion.ProductoAjustado;
import uniandes.dpoo.taller2.modeloModificacion.ProductoMenu;
import uniandes.dpoo.taller2.procesamientoModificacion.RestauranteModificacion;

public class AplicacionMoificacion 

{
	
	
	public void ejecutarAplicacion()
	{
		
		try {
			ejecutarCargarInformacion();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Estadísticas sobre los Juegos Olímpicos\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					try {
						ejecutarMostarMenu();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if (opcion_seleccionada == 2)
					try {
						ejecutarInicarPedido();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if (opcion_seleccionada == 3)
					try {
						ejecutaragregarProducto();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if (opcion_seleccionada == 4)
					try {
						ejecutarCompararPedidos();
						ejecutarcerrarYGuardar();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if (opcion_seleccionada == 5)
					try {
						ejecutarConsultarPedido();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar menu");
		System.out.println("2. Iniciar pedido");
		System.out.println("3. Agregar elemento al pedido");
		System.out.println("4. Cerrar  un  pedido  y  guardar  la  factura");
		System.out.println("5. Consultar  la  informaci�n  de  un  pedido  dado  su  id");
		System.out.println("6. Cerra programa");

	}
	
	private void ejecutarCargarInformacion() throws FileNotFoundException, IOException
	{
		
		System.out.println("\n" + "Cargar un archivo" + "\n");
		String archivoIngredientes = "./data/ingredientesModifiacion.txt";
		String archivoMenu = "./data/menuModificacion.txt";
		String archivoCombos = "./data/combos.txt";
		String archivoBebidas = "./data/Bebidas.txt";
		RestauranteModificacion.cargarInformacionRestaurante(archivoIngredientes,archivoMenu,archivoCombos,archivoBebidas);
	}
	
	private void ejecutarMostarMenu() throws FileNotFoundException, IOException
	{
		System.out.println("\n" + "El menu es el siguiente:" + "\n");
		List<ProductoMenu> menu = RestauranteModificacion.getMenuBase();
		
		int opciones = 0;
		
		for (ProductoMenu productoMenu : menu)
		{
			String textoMenu = String.valueOf(opciones) + " " + productoMenu.generarTextoFactura() + "\n";
			System.out.println(textoMenu);
			opciones += 1;
		}
		
		System.out.println("\n" + "Las opciones de bebida son las siguientes:" + "\n");
		List<Bebida> bebidasMostrar = RestauranteModificacion.getBebidas();
		
		int opcionesBebida = 0;
		
		for (Bebida bebida : bebidasMostrar)
		{
			String textoBebida	 = String.valueOf(opcionesBebida) + " " + bebida.getNombre() + String.valueOf(bebida.getPrecio()) + "\n";
			System.out.println(textoBebida);
			opcionesBebida += 1;
		}
	}
	
	private void ejecutarMostarCombos() throws FileNotFoundException, IOException
	{
		System.out.println("\n" + "El menu es el siguiente:" + "\n");
		List<ComboModificacion> combos = RestauranteModificacion.getCombos();
		
		int opciones = 0;
		
		for (ComboModificacion combo : combos)
		{
			String textoCombo = String.valueOf(opciones) + " " + combo.generarTextoFactura() + "\n";
			System.out.println(textoCombo);
			opciones += 1;
		}
	}
	
	private void ejecutarMostarIngredientes() throws FileNotFoundException, IOException
	{
		System.out.println("\n" + "El menu es el siguiente:" + "\n");
		List<Ingrediente> ingredientes = RestauranteModificacion.getIngredientes();
		
		int opciones = 0;
		
		for (Ingrediente ingrediente : ingredientes)
		{
			String textoIngrediente = String.valueOf(opciones) + " " + ingrediente.getNombre() + String.valueOf(ingrediente.getCostoAdicional()) + "\n";
			System.out.println(textoIngrediente);
			opciones += 1;
		}
	}
	
	private void ejecutarInicarPedido() throws FileNotFoundException, IOException
	{
		System.out.println("\n" + "El pedido esta inciando:" + "\n");
		String nombreCliente = input("Por favor ingrese su nombre");
		String direccionCliente = input("Por favor ingrese su direccion");
		RestauranteModificacion.IniciarPedido(nombreCliente,direccionCliente);
		System.out.println("\n" + "Pedido en curso" + "\n");
	}
	
	private void ejecutaragregarProducto() throws FileNotFoundException, IOException
	{
		if(RestauranteModificacion.getPedidoEnCurso()!=(null)) 
		{
		System.out.println("\n" + "Ahora vas a armar el pedido:" + "\n");
		Pedido pedidoEnCurso= RestauranteModificacion.getPedidoEnCurso();
		System.out.println("\n" + "Aqui tienes nuestros combos por si te intersan:" + "\n");
		List<ComboModificacion> combos = RestauranteModificacion.getCombos();
		List<ProductoMenu> menu = RestauranteModificacion.getMenuBase();
		ejecutarMostarCombos();
		int opcionTipo = Integer.parseInt(input("Te intersa un combo o un producto del menu, marca 0 o 1 respectivamente, o si quieres una bebida marca 2"));
		if (opcionTipo == 0)
		{
		 int opcionCombo = Integer.parseInt(input("Porfavor seleccione una opcion de combo"));
		 Producto comboSeleccionado = combos.get(opcionCombo);
		 pedidoEnCurso.agregarProducto(comboSeleccionado);
		}
		else if (opcionTipo == 1)
		{
		 int opcionMenu = Integer.parseInt(input("Porfavor seleccione una opcion de menu"));
		 int opcionAgregar = Integer.parseInt(input("Deseas agregar o quitar algo de este producto del menu, marca 0 (Si) o marca 1 (No)"));
		 if (opcionAgregar == 1)
			{
			 Producto menuSeleccionado = menu.get(opcionMenu);
			 pedidoEnCurso.agregarProducto(menuSeleccionado);
			}
		 else if (opcionAgregar == 0)
		 	{
			 ProductoMenu menuSeleccionado = menu.get(opcionMenu);
			 ProductoAjustado productoAgregoQuito = new ProductoAjustado(menuSeleccionado);
			 ejecutarMostarIngredientes();
			 List<Ingrediente> ingredientes = RestauranteModificacion.getIngredientes();
			 boolean continuar = true;
					 while (continuar)
					 {
						 int opcionquitaragregar = Integer.parseInt(input("Deseas agregar o quitar un ingrediente, marca 0 (agregar), marca 1 (quitar)"));
						 int opcionIngrediente = Integer.parseInt(input("Porfavor seleccione una opcion de los ingredientes"));
						 Ingrediente ingredienteAgregadoQuitado = ingredientes.get(opcionIngrediente);
						 if (opcionquitaragregar == 0)
						 {
							 productoAgregoQuito.agregarIngrediente(ingredienteAgregadoQuitado);
						 }
						 else if (opcionquitaragregar == 1)
						 {
							 productoAgregoQuito.quitarIngrediente(ingredienteAgregadoQuitado);
						 }
						 int opcionfianlizar = Integer.parseInt(input("Deseas agregar o quitar mas ingredientes al producto, marca 0 (Si), marca 1 (No)"));
						 if (opcionfianlizar == 1)
						 {
							 continuar = false;
						
						 }

					 }
					 
			pedidoEnCurso.agregarProducto(productoAgregoQuito);
		 	}
		}
		List<Bebida> bebidas = RestauranteModificacion.getBebidas();
		if (opcionTipo == 2)
		 {
			 int opcionBebida = Integer.parseInt(input("Porfavor seleccione una opcion de las bebidas"));
			 Bebida bebidaSeleccionada = bebidas.get(opcionBebida);
			 pedidoEnCurso.agregarProducto(bebidaSeleccionada);
		 }
		}
		
		if(RestauranteModificacion.getPedidoEnCurso()==(null)) 
		{
			System.out.println("\n" + "Porfavor debe inicar un pedido nuevo antes de elegir los productos" + "\n");
		}
	}
	
	private void ejecutarcerrarYGuardar() throws FileNotFoundException, IOException
	{
		
		System.out.println("\n" + "La factura quedo de la siguiente manera" + "\n");
		Pedido pedidoEnCurso= RestauranteModificacion.getPedidoEnCurso();
		String Factura = pedidoEnCurso.generarTextoFactura();
		System.out.println(Factura);
		System.out.println("\n" + "Pedido guardado y factura generada" + "\n");
		RestauranteModificacion.cerrarYGuardarPedido();
	}
	
	private void ejecutarConsultarPedido() throws FileNotFoundException, IOException
	{
		int idPedido = Integer.parseInt(input("Porfavor escriba el Id del pedido que quiere buscar"));
		String infoPedido = RestauranteModificacion.ConsultarPedido(idPedido);
		System.out.println("\n" + "La factura del pedido es la siguiente" + "\n");
		System.out.println(infoPedido);
	}
	
	private void ejecutarCompararPedidos() throws FileNotFoundException, IOException
	{
		RestauranteModificacion.comprarPedidos();
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		AplicacionMoificacion consola = new AplicacionMoificacion();
		consola.ejecutarAplicacion();
	}
	
}