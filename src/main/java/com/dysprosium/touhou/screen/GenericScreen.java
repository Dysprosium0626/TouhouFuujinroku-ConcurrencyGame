package com.dysprosium.touhou.screen;

import com.dysprosium.touhou.GameUI;
import com.dysprosium.touhou.listener.GenericScreenListener;
import com.dysprosium.touhou.listener.MainScreenListener;
import com.dysprosium.touhou.util.LoadImage;
import lombok.AllArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Dysprosium
 * @title: GenericScreen
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1722:14
 */
public class GenericScreen implements Runnable{

    GameUI gui;
    GenericScreenListener genericScreenListener;
    static volatile int rankSelection = 0;

    @AllArgsConstructor
    private class ImageWithAxis {
        BufferedImage image;
        int x;
        int y;
    }

    volatile boolean endFlag = false;
    public GenericScreen(GameUI gui, GenericScreenListener genericScreenListener) {
        this.gui = gui;
        this.genericScreenListener = genericScreenListener;
        this.genericScreenListener.setGenericScreen(this);
    }

    static BufferedImage genericImage;
    static BufferedImage genericImage_RANK_SELECTION;
    static BufferedImage genericImage_TITLE_ZH;
    static BufferedImage genericImage_EASY_ACTIVE;
    static BufferedImage genericImage_EASY_INACTIVE;
    static BufferedImage genericImage_NORMAL_ACTIVE;
    static BufferedImage genericImage_NORMAL_INACTIVE;
    static BufferedImage genericImage_HARD_ACTIVE;
    static BufferedImage genericImage_HARD_INACTIVE;
    static BufferedImage genericImage_LUNATIC_ACTIVE;
    static BufferedImage genericImage_LUNATIC_INACTIVE;
    static BufferedImage genericImage_EXTRA_ACTIVE;
    static BufferedImage genericImage_EXTRA_INACTIVE;

    HashMap<Integer, ImageWithAxis> rankSelectionMap = new HashMap<>(3){{
        put(0, new ImageWithAxis(genericImage_EASY_ACTIVE, 200, 100));
        put(1, new ImageWithAxis(genericImage_NORMAL_ACTIVE, 200, 200));
        put(2, new ImageWithAxis(genericImage_HARD_ACTIVE, 200, 300));
    }};

    static {

        try {
            genericImage = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(32,856,640,480);
            genericImage_RANK_SELECTION = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1919,47,217,47);
            genericImage_EASY_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(719,863,245,88);
            genericImage_EASY_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(975,863,245,88);
            genericImage_NORMAL_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(719,959,245,88);
            genericImage_NORMAL_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(975,959,245,88);
            genericImage_HARD_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(719,1055,245,88);
            genericImage_HARD_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(975,1055,245,88);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ChangeRankSelection(int commandCode) {
        if(commandCode == 38)   rankSelection = (rankSelection + 2) % 3;
        else if (commandCode == 40)   rankSelection = (rankSelection + 1) % 3;
        System.out.println("Changing game mode");
    }

    @Override
    public void run() {
        BufferedImage genericBufferedImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        Graphics2D bg = (Graphics2D) genericBufferedImage.getGraphics ();
        bg.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        bg.drawImage(genericImage,0,0,null);
        while(endFlag == false) {
//            Thread.sleep(1000);
            bg.drawImage(genericImage,0,0,null);
            bg.drawImage(genericImage_RANK_SELECTION,20,50,null);
            bg.drawImage(genericImage_EASY_INACTIVE, 200, 100, null);
            bg.drawImage(genericImage_NORMAL_INACTIVE,200,200,null);
            bg.drawImage(genericImage_HARD_INACTIVE,200,300,null);
            bg.drawImage(rankSelectionMap.get(rankSelection).image,rankSelectionMap.get(rankSelection).x,rankSelectionMap.get(rankSelection).y, null);
            gui.getGraphics().drawImage(genericBufferedImage, 0, 0, null);
        }
    }


}
