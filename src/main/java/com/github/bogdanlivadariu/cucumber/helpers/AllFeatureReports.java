package com.github.bogdanlivadariu.cucumber.helpers;

import java.util.List;

import com.github.bogdanlivadariu.cucumber.json.models.Feature;

public class AllFeatureReports {

    private List<Feature> features;

    private int scenariosTotal;

    private int scenariosTotalPassed;

    private int scenariosTotalFailed;

    private int stepsTotal;

    private int stepsTotalPassed;

    private int stepsTotalFailed;

    private int stepsTotalSkipped;

    private long totalDuration;

    public AllFeatureReports(List<Feature> features) {
        this.features = features;
        for (Feature feature : this.features) {
            scenariosTotal += feature.getElements().length;
            scenariosTotalPassed += feature.getScenariosPassedCount();
            scenariosTotalFailed += feature.getScenariosFailedCount();

            stepsTotal += feature.getStepsTotalCount();
            stepsTotalPassed += feature.getStepsPassedCount();
            stepsTotalFailed += feature.getStepsFailedCount();
            stepsTotalSkipped += feature.getStepsSkippedCount();

            totalDuration += feature.getTotal_duration();
        }
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public int getScenariosTotal() {
        return scenariosTotal;
    }

    public int getScenariosTotalPassed() {
        return scenariosTotalPassed;
    }

    public int getScenariosTotalFailed() {
        return scenariosTotalFailed;
    }

    public int getStepsTotal() {
        return stepsTotal;
    }

    public int getStepsTotalPassed() {
        return stepsTotalPassed;
    }

    public int getStepsTotalFailed() {
        return stepsTotalFailed;
    }

    public int getStepsTotalSkipped() {
        return stepsTotalSkipped;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public int getFeaturesCount() {
        return features.size();
    }
}
