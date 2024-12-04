public interface RideInterface {
    /**
     *  to add a visitor to the queue. It has
     * a parameter of Visitor.
     * @param visitor
     */
    void addVisitorToQueue(Visitor visitor);

    /**
     * to remove a visitor from the
     * queue
     * @param visitor
     */
    void removeVisitorFromQueue(Visitor visitor);

    /**
     *to print the list of waiting visitors in the
     * queue
     */
    void printQueue();

    /**
     * to run the ride for one cycle
     */
    void runOneCycle();

    /**
     * to add a visitor to the ride history.
     * It has a parameter of Visitor
     * @param visitor
     */
    void addVisitorToHistory(Visitor visitor);

    /**
     * to check whether the visitor is
     * in the ride history
     * @param visitor
     * @return
     */
    boolean checkVisitorFromHistory(Visitor visitor);

    /**
     * to return the number of Visitors in
     * the ride history.
     * @return
     */
    int numberOfVisitors();

    /**
     * to print the list of visitors who took
     * the rides
     */
    void printRideHistory();
}
