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
            process.addEndTime(currentTime);
            process.setWaitingTime(process.getEndTimes().getFirst() - process.getArrivalTime() - process.getBurstTime());
            process.setResponseTime(process.getStartTimes().getFirst() - process.getArrivalTime() + 1);
        }
    }

    @Override
    public double getAverageWaitingTime() {
        double totalWaitingTime = 0;
        for (Process process : processQueue) {
            totalWaitingTime += process.getWaitingTime();
        }
        return totalWaitingTime / processQueue.size();
    }

    @Override
    public double getAverageResponseTime() {
        double totalResponseTime = 0;
        for (Process process : processQueue) {
            totalResponseTime += process.getResponseTime();
        }
        return totalResponseTime / processQueue.size();
    }

    @Override
    public void printDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
