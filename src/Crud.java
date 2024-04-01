//* Importacion de librearias

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Crud 
{

    static ArrayList<Client> clients = new ArrayList<Client>();
    static Map<String, Double> moneyData = new HashMap<String, Double>() ;
    static Client currentClient; 
    static Scanner input = new Scanner(System.in);  

    //!Funcion Main -------------------------------------------------------------------------------
    public static void main(String[] args) throws Exception 
    {
        menu(); //? Se llama a la funcion menu
    }

    //!Funcion que despliega el menu en consola ---------------------------------------------------
    static void menu() 
    {
        int option = -1;
            
        do 
        { //?ESTA ES LA INTERFAZ DEL CRUD
            String currentC = currentClient == null ? "Ninguno" : currentClient.getName();
            System.out.println("\033[H\033[2J"); //?This thing cleans the screen 
            System.out.println("Sistema de Administracion de Clientes                   Cliente Actual: " + currentC + "\n");
            System.out.println("1- Insertar Dinero");
            System.out.println("2- Actualizar Dinero");
            System.out.println("3- Eliminar Dinero");
            System.out.println("4- Buscar Cliente por nombre y mostrar ahorro");
            System.out.println("5- Listar Clientes \n");
            System.out.println(" O sino, tambien podrias: \n");
            System.out.println("6- Crear Cliente");
            System.out.println("7- Seleccionar Cliente");
            System.out.println("0- Salir");

            System.out.println("Elige una opcion: "); 
            option = input.nextInt();
            
            switch (option) //?Llama una funcion segun la opcion seleccionada en la interfaz
            {
                case 0: option = 0;  break;
                case 1: insertMoney(); break;
                case 2: updateMoney(); break;
                case 3: deleteMoney(); break;
                case 4: searchByName(); break;
                case 5: listClients(); break;
                case 6: createClient();  break;
                case 7: selectClient(); break;
            }
        } while (option != 0);

        input.close(); //?Se cierra la variable input de tipo Scanner
    }

    //!Funcion que borra el dinero-----------------------------------------------------------------
    static void deleteMoney()
    {
        double moneyToRemove, moneySaved;
        moneySaved = moneyData.get(currentClient.getIdCard());

        System.out.println("\033[H\033[2J");
        System.out.println("Ingrese la cantidad que desea eliminar de la cuenta");
        moneyToRemove = input.nextDouble();

        if(moneyToRemove <= moneySaved)
        {
            moneyData.put(currentClient.getIdCard(), moneySaved - moneyToRemove);
            System.out.println("El dinero ha sido eliminado de la cuenta");
        }
        else
        {
            System.out.println("La cantidad de dinero que se desea eliminar excede la cantidad de dinero actual");
        }
        System.out.println("\n Ingrese cualquier cosa y oprima enter para regresar");
        input.next(); //?makes a pause to show client data
    }

    //!Funcion que permite crear un cliente--------------------------------------------------------
    static void createClient()
    {
        String name;
        String idCard;
        double incomeLevel;
        String userCreationDate;

        System.out.println("\033[H\033[2J"); //?This thing cleans the screen 
        System.out.println("Creacion de Cliente:"); 

        System.out.println("Ingrese el nombre:");
        input.nextLine(); //?This is to clean the line jump from the buffer
        name = input.nextLine();

        System.out.println("Ingrese el id:");
        idCard = input.next();

        System.out.println("Ingrese el nivel de ingreso:");
        incomeLevel = input.nextDouble();

        System.out.println("Ingrese la fecha de creacion del ususario:");
        userCreationDate = input.next();

        moneyData.put(idCard, 0.0);

        clients.add(new Client(name, idCard, incomeLevel, userCreationDate));
        if (clients.size() == 1) currentClient = clients.get(0);
    } 

    //!Funcion que permite seleccionar un cliente--------------------------------------------------
    static void selectClient() 
    {
        int index;
        System.out.println("\033[H\033[2J"); //?This thing cleans the screen 
        
        //*the method listClients should show an enumerated list from 1 to the number of users that are in the system
        listClients();
        System.out.println("Ingrese el numero de cliente que desea seleccionar");
        index = input.nextInt();
        currentClient = clients.get(index-1);
    }

    //!Funcion que inserta dinero------------------------------------------------------------------
    static void insertMoney() 
    {
        double moneyToAdd, moneySaved;
        moneySaved = moneyData.get(currentClient.getIdCard());

        System.out.println("\033[H\033[2J"); //?This thing cleans the screen 
        System.out.println("Ingrese la cantidad que desea agregar a la cuenta");
        moneyToAdd = input.nextDouble();
        moneyData.put(currentClient.getIdCard(), moneySaved + moneyToAdd);
    }

    //!Funcion que actualiza el dinero-------------------------------------------------------------
    static void updateMoney() 
    {
        double money;

        System.out.println("\033[H\033[2J"); //?This thing cleans the screen 
        System.out.println("Ingrese la cantidad que desea asignar a la cuenta");
        money = input.nextDouble();
        moneyData.put(currentClient.getIdCard(), money);
    }

    //!Funcion que imprime cliente-----------------------------------------------------------------
    //?Esta funcion se usa en listar cliente para mostrar los clientes que hay
    static void printClient(Client pClient)
    {
        System.out.println("NOMBRE : " + pClient.getName());
        System.out.println("ID : " + pClient.getIdCard());
        System.out.println("FECHA CREACIÓN : " + pClient.getUserCreationDate() + "\n");
    }

    //!Funcion que permite buscar un cliente por su nomrbe-----------------------------------------
    static void searchByName() 
    {
        String lookingFor;
        boolean foundClient = false;

        System.out.println("\033[H\033[2J"); //?This thing cleans the screen 
        System.out.println("Ingresa un nombre de cliente valido para mostrar sus datos"); 
        lookingFor = input.next();

        for(int i = 0; i <= ((clients.size())-1 ); i++)//?for cicle ---> iterate all indexes
        {
            if((clients.get(i)).getName().equals(lookingFor)) System.out.println(
                (clients.get(i)).getName() + " AHORRO TOTAL :     " + 
                moneyData.get(clients.get(i).getIdCard()) ); //*printClient(clients.get(i));
                
            if((clients.get(i)).getName().equals(lookingFor))foundClient = true;
        }
        if(foundClient == false) System.out.println("no hay naides que se llame asi");
        System.out.println("\n Ingrese cualquier cosa y oprima enter para regresar");
        input.next(); //?makes a pause to show client data
    }

    //!Funcion que lista los clientes--------------------------------------------------------------
    static void listClients()
    {
        System.out.println("\033[H\033[2J"); //?This thing cleans the screen 

        for(int i = 0; i <= ((clients.size())-1 ); i++)//?for cicle ---> iterate all indexes
        {
            System.out.println(
                "----------------" + " Cliente numero " + Integer.toString(i+1) + " ----------------"); 
            printClient(clients.get(i)); 
        }
        System.out.println("\n Ingrese cualquier cosa y oprima enter para regresar");
        input.next();
    }
}
