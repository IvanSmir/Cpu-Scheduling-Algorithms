package main.java.models;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private int processId;
    private int arrivalTime;
    private int burstTime;
    private int priority = 1;
    private int remainingTime;
    private List<Integer> startTimes;
    private List<Integer> endTimes;
    private int waitingTime;
    private int responseTime;

    private boolean isStarted = false;


    public Process(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.startTimes = new ArrayList<>();
        this.endTimes = new ArrayList<>();
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }


    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public List<Integer> getStartTimes() {
        return startTimes;
    }

    public void addStartTime(int startTime) {
        if (!isStarted) {
            this.responseTime = startTime - this.arrivalTime;
            isStarted = true;
        }
        this.startTimes.add(startTime);
    }

    public List<Integer> getEndTimes() {
        return endTimes;
    }

    public void addEndTime(int endTime) {
        this.endTimes.add(endTime);
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public int getEjucutionTime() {
        return burstTime + waitingTime;
    }

}
