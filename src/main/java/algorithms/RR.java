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
        int currentTime = 0;
        int processCount = processQueue.size();
        while (processCount>0) {
            for (Process process : processQueue) {
                process.addStartTime(currentTime);
                if(quantum>=process.getRemainingTime()) {
                    currentTime += process.getRemainingTime();
                    process.setRemainingTime(0);
                }
                else{
                    currentTime += quantum;
                    process.setRemainingTime(process.getRemainingTime()-quantum);
                }
                process.addEndTime(currentTime);
                if(process.getRemainingTime() == 0) {
                    process.setWaitingTime(currentTime - process.getArrivalTime() - process.getBurstTime());
                    process.setResponseTime(process.getStartTimes().getFirst() - process.getArrivalTime() + 1);
                    processCount--;
                }
            }
        }
    }


    @Override
    public void printDetails() {

    }
}
