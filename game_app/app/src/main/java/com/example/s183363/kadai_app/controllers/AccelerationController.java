package com.example.s183363.kadai_app.controllers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

/**
 * Created by fyoshida on 2018/07/04.
 */

public class AccelerationController implements android.hardware.SensorEventListener {
    public float x;
    public float y;
    public float z;

    SensorManager sensorManager;
    Sensor sensor;

    // センサーの初期設定
    public AccelerationController(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void start() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);

    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    // イベントリスナー用メソッド
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
        }
    }

    // イベントリスナー用メソッド
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 今回は使用しない
    }
}
