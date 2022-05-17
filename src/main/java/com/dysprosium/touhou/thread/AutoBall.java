package com.dysprosium.touhou.thread;

import com.dysprosium.touhou.GameUI;
import com.dysprosium.touhou.model.Ball;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Dysprosium
 * @title: AutoBall
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1619:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoBall implements Runnable{
    private GameUI gui;
    private ArrayList<Ball> balls;
    private ArrayList<Ball> bullets;

    public AutoBall(GameUI gui, ArrayList<Ball> balls, ArrayList<Ball> bullets) {
        this.gui = gui;
        this.balls = balls;
        this.bullets = bullets;
    }

    Random ran = new Random ();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int x = ran.nextInt(gui.getWidth());
        int y = 0;
        int size = ran.nextInt (50) + 25;
        int speedX = ran.nextInt (5) + 1;
        int speedY = ran.nextInt (5) + 1;
        Color color = new Color (ran.nextInt (Integer.MAX_VALUE));
        Ball ball = new Ball (x, y, size, speedX, speedY, color);
        balls.add (ball);
        System.out.println("add ball");
        new Thread (new GunThread (ball, bullets)).start ();

    }
}
