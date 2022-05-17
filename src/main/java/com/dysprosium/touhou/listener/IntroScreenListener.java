package com.dysprosium.touhou.listener;

import com.dysprosium.touhou.GameUI;
import com.dysprosium.touhou.screen.IntroScreen;
import lombok.Data;
import lombok.SneakyThrows;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;

/**
 * @author Dysprosium
 * @title: IntroScreenListener
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-171:22
 */
public class IntroScreenListener extends KeyAdapter {

    IntroScreen introScreen;

    public void setIntroScreen(IntroScreen introScreen) {
        this.introScreen = introScreen;
    }

    @SneakyThrows
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("===============INTERRUPT===================");
        introScreen.ChangeEndFlag();
        ExecutorService executorService = introScreen.getGui().getExecutorService();
        executorService.submit(introScreen.getGui().getMainScreenThread());
        introScreen.getGui().addKeyListener(introScreen.getGui().getMainScreenListener());
        introScreen.getGui().removeKeyListener(introScreen.getGui().getIntroScreenListener());

    }
}
