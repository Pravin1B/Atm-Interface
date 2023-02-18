import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
class Atmdetails {  
    private String accno;  
    private String name;  
    private String acc_pin;  
    private long balance; 
    Scanner sc = new Scanner(System.in);  
    //method to open new account  
    public void openAccount(int k) {    	     
    	System.out.println("Enter detais account :"+k);
        System.out.print("Enter Account No: ");  
        accno = sc.next();  
        System.out.print("Enter Account Pin: ");  
        acc_pin = sc.next();  
        System.out.print("Enter Name: ");  
        name = sc.next();  
        System.out.print("Enter Balance: ");  
        balance = sc.nextLong();  
    }  
    //method to display account details  
    public void showAccount() {  
        System.out.println("Name of account holder: " + name);  
        System.out.println("Account no.: " + accno);    
        System.out.println("Balance: " + balance);  
    }  
    //method to deposit money  
    public void deposit() {  
        long amt;  
        System.out.println("Enter the amount you want to deposit: ");  
        amt = sc.nextLong();  
        balance = balance + amt;  
        System.out.println(amt+" deposited Succesfully: ");
        atmstmt.put(amt,"Has been deposited");
    }  
    //method to withdraw money  
    public void withdrawal() {  
        long amt;         
        System.out.println("Enter the amount you want to withdraw: ");  
        amt = sc.nextLong();  
        if (balance >= amt) {  
        	atmstmt.put( amt,"Has been Withdrawl");
             balance = balance - amt;  
            System.out.println("Balance after withdrawal: " + balance);  
        } 
        else {  
            System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!" );  
        }  
    }  
    
    public boolean transfer(Atmdetails C[], Atmdetails x)
    {
    	
    System.out.print("Enter account no.amount to be transferred: ");
    String accno=sc.next() ;
     boolean found=false;
      for (int j = 0; j < C.length; j++) 
      {
        found = C[j].searching(accno);
        
         if(found) 
          {
	         System.out.print("Enter amount to be transferred: ");
	          long amount =sc.nextInt();
	           if(x.balance>=amount)
	           {
		         C[j].balance = C[j].balance + amount ;
		         System.out.print("Amount transferred Successfully: ");
		         x.balance = x.balance - amount ;
		         atmstmt.put(amount,"Has been Transfered");
		         return true ;
	           }
	            else 
               System.out.print("Insufficient balance!!!");
	            return true ;
	      }              
      }
	    return false;       
}
    Hashtable<Long,String> atmstmt=new Hashtable<>();
    public void showHistory()
    {
    	for(Map.Entry<Long,String> m:atmstmt.entrySet())
    	{
    		System.out.println(m.getKey()+" "+m.getValue());
    	}
    }
    
    //method to search an account number  
    public boolean search(String ac_no,String ac_pin) {  
        if (accno.equals(ac_no) && (acc_pin.equals(ac_pin))) {  
            showAccount();  
            return (true);  
        }  
        return (false);  
    } 
    public boolean searching(String ac_no) {
    	if (accno.equals(ac_no)) {
    		System.out.println("Name of account holder: " + name);  
            System.out.println("Account no.: " + accno);  
  		     return (true);
    	}
		return (false);	
    }
   
public class AtmInterface {  
    public static void main(String arg[]) {     	
        try (Scanner sc = new Scanner(System.in)) {
			//create initial accounts  
			System.out.print("How many number of customers do you want to input ? :");  
			int n = sc.nextInt();  
			Atmdetails C[] = new Atmdetails[n];  
			for (int i = 0; i < C.length; i++) {  
			    C[i] = new Atmdetails();  
			    C[i].openAccount(i+1);  
			}  
			// loop runs until number 5 is not pressed to exit  
			int ch;  
			do {  
			    System.out.println("\n **AtmInterface** ");  
			    System.out.println(" 1. Search by Account number \n 2. Transaction History \n 3. Withdraw \n 4. Diposit \n 5. Transfer \n 6. Exit ");  
			    System.out.println("Enter your choice: ");  
			    ch = sc.nextInt();  
			        switch (ch) {  
			            case 5:  
			            {
			            	int i;
			            	System.out.print("Enter account no. from which amount to be transferred: "); 
			            	String ac_no=sc.next();
			            	System.out.print("Enter account Pin : ");
			                String ac_pin =sc.next();
			            	boolean found =false ;
			            	for (i = 0; i < C.length; i++)  
			            	   {
			            	     found = C[i].search(ac_no,ac_pin);
			                      if (found)
			                       { 
			                         found= C[i].transfer(C,C[i]);
			                         break ;
			                       }
			            	   }
			            	if(!found||found==false)
			            	{
			            		System.out.print("Account not present : ");
			            	}
			            }
			            	
			                      break ;
			            case 1:  
			                System.out.print("Enter account no. you want to search: ");  
			                String ac_no1 = sc.next();
			                System.out.print("Enter your account pin : ");
			                String ac_pin1 =sc.next();
			                boolean found1 = false;  
			                for (int i = 0; i < C.length; i++) {  
			                    found1 = C[i].search(ac_no1,ac_pin1);  
			                    if (found1) {  
			                        break;  
			                    }  
			                }  
			                if (!found1) {  
			                    System.out.println("Search failed! Account doesn't exist..!!");  
			                }  
			                break;  
			            case 4:  
			                System.out.print("Enter Account no. : ");  
			                ac_no1 = sc.next();
			                System.out.print("Enter your account pin : ");
			                String ac_pin2=sc.next();
			                found1 = false;  
			                for (int i = 0; i < C.length; i++) {  
			                    found1 = C[i].search(ac_no1,ac_pin2);  
			                    if (found1) {  
			                        C[i].deposit();  
			                        break;  
			                    }  
			                }  
			                if (!found1) {  
			                    System.out.println("Search failed! Account doesn't exist..!!");  
			                }  
			                break;  
			            case 3:  
			                System.out.print("Enter Account No : ");  
			                ac_no1 = sc.next(); 
			                System.out.print("Enter your account pin : ");
			                String ac_pin3 =sc.next();  
			                found1 = false;  
			                for (int i = 0; i < C.length; i++) {  
			                    found1 = C[i].search(ac_no1,ac_pin3);  
			                    if (found1) {  
			                        C[i].withdrawal();  
			                        break;  
			                    }  
			                }  
			                if (!found1) {  
			                    System.out.println("Search failed! Account doesn't exist..!!");  
			                }  
			                break;
			            case 2 :
			            	System.out.print("Enter Account No : ");  
			                String ac_no2 = sc.next(); 
			                System.out.print("Enter your account pin : ");
			                String ac_pin4 =sc.next();  
			                found1 = false;  
			                for (int i = 0; i < C.length; i++) 
			                {  
			                    found1 = C[i].search(ac_no2,ac_pin4);  
			                    if (found1) 
			                    { 
			                    	C[i].showHistory();
			                    }
			                }
			            	  break ;
			            case 6:  
			                System.out.println("See you soon...");  
			                break;  
			        }  
			    }  
			    while (ch != 6);
		}  
        }  
    }  
}