package com.example.indoorfit;
import androidx.lifecycle.ViewModel;

public class StepDataSingleton {
    private static StepDataSingleton instance;
    private float stepData;

    private StepDataSingleton() {
        // Initialize your data if needed
        stepData = 0.0f;
    }

    public static StepDataSingleton getInstance() {
        if (instance == null) {
            instance = new StepDataSingleton();
        }
        return instance;
    }

    public float getStepData() {
        return stepData;
    }

    public void setStepData(float stepData) {
        this.stepData = stepData;
    }
}
