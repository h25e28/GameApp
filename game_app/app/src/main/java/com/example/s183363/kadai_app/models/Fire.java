package com.example.s183363.kadai_app.models;

public class Fire extends GameCharacter {

    private  Player player = null;
    private int ySpeed = 2;
    private boolean activeFlag = false;
    private OnOverlapListener onOverlapListener;
    public Fire(int x){
        xSize = 24;
        ySize = 48;
        this.x = getRandom(100,1000);
        this.y = 600;
    }
    public boolean isActiveFlag(){
        return activeFlag;
    }
    public void setPlayer(Player player){
        this.player = player;
    }
    public void setOnOverlapListener(OnOverlapListener onOverlapListener){
        this.onOverlapListener = onOverlapListener;
    }
    public void fire(){
        if(activeFlag == false){
            this.x = x;
            this.y = y;
            activeFlag = true;
        }
    }
    public void move(){
        if(activeFlag == true){
            y = y - ySpeed;
            if(y + ySize < 0){
                activeFlag = false;
            }
        }
        if(isOverlapWith(player) == true){
            player.dead();
            onOverlapListener.onOverlap(this);
        }
    }
}
