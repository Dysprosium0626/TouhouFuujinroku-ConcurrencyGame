package com.dysprosium.touhou.listener;

import com.dysprosium.touhou.screen.GenericScreen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Dysprosium
 * @title: GenericScreenListener
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1722:19
 */
public class GenericScreenListener extends KeyAdapter {

    GenericScreen genericScreen;

    public void setGenericScreen(GenericScreen genericScreen) {
        this.genericScreen = genericScreen;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 38 || e.getKeyCode() == 40)    genericScreen.ChangeRankSelection(e.getKeyCode());
//        if(e.getKeyCode() == 10)    mainScreen.EnterGameMode();
    }
}
