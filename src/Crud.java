//This would be the main class where the CRUD is being created

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Crud 
{

    static ArrayList<Client> clients = new ArrayList<Client>();
    static Map<Integer, Double> moneyData = new HashMap<Integer, Double>() ;
    static Client currentClient; 
    static Scanner input = new Scanner(System.in);  

    public static void main(String[] args) throws Exception 
    {
       menu(); 
    }

    static void menu() 
    {
        int option = -1;
            
        do 
        {
            String currentC = currentClient == null ? "Ninguno" : currentClient.getName();
            System.out.println("\033[H\033[2J"); // This thing cleans the screen 
            System.out.println("Sistema de Administracion de Clientes                   Cliente Actual: " + currentC);
            System.out.println("1- Crear Cliente");
            System.out.println("2- Seleccionar Cliente");
            System.out.println("3- Insertar dinero");
            System.out.println("4- Actualizar dinero");
            System.out.println("5- Buscar Cliente por nombre y mostrar ahorro");
            System.out.println("6- Listar Clientes");
            System.out.println("0- Salir");
            try 
            {
                option = input.nextInt();
            } 
            catch ( Exception e) {
                System.out.println(e.getMessage());
            }
            switch (option) {
                case 1: createClient();  break;
                case 2: selectClient(); break;
                case 3: insertMoney(); break;
                case 4: updateMoney(); break;
                case 5: searchByName(); break;
                case 6: listClients(); break;
                case 0: option = 0;  break;
            }
        } while (option != 0);

        input.close();
    }

    static void createClient()
    {
        String name;
        int idCard;
        double incomeLevel;
        int userCreationDate;

        System.out.println("\033[H\033[2J"); // This thing cleans the screen 
        System.out.println("Creacion de Cliente:"); 
        System.out.println("Ingrese el nombre:");
        name = input.next();
        System.out.println("Ingrese el id:");
        idCard = input.nextInt();
        System.out.println("Ingrese el nivel de ingreso:");
        incomeLevel = input.nextDouble();
        System.out.println("Ingrese la fecha de creacion del ususario:");
        userCreationDate = input.nextInt();

        moneyData.put(idCard, 0.0);

        clients.add(new Client(name, idCard, incomeLevel, userCreationDate));
        if (clients.size() == 1) currentClient = clients.get(0);

    } 

    static void selectClient() 
    {
        int index;
        System.out.println("\033[H\033[2J"); // This thing cleans the screen 
        // the method listClients should show an enumerated list from 1 to the number of users that are in the system;
        listClients();
        System.out.println("Ingrese el numero de cliente que desea seleccionar");
        index = input.nextInt();
        currentClient = clients.get(index-1);
    }

    static void insertMoney() 
    {
        double moneyToAdd, moneySaved;
        moneySaved = moneyData.get(currentClient.getIdCard());

        System.out.println("\033[H\033[2J"); // This thing cleans the screen 
        System.out.println("Ingrese la cantidad que desea agregar a la cuenta");
        moneyToAdd = input.nextDouble();

        moneyData.put(currentClient.getIdCard(), moneySaved + moneyToAdd);
    }

    static void updateMoney() 
    {
        double money;

        System.out.println("\033[H\033[2J"); // This thing cleans the screen 
        System.out.println("Ingrese la cantidad que desea asignar a la cuenta");
        money = input.nextDouble();

        moneyData.put(currentClient.getIdCard(), money);

    }

    static void searchByName() {
        System.out.println("\033[H\033[2J"); // This thing cleans the screen 
        System.out.println("TODO"); 
    }

    static void listClients()
    { //TO DO
        System.out.println("\033[H\033[2J"); // This thing cleans the screen 
        System.out.println("TODO"); 
    }
}
