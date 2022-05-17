package com.dysprosium.touhou.thread;

import com.dysprosium.touhou.model.Ball;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @author Dysprosium
 * @title: MoveThread
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1617:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveThread implements Runnable{

    private ArrayList<Ball> balls;

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep (30);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            for (int i = 0; i < balls.size(); i++) {
                if(balls.get(i).getX() < 50 || balls.get(i).getX() > 750 || balls.get(i).getY() > 700)  balls.remove(i);
            }

            for(int i = 0; i < balls.size (); i++){
                Ball ball = balls.get (i);
                ball.move();
            }
        }
    }
}
