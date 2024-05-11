package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    private static final ImageLoader imageLoader = new ImageLoader();
    private static final ImageCropper imageCropper = new ImageCropper();

    private static final MedianFilter medianFilter = new MedianFilter();
    private static final BrightnessFilter brightnessFilter = new BrightnessFilter();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BufferedImage bufferedImage = null;
        System.out.print("URL or Path: ");
        String urlOrPath = scanner.nextLine();


        if (urlOrPath.equals("URL")){
            //https://th.bing.com/th/id/R.eb187dd4bb19d08450235754be39f3a8?rik=leU6LiGHD2zHcw&riu=http%3a%2f%2f1.bp.blogspot.com%2f_cUuIMoP_aeE%2fS73Lj3zCtyI%2fAAAAAAAACTQ%2fZ3k9Cyz2OfU%2fs1600%2fdog.jpg&ehk=8%2bIMId5aNcvym7X0ccrF%2bY9uae2CL0fk49MXGN8R%2b0s%3d&risl=&pid=ImgRaw&r=0
            System.out.print("String URL: ");
            String image = scanner.nextLine();
            try {
                bufferedImage = ImageIO.read(new URL(image));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (urlOrPath.equals("Path")) {
            //"C:\\Users\\User\\Desktop\\bmwSiteImg\\2023-bmw-m4-csl-dashboard-view.jpg"
            System.out.print("String path: ");
            String image = scanner.nextLine();
            File file = new File(image);
            try {
                bufferedImage = ImageIO.read(file);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        System.out.print("Choose method: ");
        String input = scanner.nextLine();

        System.out.print("Choose color --red--green--blue--all:");
        String color = scanner.nextLine();

        System.out.print("Do you want to crop the image: ");
        String croppingAnswer = scanner.nextLine();

        if (croppingAnswer.equals("Yes")) {
            System.out.print("Now type x, y, width, height: ");
            int[] data = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = data[0];
            int y = data[1];
            int width = data[2];
            int height = data[3];

            bufferedImage = imageCropper.crop(bufferedImage, x, y, width, height);
        }


        if(bufferedImage != null){

            if (input.equals("Median")){
                System.out.print("Matrix size: ");
                int matrixSizeBlurring = Integer.parseInt(scanner.nextLine());

                bufferedImage = medianFilter.blur(bufferedImage, matrixSizeBlurring, color);

                imageLoader.loadImage(bufferedImage);

            }else if(input.equals("Brightness")){
                System.out.print("Percentage: ");
                int percentage = Integer.parseInt(scanner.nextLine());

                bufferedImage = brightnessFilter.blur(bufferedImage, percentage, color);

                imageLoader.loadImage(bufferedImage);

            }else{
                System.out.print("Invalid input.");
            }

        }else {
            System.out.print("Could not load it");
        }
    }


}