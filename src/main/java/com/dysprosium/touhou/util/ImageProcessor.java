package com.dysprosium.touhou.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Dysprosium
 * @title: AlphaProcessor
 * @projectName TouhouFuujinroku
 * @description: TODO
 * @date 2022-05-171:02
 */
public class ImageProcessor {

    /**
     * Processing transparency
     */
    public static BufferedImage alphaProcess(BufferedImage bufferedImage) {
        //Get the width and height of the source image
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        //Instantiate a picture of the same size and set the type to BufferedImage.TYPE_4BYTE_ABGR, which supports rgb images for alpha channels
        BufferedImage resImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

        double grayMean = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufferedImage.getRGB(i,j);
                int r = (0xff&rgb);
                int g = (0xff&(rgb>>8));
                int b = (0xff&(rgb>>16));
                //This is the formula for calculating the gray value
                grayMean += (r*0.299+g*0.587+b*0.114);
            }
        }
        //Calculate average gray level
        grayMean = grayMean/(width*height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufferedImage.getRGB(i,j);
                //An int is 32 bits, which is stored in java in the order of abgr, i.e., alpha is the first 8 bits, and r is the last 8 bits, so you can get the rgb value as follows
                int r = (0xff&rgb);
                int g = (0xff&(rgb>>8));
                int b = (0xff&(rgb>>16));
                double gray = (r*0.299+g*0.587+b*0.114);
                //If the gray value is greater than the average gray value previously requested, set its alpha to 0, and the following should be r g b = R + (g << 8) + (b << 16) + (0 << 24);
                if (gray>grayMean){
                    rgb = r + (g << 8) + (b << 16);
                }
                resImage.setRGB(i,j,rgb);
            }
        }
        //ok, the result is a transparent BufferedImage with a light background, which can be written as a file in the way mentioned in Grayscale
        return resImage;
    }

    public static BufferedImage lumAdjustment(BufferedImage bufferedImage, int param) throws IOException {
        //Get the width and height of the source image
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        //Instantiate a picture of the same size and set the type to BufferedImage.TYPE_4BYTE_ABGR, which supports rgb images for alpha channels
        BufferedImage resImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

        double grayMean = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufferedImage.getRGB(i,j);
                int r = (0xff&rgb);
                int g = (0xff&(rgb>>8));
                int b = (0xff&(rgb>>16));
                //This is the formula for calculating the gray value
                grayMean += (r*0.299+g*0.587+b*0.114);
            }
        }
        //Calculate average gray level
        grayMean = grayMean/(width*height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufferedImage.getRGB(i,j);
                //An int is 32 bits, which is stored in java in the order of abgr, i.e., alpha is the first 8 bits, and r is the last 8 bits, so you can get the rgb value as follows
                int r = (0xff&rgb);
                int g = (0xff&(rgb>>8));
                int b = (0xff&(rgb>>16));
                double gray = (r*0.299+g*0.587+b*0.114);
                //If the gray value is greater than the average gray value previously requested, set its alpha to 0, and the following should be r g b = R + (g << 8) + (b << 16) + (0 << 24);
                if (gray>grayMean){
                    rgb = ((clamp(255) & 0xff) << 24) | ((clamp(r) & 0xff) << 16) | ((clamp(g) & 0xff) << 8)
                            | ((clamp(b) & 0xff));
                }
                resImage.setRGB(i,j,rgb);
            }
        }
        //ok, the result is a transparent BufferedImage with a light background, which can be written as a file in the way mentioned in Grayscale
        return resImage;
    }

    // 判断a,r,g,b值，大于256返回256，小于0则返回0,0到256之间则直接返回原始值
    private static int clamp(int rgb) {
        if (rgb > 255)
            return 255;
        if (rgb < 0)
            return 0;
        return rgb;
    }

}