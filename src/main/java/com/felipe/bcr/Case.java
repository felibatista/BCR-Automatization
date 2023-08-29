package com.felipe.bcr;

import java.util.ArrayList;

public class Case {
    private int id;
    private String description;
    private ArrayList<String> preCondition;
    private ArrayList<String> postCondition;
    private ArrayList<String> steps;
    private ArrayList<String> expectedResults;
    private Status status;

    public Case(){

    }

    public Case(int id, String description, ArrayList<String> preCondition, ArrayList<String> postCondition, ArrayList<String> steps, ArrayList<String> expectedResults, Status status) {
        this.id = id;
        this.description = description;
        this.preCondition = preCondition;
        this.postCondition = postCondition;
        this.steps = steps;
        this.expectedResults = expectedResults;
        this.status = status;
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
}
