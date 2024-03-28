/*
    *This will be the class Client. This class will have the next atributes:
    *name, idCard, incomeLevel y userCreationDate.
*/
public class Client 
{
    //*Variables
    private String name;
    private String idCard;
    private double incomeLevel;
    private int userCreationDate;
    
    //*Constructor
    public Client(String name, String idCard, double incomeLevel, int userCreationDate)
    {
        this.name = name;
        this.idCard = idCard;
        this.incomeLevel = incomeLevel;
        this.userCreationDate = userCreationDate;
    }
    //!--------GETTERS & SETTERS-------------
    //?NAME-------------------------
    public String getName() 
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    //?IdCard-------------------------
    public String getIdCard()
    {
        return idCard;
    }
    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    //?incomeLevel-------------------------
    public double getIncomeLevel()
    {
        return incomeLevel;
    }
    public void setIncomeLevel(double incomeLevel)
    {
        this.incomeLevel = incomeLevel;
    }

    //?UserCreationDate-------------------
    public int getUserCreationDate()
    {
        return userCreationDate;
    }
    public void setUserCreationDate(int userCreationDate)
    {
        this.userCreationDate = userCreationDate;
    }
}
