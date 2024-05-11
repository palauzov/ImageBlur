package org.example;

import java.awt.image.BufferedImage;

public class ImageCropper {

    public ImageCropper(){

    }
    public BufferedImage crop(BufferedImage image, int x, int y, int width, int height){
        x = Math.max(0, x);
        y = Math.max(0, y);
        width = Math.min(width, image.getWidth() - x);
        height = Math.min(height, image.getHeight() - y);

        return image.getSubimage(x, y, width, height);
    }
}
