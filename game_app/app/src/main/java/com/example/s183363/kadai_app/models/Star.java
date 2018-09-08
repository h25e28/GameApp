package com.example.s183363.kadai_app.models;

public class Star extends GameCharacter {

    private Player player = null;
    private OnOverlapListener onOverlapListener;

    public Star(int y){
        xSize = 32;
        ySize = 32;
        this.x = getRandom(200,1000);
        this.y = getRandom(-5,500);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setOnOverlapListener(OnOverlapListener onOverlapListener){
        this.onOverlapListener = onOverlapListener;
    }

    public void move(){
        if(isOverlapWith(player) == true){
            player.dead();
            onOverlapListener.onOverlap(this);
        }
    }
}
