import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AssignmentTwo {
    public static Random random = new Random();
    public static void main(String[] args) {
        random.setSeed(System.currentTimeMillis());
        partThree();
    }
    public static void partThree(){
        Ride ride = new Ride("rollerCoaster",true,null);
        List<Visitor>visitors = createVisitors(5);
        for(Visitor visitor : visitors)
        {
            ride.addVisitorToQueue(visitor);
        }
        ride.removeVisitorFromQueue(visitors.get(random.nextInt(5)));
        ride.printQueue();
    }

    public void partFourA(){
    }

    public void partFourB(){
    }

    public void partFive(){
    }

    public void partSix(){
    }

    public void partSeven(){
    }

    public static List<Visitor> createVisitors(int n)
    {
        List<Visitor> visitorList = new ArrayList<Visitor>();
        for(int i = 0 ; i < n; i++)
        {
            Visitor visitor = new Visitor("ride "+i,18+i,i % 2 == 0 ? "man":"woman","Full Day Ticket" , true);
            visitorList.add(visitor);
        }

        return visitorList;
    }
}
