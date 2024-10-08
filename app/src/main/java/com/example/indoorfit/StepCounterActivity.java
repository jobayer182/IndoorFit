package com.example.indoorfit;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.indoorfit.databinding.ActivityStepCounterBinding;
import com.example.indoorfit.permissionUtil.PermissionManager;
import com.example.indoorfit.permissionUtil.Permissions;
import com.example.indoorfit.utils.CommonUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class StepCounterActivity extends AppCompatActivity implements PermissionManager.PermissionListener, OnSuccessListener {

    int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1;

    private final String TAG = "StepCounterActivity";

    private FitnessOptions fitnessOptions;

    private FitnessDataResponseModel fitnessDataResponseModel;

    private ActivityStepCounterBinding activityStepCounterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityStepCounterBinding = DataBindingUtil.setContentView(this, R.layout.activity_step_counter);


        initialization();

        checkPermissions();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menuStep);
        setupBottomNavigationView(bottomNavigationView);

        Button updateAndSendDataButton = findViewById(R.id.sendDataToMainButton);
        updateAndSendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float stepData = fitnessDataResponseModel.steps; // Get the step data from your model
                StepDataSingleton.getInstance().setStepData(stepData); // Set the step data in the Singleton
            }
        });

    }


    private void sendStepDataToMainActivity(float stepData) {
        Intent intent = new Intent(StepCounterActivity.this, MainActivity.class);
        intent.putExtra("", stepData);
        startActivity(intent);
    }


    private void setupBottomNavigationView(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuHome:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.menuStep:
                    return true;
                case R.id.menuWorkout:
                    startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.menuNutrition:
                    startActivity(new Intent(getApplicationContext(), NutritionActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
                case R.id.menuProfile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                    return true;
            }
            return false;
        });
    }


    private void initialization() {
        fitnessDataResponseModel = new FitnessDataResponseModel();

        activityStepCounterBinding.btnLastWeekData.setOnClickListener(v -> {
            requestForHistory();
        });
    }

    private void checkPermissions() {
        if (!PermissionManager.hasPermissions(this, Permissions.LOCATION_PERMISSION)) {
            PermissionManager.requestPermissions(this, this, "", Permissions.LOCATION_PERMISSION);
        } else {
            checkGoogleFitPermission();
        }
    }

    private void checkGoogleFitPermission() {

        fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.TYPE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)

                .build();
        GoogleSignInAccount account = getGoogleAccount();

        if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    StepCounterActivity.this,
                    GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
                    account,
                    fitnessOptions);
        } else {
            startDataReading();
        }

    }

    private void startDataReading() {

        getTodayData();

        subscribeAndGetRealTimeData(DataType.TYPE_STEP_COUNT_DELTA);


    }

    /*
     * You can subscribe specific data types
     */
    private void subscribeAndGetRealTimeData(DataType dataType) {
        Fitness.getRecordingClient(this, getGoogleAccount())
                .subscribe(dataType)
                .addOnSuccessListener(aVoid -> {
                    Log.e(TAG, "Subscribed");
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Failure " + e.getLocalizedMessage());
                });

        getDataUsingSensor(dataType);

    }

    /*
     * Register Sensor Client to get Real Time Data
     */
    private void getDataUsingSensor(DataType dataType) {
        Fitness.getSensorsClient(this, getGoogleAccount())
                .add(new SensorRequest.Builder()
                                .setDataType(dataType)
                                .setSamplingRate(1, TimeUnit.SECONDS)  // sample once per minute
                                .build(),
                        new OnDataPointListener() {
                            @Override
                            public void onDataPoint(@NonNull DataPoint dataPoint) {
                                float value = Float.parseFloat(dataPoint.getValue(Field.FIELD_STEPS).toString());
                                fitnessDataResponseModel.steps = Float.parseFloat(new DecimalFormat("#.##").format(value + fitnessDataResponseModel.steps));
                                // Calculate distance and calories based on the real-time step data
                                float steps = fitnessDataResponseModel.steps;
                                float distance = steps / 2000.0f; // 2000 steps per mile
                                float calories = steps / 100.0f; // 4 kcal per 100 steps

                                // Update the model with calculated values
                                fitnessDataResponseModel.distance = distance;
                                fitnessDataResponseModel.calories = calories;

                                // Update the UI with the calculated values
                                activityStepCounterBinding.setFitnessData(fitnessDataResponseModel);
                            }
                        }
                );
    }


    private void getTodayData() {

        Fitness.getHistoryClient(this, getGoogleAccount())
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(this);
        Fitness.getHistoryClient(this, getGoogleAccount())
                .readDailyTotal(DataType.TYPE_CALORIES_EXPENDED)
                .addOnSuccessListener(this);
        Fitness.getHistoryClient(this, getGoogleAccount())
                .readDailyTotal(DataType.TYPE_DISTANCE_DELTA)
                .addOnSuccessListener(this);
    }


    private void requestForHistory() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        long endTime = cal.getTimeInMillis();

        cal.set(2021, 2, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0); //so it get all day and not the current hour
        cal.set(Calendar.MINUTE, 0); //so it get all day and not the current minute
        cal.set(Calendar.SECOND, 0); //so it get all day and not the current second
        long startTime = cal.getTimeInMillis();


        DataReadRequest readRequest = new DataReadRequest.Builder()
                .aggregate(DataType.TYPE_STEP_COUNT_DELTA)
                .aggregate(DataType.AGGREGATE_STEP_COUNT_DELTA)
                .aggregate(DataType.TYPE_CALORIES_EXPENDED)
                .aggregate(DataType.AGGREGATE_CALORIES_EXPENDED)
                .aggregate(DataType.TYPE_DISTANCE_DELTA)
                .aggregate(DataType.AGGREGATE_DISTANCE_DELTA)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build();

        Fitness.getHistoryClient(this, getGoogleAccount())
                .readData(readRequest)
                .addOnSuccessListener(this);
    }

    private GoogleSignInAccount getGoogleAccount() {
        return GoogleSignIn.getAccountForExtension(StepCounterActivity.this, fitnessOptions);
    }


    @Override
    public void onSuccess(Object o) {
        if (o instanceof DataSet) {
            DataSet dataSet = (DataSet) o;
            if (dataSet != null) {
                getDataFromDataSet(dataSet);
            }
        } else if (o instanceof DataReadResponse) {
            fitnessDataResponseModel.steps = 0f;
            fitnessDataResponseModel.calories = 0f;
            fitnessDataResponseModel.distance = 0f;
            DataReadResponse dataReadResponse = (DataReadResponse) o;
            if (dataReadResponse.getBuckets() != null && !dataReadResponse.getBuckets().isEmpty()) {
                List<Bucket> bucketList = dataReadResponse.getBuckets();

                if (bucketList != null && !bucketList.isEmpty()) {
                    for (Bucket bucket : bucketList) {
                        DataSet stepsDataSet = bucket.getDataSet(DataType.TYPE_STEP_COUNT_DELTA);
                        getDataFromDataReadResponse(stepsDataSet);
                        DataSet caloriesDataSet = bucket.getDataSet(DataType.TYPE_CALORIES_EXPENDED);
                        getDataFromDataReadResponse(caloriesDataSet);
                        DataSet distanceDataSet = bucket.getDataSet(DataType.TYPE_DISTANCE_DELTA);
                        getDataFromDataReadResponse(distanceDataSet);
                    }
                }
            }
        }

    }

    private void getDataFromDataReadResponse(DataSet dataSet) {

        List<DataPoint> dataPoints = dataSet.getDataPoints();
        for (DataPoint dataPoint : dataPoints) {
            for (Field field : dataPoint.getDataType().getFields()) {

                float value = Float.parseFloat(dataPoint.getValue(field).toString());
                Log.e(TAG, " data : " + value);

                if (field.getName().equals(Field.FIELD_STEPS.getName())) {
                    fitnessDataResponseModel.steps = Float.parseFloat(new DecimalFormat("#.##").format(value + fitnessDataResponseModel.steps));
                } else if (field.getName().equals(Field.FIELD_CALORIES.getName())) {
                    fitnessDataResponseModel.calories = Float.parseFloat(new DecimalFormat("#.##").format(value + fitnessDataResponseModel.calories));
                } else if (field.getName().equals(Field.FIELD_DISTANCE.getName())) {
                    fitnessDataResponseModel.distance = Float.parseFloat(new DecimalFormat("#.##").format(value + fitnessDataResponseModel.distance));
                }
            }
        }
        activityStepCounterBinding.setFitnessData(fitnessDataResponseModel);
    }

    private void getDataFromDataSet(DataSet dataSet) {

        List<DataPoint> dataPoints = dataSet.getDataPoints();
        for (DataPoint dataPoint : dataPoints) {
            Log.e(TAG, " data manual : " + dataPoint.getOriginalDataSource().getStreamName());

            for (Field field : dataPoint.getDataType().getFields()) {

                float value = Float.parseFloat(dataPoint.getValue(field).toString());
                Log.e(TAG, " data : " + value);

                if (field.getName().equals(Field.FIELD_STEPS.getName())) {
                    fitnessDataResponseModel.steps = Float.parseFloat(new DecimalFormat("#.##").format(value));
                } else if (field.getName().equals(Field.FIELD_CALORIES.getName())) {
                    fitnessDataResponseModel.calories = Float.parseFloat(new DecimalFormat("#.##").format(value));
                } else if (field.getName().equals(Field.FIELD_DISTANCE.getName())) {
                    fitnessDataResponseModel.distance = Float.parseFloat(new DecimalFormat("#.##").format(value));
                }
            }
        }
        activityStepCounterBinding.setFitnessData(fitnessDataResponseModel);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
            startDataReading();
        }
    }

    @Override
    public void onPermissionsGranted(List<String> perms) {
        if (perms != null && perms.size() == Permissions.LOCATION_PERMISSION.length) {
            checkGoogleFitPermission();
        }
    }

    @Override
    public void onPermissionsDenied(List<String> perms) {
        if (perms.size() > 0) {
            PermissionManager.requestPermissions(this, this, "", Permissions.LOCATION_PERMISSION);
        }
    }

    @Override
    public void onPermissionNeverAsked(List<String> perms) {
        CommonUtils.openSettingForPermission(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.onRequestPermissionsResult(this, this, requestCode, permissions, grantResults);
    }

}