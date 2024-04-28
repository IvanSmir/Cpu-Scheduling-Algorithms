package main.java.algorithms;

import main.java.models.Process;

import java.util.List;

public class RR extends Algorithm {
    private final int quantum;

    public RR(List<Process> processQueue, int quantum) {
        super(processQueue);
        this.quantum = quantum;
    }

    @Override
    public void execute() {

        int processCount = processQueue.size();
        while (processCount > 0) {
            for (Process process : processQueue) {
                if(process.getArrivalTime() > getCurrentTime()) {
                    continue;
                }
                if(process.getRemainingTime() == 0) {
                    continue;
                }
                 process.addStartTime(getCurrentTime());
                if(quantum>=process.getRemainingTime()) {
                    setCurrentTime(getCurrentTime()+process.getRemainingTime());
                    process.setRemainingTime(0);
                }
                else{
                    setCurrentTime(getCurrentTime()+quantum);
                    process.setRemainingTime(process.getRemainingTime()-quantum);
                }
                process.addEndTime(getCurrentTime());
                if(process.getRemainingTime() < 1) {
                    process.setWaitingTime(getCurrentTime() - process.getArrivalTime() - process.getBurstTime());
                    process.setResponseTime(process.getStartTimes().getFirst() - process.getArrivalTime() + 1);
                    processCount--;
                }
            }
        }
    }

    @Override
    public void printDetails() {
        System.out.println("Round Robin Scheduling Algorithm");
        System.out.println("Process ID\tStart Time\tWaiting Time\tEjecution Time\tResponse Time");
        for (Process process : processQueue) {
            System.out.println(process.getProcessId() + "\t\t\t\t" + process.getStartTimes().getFirst() + "\t\t\t\t"  + process.getWaitingTime() +"\t\t\t\t" + (process.getWaitingTime()+process.getBurstTime())  + "\t\t\t\t" + process.getResponseTime());
        }
    }
}
