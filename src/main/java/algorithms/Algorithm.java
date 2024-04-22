package main.java.algorithms;

import main.java.models.Process;

import java.util.List;

/**
 * Abstract class Algorithm to define the basic structure of a process scheduling algorithm.
 */
public abstract class Algorithm {

    protected List<Process> processQueue;

    /**
     * Constructor for the Algorithm class.
     * @param processQueue List of processes to be scheduled.
     */
    public Algorithm(List<Process> processQueue) {
        this.processQueue = processQueue;
    }

    /**
     * Method to execute the scheduling algorithm.
     */
    public abstract void execute();

    /**
     * Method to get the average waiting time of the processes.
     * @return Average waiting time.
     */
    public abstract double getAverageWaitingTime();

    /**
     * Method to get the average response time of the processes.
     * @return Average response time.
     */
    public abstract double getAverageResponseTime();

    /**
     * Method to print the scheduling details.
     */
    public abstract void printDetails();
}
