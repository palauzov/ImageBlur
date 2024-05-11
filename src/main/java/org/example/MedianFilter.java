package org.example;

import java.awt.image.BufferedImage;

public class MedianFilter implements Filter{
    @Override
    public BufferedImage blur(BufferedImage image, int matrixSize, String color) {
        BufferedImage blurredImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        int pix = 0;
        int p = 0;

        for (int j = 0; j < image.getHeight() - matrixSize + 1; j++) {
            for (int i = 0; i < image.getWidth() - matrixSize + 1; i++) {
                int sumRed = 0, sumGreen = 0, sumBlue = 0;
                for (int y = j; y < j + matrixSize; y++) {
                    for (int x = i; x < i + matrixSize; x++) {
                        if (x <= image.getWidth() && y <= image.getHeight()) {
                            int rgb = image.getRGB(x, y);
                            switch (color) {
                                case "red":
                                    sumRed += (rgb >> 16) & 0xFF;
                                    break;
                                case "green":
                                    sumGreen += (rgb >> 8) & 0xFF;
                                    break;
                                case "blue":
                                    sumBlue += rgb & 0xFF;
                                    break;
                                case "all":
                                    sumRed += (rgb >> 16) & 0xFF;
                                    sumGreen += (rgb >> 8) & 0xFF;
                                    sumBlue += rgb & 0xFF;
                            }
                        }
                    }

                }
                int divider = matrixSize * matrixSize;
                int avgRed = sumRed / divider;
                int avgGreen = sumGreen / divider;
                int avgBlue = sumBlue / divider;
                int blurredPixel = (avgRed << 16) | (avgGreen << 8) | avgBlue;
                blurredImg.setRGB(i, j, blurredPixel);
            }
        }
        return image;
    }

}
