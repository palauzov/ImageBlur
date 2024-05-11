package org.example;

import java.awt.image.BufferedImage;

public class BrightnessFilter implements Filter{
    @Override
    public BufferedImage blur(BufferedImage image, int percentage, String color) {
        BufferedImage blurredImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int p = 0;
        int rgb = 0;
        int red = 0, green = 0, blue = 0;
        int amountBrightness = (int)(percentage);

        for (int i = 0; i < blurredImg.getHeight(); i++) {
            for (int j = 0; j < blurredImg.getWidth(); j++) {
                rgb = image.getRGB(j, i);
                switch (color){
                    case "red": red = ((rgb >> 16) & 0xFF) + amountBrightness;
                        break;
                    case "green": green = ((rgb >> 8) & 0xFF) + amountBrightness;
                        break;
                    case "blue": blue = (rgb & 0xFF) + amountBrightness;
                        break;
                    case "all":  red = ((rgb >> 16) & 0xFF) + amountBrightness;
                        green = ((rgb >> 8) & 0xFF) + amountBrightness;
                        blue = (rgb & 0xFF) + amountBrightness;
                        break;
                }
                if (red > 255){red = 255;}
                if(green > 255){green = 255;}
                if(blue > 255){blue = 255;}
                p = (255<<24) | (red<<16) | (green<<8) | blue;
                blurredImg.setRGB(j, i, p);
            }
        }



        return blurredImg;
    }

}

