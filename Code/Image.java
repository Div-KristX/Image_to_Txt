package com.company;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class Image {
    public void converter(String path) {
        try {
            System.out.println("Converting to W/B...");
            File file = new File(path);
            BufferedImage source = ImageIO.read(file);
            BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
            for (int x = 0; x < source.getWidth(); x++) {
                for (int y = 0; y < source.getHeight(); y++) {
                    Color color = new Color(source.getRGB(x, y));
                    int blue = color.getBlue();
                    int red = color.getRed();
                    int green = color.getGreen();
                    int grey = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                    int newRed;
                    newRed = grey;
                    int newGreen;
                    newGreen = grey;
                    int newBlue;
                    newBlue = grey;
                    Color newColor = new Color(newRed, newGreen, newBlue);
                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            File output = new File("WhiteBlackImage.jpg");
            ImageIO.write(result, "jpg", output);
            System.out.println("Converted to W/B");

        } catch (IOException e) {

            System.out.println("Invalid path");
        }

    }

    public void TextBuilderCustom() {
        try {
            File TextedFile = new File("Texted Image.txt");
            FileWriter writer = new FileWriter(TextedFile);
            File jpg = new File("WhiteBlackImage.jpg");
            BufferedImage source = ImageIO.read(jpg);
            boolean error = false;
            boolean symbol = false;
            String DefaultB = "*";
            String DefaultD = "&";
            String DefaultMB = "·";
            int min = 256;
            int[] ValuesColor = new int[255];
            int[] ValuesCount = new int[255];
            Scanner c = new Scanner(System.in);
            Scanner SB = new Scanner(System.in);
            Scanner SD = new Scanner(System.in);
            int count = 0;
            System.out.println("Change default symbols? (*) - for bright, (&) - for dark, (·) - for the brightest (Sometimes it can not be viewed, it is a Unicode symbol) ");
            System.out.println("Write 1 to change -");
            int chars = c.nextInt();
            if (chars == 1) {
                symbol = true;
                System.out.print("Write symbol for bright color - ");
                String newBsymb = SB.nextLine();
                System.out.print("Write symbol for dark color - ");
                String newDsymb = SD.nextLine();
                System.out.print("Write symbol for the brightest color - ");
                String newMBsymb = SD.nextLine();
                DefaultB = newBsymb;
                DefaultD = newDsymb;
                DefaultMB = newMBsymb;
                System.out.println(" ");
            } else {
                symbol = false;
                System.out.println("Symbols will not change");
            }
            for (int y = 0; y < source.getHeight(); y++) {
                for (int x = 0; x < source.getWidth(); x++) {
                    Color color = new Color(source.getRGB(x, y));
                    int RGB = color.getBlue();
                    if (min > RGB) {
                        min = RGB;
                        ValuesColor[count] = min;
                        ++count;

                    }
                }
            }
            for (int y = 0; y < source.getHeight(); y++) {
                for (int x = 0; x < source.getWidth(); x++) {
                    Color color = new Color(source.getRGB(x, y));
                    int RGB = color.getBlue();
                    for (int k = 0; k <= count; k++) {
                        if (ValuesColor[k] == RGB) {
                            ValuesCount[k] = ValuesCount[k] + 1;
                        }
                    }
                }
            }
            int xSquare = source.getWidth();
            int ySquare = source.getHeight();
            for (int i = 0; i <= count; i++) {
                System.out.println(i + "." + "\t Color = " + ValuesColor[i] + " ->" + "\t Quantity of the pixels = " + ValuesCount[i]);
            }
            System.out.println("Total count of the pixels = " + xSquare * ySquare);
            System.out.print("Choose the number of a color" + " (from " + 0 + " to " + count + ") - ");
            Scanner CN = new Scanner(System.in);
            int Number = CN.nextInt();
            if (Number > count || Number < 0) {
                System.out.println("Error, empty color detected");
                error = true;
            }
            if (error) {
                for (int y = 0; y < source.getHeight(); y++) {
                    for (int x = 0; x < source.getWidth(); x++) {
                        writer.write(" ");
                        Color color = new Color(source.getRGB(x, y));
                        int RGB = color.getBlue();
                        if (RGB == 0) {
                            writer.write("&");
                        } else {
                            writer.write(" ");
                        }
                    }
                    writer.write("\r\n");
                }
            } else {
                for (int y = 0; y < source.getHeight(); y++) {
                    for (int x = 0; x < source.getWidth(); x++) {
                        writer.write(" ");
                        Color color = new Color(source.getRGB(x, y));
                        int RGB = color.getBlue();

                        if (RGB <= ValuesColor[Number]) {
                            if (RGB <= 30) {
                                writer.write(DefaultD);
                            } else if (RGB > 30 && RGB <= 135) {
                                writer.write(DefaultB);
                            } else {
                                writer.write(DefaultMB);
                            }
                        } else {
                            writer.write(" ");
                        }

                    }
                    writer.write("\r\n");
                }
            }
            writer.flush();
            writer.close();
            System.out.println("--Success--");
            System.out.println("The file was generated in a User directory");
        } catch (IOException e) {

            System.out.println("Invalid path or Out of memory");
        }


    }

    public void TextBuilderAuto() {
        try {
            File TextedFile = new File("Texted Image.txt");
            FileWriter writer = new FileWriter(TextedFile);
            File jpg = new File("WhiteBlackImage.jpg");
            BufferedImage source = ImageIO.read(jpg);
            int min = 256;
            int[] ValuesColor = new int[255];
            int[] ValuesCount = new int[255];

            int count = 0;
            for (int y = 0; y < source.getHeight(); y++) {
                for (int x = 0; x < source.getWidth(); x++) {
                    Color color = new Color(source.getRGB(x, y));
                    int RGB = color.getBlue();
                    if (min > RGB) {
                        min = RGB;
                        ValuesColor[count] = min;
                        ++count;

                    }
                }
            }
            for (int y = 0; y < source.getHeight(); y++) {
                for (int x = 0; x < source.getWidth(); x++) {
                    Color color = new Color(source.getRGB(x, y));
                    int RGB = color.getBlue();
                    for (int k = 0; k <= count; k++) {
                        if (ValuesColor[k] == RGB) {
                            ValuesCount[k] = ValuesCount[k] + 1;
                        }
                    }
                }
            }
            int Sum = 0;
            for (int i = 0; i <= count; i++) {

                Sum = Sum + ValuesCount[i];
            }
            int Avg = Sum / count;
            int check = 0;
            for (int i = 0; i <= count; i++) {
                if (ValuesCount[i] >= Avg) {
                    check = i + 1;
                }
            }


            for (int y = 0; y < source.getHeight(); y++) {
                for (int x = 0; x < source.getWidth(); x++) {
                    writer.write(" ");
                    Color color = new Color(source.getRGB(x, y));
                    int RGB = color.getBlue();

                    if (RGB <= ValuesColor[check]) {
                        if (RGB <= 30) {
                            writer.write("&");
                        } else if (RGB > 30 && RGB <= 135) {
                            writer.write("*");
                        } else {
                            writer.write("·");
                        }
                    } else {
                        writer.write(" ");
                    }

                }
                writer.write("\r\n");
            }
            writer.flush();
            writer.close();
            System.out.println("--Success--");
            System.out.println("The file was generated in a User directory");

        } catch (IOException e) {

            System.out.println("Invalid path or Out of memory");
        }


    }
}