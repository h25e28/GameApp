package com.example.s183363.kadai_app.controllers;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class BaseActivity extends AppCompatActivity implements Runnable, MediaPlayer.OnCompletionListener {
    // スレッド処理用
    Handler handler;
    long startTime = 0;

    // デバイスの表示領域
    private int deviceWindowWidth;
    private int deviceWindowHeight;

    // センサー制御用コントローラ
    AccelerationController accelerationController;
    GpsController gpsController;
    TouchController touchController;

    // センサー設定
    boolean gravityEnabled = false;
    boolean gpsEnabled = false;
    int orientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;

    // 音楽設定
    boolean mediaPlayerIsInitialized = false;
    MediaPlayer mediaPlayer;
    SoundPool soundPool;

    //=========================
    //  ライフサイクル用関数
    //=========================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ステータスバーを非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // タイトルを非表示
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 初期設定
        initializeWindow();
        initializeAccelerationController();
        initializeGpsController();
        initializeOrientation();
        initializeTouchController();
        initializeMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gravityEnabled && accelerationController != null) {
            accelerationController.start();
        }
        if (gpsEnabled && gpsController != null) {
            gpsController.start();
        }
        startThread();
        continueThread();
    }

    @Override
    protected void onPause() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        if (gravityEnabled) {
            accelerationController.stop();
        }
        if (gpsEnabled) {
            gpsController.stop();
        }
        stopThread();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //=========================
    //  スレッド処理用の関数
    //=========================
    @Override
    public void run() {
        float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
        update();
        continueThread();
    }

    private void startThread() {
        handler = new Handler();
        startTime = System.nanoTime();
    }

    private void continueThread() {
        handler.postDelayed(this, 10);
    }

    private void stopThread() {
        handler.removeCallbacks(this);
    }

    //=========================
    //  更新処理用の関数
    //=========================
    // コントローラの更新処理
    protected void update() {
    }

    //=========================
    //  初期化用の関数
    //=========================
    private void initializeWindow() {
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display dp = wm.getDefaultDisplay();
        Point point = new Point();
        dp.getSize(point);
        deviceWindowWidth = point.x;
        deviceWindowHeight = point.y;
    }

    protected void initializeAccelerationController() {
        if (gravityEnabled) {
            accelerationController = new AccelerationController(
                    (SensorManager) getSystemService(Context.SENSOR_SERVICE));
            accelerationController.start();
        } else {
            accelerationController = null;
        }
    }

    protected void initializeGpsController() {
        if (gpsEnabled) {
            gpsController = new GpsController((LocationManager) getSystemService(Context.LOCATION_SERVICE));
        }
    }

    protected void initializeTouchController() {
        touchController = new TouchController();
    }

    protected void initializeOrientation() {
        setRequestedOrientation(orientation);
    }

    protected void initializeMusic() {
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = buildSoundPool(20);
    }

    //=========================
    //  サウンド用の関数
    //=========================
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected SoundPool buildSoundPool(int poolMax) {
        SoundPool pool = null;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            pool = new SoundPool(poolMax, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            pool = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(poolMax)
                    .build();
        }
        return pool;
    }

    protected void loadMusic(int id) {
        mediaPlayer = MediaPlayer.create(this, id);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setLooping(true);
    }

    protected void setMusicVolume(float volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume, volume);
        }
    }

    protected void startMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    protected void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    protected int loadSound(int id) {
        return soundPool.load(this, id, 1);
    }

    protected void playSound(int soundId, float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this) {
            mediaPlayerIsInitialized = false;
        }
    }

    //=========================
    //  アクセッサ
    //=========================
    public int getDeviceWindowWidth() {
        return deviceWindowWidth;
    }

    public int getDeviceWindowHeight() {
        return deviceWindowHeight;
    }
}
