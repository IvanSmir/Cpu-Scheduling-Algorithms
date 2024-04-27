package main.java.algorithms;

import main.java.models.Process;

import java.util.ArrayList;
import java.util.List;

public class SJF_P extends  Algorithm{

    public SJF_P(List<Process> processQueue) {
        super(processQueue);
    }

    @Override
    public void execute() {
        int currentTime = 0;
        List<Process> readyQueue = new ArrayList<>();
        int processCount = processQueue.size();
        processQueue.getFirst().addStartTime(0);
        processQueue.getFirst().addEndTime(1);
        processQueue.getFirst().setResponseTime(1);
        processQueue.getFirst().setRemainingTime(processQueue.getFirst().getBurstTime() - 1);
        currentTime = 1;
        while (processCount > 0){
            for (Process process : processQueue) {
                if (process.getArrivalTime() <= currentTime && process.getRemainingTime() > 0){
                    readyQueue.add(process);
                }
            }
            readyQueue.sort((o1, o2) -> {
                if (o1.getRemainingTime() == o2.getRemainingTime()){
                    return o1.getArrivalTime() - o2.getArrivalTime();
                }
                return o1.getRemainingTime() - o2.getRemainingTime();
            });
            Process currentProcess = readyQueue.getFirst();
            currentProcess.addStartTime(currentTime);
            currentTime += currentProcess.getRemainingTime();
            currentProcess.addEndTime(currentTime);
            currentProcess.setWaitingTime(currentProcess.getEndTimes().getFirst() - currentProcess.getArrivalTime() - currentProcess.getBurstTime());
            currentProcess.setResponseTime(currentProcess.getStartTimes().getFirst() - currentProcess.getArrivalTime() + 1);
            currentProcess.setRemainingTime(0);
            processCount--;
            readyQueue.clear();
        }
    }

    @Override
    public double getAverageWaitingTime() {
        return 0;
    }

    @Override
    public double getAverageResponseTime() {
        return 0;
    }

    @Override
    public void printDetails() {

    }
}
