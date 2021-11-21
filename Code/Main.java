package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    Image picture = new Image();
    Scanner str = new Scanner(System.in);
    Scanner ch = new Scanner(System.in);
    System.out.print("Write the path, to an image - ");
    String path = str.nextLine();
    picture.converter(path);
    System.out.println("Choose mode (1 - Auto convert, 2 - Custom convert) - ");
    int choose = ch.nextInt();
    switch (choose) {
      case (1) -> picture.TextBuilderAuto();
      case (2) -> picture.TextBuilderCustom();
      default -> System.out.print("Wrong number of the operation");
    }

  }
}
