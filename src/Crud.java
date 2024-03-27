//This would be the main class where the CRUD is being created

import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;


public class Crud 
{

    static ArrayList<Client> clients = new ArrayList<Client>();
    static Map<String, Double> moneyData = new HashMap<String, Double>() ;
    static Client currentClient; 
    

    public static void main(String[] args) throws Exception 
    {
       Menu(); 
    }

    static void Menu() 
    {
        int option = -1;
        Scanner scanner = new Scanner(System.in);
            
        do 
        {
            String currentC = currentClient == null ? "Ninguno" : currentClient.getName();
            
            System.out.println("Sistema de Administracion de Clientes                   Cliente Actual: " + currentC);
            System.out.println("1- Crear Cliente");
            System.out.println("2- Seleccionar Cliente");
            System.out.println("3- Insertar dinero");
            System.out.println("4- Actualizar dinero");
            System.out.println("5- Buscar Cliente por nombre y mostrar ahorro");
            System.out.println("6- Listar Clientes");
            System.out.println("0- Salir");

            option = scanner.nextInt();

        } while (option != 0);

        scanner.close();
    }

    static void createClient()
    {
        Scanner input = new Scanner(System.in);
        String name;
        int idCard;
        double incomeLevel;
        int userCreationDate;

        System.out.println("Creacion de Cliente:"); 
        System.out.println("Ingrese el nombre:");
        name = input.next();
        System.out.println("Ingrese el id:");
        idCard = input.nextInt();
        System.out.println("Ingrese el nivel de ingreso:");
        incomeLevel = input.nextDouble();
        System.out.println("Ingrese la fecha de creacion del ususario:");
        userCreationDate = input.nextInt();

        clients.add(new Client(name, idCard, incomeLevel, userCreationDate));
        if (clients.size() == 1) currentClient = clients.get(0);

        input.close();
    }
}
