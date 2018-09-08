package com.example.s183363.kadai_app.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.s183363.kadai_app.R;
import com.example.s183363.kadai_app.models.Beam;
import com.example.s183363.kadai_app.models.Enemy1;
import com.example.s183363.kadai_app.models.Enemy2;
import com.example.s183363.kadai_app.models.Fire;
import com.example.s183363.kadai_app.models.GameCharacter;
import com.example.s183363.kadai_app.models.Goal;
import com.example.s183363.kadai_app.models.OnOverlapListener;
import com.example.s183363.kadai_app.models.Player;
import com.example.s183363.kadai_app.models.Star;
import com.example.s183363.kadai_app.models.World;
import com.example.s183363.kadai_app.models.World1;
import com.example.s183363.kadai_app.models.World2;
import com.example.s183363.kadai_app.views.StageView;

import java.util.LinkedList;

public class MainActivity1 extends BaseActivity {

    // モデル
    World world;

    // ビュー
    StageView stageView;

    // 音源ファイル
    int bgmSound;
    int gameclearSound;
    int gameoverSound;
    int magicSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        gravityEnabled = true;

        super.onCreate(savedInstanceState);

        initialize();
    }

    // 初期化処理
    private void initialize() {
        // モデルの生成
        world = new World1();

        // ビューの生成
        stageView = new StageView(this);

        // リソースの取得
        bgmSound = loadSound(R.raw.bgm);
        gameclearSound = loadSound(R.raw.gameclear);
        gameoverSound = loadSound(R.raw.gameover);
        magicSound = loadSound(R.raw.magic);

        //BGMの設定
        loadMusic(R.raw.bgm);
        setMusicVolume(0.5f);
        startMusic();


        // イベントリスナーの接続
        for (Enemy1 enemy1 : world.getEnemy1s()) {
            enemy1.setOnOverlapListener(new Enemy1OnOverlapListener());
        }
        for (Enemy2 enemy2 : world.getEnemy2s()) {
            enemy2.setOnOverlapListener(new Enemy2OnOverlapListener());
        }
        for (Star star : world.getStars()){
            star.setOnOverlapListener(new StarOnOverlapListener());
        }
        for(Fire fire : world.getFires()){
            fire.setOnOverlapListener(new FireOnOverlapListener());
        }
        for(Beam beam:world.getBeams()){
            beam.setOnOverlapListener(new BeamOnOverlapListener());
        }

        Button retryButton = stageView.getRetryButton();
        retryButton.setOnClickListener(new RetryButtonOnClickListener());

        Button jumpButton = stageView.getJumpButton();
        jumpButton.setOnClickListener(new JumpButtonOnClickListener());

        Button beamButton = stageView.getBeamButton();
        beamButton.setOnClickListener(new beamButtonOnClickListener());

        Goal goal = world.getGoal();
        goal.setOnOverlapListener(new GoalOnOverlapListener());

//        int accelx = 0;
//        int orientation = getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            accelx = (int) accelerationController.y;
//        }
//        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//            accelx = (int) accelerationController.x;
//        }
    }


    // 更新処理
    @Override
    public void update() {
        Player player = world.getPlayer();
        if (accelerationController.x < 0) {
            player.turnRight();
        } else {
            player.turnLeft();
        }
        world.move();
        stageView.draw(world);
        LinkedList<Beam> beams = world.getBeams();
        for(Beam beam:beams){
            beam.move();
        }
        if(world.isClear()){
            Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(intent);
            finish();
        }
    }

    private class Enemy1OnOverlapListener implements OnOverlapListener {
        @Override
        public void onOverlap(GameCharacter character) {
            playSound(gameoverSound,1.0f);
        }
    }


    private class RetryButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            initialize();
        }
    }

    private class JumpButtonOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            world.jump();
        }
    }

    private class beamButtonOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            world.getPlayer().fire();
            playSound(magicSound,1.0f);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("World",world);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        world = (World) savedInstanceState.getSerializable("World");
    }



    private class BeamOnOverlapListener implements OnOverlapListener {
        @Override
        public void onOverlap(GameCharacter character) {

        }
    }

    private class Enemy2OnOverlapListener implements OnOverlapListener {
        @Override
        public void onOverlap(GameCharacter character) {
            stopMusic();
            playSound(gameoverSound,1.0f);
        }
    }

    private class StarOnOverlapListener implements OnOverlapListener {
        @Override
        public void onOverlap(GameCharacter character) {
            stopMusic();
            playSound(gameoverSound,1.0f);
        }
    }

    private class FireOnOverlapListener implements OnOverlapListener {
        @Override
        public void onOverlap(GameCharacter character) {
            stopMusic();
            playSound(gameoverSound,1.0f);
        }
    }

    private class GoalOnOverlapListener implements OnOverlapListener {
        @Override
        public void onOverlap(GameCharacter character) {
            stopMusic();
            playSound(gameclearSound,1.0f);
        }
    }


    // イベントリスナー
}
