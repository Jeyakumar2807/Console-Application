import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;

public class ticketbooking {
    private static int berthlimit=2;
    private static int raclimit=1;
    private static int waitinglistlimit=1;

    private static int upperseatno=1;
    private static int middleseatno=2;
    private static int lowseatno=3;

    static ArrayList<Passanger>confirmedlist=new ArrayList<>();
    static ArrayList<Passanger>upperlist=new ArrayList<>();
    static ArrayList<Passanger>middlelist=new ArrayList<>();
    static ArrayList<Passanger> lowerlist =new ArrayList<>();

    static Queue<Passanger>racQueue=new LinkedList<>();
    static Queue<Passanger>waitingQueue=new LinkedList<>();

    public static void bookticket(Passanger p)
    {
        if(upperlist.size()==berthlimit&&middlelist.size()==berthlimit&&lowerlist.size()==berthlimit)
        {
            if(updateracqueue(p))
            {
                p.generateId();
                System.out.println("Added to RAC\n Your ticket id is "+p.getId());
            }
            else if(updatewaitingqueue(p))
            {
                p.generateId();
                System.out.println("Added to Waitinglist\n Your ticket id is "+p.getId());
            }
            else
            {
                System.out.println("Ticket is unavailable");
            }
        }
        else if(checkavailability(p))
        {
            p.generateId();
            System.out.println("Booking confirmed....\n your ticket id is "+p.getId());
            p.setTickettype("berth");
            confirmedlist.add(p);
        }
        else {
            System.out.println("ticket is not available...");
            availablelist();
        }
    }
    private static void availablelist()
    {
        System.out.println("check the seat availability...");
        System.out.println("Upper berth "+(berthlimit-upperlist.size()));
        System.out.println("middle berth "+(berthlimit-middlelist.size()));
        System.out.println("lower berth "+(berthlimit-lowerlist.size()));
    }
    private static boolean checkavailability(Passanger p)
    {
        Map<Integer,Character>map=ticketcancelling.getSeatnumberwithberth();
        if(p.getPreference()=='U')
        {
            if(upperlist.size()<berthlimit)
            {
                if(!map.isEmpty())
                {
                    getseatdetails(map,p);
                }
                else {
                    p.setSeatno(upperseatno);
                    upperseatno+=3;
                }
                upperlist.add(p);
                return true;
            }
        }
        else if(p.getPreference()=='M')
        {
            if(middlelist.size()<berthlimit){
                if(!map.isEmpty())
                {
                    getseatdetails(map,p);
                }
                else {
                    p.setSeatno(middleseatno);
                    middleseatno+=3;
                }
                middlelist.add(p);
                return true;
            }
        }
        else {
            if(lowerlist.size()<berthlimit){
                if(!map.isEmpty())
                {
                    getseatdetails(map,p);
                }
                else {
                    p.setSeatno(lowseatno);
                    lowseatno+=3;
                }
                lowerlist.add(p);
                return true;
            }
        }
        return false;
    }
    private static boolean updateracqueue(Passanger p)
    {
        if(racQueue.size()<raclimit)
        {
            p.setTickettype("rac");
            racQueue.add(p);
            return true;
        }
        return false;
    }
    private static boolean updatewaitingqueue(Passanger p)
    {
        if(waitingQueue.size()<waitinglistlimit)
        {
            p.setTickettype("waiting-list");
            waitingQueue.add(p);
            return true;
        }
        return false;
    }
    public static void getseatdetails(Map<Integer,Character>map,Passanger p)
    {
        int seatnumber=checkforprefernceavailabilty(map,p.getPreference());
        p.setSeatno(seatnumber);
        map.remove(seatnumber);
    }
    public static int checkforprefernceavailabilty(Map<Integer,Character>map,char preference)
    {
        for(Map.Entry<Integer,Character>e:map.entrySet())
        {
            if(preference==(char)e.getValue())
            {
                return (int)e.getKey();
            }
        }
        return 0;
    }
    public static void displayconfirmed()
    {
        System.out.println("-------------------------");
        for(Passanger p:confirmedlist)
        {
            System.out.println(p.toString());
            System.out.println("-------------------------");
        }
    }
    public static void displayrac()
    {
        System.out.println("-------------------------");
        for(Passanger p:racQueue)
        {
            System.out.println(p.toString());
            System.out.println("-------------------------");
        }
    }
    public static void displaywaiting()
    {
        System.out.println("-------------------------");
        for(Passanger p:waitingQueue)
        {
            System.out.println(p.toString());
            System.out.println("-------------------------");
        }
    }

}
