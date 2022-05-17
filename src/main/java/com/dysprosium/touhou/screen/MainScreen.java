package com.dysprosium.touhou.screen;

import com.dysprosium.touhou.GameUI;
import com.dysprosium.touhou.listener.MainScreenListener;
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
import java.util.ArrayList;

/**
 * @author Dysprosium
 * @title: MainScreen
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-171:10
 */
@Data
public class MainScreen implements Runnable{

    GameUI gui;
    MainScreenListener mainScreenListener;
    static volatile boolean endFlag = false;

    ArrayList<Integer> modeSelection = new ArrayList<>();

    public MainScreen(GameUI gui, MainScreenListener mainScreenListener) {
        this.gui = gui;
        this.mainScreenListener = mainScreenListener;
        this.mainScreenListener.setMainScreen(this);
    }

    static volatile int gameModeOption = 0;

    public static int getGameModeOption() {
        return gameModeOption;
    }

    static BufferedImage mainImage;
    static BufferedImage mainImage_TITLE_ZH;
    static BufferedImage mainImage_GAME_START_ACTIVE;
    static BufferedImage mainImage_GAME_START_INACTIVE;
    static BufferedImage mainImage_EXTRA_START_ACTIVE;
    static BufferedImage mainImage_EXTRA_START_INACTIVE;
    static BufferedImage mainImage_PRACTICE_START_ACTIVE;
    static BufferedImage mainImage_PRACTICE_START_INACTIVE;
    static BufferedImage mainImage_REPLAY_ACTIVE;
    static BufferedImage mainImage_REPLAY_INACTIVE;
    static BufferedImage mainImage_PLAYER_DATA_ACTIVE;
    static BufferedImage mainImage_PLAYER_DATA_INACTIVE;
    static BufferedImage mainImage_MUSIC_ROOM_ACTIVE;
    static BufferedImage mainImage_MUSIC_ROOM_INACTIVE;
    static BufferedImage mainImage_HOW_TO_PLAY_ACTIVE;
    static BufferedImage mainImage_HOW_TO_PLAY_INACTIVE;
    static BufferedImage mainImage_OPTION_ACTIVE;
    static BufferedImage mainImage_OPTION_INACTIVE;
    static BufferedImage mainImage_QUIT_ACTIVE;
    static BufferedImage mainImage_QUIT_INACTIVE;

    static {
        try {
            mainImage = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(40,48,640,480);
            mainImage_TITLE_ZH = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(40,670,320,70);
            mainImage_GAME_START_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(692,48,149,29);
            mainImage_GAME_START_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(852,48,149,29);
            mainImage_EXTRA_START_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(693,80,153,28);
            mainImage_EXTRA_START_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(854,80,153,28);
//            mainImage_PRACTICE_START_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
//            mainImage_PRACTICE_START_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
//            mainImage_REPLAY_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
//            mainImage_REPLAY_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
//            mainImage_PLAYER_DATA_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
//            mainImage_PLAYER_DATA_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
//            mainImage_MUSIC_ROOM_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
//            mainImage_MUSIC_ROOM_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
//            mainImage_HOW_TO_PLAY_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
//            mainImage_HOW_TO_PLAY_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
//            mainImage_OPTION_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
//            mainImage_OPTION_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1240,48,640,480);
            mainImage_QUIT_ACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(691,304,63,31);
            mainImage_QUIT_INACTIVE = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(770,304,63,31);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void ChangeGameMode(int commandCode) {
        if(commandCode == 38)   gameModeOption = (gameModeOption + 2) % 3;
        else if (commandCode == 40)   gameModeOption = (gameModeOption + 1) % 3;
        System.out.println("Changing game mode");
    }

    public static void ChangeEndFlag() {
        endFlag = true;
    }


    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(30);
        BufferedImage mainBufferedImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        Graphics2D bg = (Graphics2D) mainBufferedImage.getGraphics ();
        bg.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        bg.drawImage(mainImage,0,0,null);
        while(endFlag == false) {
//            Thread.sleep(1000);
            bg.drawImage(mainImage,0,0,null);
            bg.drawImage(mainImage_TITLE_ZH, 100, 300, null);
            if(gameModeOption == 0) {
                bg.drawImage(mainImage_GAME_START_ACTIVE,460,100,null);
                bg.drawImage(mainImage_EXTRA_START_INACTIVE,460,200,null);
                bg.drawImage(mainImage_QUIT_INACTIVE,460,300,null);
            } else if (gameModeOption == 1){
                bg.drawImage(mainImage_GAME_START_INACTIVE,460,100,null);
                bg.drawImage(mainImage_EXTRA_START_ACTIVE,460,200,null);
                bg.drawImage(mainImage_QUIT_INACTIVE,460,300,null);
            } else if (gameModeOption == 2){
                bg.drawImage(mainImage_GAME_START_INACTIVE,460,100,null);
                bg.drawImage(mainImage_EXTRA_START_INACTIVE,460,200,null);
                bg.drawImage(mainImage_QUIT_ACTIVE,460,300,null);
            }
            System.out.println("gameModeOption:" + gameModeOption);
            gui.getGraphics().drawImage(mainBufferedImage, 0, 0, null);
        }
        System.out.println("MainThread is ending");
    }
}
