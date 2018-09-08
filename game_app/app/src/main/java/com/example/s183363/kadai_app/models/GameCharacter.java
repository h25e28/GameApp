package com.example.s183363.kadai_app.models;

public class GameCharacter {
    protected int x = 0;
    protected int y = 0;
    protected int xSize = 0;
    protected int ySize = 0;
    protected int xSpeed = 3;
    protected int ySpeed = 3;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public int getxSpeed(){
        return xSpeed;
    }

    public void turnRight(){
        xSpeed = 3;
    }
    public void turnLeft(){
        xSpeed = -3;
    }

    public void move(){
        x = x + xSpeed;
        if(x+xSize < 0){
            x = World.WORLD_WIDTH;
        }
        if(x > World.WORLD_WIDTH){
            x = 0 - xSize;
        }
    }

    protected boolean isOverlapWith(GameCharacter character) {
        // プレーヤが自分よりも右にいたら衝突していない
        if (character.getX() + character.getxSize() - 1 < x) {
            return false;
        }

        // プレーヤが自分よりも左にいたら衝突していない
        if (x + xSize - 1 < character.getX()) {
            return false;
        }

        // プレーヤが自分よりも下にいたら衝突していない
        if (character.getY() + character.getySize() - 1 < y) {
            return false;
        }

        // プレーヤが自分よりも上にいたら衝突していない
        if (y + ySize - 1 < character.getY()) {
            return false;
        }

        // それ以外なら衝突している
        return true;
    }

    // 乱数生成
    protected int getRandom(int from, int to){
        return (int) (Math.random()*(to-from+1))+from;
    }
    protected int getRandomX(int xSize){
        return getRandom(0,World.WORLD_WIDTH-xSize);
    }



}

