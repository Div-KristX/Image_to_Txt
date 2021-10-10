package com.company;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
public class Image {
    public void converter(){
        try {
            File file = new File("example.jpg");
           // File file = new File("camo-forest.jpg");
            BufferedImage source = ImageIO.read(file);
            BufferedImage result = new BufferedImage(source.getWidth(),source.getHeight(),source.getType());
            for(int x =0; x<source.getWidth();x++){
                for(int y =0; y<source.getHeight();y++){
                    Color color = new Color(source.getRGB(x,y));
                    int blue = color.getBlue();
                    int red = color.getRed();
                    int green = color.getGreen();
                    int grey = (int) (red*0.299+green*0.587+blue*0.114);
                    int newRed;
                    newRed = grey;
                    int newGreen;
                    newGreen = grey;
                    int newBlue;
                    newBlue = grey;
                    Color newColor = new Color(newRed,newGreen,newBlue);
                    result.setRGB(x,y,newColor.getRGB());
                }
            }
            File output = new File ("WhiteBlackImage.jpg");
            ImageIO.write(result,"jpg",output);

        } catch (IOException e){

            System.out.println("Invalid path");
        }

    }
    public void TextBuilder()  {
        try {
            File TextedFile = new File("Texted Image.txt");
            FileWriter writer = new FileWriter(TextedFile);
            File jpg = new File("WhiteBlackImage.jpg");
            BufferedImage source = ImageIO.read(jpg);
            boolean error = false;
            int min = 256;
            int[] ValuesColor = new int[255];
            int[] ValuesCount = new int[255];

            int count = 0;
            for(int y=0;y<source.getHeight();y++){
                for(int x=0;x<source.getWidth();x++){
                    Color color = new Color(source.getRGB(x,y));
                    int RGB = color.getBlue();
                    if(min>RGB){
                        min=RGB;
                        ValuesColor[count]=min;
                        ++count;

                    }
                }
            }
            for(int y=0;y<source.getHeight();y++){
                for(int x=0;x<source.getWidth();x++){
                    Color color = new Color(source.getRGB(x,y));
                    int RGB = color.getBlue();
                    for(int k=0; k<=count;k++){
                        if(ValuesColor[k]==RGB){
                            ValuesCount[k]=ValuesCount[k]+1;
                        }
                    }
                }
            }
            int xSquare =source.getWidth();
            int ySquare =source.getHeight();
            System.out.println("Total count of the pixels = "+ xSquare*ySquare);
            for(int i =0; i<=count;i++){
                System.out.println(i+"."+ " Color = "+ValuesColor[i]+" - " + "Quantity of the pixels = " +ValuesCount[i]);
            }
            System.out.print("Choose the number of a color"+" (from "+0+" to "+count+") - " );
            Scanner CN = new Scanner(System.in);
            int Number = CN.nextInt();
            if(Number>count){
                System.out.println("Error, empty color detected");
                error=true;
            }
            if(error){
                for(int y=0;y<source.getHeight();y++){
                    for(int x=0;x<source.getWidth();x++){
                        writer.write(" ");
                        Color color = new Color(source.getRGB(x,y));
                        int RGB = color.getBlue();
                        if(RGB ==0){
                            writer.write("&");
                        }else {
                            writer.write(" ");
                        }
                    }
                    writer.write("\r\n");
                }
            }else {
                for(int y=0;y<source.getHeight();y++){
                    for(int x=0;x<source.getWidth();x++){
                        writer.write(" ");
                        Color color = new Color(source.getRGB(x,y));
                        int RGB = color.getBlue();

                         if(RGB <=ValuesColor[Number]){
                             if (RGB<=16){
                                 writer.write("&");
                             }else {
                                 writer.write("*");

                             }



                        } else {
                            writer.write(" ");
                        }


                        /*
                        if(RGB<=255&&RGB>=230){
                            writer.write(" ");
                        }
                        else if(RGB <=ValuesColor[Number]){
                            writer.write("&");
                            if(RGB<=3){
                                writer.write("&");}
                            else {
                                writer.write("*");

                            }

                        }*/
                    }
                    writer.write("\r\n");
                }}
            writer.flush();
            writer.close();

        }catch (IOException e){

            System.out.println("Invalid path or Out of memory");
        }


    }
}