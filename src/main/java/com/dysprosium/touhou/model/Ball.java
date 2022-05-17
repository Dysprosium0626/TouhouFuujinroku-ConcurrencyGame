package com.dysprosium.touhou.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * @author Dysprosium
 * @title: Ball
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1617:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ball {

    private int x, y, size, speedX, speedY;
    private Color color;

    public void drawBall(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }

    public void move(){
        if(x < 0 || x > 800 - size){
            speedX = -speedX;
        }
        if(y < 0 || y > 800 - size){
            speedY = -speedY;
        }
        x += speedX;
        y += speedY;
    }


}
