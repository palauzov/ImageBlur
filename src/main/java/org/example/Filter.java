package org.example;

import java.awt.image.BufferedImage;

public interface Filter {

    BufferedImage blur(BufferedImage image, int blurRate, String color);
}
