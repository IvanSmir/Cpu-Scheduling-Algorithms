package main.java.algorithms;

import main.java.models.Process;

import java.util.ArrayList;
import java.util.List;

public class Priority extends Algorithm{
    public Priority(List<Process> processQueue) {
        super(processQueue);
    }
    @java.lang.Override
    public void execute() {
        List<Process> readyQueue = new ArrayList<>();
        int processCount = processQueue.size();
        while (processCount > 0){
            for (Process process : processQueue) {
                if (process.getArrivalTime() <= getCurrentTime() && process.getRemainingTime() > 0){
                    readyQueue.add(process);
                }
            }
            readyQueue.sort((o1, o2) -> {
                if (o1.getPriority() == o2.getPriority()) {
                    return o1.getArrivalTime() - o2.getArrivalTime();
                }
                return o1.getPriority() - o2.getPriority();
            });
            Process  currentProcess = readyQueue.getFirst();
            currentProcess.addStartTime(getCurrentTime());
            setCurrentTime(getCurrentTime()+currentProcess.getRemainingTime());
            currentProcess.setRemainingTime(0);
            currentProcess.addEndTime(getCurrentTime());
            currentProcess.setWaitingTime(currentProcess.getEndTimes().getFirst() - currentProcess.getArrivalTime() - currentProcess.getBurstTime());
            currentProcess.setResponseTime(currentProcess.getStartTimes().getFirst() - currentProcess.getArrivalTime() + 1);
            processCount--;
            readyQueue.clear();
        }
    }


    @java.lang.Override
    public void printDetails() {
        System.out.println("Priority Scheduling Algorithm");
        System.out.println("Process ID\tStart Time\tWaiting Time\tEjecution Time\tResponse Time");
        for (Process process : processQueue) {
            System.out.println(process.getProcessId() + "\t\t\t\t" + process.getStartTimes().getFirst() + "\t\t\t\t"  + process.getWaitingTime() +"\t\t\t\t" + (process.getWaitingTime()+process.getBurstTime())  + "\t\t\t\t" + process.getResponseTime());
        }

    }
}
