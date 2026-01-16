public class Passanger {
    private static int idprovider=0;
    private int id;
    private String name;
    private int age;
    private char preference;
    private int seatno;
    private String tickettype;

    public Passanger(String name,int age,char preference)
    {
        this.name=name;
        this.age=age;
        this.preference=preference;
    }
    public void generateId() {
        this.id = ++idprovider;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getPreference() {
        return preference;
    }

    public void setPreference(char preference) {
        this.preference = preference;
    }

    public String getTickettype() {
        return tickettype;
    }

    public void setTickettype(String tickettype) {
        this.tickettype = tickettype;
    }
    public int getSeatno()
    {
        return seatno;
    }
    public void setSeatno(int seatno)
    {
        this.seatno=seatno;
    }

    @Override
    public String toString() {
        return "Passenger Ticket id: " + id +
                "\nPassenger name: " + name +
                "\nPassenger age: " + age +
                "\nPassenger Seat no: " + seatno +
                "\nPassenger preference: " + preference;
    }

}
