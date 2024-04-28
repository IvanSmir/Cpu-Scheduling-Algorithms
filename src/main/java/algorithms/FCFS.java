package main.java.algorithms;

import java.util.List;
import main.java.models.Process;

public class FCFS extends Algorithm {
    public FCFS (List<Process> processQueue) {
        super(processQueue);
    }

    @Override
    public void execute() {
        for (Process process : processQueue) {
            process.addStartTime(getCurrentTime());
            setCurrentTime(getCurrentTime()+process.getBurstTime());
            process.setRemainingTime(0);
            process.addEndTime(getCurrentTime());
            process.setWaitingTime(getCurrentTime() - process.getArrivalTime() - process.getBurstTime());
            process.setResponseTime(process.getStartTimes().getFirst() - process.getArrivalTime() + 1);
        }
    }

    @Override
    public void printDetails() {
        System.out.println("First Come First Serve (FCFS) Scheduling Algorithm");
        System.out.println("Process ID\tStart Time\tWaiting Time\tEjecution Time\tResponse Time");
        for (Process process : processQueue) {
            System.out.println(process.getProcessId() + "\t\t\t\t" + process.getStartTimes().getFirst() + "\t\t\t\t"  + process.getWaitingTime() +"\t\t\t\t" + (process.getWaitingTime()+process.getBurstTime())  + "\t\t\t\t" + process.getResponseTime());
        }
        System.out.println("Average Waiting Time: " + getAverageWaitingTime());
        System.out.println("Average Response Time: " + getAverageResponseTime());
    }
}
