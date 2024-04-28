package main.java.algorithms;

import main.java.models.Process;

import java.util.ArrayList;
import java.util.List;

public class HRRN extends Algorithm{
public HRRN (List<Process> processQueue) {
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

            int finalCurrentTime = getCurrentTime();
            readyQueue.sort((o1, o2) -> {
                double responseRatio1 = (double) (finalCurrentTime - o1.getArrivalTime() + o1.getBurstTime()) / o1.getBurstTime();
                double responseRatio2 = (double) (finalCurrentTime - o2.getArrivalTime() + o2.getBurstTime()) / o2.getBurstTime();
                if (responseRatio1 == responseRatio2){
                    return o1.getArrivalTime() - o2.getArrivalTime();
                }
                return responseRatio1 > responseRatio2 ? -1 : 1;
            });
            Process currentProcess = readyQueue.getFirst();
            currentProcess.addStartTime(getCurrentTime());
            setCurrentTime(getCurrentTime()+ currentProcess.getBurstTime());
            currentProcess.addEndTime(getCurrentTime());
            currentProcess.setWaitingTime(getCurrentTime() - currentProcess.getArrivalTime() - currentProcess.getBurstTime());
            currentProcess.setResponseTime(currentProcess.getStartTimes().getFirst() - currentProcess.getArrivalTime() + 1);
            currentProcess.setRemainingTime(0);
            processesQueue.remove(currentProcess);
            readyQueue.clear();
        }



    }


    @Override
    public void printDetails() {
        System.out.println("Highest Response Ratio Next (HRRN) Scheduling Algorithm");
        System.out.println("Process ID\tStart Time\tWaiting Time\tEjecution Time\tResponse Time");
        for (Process process : processQueue) {
            System.out.println(process.getProcessId() + "\t\t\t\t" + process.getStartTimes().getFirst() + "\t\t\t\t"  + process.getWaitingTime() +"\t\t\t\t" + (process.getWaitingTime()+process.getBurstTime())  + "\t\t\t\t" + process.getResponseTime());
        }
        System.out.println("Average Waiting Time: " + getAverageWaitingTime());
        System.out.println("Average Response Time: " + getAverageResponseTime());
    }
}
