package main.java.algorithms;

import main.java.models.Process;

import java.util.ArrayList;
import java.util.List;

public class SJF_NP  extends  Algorithm{

    public SJF_NP(List<Process> processQueue) {
        super(processQueue);
    }

    @Override
    public void execute() {
        List<Process> processesQueue = new ArrayList<>(processQueue);
        List<Process> readyQueue = new ArrayList<>();
        while (!processesQueue.isEmpty()){
            for (Process process : processQueue) {
                if (process.getArrivalTime() <= getCurrentTime() && process.getRemainingTime() > 0){
                    readyQueue.add(process);
                }
            }
            if (readyQueue.isEmpty()){
                setCurrentTime(getCurrentTime()+1);
                continue;
            }
            readyQueue.sort((o1, o2) -> {
                if (o1.getRemainingTime() == o2.getRemainingTime()){
                    return o1.getArrivalTime() - o2.getArrivalTime();
                }
                return o1.getRemainingTime() - o2.getRemainingTime();
            });            Process currentProcess = readyQueue.getFirst();
            currentProcess.addStartTime(getCurrentTime());
            setCurrentTime(getCurrentTime()+currentProcess.getRemainingTime());
            currentProcess.setRemainingTime(0);
            currentProcess.addEndTime(getCurrentTime());
            currentProcess.setWaitingTime(getCurrentTime() - currentProcess.getArrivalTime() - currentProcess.getBurstTime());
            currentProcess.setResponseTime(currentProcess.getStartTimes().getFirst() - currentProcess.getArrivalTime() + 1);
            processesQueue.remove(currentProcess);
            readyQueue.clear();
        }
    }

    @Override
    public void printDetails() {
        System.out.println("Shortest Job First (Non-Preemptive) Scheduling Algorithm");
        System.out.println("Process ID\tStart Time\tWaiting Time\tEjecution Time\tResponse Time");
        for (Process process : processQueue) {
            System.out.println(process.getProcessId() + "\t\t\t\t" + process.getStartTimes().getFirst() + "\t\t\t\t"  + process.getWaitingTime() +"\t\t\t\t" + (process.getWaitingTime()+process.getBurstTime())  + "\t\t\t\t" + process.getResponseTime());
        }

    }
}
