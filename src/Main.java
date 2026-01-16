import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        boolean loop=true;
        while(loop)
        {
            Scanner sc=new Scanner(System.in);
            System.out.println("\nchoose any one" +
                    "\n 1.Book ticket \n 2.cancel ticket \n 3.Displayconfirmedticket" +
                    "\n 4.display Rac list" +
                    "\n 5.display waiting list" +
                    "\n 6.Exit");
            int n=sc.nextInt();
            switch(n)
            {
                case 1:
                    System.out.println("Enter your name: ");
                    String name=sc.next();
                    System.out.println("Enter your age:");
                    int age=sc.nextInt();
                    System.out.println("Enter berth: ");
                    char preference=sc.next().charAt(0);
                    if(preference=='U'||preference=='M'||preference=='L')
                    {
                        ticketbooking.bookticket(new Passanger(name,age,preference));
                    }
                    else {
                        System.out.println("Invalid berth");
                    }
                    break;
                case 2:
                    System.out.println("Enter your ticket id: ");
                    int id=sc.nextInt();
                    System.out.println(ticketcancelling.cancelling(id));
                    break;
                case 3:
                    ticketbooking.displayconfirmed();
                    break;
                case 4:
                    ticketbooking.displayrac();
                    break;
                case 5:
                    ticketbooking.displaywaiting();
                    break;
                case 6:
                    System.out.println("Thankyou");
                    sc.close();
                    loop=false;
                    break;
            }

        }
    }
}