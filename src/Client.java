/*
    This will be the class Client. This class will have the next atributes:
    name, idCard, incomeLevel y userCreationDate.
*/
public class Client 
{
    private String name;
    private int idCard;
    private double incomeLevel;
    private int userCreationDate;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIdCard() {
        return idCard;
    }
    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }
    public double getIncomeLevel() {
        return incomeLevel;
    }
    public void setIncomeLevel(double incomeLevel) {
        this.incomeLevel = incomeLevel;
    }
    public int getUserCreationDate() {
        return userCreationDate;
    }
    public void setUserCreationDate(int userCreationDate) {
        this.userCreationDate = userCreationDate;
    }
}
