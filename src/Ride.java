import java.util.*;

public class Ride implements RideInterface{
    private String name;
    private boolean isOpen;
    private Employee operator;
    private Queue<Visitor> queue;
    private List<Visitor> history;
    public Ride() {
    }

    public Ride(String name, boolean isOpen, Employee operator) {
        this.name = name;
        this.isOpen = isOpen;
        this.operator = operator;
        this.queue = new LinkedList<>();
        this.history = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    @Override
    public void addVisitorToQueue(Visitor visitor) {
        this.queue.add(visitor);
        System.out.println("Queue for " + name + " ride add a visitor " + visitor.getName());
    }

    @Override
    public void removeVisitorFromQueue(Visitor visitor) {
        this.queue.remove(visitor);
        System.out.println("Queue for " + name + " ride remove a visitor " + visitor.getName());
    }

    @Override
    public void printQueue() {
        System.out.println("Queue for " + name + " ride:");
        for (Visitor visitor : queue) {
            System.out.println(visitor.getName());
        }
    }

    @Override
    public void runOneCycle() {

    }

    @Override
    public void addVisitorToHistory(Visitor visitor) {

    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        return false;
    }

    @Override
    public int numberOfVisitors() {
        return 0;
    }

    @Override
    public void printRideHistory() {

    }
}
