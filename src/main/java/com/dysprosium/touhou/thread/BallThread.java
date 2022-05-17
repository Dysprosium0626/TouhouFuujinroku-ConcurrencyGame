package com.dysprosium.touhou.thread;

import com.dysprosium.touhou.model.Ball;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Dysprosium
 * @title: BallThread
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1617:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BallThread implements Runnable{
    private ArrayList<Ball> balls;
    private Graphics g;
    Ball mainBall;

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BufferedImage buffimg = new BufferedImage (800, 800, BufferedImage.TYPE_INT_RGB);
            Graphics2D bg = (Graphics2D) buffimg.getGraphics ();
            // 设置Graphics 抗锯齿
            bg.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 先清屏
            bg.setColor (new Color (238, 238, 238));
            bg.fillRect (0, 0, 800, 800);
            // 绘制主球
            mainBall.drawBall(bg);
            System.out.println("mainball");
            // 绘制其他球
            for(int i = 0; i < balls.size (); i++){
                System.out.println("balllist length = " + balls.size());
                System.out.println("ball"+"["+i+"]" + balls.get (i).toString());
                Ball ball = balls.get (i);
                ball.drawBall(bg);
            }
            // 绘制这一帧 图片
            g.drawImage (buffimg, 0, 0, null);
        }


    }
}
