package com.dysprosium.touhou.listener;

import com.dysprosium.touhou.GameUI;
import com.dysprosium.touhou.model.Ball;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Dysprosium
 * @title: UIListener
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1617:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UIListener extends MouseAdapter {




    private Ball mainBall;
    private GameUI gui;
    private Graphics g;
    Random random = new Random();
    ArrayList<Ball> balls = new ArrayList<> (500);


    @Override
    public void mousePressed(MouseEvent e){
        // 获取Graphics
        if(g == null){
            g = gui.getGraphics ();
        }
        // 创建一个球
        for(int i = 0; i < 10; i++){
            int x = random.nextInt (gui.getWidth ());
            int y = random.nextInt (gui.getHeight ());
            // 准备小球的数据
            int size = random.nextInt (25) + 5;
            int speedX = random.nextInt (5) + 1;
            int speedY = random.nextInt (5) + 1;
            Color color = new Color (random.nextInt (Integer.MAX_VALUE));
            Ball ball = new Ball (x, y, size, speedX, speedY, color);
            balls.add (ball);
        }
    }

    /**
     * 拖动
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e){
        // 修改球的坐标
        mainBall.setX (e.getX ()-mainBall.getSize ()/2);
        mainBall.setY (e.getY ()-mainBall.getSize ()/2);
    }
}
