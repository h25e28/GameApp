package com.example.s183363.kadai_app.models;

import java.io.Serializable;
import java.util.LinkedList;

public class World implements Serializable {
    public static final int WORLD_WIDTH = 360;
    public static final float GRAVITY = -0.1f;

    // 登場人物一覧
    protected Player player;
    protected Goal goal;
    protected LinkedList<Enemy1> enemy1s;
    protected LinkedList<Enemy2> enemy2s;
    protected LinkedList<Beam>beams;
    protected LinkedList<Star>stars;
    protected LinkedList<Fire>fires;

    public World() {
        // リストの生成
        enemy1s = new LinkedList<>();
        enemy2s = new LinkedList<>();
        stars = new LinkedList<>();
        fires = new LinkedList<>();
        // オブジェクトの生成
        createCharacters();
        // オブジェクトの接続
        connectCharacters();
    }

    // オブジェクトの生成
    protected void createCharacters() {
    }

    // オブジェクトの接続
    protected void connectCharacters() {
    }


    // コントローラ用インタフェース
    public void move() {
        player.move();
        goal.move();
        for (Enemy1 enemy1 : enemy1s) {
            enemy1.move();
        }
        for (Enemy2 enemy2 : enemy2s) {
            enemy2.move();
        }
        for(Beam beam : beams){
            beam.move();
        }
        for(Star star : stars){
            star.move();
        }
        for(Fire fire : fires){
            fire.fire();
            fire.move();
        }
    }

    public void jump(){
        player.jump();
    }

    public void beam(){
        player.fire();
    }



    // アクセッサ
     public Player getPlayer() {
         return player;
     }

     public boolean isEnd(){
        return player.isDeadFlag();
    }

    public boolean isClear(){
        return goal.isClearFlag();
    }

    public Goal getGoal(){
        return goal;
    }

    public LinkedList<Enemy1> getEnemy1s() {
        return enemy1s;
    }

    public LinkedList<Enemy2> getEnemy2s() {
        return enemy2s;
    }

    public LinkedList<Beam> getBeams() {
        return beams;
    }

    public LinkedList<Star> getStars() {
        return stars;
    }

    public LinkedList<Fire> getFires(){
        return fires;
    }
}
