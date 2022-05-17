package com.dysprosium.touhou;

import com.dysprosium.touhou.listener.GenericScreenListener;
import com.dysprosium.touhou.listener.IntroScreenListener;
import com.dysprosium.touhou.listener.MainScreenListener;
import com.dysprosium.touhou.listener.UIListener;
import com.dysprosium.touhou.model.Ball;
import com.dysprosium.touhou.screen.GenericScreen;
import com.dysprosium.touhou.screen.IntroScreen;
import com.dysprosium.touhou.screen.MainScreen;
import com.sun.tools.javac.Main;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dysprosium
 * @title: GameUI
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1617:28
 */
@Data
public class GameUI extends JFrame {
//    private UIListener uiListener = new UIListener();
    private IntroScreenListener introScreenListener = new IntroScreenListener();
    private MainScreenListener mainScreenListener = new MainScreenListener();
    private GenericScreenListener genericScreenListener = new GenericScreenListener();

    Thread introScreenThread = new Thread(new IntroScreen(this, introScreenListener));
    Thread mainScreenThread = new Thread(new MainScreen(this, mainScreenListener));
    Thread genericScreenThread = new Thread(new GenericScreen(this, genericScreenListener));

    ExecutorService executorService = Executors.newFixedThreadPool(20);

    ArrayList<Ball> balls = new ArrayList<> (500);
    ArrayList<Ball> bullets = new ArrayList<> (500);
    public GameUI() {
        initUI();

//        uiListener.setGui(this);
    }

    void initUI() {
        this.setTitle("TouhouFuujinroku");
        this.setSize(640, 480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
//        this.addKeyListener(genericScreenListener);
//        this.addMouseListener (uiListener);
//        this.addMouseMotionListener (uiListener);



//        Ball mainBall = new Ball(300,500,100,0,0, Color.BLACK);
//        uiListener.setMainBall(mainBall);
//        Thread introScreenThread = new Thread(new IntroScreen(this, introScreenListener));
        introScreenThread.setName("IntroScreenThread");
        mainScreenThread.setName("MainScreenThread");
        genericScreenThread.setName("GenericScreenThread");
//        introScreenThread.start ();
        executorService.submit(introScreenThread);
//        Thread mainScreenThread = new Thread(new MainScreen(this, mainScreenListener));

//        executorService.submit(mainScreenThread);
//        mainScreenThread.start ();

//        new Thread (new AutoBall(this, balls, bullets)).start ();
//        new Thread(new BallThread(balls, this.getGraphics(), mainBall)).start ();
//        new Thread(new BallThread(bullets, this.getGraphics(), mainBall)).start ();
//        new Thread (new MoveThread(balls)).start ();
//        new Thread (new MoveThread(bullets)).start ();

//        new Thread(new BallThread(bullets ,this.getGraphics ())).start ();
//        new Thread (new MoveThread(bullets)).start ();

    }


    public static void main(String[] args) {
        new GameUI();
    }

}
