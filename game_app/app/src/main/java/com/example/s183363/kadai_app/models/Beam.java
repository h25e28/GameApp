package com.example.s183363.kadai_app.models;

import java.util.LinkedList;

public class Beam extends GameCharacter{
    private Player player = null;
    private int xSpeed = 3;
    private LinkedList<Enemy1>enemy1s = new LinkedList<>();
    private LinkedList<Enemy2>enemy2s = new LinkedList<>();
    private boolean activeFlag = false;
    private OnOverlapListener onOverlapListener;

    public Beam(){
        this.xSize = 30;
        this.ySize = 16;
    }

    public void setOnOverlapListener(OnOverlapListener onOverlapListener){
        this.onOverlapListener = onOverlapListener;
    }
    public boolean isActive(){
        return activeFlag;
    }

    public void fire(){
        activeFlag=true;
        this.x = player.x+32;
        this.y = player.y+7;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public int getxSpeed(){
        return xSpeed;
    }



    public void move(){
        if(activeFlag==true){
            x = x + xSpeed;
           // this.x = player.x+32;
            this.y = player.y+5;

            if(x>2000){
                activeFlag=false;
                x=3000;

            }

            for(Enemy1 enemy1:enemy1s){
                if(isOverlapWith(enemy1) == true){
                    enemy1.destruct();
                    onOverlapListener.onOverlap(this);
                }
            }
            for(Enemy2 enemy2:enemy2s){
                if(isOverlapWith(enemy2) == true){
                    enemy2.destruct();
                    onOverlapListener.onOverlap(this);
                }
            }
        }

//        for(Enemy1 enemy1:enemy1s){
//            if(isOverlapWith(enemy1) == true){
//                enemy1.destruct();
//            }
//        }
    }

    public void setEnemy1(LinkedList<Enemy1>enemy1s){
        this.enemy1s = enemy1s;
    }

    public void setEnemy2(LinkedList<Enemy2>enemy2s){
        this.enemy2s = enemy2s;
    }
}
