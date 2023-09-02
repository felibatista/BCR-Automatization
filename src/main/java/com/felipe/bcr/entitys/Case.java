package com.felipe.bcr.entitys;

import com.felipe.bcr.Util;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Case {
    private static HashMap<UserStory, HashMap<Integer, Case>> cases;

    private UserStory userStory;

    private int id;
    private String description;
    private Status status;

    private ArrayList<String> preCondition;
    private ArrayList<String> postCondition;
    private ArrayList<String> steps;
    private ArrayList<String> expectedResults;
    private ArrayList<String> logs;

    Instant start;
    Instant finish;

    public Case(UserStory userStory, int id, String description, Status status) {
        System.out.println("Ejecutando caso de prueba #" + id + ": " + description);

        this.userStory = userStory;
        this.id = id;
        this.description = description;
        this.status = status;

        this.preCondition = new ArrayList<String>();
        this.postCondition = new ArrayList<String>();
        this.steps = new ArrayList<String>();
        this.expectedResults = new ArrayList<String>();
        this.logs = new ArrayList<String>();

        this.start = Instant.now();
        this.finish = Instant.now();

        cases.get(userStory).put(id, this);
    }

    public Case(UserStory userStory, int id, String description, ArrayList<String> preCondition, ArrayList<String> postCondition, ArrayList<String> steps, ArrayList<String> expectedResults, Status status) {
        this.userStory = userStory;
        this.id = id;
        this.description = description;
        this.preCondition = preCondition;
        this.postCondition = postCondition;
        this.steps = steps;
        this.expectedResults = expectedResults;
        this.status = status;

        this.start = Instant.now();
        this.finish = Instant.now();

        cases.get(userStory).put(id, this);
    }

    public static void generateHashMaps(){
        cases = new HashMap<UserStory, HashMap<Integer, Case>>();

        for (UserStory userStory : UserStory.values()) {
            cases.put(userStory, new HashMap<Integer, Case>());
        }
    }

    public static Collection<Case> getCasesByUserStory(UserStory userStory){
        return cases.get(userStory).values();
    }

    public static Case getCaseByUserStoryAndID(UserStory userStory, int id){
        return cases.get(userStory).getOrDefault(id, null);
    }

    public UserStory getUserHistory() {
        return userStory;
    }

    public void setUserHistory(UserStory userStory) {
        this.userStory = userStory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getPreCondition() {
        return preCondition;
    }

    public void setPreCondition(ArrayList<String> preCondition) {
        this.preCondition = preCondition;
    }

    public ArrayList<String> getPostCondition() {
        return postCondition;
    }

    public void setPostCondition(ArrayList<String> postCondition) {
        this.postCondition = postCondition;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public ArrayList<String> getExpectedResults() {
        return expectedResults;
    }

    public void setExpectedResults(ArrayList<String> expectedResults) {
        this.expectedResults = expectedResults;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void end() {
        this.finish = Instant.now();
        this.addLog("(LOG) Test finalizado en " + this.getFormatTimeElapsed() + " con estado " + this.getStatus() + ".");
    }

    public long getTimeElapsed() {
        return Duration.between(start, finish).toMillis();
    }

    public String getFormatTimeElapsed() {
        return Util.formatInterval(this.getTimeElapsed());
    }

    public void addLog(String log) {
        this.logs.add(log);
    }

    public ArrayList<String> getLogs() {
        return this.logs;
    }

    public String getLogsAsString() {
        String logs = "";

        for (String log : this.logs) {
            logs += log + "\n";
        }

        return logs;
    }

    public void clearLogs() {
        this.logs.clear();
    }

    @Override
    public String toString() {
        return "[Caso #" + this.getId() + "] " + this.getDescription() + " - " + this.getStatus() + " - " + this.getFormatTimeElapsed();
    }
}
