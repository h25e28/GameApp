package com.example.s183363.kadai_app.models;

import com.example.s183363.kadai_app.controllers.MainActivity2;

public class Goal extends GameCharacter {

    private boolean clearFlag = false;
    private Player player = null;
    private OnOverlapListener onOverlapListener;

    public Goal(int x, int y){
        xSize = 40;
        ySize = 40;
        this.x = x;
        this.y = y;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
    public boolean isClearFlag(){
        return clearFlag;
    }
    public void setOnOverlapListener(OnOverlapListener onOverlapListener){
        this.onOverlapListener = onOverlapListener;
    }

    public void move(){
        if(isOverlapWith(player) == true){
            clearFlag = true;
            onOverlapListener.onOverlap(this);
        }
    }

}
