import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.util.*;

public class ATM {
    private static double amount;
    public static HashMap<Integer, Double> amountmap = new HashMap<Integer, Double>();
    public static HashMap<Integer, Integer> userMap = new HashMap<Integer, Integer>();
    public static List<String> arrlist= new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM");
        Scanner sc = new Scanner(System.in);
        UserDetails u = new UserDetails();
        u.promptUserForDetails();
        userMap.put(u.getUserID(),u.getPin());
        while (true) {
            System.out.println("Press 1 to verify the details");
            System.out.println("Press 2 to deposit");
		  System.out.println("Press 3 to withdraw the amount");
            System.out.println("Press 4 to transfer");
		  System.out.println("Press 5 to see Transcarion histroy");
		  System.out.println("Press 6 to exit");
            int Userchoice = sc.nextInt();
            if (Userchoice == 1) {
                u.promptUserForDetails();
                if (userMap.containsKey(u.getUserID()) && userMap.get(u.getUserID()) == u.getPin()) {
                    System.out.println("Successfully login.");
                } else {
                    System.out.println("User ID or PIN is incorrect.");
                    System.out.println("Please try again");
                    u.promptUserForDetails();
                }
            } 
		  else if (Userchoice == 2) {
                Deposite d = new Deposite();
                d.userdeposite();
            }
		  else if (Userchoice == 3) {
                Withdraw w = new Withdraw();
                w.userwithdraw();
            }
		  else if (Userchoice == 4) {
                Transfer T = new Transfer();
                T.Usertransfer();
            }
		  else if (Userchoice == 5) {
                TranscationHistroy th = new TranscationHistroy ();
                th.TrancationHis();
            }
		  else if(Userchoice == 6){
			System.out.print("Thank You");
			break;
		}
        }
    }

    public static void setAmount(double amount) {
        ATM.amount = amount;
    }

    public static double getAmount() {
        return amount;
    }
   
}

class UserDetails {
    Scanner sc = new Scanner(System.in);
    public static int userID;
    int pin;

    public void promptUserForDetails() {
        System.out.println("Please enter user ID:");
        userID = sc.nextInt();
        System.out.println("Please enter PIN:");
        pin = sc.nextInt();

    }

    public int getUserID() {
        return userID;
    }

    public int getPin() {
        return pin;
    }
}

class Deposite {
    Scanner sc = new Scanner(System.in);
    double amountdep;

    public void userdeposite() {
        System.out.println("enter the amount to deposit");
        amountdep = sc.nextDouble();
        ATM.setAmount(ATM.getAmount() + amountdep);
        System.out.printf("%.2f is deposited%n", amountdep);
        System.out.printf("New balance: %.2f%n", ATM.getAmount());
	   ATM.amountmap.put(UserDetails.userID,ATM.getAmount());
	   ATM.arrlist.add(String.format("amount is deposite %.2f", amountdep));
    }
}
class Withdraw{
	Scanner sc=new Scanner(System.in);
	double amountwd;
	public void userwithdraw()
	{	System.out.println("enter the amount for withdraw");
		amountwd = sc.nextDouble();
		if(amountwd<=ATM.getAmount())
		{
			ATM.setAmount(ATM.getAmount()-amountwd);
			System.out.printf("%.2f is  withdrawn %n", amountwd);
               System.out.printf("New balance: %.2f%n", ATM.getAmount());
			ATM.amountmap.put(UserDetails.userID,ATM.getAmount());
			ATM.arrlist.add(String.format("amount is withdrawn %.2f", amountwd));
   
		}
		else{
			System.out.println("please enter the valid amount");
			System.out.println("TRY AGAIN");
			System.out.println("Press 3 if u want to withdraw amount");
			
		}
		
	}
	
}
class Transfer{
	Scanner sc=new Scanner(System.in);
	double amounttf;
	public void Usertransfer()
	{
		System.out.println("please enter destination account details to transfer amount");
		int useriid;
		useriid=sc.nextInt();
		System.out.println("Enter the amount to transfer:");
		amounttf = sc.nextDouble();
		if(amounttf<=ATM.getAmount())
		{	
			double destAccountBalance = ATM.amountmap.getOrDefault(useriid, 0.0); // get current balance of destination account, or 0 if not in map
            	destAccountBalance += amounttf; // add transferred amount to balance
            	ATM.amountmap.put(useriid, destAccountBalance); // update balance in userMap
			ATM.setAmount(ATM.getAmount()-amounttf);
			System.out.printf("%.2f  is Transfer to %d account number %n", amounttf,useriid);
               System.out.printf("New balance: %.2f%n", ATM.getAmount());
			ATM.amountmap.put(UserDetails.userID,ATM.getAmount());
			ATM.arrlist.add(String.format("amount transfer %.2f", amounttf));

   
		}
		else{
			System.out.println("please enter the valid amount");
			System.out.println("TRY AGAIN");
			System.out.println("Press 4 if u want to transfer amount");
			
		}
		
		
	}
}
class Account{
	public void view()
	{
		for (Map.Entry<Integer, Double> entry : ATM.amountmap.entrySet()) {
    			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}	
	
}
class TranscationHistroy{
	
	
	public void TrancationHis()
	{
		ATM.arrlist.add(String.format("balance amount %.2f", ATM.getAmount()));
		for(int i=0;i<ATM.arrlist.size();++i)
		{
			System.out.println(ATM.arrlist.get(i));
		}
		
	} 
}

