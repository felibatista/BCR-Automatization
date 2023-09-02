package com.felipe.bcr.entitys;

public enum UserStory {
    US01(100, 2), US02(200, 12), US04(400,10);

    private int startCasesID;
    private int maxCases;

    UserStory(int startCasesID, int maxCases) {
        this.startCasesID = startCasesID;
        this.maxCases = maxCases;
    }

    public int getStartCasesID() {
        return startCasesID;
    }

    public void setStartCasesID(int startCasesID) {
        this.startCasesID = startCasesID;
    }

    public int getMaxCases() {
        return maxCases;
    }

    public void setMaxCases(int maxCases) {
        this.maxCases = maxCases;
    }
}
