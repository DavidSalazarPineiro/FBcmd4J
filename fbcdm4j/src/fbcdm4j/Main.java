package fbcdm4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Post;
import facebook4j.ResponseList;
import utils.Utils;

public class Main {
	static final Logger logger = LogManager.getLogger(Main.class);

	private static final String CONFIG_DIR = "config";
	private static final String CONFIG_FILE = "fbcmd4j.properties";

	public static void main(String[] args) 
	{
		logger.info("Iniciando app");
		Facebook fb =  null;
		Properties props = null;
		int opcion = 0;

		try 
		{
			props = Utils.loadConfigFile(CONFIG_DIR, CONFIG_FILE);
		} catch (IOException ex) 
		{
			logger.error(ex);
		}
	
		try 
		{
			Scanner scanner = new Scanner(System.in);
			while(true) 
			{
				fb = Utils.configFacebook(props);
				System.out.println("Facebook Console Client \n");
				System.out.println("(1) Obtener NewsFeed");
				System.out.println("(2) Obtener Wall");
				System.out.println("(3) Publicar nuevo estado");
				System.out.println("(4) Publicar link");
				System.out.println("(5) Log out");
				System.out.println("Select and option:");	
				
				try {
					opcion= scanner.nextInt();
					scanner.nextLine();
					switch (opcion) 
					{
					case 1:
						System.out.println(" |- NewsFeed  -|");
						ResponseList<Post> newsFeed = fb.getFeed();
						for (Post p : newsFeed) 
						{
							Utils.printPost(p);
						}
						SaveTxt("NewsFeed", newsFeed,scanner);
						break;
					case 2:
						System.out.println(" |- Show Wall -|");
						ResponseList<Post> wall = fb.getPosts();
						for (Post p : wall) 
						{
							Utils.printPost(p);
						}		
						SaveTxt("Wall", wall, scanner);
						break;
					case 3:
						System.out.println("Salir");
						System.exit(0);
						break;
					default:
						logger.error("Opción inválida");
						break;
					}
				} 
				catch (InputMismatchException e) 
				{
					System.out.println("Ocurrió un error, revisar log.");
					logger.error("Opción inválida. %s. \n", e.getClass());
				} 
				catch (FacebookException e) 
				{
					System.out.println("Ocurrió un error, revisar log.");
					logger.error(e.getErrorMessage());
				} 
				catch (Exception e) 
				{
					System.out.println("Ocurrió un error, revisar log.");
					logger.error(e);
				}
				System.out.println();
			}
		} 
		catch (Exception ex) {
			logger.error(ex);
		}
	}
	
	
	public static void SaveTxt(String fileName, ResponseList<Post> posts, Scanner scanner) 
	{
		System.out.println("¿Quieres guardar lo mostrado en un archivo txt?");
		String opcion= scanner.nextLine();
		if (opcion.contains("Si") || opcion.contains("si"))
		{
		List<Post> post = new ArrayList<>();
		int num = 0;
		while(num <= 0) 
		{
			try 
			{
			System.out.println("¿Cuantas lineas quieres guaradar?");
			num = Integer.parseInt(scanner.nextLine());					
			if(num <= 0) 
			{
				System.out.println("Ingrese un numero valido!!!!!");
			} 
			else 
			{
				for(int i = 0; i<num; i++)
				{
					if(i>posts.size()-1) break;
					post.add(posts.get(i));
					}
				}
			} 
			catch(NumberFormatException e) 
			{
				logger.error(e);
			}
		}
	Utils.Save_Post(fileName, post);
	}
  }
}