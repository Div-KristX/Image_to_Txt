package com.company;

import java.awt.image.BufferedImage;
import java.util.SortedSet;
import java.util.TreeSet;
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
      boolean FullInfo = false;
      String DefaultB = "*";
      String DefaultD = "&";
      String DefaultMB = "·";
      String DefaultW = " ";
      int IntVar = 0;

      Scanner c = new Scanner(System.in);
      Scanner SymbolsScanner = new Scanner(System.in);

      System.out.println(
          """
              Change default symbols? (1 - Yes)
              (&) - for dark,
              (*) - for bright,\s
              (·) - for the brightest (Sometimes it can not be viewed, it is a Unicode symbol),\s
              ( ) - for white""");
      System.out.print("Answer - ");
      int chars = c.nextInt();

      if (chars == 1) {
        System.out.print("Write the symbol for dark color - ");
        String newDsymb = SymbolsScanner.nextLine();
        System.out.print("Write the symbol for bright color - ");
        String newBsymb = SymbolsScanner.nextLine();
        System.out.print("Write the symbol for the brightest color - ");
        String newTBsymb = SymbolsScanner.nextLine();
        System.out.print("Write the symbol for white color - ");
        String newWhiteSymbol = SymbolsScanner.nextLine();
        DefaultB = newBsymb;
        DefaultD = newDsymb;
        DefaultMB = newTBsymb;
        DefaultW = newWhiteSymbol;
        System.out.println(" ");
      } else {
        System.out.println("Symbols will not change");
      }
      SortedSet<Integer> ValuesColorFull = new TreeSet<>();
      for (int y = 0; y < source.getHeight(); y++) {
        for (int x = 0; x < source.getWidth(); x++) {
          Color color = new Color(source.getRGB(x, y));
          int RGB = color.getBlue();
          ValuesColorFull.add(RGB);
        }
      }
      int[] ValuesColor = new int[ValuesColorFull.size()];
      int[] ValuesCount = new int[ValuesColor.length];
      for (Integer var : ValuesColorFull) {
        ValuesColor[IntVar] = var;
        IntVar++;
      }

      for (int y = 0; y < source.getHeight(); y++) {
        for (int x = 0; x < source.getWidth(); x++) {
          Color color = new Color(source.getRGB(x, y));
          int RGB = color.getBlue();
          for (int k = 0; k <= ValuesColor.length - 1; k++) {
            if (ValuesColor[k] == RGB) {
              ValuesCount[k] = ValuesCount[k] + 1;
            }
          }
        }
      }
      int check = ValuesColorFull.last();
      int BlackDivide;
      int GrayDivide;
      int BrightDivide;

      BlackDivide = (int) (check * 0.245);
      GrayDivide = (int) (check * 0.254);
      BrightDivide = (int) (check * 0.267);

      int Black, Gray, Bright;
      Black = BlackDivide;
      Gray = BlackDivide + GrayDivide;
      Bright = BlackDivide + GrayDivide + BrightDivide;

      System.out.print("Full information about this image? (1 - Yes) - ");
      int answer = SymbolsScanner.nextInt();
      if (answer == 1) {
        FullInfo = true;
      }
      if (FullInfo) {
        for (int i = 0; i <= ValuesColor.length - 1; i++) {
          System.out.println(
              i + "." + "\t Color = " + ValuesColor[i] + " ->" + "\t Quantity of the pixels = "
                  + ValuesCount[i]);
        }
      } else {
        int ColorsSum = 0;
        for (int i = 0; i < Black; i++) {
          ColorsSum = ColorsSum + ValuesCount[i];
        }
        System.out.println(
            "1." + " Color (Black)  -> " + 0 + " - " + Black + "  \t Quantity of the pixels = "
                + ColorsSum);
        ColorsSum = 0;
        for (int i = Black; i < Gray; i++) {
          ColorsSum = ColorsSum + ValuesCount[i];
        }
        System.out.println(
            "2." + " Color (Gray)   -> " + Black + " - " + Gray + " \t Quantity of the pixels = "
                + ColorsSum);
        ColorsSum = 0;
        for (int i = Gray; i < Bright; i++) {
          ColorsSum = ColorsSum + ValuesCount[i];
        }
        System.out.println(
            "3." + " Color (Bright) -> " + Gray + " - " + Bright + "\t Quantity of the pixels = "
                + ColorsSum);
        ColorsSum = 0;
        for (int i = Bright; i <= ValuesColor.length - 1; i++) {
          ColorsSum = ColorsSum + ValuesCount[i];
        }
        System.out.println(
            "4." + " Color (White)  -> " + Bright + " - " + 255 + "\t Quantity of the pixels = "
                + ColorsSum);

      }

      int xSquare = source.getWidth();
      int ySquare = source.getHeight();

      System.out.println("Total count of the pixels = " + xSquare * ySquare);
      System.out.println("Size - " + xSquare + "*" + ySquare);
      System.out.println("Change a range of colors? (1 - Yes)\n"
          + "Default:\n"
          + "White  -> " + 255 + "-" + Bright + "\n"
          + "Bright -> " + Bright + "-" + Gray + "\n"
          + "Gray   -> " + Gray + "-" + Black + "\n"
          + "Black  -> " + Black + "-" + 0);
      Scanner CN = new Scanner(System.in);
      System.out.print("Answer - ");
      int Number = CN.nextInt();
      if (Number == 1) {
        ColorsSettings = true;
      }
      if (ColorsSettings) {
        System.out.println("Custom Settings");
        Scanner Colors = new Scanner(System.in);
        System.out.print("Value of Black  -> ");
        int NewColors = Colors.nextInt();
        Black = NewColors;
        System.out.print("Value of Gray   -> ");
        NewColors = Colors.nextInt();
        Gray = NewColors;
        System.out.print("Value of Bright -> ");
        NewColors = Colors.nextInt();
        Bright = NewColors;
      } else {
        System.out.println("Default Settings");
      }
      for (int y = 0; y < source.getHeight(); y++) {
        for (int x = 0; x < source.getWidth(); x++) {
          writer.write(" ");
          Color color = new Color(source.getRGB(x, y));
          int RGB = color.getBlue();

          if (RGB <= Black) {
            writer.write(DefaultD);
          } else if (RGB <= Gray) {
            writer.write(DefaultB);
          } else if (RGB <= Bright) {
            writer.write(DefaultMB);
          } else {
            writer.write(DefaultW);
          }

        }
        writer.write("\r\n");
      }

      writer.flush();
      System.out.println("--Success--");
      System.out.println("The file was generated");
      System.out.println("Path: " + TextedFile.getAbsolutePath());
      long finish = System.currentTimeMillis() - start;
      System.out.println(
          "Total time of work (Custom Convertor) - " + finish / 1000 + "." + finish % 1000 + "s");
    } catch (IOException e) {

      System.out.println("Invalid path or Out of memory");
    }


  }

  public void TextBuilderAuto() {
    boolean HighPrecision = false;
    File TextedFile = new File("Texted Image.txt");
    try (FileWriter writer = new FileWriter(TextedFile)) {
      long start = System.currentTimeMillis();
      int check;
      File jpg = new File("WhiteBlackImage.jpg");
      BufferedImage source = ImageIO.read(jpg);
      System.out.print("Select mode (1 - Medium, 2 - High precision) - ");
      Scanner answer = new Scanner(System.in);
      int Answer = answer.nextInt();
      if (Answer == 2) {
        HighPrecision = true;
        System.out.println("Selected High");
      } else {
        System.out.println("Selected Medium");
      }
      if (HighPrecision) {
        TreeSet<Integer> ValuesColorHighPrecision = new TreeSet<>();
        for (int y = 0; y < source.getHeight(); y++) {
          for (int x = 0; x < source.getWidth(); x++) {
            Color color = new Color(source.getRGB(x, y));
            int RGB = color.getBlue();
            ValuesColorHighPrecision.add(RGB);
          }
        }
        check = ValuesColorHighPrecision.last();
      } else {
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
        check = ValuesColor[0];
      }

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
      System.out.println("Path: " + TextedFile.getAbsolutePath());
      long finish = System.currentTimeMillis() - start;
      System.out.println(
          "Total time of work (Auto Convertor)- " + finish / 1000 + "." + finish % 1000 + "s");

    } catch (IOException e) {
      System.out.println("Invalid path or Out of memory");
    }


  }
}