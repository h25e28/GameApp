package com.example.s183363.kadai_app.models;

import android.app.UiAutomation;

import java.io.Serializable;

public class Enemy1 extends GameCharacter {

    private Player player = null;
    private int xSpeed = -1;
    private OnOverlapListener onOverlapListener;
    private boolean activeFlag = true;


    public Enemy1(int x){
        xSize = 32;
        ySize = 32;
        this.x = getRandom(50,1500);
        this.y = getRandom(-5, 590);
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

    public void move(){
        if(activeFlag == true){
            x = x + xSpeed;
            if(isOverlapWith(player) == true){
                player.dead();
                onOverlapListener.onOverlap(this);
            }
        }
    }


}
