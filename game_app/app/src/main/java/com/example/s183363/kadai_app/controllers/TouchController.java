package com.example.s183363.kadai_app.controllers;

import android.view.MotionEvent;
import android.view.View;

import com.example.s183363.kadai_app.models.Player;

/**
 * Created by fyoshida on 2018/07/04.
 */

public class TouchController implements View.OnTouchListener{
    public View touchView;
    public MotionEvent touchEvent;
    public Player player;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchView = v;
            touchEvent = event;
        }else if (event.getAction() == MotionEvent.ACTION_UP) {
            touchView = null;
            touchEvent = null;
        }
        return true;
    }
}

