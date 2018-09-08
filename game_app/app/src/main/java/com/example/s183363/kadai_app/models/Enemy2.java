package com.example.s183363.kadai_app.models;

import java.io.Serializable;

public class Enemy2 extends GameCharacter {

    private Player player = null;
    private int xSpeed = -2;
    private float ySpped = 0;
    private boolean jumpFlag = false;
    private OnOverlapListener onOverlapListener;
    private boolean activeFlag = true;

    public Enemy2(int x){
        xSize = 46;
        ySize = 46;
        this.x = getRandom(50,1500);
        this.y = getRandom(0, 1);
    }

    public boolean isActiveFlag(){
        return activeFlag;
    }
    public int getxSpeed(){
        return xSpeed;
    }

    public  void setPlayer(Player player){
        this.player = player;
    }

    public void setOnOverlapListener (OnOverlapListener onOverlapListener){
        this.onOverlapListener = onOverlapListener;
    }

    public void destruct(){
        activeFlag = false;
    }

    public void jump(){
        ySpped = 6.5f;
        jumpFlag = true;
    }

    public void move(){
        if(activeFlag == true){
            x = x + xSpeed;
            if(isOverlapWith(player) == true){
                player.dead();
                onOverlapListener.onOverlap(this);
            }
            if(jumpFlag == false){
                jump();
            }
            y = (int) (y + ySpped);
            ySpped = ySpped + World.GRAVITY;

            if(y < 0){
                y = 0;
                ySpped = 0;
                jumpFlag = false;
            }
        }

    }
}