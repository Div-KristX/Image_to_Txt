package com.company;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.io.IOException;
import java.io.*;
public class Image {
    public void converter(){
        try {
           // File file = new File("camo-forest.jpg");
            File file = new File("Test-1.jpg");
            BufferedImage source = ImageIO.read(file);
            BufferedImage result = new BufferedImage(source.getWidth(),source.getHeight(),source.getType());
            for(int x =0; x<source.getWidth();x++){
                for(int y =0; y<source.getHeight();y++){
                    Color color = new Color(source.getRGB(x,y));
                    int blue = color.getBlue();
                    int red = color.getRed();
                    int green = color.getGreen();
                    int grey = (int) (red*0.299+green*0.587+blue*0.114);
                    int newRed = grey;
                    int newGreen = grey;
                    int newBlue = grey;
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
    public void TextBuilder() throws IOException {
        File Tfile = new File("Texted Image.txt");
       // Tfile.createNewFile();
        FileWriter writer = new FileWriter(Tfile);
        //File jpg = new File("camo-forest.jpg");
        File jpg = new File("WhiteBlackImage.jpg");
        BufferedImage source = ImageIO.read(jpg);
        // min
        int min = 255;
        
        for(int y=0;y<source.getHeight();y++){
            for(int x=0;x<source.getWidth();x++){
                Color color = new Color(source.getRGB(x,y));
                int RGB = color.getBlue();
                if(min>=RGB){
                    min=RGB;
                    System.out.println(min);
                }
            }
        }


        for(int y=0;y<source.getHeight();y++){
            for(int x=0;x<source.getWidth();x++){
                writer.write(" ");
                Color color = new Color(source.getRGB(x,y));
                int RGB = color.getBlue();


                if(RGB <=min /*35*/){
                    writer.write("*");
                }else {
                    writer.write(" ");
                }
            }
            writer.write("\r\n");



        }
        writer.flush();
        writer.close();

    }

}