package com.felipe.bcr;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

public class Case {
    private static HashMap<Integer, Case> cases = new HashMap<Integer, Case>();
    private int id;
    private String description;
    private ArrayList<String> preCondition;
    private ArrayList<String> postCondition;
    private ArrayList<String> steps;
    private ArrayList<String> expectedResults;
    private Status status;

    Instant start;
    Instant finish;

    public Case(){
        this.start = Instant.now();
        this.finish = Instant.now();
    }

    public Case(int id, String description, Status status) {
        this.id = id;
        this.description = description;
        this.status = status;

        this.start = Instant.now();
        this.finish = Instant.now();

        cases.put(id, this);
    }

    public Case(int id, String description, ArrayList<String> preCondition, ArrayList<String> postCondition, ArrayList<String> steps, ArrayList<String> expectedResults, Status status) {
        this.id = id;
        this.description = description;
        this.preCondition = preCondition;
        this.postCondition = postCondition;
        this.steps = steps;
        this.expectedResults = expectedResults;
        this.status = status;

        this.start = Instant.now();
        this.finish = Instant.now();

        cases.put(id, this);
    }

    public static Case getCaseByID(int id){
        return cases.get(id);
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
    }

    public long getTimeElapsed() {
        return Duration.between(start, finish).toMillis();
    }

    public String getFormatTimeElapsed() {
        return Util.formatInterval(this.getTimeElapsed());
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", preCondition=" + preCondition +
                ", postCondition=" + postCondition +
                ", steps=" + steps +
                ", expectedResults=" + expectedResults +
                ", status=" + status +
                '}';
    }

    public static HashMap<Integer, Case> getCases() {
        return cases;
    }

    public void setCases(HashMap<Integer, Case> cases) {
        Case.cases = cases;
    }
}
