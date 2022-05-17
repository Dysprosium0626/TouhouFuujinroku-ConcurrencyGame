package com.dysprosium.touhou.listener;

import com.dysprosium.touhou.screen.MainScreen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;

/**
 * @author Dysprosium
 * @title: MainScreenListener
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1715:03
 */
public class MainScreenListener extends KeyAdapter {

    MainScreen mainScreen;

    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    //    @Override
//    public void keyTyped(KeyEvent e) {
//        if(e.getKeyCode() == 38 || e.getKeyCode() == 40)    mainScreen.ChangeGameMode(e.getKeyCode());
//        System.out.println(e.getKeyCode());
//    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 38 || e.getKeyCode() == 40)    mainScreen.ChangeGameMode(e.getKeyCode());
        if(e.getKeyCode() == 10) {
            switch (MainScreen.getGameModeOption()) {
                case 0:
                    mainScreen.ChangeEndFlag();
                    ExecutorService executorService = mainScreen.getGui().getExecutorService();
                    executorService.submit(mainScreen.getGui().getGenericScreenThread());
                    mainScreen.getGui().addKeyListener(mainScreen.getGui().getGenericScreenListener());
                    mainScreen.getGui().removeKeyListener(mainScreen.getGui().getMainScreenListener());
                case 1:
                    return;
                case 2:
                    System.exit(0);
            }

        }
    }
}
