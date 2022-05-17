package com.dysprosium.touhou.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Dysprosium
 * @title: LoadImage
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-1622:55
 */
public class LoadImage {
    public static final String INTRO_SCREEN_IMAGE_PATH = "src/main/resources/pics/PC Computer - Touhou 10 - Mountain of Faith - Menu and Other Screens.png";




    public static void main(String[] args) throws IOException {
        BufferedImage loadingImage = ImageIO.read(new File(LoadImage.INTRO_SCREEN_IMAGE_PATH)).getSubimage(1358,563,115,25);
        JLabel label = new JLabel(new ImageIcon(loadingImage));
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(label);
        f.pack();
        f.setVisible(true);
    }



}
