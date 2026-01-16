import java.lang.classfile.instruction.CharacterRange;
import java.util.HashMap;
import java.util.Map;
public class ticketcancelling extends ticketbooking{
    private static char preferencetracker='\0';
    private static int cancelledseatno=0;
    private static Map<Integer, Character>seatnumberwithberth=new HashMap<>();

    public static String cancelling(int id)
    {
        for(Passanger p:confirmedlist)
        {
            if(p.getId()==id)
            {
                cancel(p);
                return "Success";
            }
        }
        for(Passanger p:racQueue)
        {
            if(p.getId()==id)
            {
                cancel(p);
                return "Success";
            }
        }
        for(Passanger p:waitingQueue)
        {
            if(p.getId()==id)
            {
                cancel(p);
                return "Success";
            }
        }
        return "Invalid id";
    }
    private static void cancel(Passanger p)
    {
        if(p.getTickettype()=="berth")
        {
            preferencetracker=p.getPreference();
            cancelledseatno=p.getSeatno();
            seatnumberwithberth.put(cancelledseatno,preferencetracker);
            deletefromAlllist(p);
            addractoberth(racQueue.poll());
            addwaitingtorac(waitingQueue.poll());
        }
        else if(p.getTickettype()=="rac")
        {
            racQueue.remove(p);
            addwaitingtorac(waitingQueue.poll());
        }
        else{
            waitingQueue.remove(p);
        }
    }
    private static void deletefromAlllist(Passanger p)
    {
        confirmedlist.remove(p);
        upperlist.remove(p);
        lowerlist.remove(p);
        middlelist.remove(p);
    }
    private static void addractoberth(Passanger p)
    {
        if(p!=null)
        {
            p.setSeatno(cancelledseatno);
            p.setPreference(preferencetracker);
            p.setTickettype("berth");
            if(p.getPreference()=='U')
            {
                upperlist.add(p);
            }
            else if(p.getPreference()=='M')
            {
                middlelist.add(p);
            }
            else {
                lowerlist.add(p);
            }
            confirmedlist.add(p);
            seatnumberwithberth.remove(cancelledseatno);
            preferencetracker='\0';
            cancelledseatno=0;
        }
    }
    private static void addwaitingtorac(Passanger p)
    {
        if(p!=null)
        {
            p.setTickettype("rac");
            racQueue.add(p);
        }
    }
    public static Map<Integer,Character> getSeatnumberwithberth()
    {
        return seatnumberwithberth;
    }
}
