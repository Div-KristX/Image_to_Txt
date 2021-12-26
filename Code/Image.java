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
      BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(),
          source.getType());
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
    File TextedFile = new File("Texted Image.txt");
    try (FileWriter writer = new FileWriter(TextedFile)) {
      long start = System.currentTimeMillis();
      File jpg = new File("WhiteBlackImage.jpg");
      BufferedImage source = ImageIO.read(jpg);
      boolean ColorsSettings = false;
      boolean symbol = false;
      String DefaultB = "*";
      String DefaultD = "&";
      String DefaultMB = "·";
      String DefaultW = " ";
      int min = 256;
      int[] ValuesColor = new int[255];
      int[] ValuesCount = new int[255];
      Scanner c = new Scanner(System.in);
      Scanner SB = new Scanner(System.in);
      Scanner SD = new Scanner(System.in);
      int count = 0;
      System.out.println(
          "Change default symbols? \n"
              + "(&) - for dark,\n"
              + "(*) - for bright, \n"
              + "(·) - for the brightest (Sometimes it can not be viewed, it is a Unicode symbol), \n"
              + "( ) - for white");
      System.out.print("Write 1 to change - ");
      int chars = c.nextInt();

      if (chars == 1) {
        symbol = true;
        System.out.print("Write the symbol for dark color - ");
        String newDsymb = SD.nextLine();
        System.out.print("Write the symbol for bright color - ");
        String newBsymb = SB.nextLine();
        System.out.print("Write the symbol for the brightest color - ");
        String newMBsymb = SD.nextLine();
        System.out.print("Write the symbol for white color - ");
        String newWsymb = SD.nextLine();
        DefaultB = newBsymb;
        DefaultD = newDsymb;
        DefaultMB = newMBsymb;
        DefaultW = newWsymb;
        System.out.println(" ");
      } else {
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
      int check = ValuesColor[0];
      int BlackDivide;
      int GrayDivide;
      int BrightDivide;
      int White;
      BlackDivide = (int) (check * 0.245);
      GrayDivide = (int) (check * 0.254);
      BrightDivide = (int) (check * 0.267);
      White = check - BlackDivide - GrayDivide - BrightDivide;
      int xSquare = source.getWidth();
      int ySquare = source.getHeight();
      for (int i = 0; i <= count; i++) {
        System.out.println(
            i + "." + "\t Color = " + ValuesColor[i] + " ->" + "\t Quantity of the pixels = "
                + ValuesCount[i]);
      }
      System.out.println("Total count of the pixels = " + xSquare * ySquare);
      System.out.println("Change a range of colors? (1 - Yes)\n"
          + "Default:\n"
          + "White -> " + 255 + "-" + (check - White) + "\n"
          + "Bright -> " + (check - White) + "-" + (check - White - BrightDivide) + "\n"
          + "Gray -> " + (check - White - BrightDivide) + "-" + (check - White - BrightDivide
          - GrayDivide) + "\n"
          + "Black -> " + (check - White - BrightDivide - GrayDivide) + "-" + 0);
      Scanner CN = new Scanner(System.in);
      System.out.print("Answer - ");
      int Number = CN.nextInt();
      if (Number == 1) {
        ColorsSettings = true;
      }
      if (ColorsSettings) {
        System.out.println("Custom Settings");
        Scanner Colors = new Scanner(System.in);
        System.out.print("Value of Black -> ");
        int NewColors = Colors.nextInt();
        BlackDivide = NewColors;
        System.out.print("Value of Gray -> ");
        NewColors = Colors.nextInt();
        GrayDivide = NewColors;
        System.out.print("Value of Bright -> ");
        NewColors = Colors.nextInt();
        BrightDivide = NewColors;

        for (int y = 0; y < source.getHeight(); y++) {
          for (int x = 0; x < source.getWidth(); x++) {
            writer.write(" ");
            Color color = new Color(source.getRGB(x, y));
            int RGB = color.getBlue();
            if (RGB <= BlackDivide) {
              writer.write(DefaultD);
            } else if (RGB <= GrayDivide) {
              writer.write(DefaultB);
            } else if (RGB <= BrightDivide) {
              writer.write(DefaultMB);
            } else {
              writer.write(DefaultW);
            }
          }
          writer.write("\r\n");
        }
      } else {
        System.out.println("Default Settings");
        for (int y = 0; y < source.getHeight(); y++) {
          for (int x = 0; x < source.getWidth(); x++) {
            writer.write(" ");
            Color color = new Color(source.getRGB(x, y));
            int RGB = color.getBlue();

            if (RGB <= BlackDivide) {
              writer.write(DefaultD);
            } else if (RGB <= BlackDivide + GrayDivide) {
              writer.write(DefaultB);
            } else if (RGB <= BlackDivide + GrayDivide + BrightDivide) {
              writer.write(DefaultMB);
            } else {
              writer.write(DefaultW);
            }

          }
          writer.write("\r\n");
        }
      }
      writer.flush();
      System.out.println("--Success--");
      System.out.println("The file was generated");
      System.out.println("Path: " + TextedFile.getName());
      long finish = System.currentTimeMillis() - start;
      System.out.println(
          "Total time of work (Custom Convertor) - " + finish / 1000 + "." + finish % 1000 + "s");
    } catch (IOException e) {

      System.out.println("Invalid path or Out of memory");
    }


  }

  public void TextBuilderAuto() {
    File TextedFile = new File("Texted Image.txt");
    try (FileWriter writer = new FileWriter(TextedFile)) {
      long start = System.currentTimeMillis();
      File jpg = new File("WhiteBlackImage.jpg");
      BufferedImage source = ImageIO.read(jpg);
      int min = 256;
      int[] ValuesColor = new int[255];
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

      int check = ValuesColor[0];
      int BlackDivide;
      int GrayDivide;
      int BrightDivide;
      BlackDivide = (int) (check * 0.245);
      GrayDivide = (int) (check * 0.254);
      BrightDivide = (int) (check * 0.267);

      // Draw
      for (int y = 0; y < source.getHeight(); y++) {
        for (int x = 0; x < source.getWidth(); x++) {
          writer.write(" ");
          Color color = new Color(source.getRGB(x, y));
          int RGB = color.getBlue();

          if (RGB <= BlackDivide) {
            writer.write("&");
          } else if (RGB <= BlackDivide + GrayDivide) {
            writer.write("*");
          } else if (RGB <= BlackDivide + GrayDivide + BrightDivide) {
            writer.write("·");
          } else {
            writer.write(" ");
          }

        }
        writer.write("\r\n");
      }
      writer.flush();

      System.out.println("--Success--");
      System.out.println("The file was generated");
      System.out.println("Path: " + TextedFile.getName());
      long finish = System.currentTimeMillis() - start;
      System.out.println(
          "Total time of work (Auto Convertor)- " + finish / 1000 + "." + finish % 1000 + "s");

    } catch (IOException e) {
      System.out.println("Invalid path or Out of memory");
    }


  }
}