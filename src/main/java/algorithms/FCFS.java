package main.java.algorithms;

import java.util.List;
import main.java.models.Process;

public class FCFS extends Algorithm {
    public FCFS (List<Process> processQueue) {
        super(processQueue);
    }

    @Override
    public void execute() {
        int currentTime = 0;
        for (Process process : processQueue) {
            process.addStartTime(currentTime);
            currentTime += process.getBurstTime();
            process.setRemainingTime(0);
            process.addEndTime(currentTime);
            process.setWaitingTime(currentTime - process.getArrivalTime() - process.getBurstTime());
            process.setResponseTime(process.getStartTimes().getFirst() - process.getArrivalTime() + 1);
        }
    }

    @Override
    public void printDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
