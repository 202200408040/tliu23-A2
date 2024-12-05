import java.time.LocalDateTime;
import java.util.Objects;

class Visitor extends Person{
    //Full Day Ticket、Half Day Ticket、Single Ride Ticket
    private String ticketType;
    private boolean isFirstVisit;

    public Visitor() {
        super();
    }



    public Visitor(String name, int age, String gender, String ticketType, boolean isFirstVisit) {
        super(name, age, gender);
        this.ticketType = ticketType;
        this.isFirstVisit = isFirstVisit;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visitor)) return false;
        if (!super.equals(o)) return false;
        Visitor visitor = (Visitor) o;
        return isFirstVisit() == visitor.isFirstVisit() &&
                Objects.equals(getTicketType(), visitor.getTicketType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTicketType(), isFirstVisit());
    }


    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public boolean isFirstVisit() {
        return isFirstVisit;
    }

    public void setFirstVisit(boolean isFirstVisit) {
        this.isFirstVisit = isFirstVisit;
    }
    @Override
    public void printInfo() {
        System.out.println("Visitor " +getName()+ " Information:");
        super.printInfo();
        System.out.println("Ticket Type: " + getTicketType());
        System.out.println("Is First Visit: " + isFirstVisit());
    }
}

