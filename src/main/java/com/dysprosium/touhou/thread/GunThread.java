package com.dysprosium.touhou.thread;

import com.dysprosium.touhou.model.Ball;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Dysprosium
 * @title: GunThread
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1619:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GunThread implements Runnable{
    Ball ball;
    ArrayList<Ball> bullets;
    Random ran = new Random ();

    public GunThread(Ball ball, ArrayList<Ball> bullets) {
        this.ball = ball;
        this.bullets = bullets;
    }


    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep (ran.nextInt (800)+300);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }

            System.out.println ("发射子弹");
            // 创建子弹
            int x = ball.getX ();
            int y = ball.getY ();
            // 准备小球的数据
            int size = 15;
            int speedX = 0;
            int speedY = 7;
            Color color = Color.BLACK;
            Ball bullet = new Ball (x, y, size, speedX, speedY, color);
            // 将小球添加到子弹集合中
            bullets.add (bullet);
        }
    }
}
