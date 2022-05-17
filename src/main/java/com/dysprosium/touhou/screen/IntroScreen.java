package com.dysprosium.touhou.screen;

import com.dysprosium.touhou.GameUI;
import com.dysprosium.touhou.listener.IntroScreenListener;
import com.dysprosium.touhou.util.ImageProcessor;
import com.dysprosium.touhou.util.LoadImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Dysprosium
 * @title: IntroScreen
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1622:46
 */
@Data
public class IntroScreen implements Runnable{

    GameUI gui;
    IntroScreenListener introScreenListener;
    static volatile boolean endFlag = false;

    public IntroScreen(GameUI gui, IntroScreenListener introScreenListener) {
        System.out.println("=======================Create IntroScreenThread====================");
        this.gui = gui;
        this.introScreenListener = introScreenListener;
        this.gui.addKeyListener(this.introScreenListener);
        introScreenListener.setIntroScreen(this);
    }

    volatile boolean transparentFlag = true;

    static BufferedImage introImage;
    static BufferedImage loadingImage_ZH_BOLD;
    static BufferedImage loadingImage_ZH_TRANSPARENT;
    static BufferedImage loadingImage_EN_BOLD;
    static BufferedImage loadingImage_EN_TRANSPARENT;

     static {
         try {
             introImage = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
             loadingImage_ZH_BOLD = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1244,538,115,25);
             loadingImage_ZH_TRANSPARENT = ImageProcessor.alphaProcess(loadingImage_ZH_BOLD);
             loadingImage_EN_BOLD = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1257,578,110,14);
             loadingImage_EN_TRANSPARENT = ImageProcessor.alphaProcess(loadingImage_EN_BOLD);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public static void ChangeEndFlag() {
        endFlag = true;
    }


    @SneakyThrows
    @Override
    public void run() {
//        Thread.sleep(3000);
        BufferedImage introBufferedImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        Graphics2D bg = (Graphics2D) introBufferedImage.getGraphics ();
        bg.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        bg.setColor (new Color (238, 238, 238));

        bg.drawImage(introImage,0,0,null);
        while(endFlag == false) {
            Thread.sleep(100);
            System.out.println(Thread.currentThread());
            System.out.println(Thread.currentThread().isInterrupted());
            Thread.sleep(30);
            bg.drawImage(introImage,0,0,null);
            if(transparentFlag == true) {
                bg.drawImage(loadingImage_ZH_BOLD,450,380,null);
                bg.drawImage(loadingImage_EN_TRANSPARENT,460,410,null);
            } else {
                bg.drawImage(loadingImage_ZH_TRANSPARENT,450,380,null);
                bg.drawImage(loadingImage_EN_BOLD,460,410,null);
            }
            System.out.println("transparentFlag:" + transparentFlag);
            transparentFlag = !transparentFlag;

            gui.getGraphics().drawImage(introBufferedImage, 0, 0, null);
        }
        System.out.println("IntroThread is ending");

    }



}
