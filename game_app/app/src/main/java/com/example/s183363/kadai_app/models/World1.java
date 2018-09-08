package com.example.s183363.kadai_app.models;

import java.util.LinkedList;

public class World1 extends World {

    protected void createCharacters(){
        player = new Player(0,0);
        goal = new Goal(1500, 200);
        beams = new LinkedList<>();

        for(int x = 200;x<1500;x=x+200){
            enemy1s.add(new Enemy1(x));
        }

        for (int x = 300; x<1500; x=x+590){
            enemy2s.add(new Enemy2(x));
        }
        for(int i = 0 ; i<10 ; i++ ){
            beams.add(new Beam());
        }
    }

    protected void connectCharacters() {
        for (Enemy1 enemy1 : enemy1s) {
            enemy1.setPlayer(player);
        }
        for (Enemy2 enemy2 : enemy2s){
            enemy2.setPlayer(player);
        }
        for(Beam beam : beams){
            beam.setEnemy1(enemy1s);
            beam.setPlayer(player);
        }
        for(Fire fire : fires){
            fire.setPlayer(player);
        }
        player.setBeams(beams);
        goal.setPlayer(player);
    }
}
