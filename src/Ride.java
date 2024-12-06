import java.io.*;
import java.util.*;

public class Ride implements RideInterface{
    private String name;
    private boolean isOpen;
    private Employee operator;
    private Queue<Visitor> queue;
    private List<Visitor> history;
    private int maxRider;
    private int numOfCycles;
    public Ride() {
    }

    public Ride(String name, boolean isOpen, Employee operator,int maxRider,int numOfCycles) {
        this.name = name;
        this.isOpen = isOpen;
        this.operator = operator;
        this.queue = new LinkedList<>();
        this.history = new LinkedList<>();
        this.maxRider = maxRider;
        this.numOfCycles = numOfCycles;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        this.maxRider = maxRider;
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    public void setNumOfCycles(int numOfCycles) {
        this.numOfCycles = numOfCycles;
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
        if(this.operator == null){
            System.out.println("The ride cannot be operated without an assigned operator.");
            return;
        }
        if(this.queue.size() == 0){
            System.out.println("There are no waiting visitors, the ride cannot be operated.");
            return;
        }
        for(int i = 0; i < Math.min(maxRider,this.queue.size());i ++){
            this.history.add(queue.poll());
        }
        this.numOfCycles++;
    }

    @Override
    public void addVisitorToHistory(Visitor visitor) {
        history.add(visitor);
        System.out.println("Visitor "+ visitor.getName()+" successfully added to the " + name + " ride history.");
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        for(Visitor v : history)
        {
            if(v == visitor)
            {
                System.out.println("Visitor:"+ visitor.getName()+"  record found in the " + name + " ride history.");
                return true;
            }
        }
        System.out.println("Visitor:"+ visitor.getName()+"  record not found in the " + name + " ride history.");
        return false;
    }

    @Override
    public int numberOfVisitors() {
        System.out.println("the number of Visitors in the " + name + " ride history is:"+history.size());
        return history.size();
    }

    @Override
    public void printRideHistory() {
        System.out.println("History of " + name + " ride:");

        Iterator<Visitor> iterator = history.iterator();
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            visitor.printInfo();
        }
        }

    public void sortVisitorsInHistory() {
        VisitorComparator comparator = new VisitorComparator();
        Collections.sort(history, comparator);
    }

    public void exportRideHistory(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            //Write the CSV file header
            writer.write("Name, Age, Gender, Ticket Type, Is First Visit");
            writer.newLine();

            Iterator<Visitor> iterator = history.iterator();
            while (iterator.hasNext()) {
                Visitor visitor = iterator.next();
                String name = escapeSpecialCharacters(visitor.getName());
                String ticketType = escapeSpecialCharacters(visitor.getTicketType());

                writer.write(name + "," + visitor.getAge() + "," + visitor.getGender() + ","
                        + ticketType + "," + visitor.isFirstVisit());
                writer.newLine();
            }
            System.out.println("Ride history successfully exported to " + filePath);
        } catch (IOException e) {
            System.out.println("Error occurred while exporting ride history: " + e.getMessage());
        }
    }
    //Auxiliary method for escaping special characters.
    private String escapeSpecialCharacters(String value) {
        if (value.contains(",") || value.contains("\"")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    public void importRideHistory(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            boolean isFirstLine = true;
            String line;
            while ((line = reader.readLine())!= null) {
                // Skip the file header row
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] visitorData = parseCsvLine(line);
                if (visitorData!= null) {
                    Visitor visitor = new Visitor(
                            visitorData[0],
                            Integer.parseInt(visitorData[1]),
                            visitorData[2],
                            visitorData[3],
                            Boolean.parseBoolean(visitorData[4])
                    );
                    history.add(visitor);
                }
            }
            System.out.println("Ride history successfully imported from " + filePath);
        } catch (IOException e) {
            System.out.println("Error occurred while importing ride history: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error occurred while parsing visitor data: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid file format: " + e.getMessage());
        }
    }

    //Auxiliary method for parsing a line of CSV data, handling possible quoted wrapping and special character escaping
    private String[] parseCsvLine(String line) {
        boolean inQuotes = false;
        StringBuilder currentValue = new StringBuilder();
        List<String> values = new LinkedList<>();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes =!inQuotes;
            } else if (c == ',' &&!inQuotes) {
                values.add(currentValue.toString());
                currentValue.setLength(0);
            } else {
                currentValue.append(c);
            }
        }

        if (currentValue.length() > 0) {
            values.add(currentValue.toString());
        }

        return values.toArray(new String[0]);
    }



}
