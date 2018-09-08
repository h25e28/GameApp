package com.example.s183363.kadai_app.models;

import com.example.s183363.kadai_app.controllers.TouchController;

import java.util.LinkedList;

public class Player extends GameCharacter {
    private int xMax=0;
    private float ySpeed = 0;
    private boolean jumpFlag = false;
    private boolean deadFlag = false;
    private TouchController touchController;
    private LinkedList<Beam> beams;
    private int beamNo = 0;

    public Player(int x, int y) {
        this.xSize = 32;
        this.ySize = 32;
        this.x = x;
        this.y = y;
        xSpeed = 2;
    }

    public int getxSpeed(){
        return xSpeed;
    }
    public float getySpeed(){
        return ySpeed;
    }
    public void turnRight(){
        xSpeed = 2;
    }
    public void turnLeft(){
        xSpeed = -2;
    }
    public int getxMax(){
        return xMax;
    }
    public void setTouchController(TouchController touchController){
        this.touchController = touchController;
    }
    public void setBeams(LinkedList<Beam> beams){
        this.beams = beams;
    }

    public boolean isDeadFlag(){
        return deadFlag;
    }

    public void jump(){
        if(deadFlag == false){
            ySpeed = 5.5f;
            jumpFlag = true;
        }
    }

    public void beam(){
        for(Beam beam : beams){
            beam.move();
        }
    }

    public void fire(){
        if(beamNo<10){
            Beam beam = beams.get(beamNo);
            beam.fire();
            beamNo++;
        }else{
            beamNo=0;
        }
    }

    public void dead(){
        ySpeed = 1;
        deadFlag = true;
    }

    public void move(){
        y = (int) (y + ySpeed);

        ySpeed = ySpeed + World.GRAVITY;

//        if(beam.isActiveFlag()==false){
//            beam.fire(x+32,y+5);
//        }

        x = x + xSpeed;
        if(x+xSize < 0){
            x = 0;
        }

        if(xMax < x){
            xMax = x;
        }

        if(y < 0){
            y = 0;
            ySpeed = 0;
            jumpFlag = false;
            if(deadFlag==true){
                y = -40;
            }
        }

        if(x < 0){
            x = 0;
            xSpeed = 0;
        }
    }
}
