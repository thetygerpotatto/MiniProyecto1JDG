
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
            if (currentClient != null) {
                System.out.println("1- Insertar Dinero");
                System.out.println("2- Actualizar Dinero");
                System.out.println("3- Eliminar Dinero");
                System.out.println("4- Buscar Cliente por nombre y mostrar ahorro");
                System.out.println("5- Listar Clientes \n");
                System.out.println(" O sino, tambien podrias: \n");
            }

            System.out.println("6- Crear Cliente");
            System.out.println("7- Seleccionar Cliente");
            System.out.println("8- Solicitar Prestamo");
            System.out.println("9- Pedir un cdt");
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
                case 8: makeLoan(); break;
                case 9: askForCDT(); break;
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
            System.out.println(
            "La cantidad de dinero que se desea eliminar excede la cantidad de dinero actual");
        }
        System.out.println("\n Ingrese cualquier mensaje y oprima enter para continuar");
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
        
        //*the method listClients should show an enumerated list from 1 to the number of users that 
        //*are in the system
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
        System.out.println("\n Ingrese cualquier mensaje y oprima enter para continuar");
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
        System.out.println("\n Ingrese cualquier mensaje y oprima enter para continuar");
        input.next();
    }


    /*
    Uncliente puede pedir dinero prestado. Si pide menos o igual de lo que tiene ahorrado, lo puede hacer y 
    simplemente se le dice cuánto dinero le quedó en su cuenta después del préstamo. En caso de que quiera pedir
    prestado más de lo que tiene ahorrado, lo puede hacer pero solo hasta el doble de lo que tenga ahorrado 
    (si tengo 10000 ahorrado, puedo pedir prestado máximo 20000). En el último caso, se le presta a la persona
    en 6 cuotas mensuales, y teniendo en cuenta un interés compuesto del 2% efectivo anual (investigar), se
    imprimirá por consola el valor de su cuota mensual 
    */

        //!Funcion que permite pedir prestado
        static void updateLoan(double dineroActual, double dineroPrestado)
        {
            System.out.println("El dinero que has pedido te sera prestado");
            moneyData.put(currentClient.getIdCard() , moneyData.get(currentClient.getIdCard()) + dineroPrestado);
            dineroActual = moneyData.get(currentClient.getIdCard());
            System.out.println("Tu dinero actual en la cuenta es de: " + dineroActual + "\n");
    
            System.out.println("\n Ingrese cualquier mensaje y oprima enter para regresar");
            input.next();
        }
    
        static void loanFee(double dineroPrestado)
        {   
            double feeInterest;
            double share;
    
            feeInterest = dineroPrestado*Math.pow(1 + (0.02), 6);
            share = feeInterest/6;
    
            System.out.println("Este prestamo tiene un interés del 2% ");
            System.out.println("Para su prestamo equivale a " + feeInterest);
            System.out.println("Se le cobraran 6 cuotas mensuales de " + share );
    
            System.out.println("\n Ingrese cualquier mensaje y oprima enter para regresar");
            input.next();
        }
    
        static void makeLoan()
        {   
            //*Nota: Cree la vairable dinero actual la cual simulara el dinero que este en la cuenta, con el fin de porder
            //* realizar varias pruebas libremente, logicamente esto sera temporal, luego se sustituira por el espacio en la matrix
            double dineroPrestado;
            double dineroActual;
            dineroActual = moneyData.get(currentClient.getIdCard());
    
            System.out.println("\033[H\033[2J"); //?This thing cleans the screen 
    
            System.out.println("dinero actual : " + dineroActual);
    
            System.out.println("Porfavor ingrese la cantidad de dinero que quiere pedir prestado: ");
            dineroPrestado = input.nextDouble();
    
            if(dineroPrestado <= dineroActual )
            {
      
                updateLoan(dineroActual, dineroPrestado);
            
                input.next();
            }
            /*En el último caso, se le presta a la persona en 6 cuotas mensuales, y teniendo en cuenta un interés compuesto del 2% 
            efectivo anual (investigar), se imprimirá por consola el valor de su cuota mensual 
        */
            else if (dineroPrestado > dineroActual & dineroPrestado <= dineroActual * 2)
            {
                
                System.out.println("La cifra solicitada excede sus ahorros");
                System.out.println("Se encuentra dentro del limite permitido(hasta el doble de sus ahorros)");
    
                updateLoan(dineroActual, dineroPrestado);
                loanFee(dineroPrestado);
    
            }
            else
            {
                System.out.println("La cifra solicitada excede sus ahorros");
                System.out.println("excede el limite permitido(hasta el doble de sus ahorros)");
                System.out.println("su prestamo no puede ser procesado, puede intentar con una cifra dentro del limite");
                System.out.println("(" + dineroActual*2 + ")");
                
                System.out.println("\n Ingrese cualquier mensaje y oprima enter para regresar");
                input.next();
            }
        }

    static void askForCDT() {
        double interestRate = 0;
        double gains = 0;
        byte option = 0;
        boolean rightAnswer = false;


        System.out.println("\033[H\033[2J"); //?This thing cleans the screen
        System.out.println("Dinero ahorrado: " + Double.toString(moneyData.get(currentClient.getIdCard())));
        while (!rightAnswer) {
            System.out.println("Seleccione a cuantos meses desea que sea su cdt\n1- 3 meses \n2- 6 meses");
            option = input.nextByte();
            switch (option) {
                case 1:  
                    interestRate = 0.03;
                    rightAnswer = true;
                    break;
                case 2:
                    interestRate = 0.05;
                    rightAnswer = true;
                    break;
            }
            if (!rightAnswer) System.out.println("Seleccion incorrecta. Por favor seleccione entre 1 y 2");
        }

        double days = option == 1 ? 30 : 60;
        gains = moneyData.get(currentClient.getIdCard()) * interestRate * (days / 365.0);
        
        System.out.println("Usted ganara " + gains + " pesos despues de " + days + " dias ");
        System.out.println("Ingrese cualquier mensaje y presione enter para continuar");
        input.next();
    }
}
