import java.util.Scanner;

class ATM{
    float balance;
    final int pin=3434;

    ATM(){

            System.out.println("Enter your pin: ");
            Scanner sc = new Scanner(System.in);
            int enterPin = sc.nextInt();
            if(enterPin == pin){
                menu();
            }else{
                System.out.println("Invalid pin");

            }

    }


    public void menu(){
        System.out.println("Enter Choice.");
        System.out.println("1. Check account balance");
        System.out.println("2. Withdraw amount");
        System.out.println("3. Deposite money");
        System.out.println("4. Exit");

        Scanner sc = new Scanner(System.in);
        int opt = sc.nextInt();
        switch (opt){
            case 1:
                checkBalance();
                break;
            case 2:
                withdrawMoney();
                break;
            case 3:
                depositeMoney();
                break;
            case 4:
                return;

            default:
                System.out.println("Choose option correctly.");
        }
    }
    public void checkBalance(){
        System.out.println("Balance: "+balance);
        menu();
    }
    public void withdrawMoney(){
        System.out.println("Enter amount to withdraw: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        if(balance < amount){
            System.out.println("Insufficient fund");
        }else {
            balance -= amount;
            System.out.println("Money withdraw Successfully");
        }

        checkBalance();
        menu();
    }
    public void depositeMoney(){
        System.out.println("Enter amount to deposite:");
        Scanner sc = new Scanner(System.in);
        float depositeMoney = sc.nextFloat();
        balance += depositeMoney;
        System.out.println("Money deposited Successfully");
        menu();
    }
}


public class ATMMachine {
    public static void main(String[] args) {
        ATM machine = new ATM();

    }
}
