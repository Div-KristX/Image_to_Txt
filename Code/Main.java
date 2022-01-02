package com.company;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    long SessionStart = System.currentTimeMillis();
    Image picture = new Image();
    Scanner str = new Scanner(System.in);
    Scanner ch = new Scanner(System.in);
    System.out.print("Write the path, to an image - ");
    String path = str.nextLine();
    picture.converter(path);
    System.out.print("Choose mode (1 - Auto convert, 2 - Custom convert) - ");
    int choose = ch.nextInt();

    switch (choose) {
      case (1) -> picture.TextBuilderAuto();
      case (2) -> picture.TextBuilderCustom();
      default -> System.out.println("Wrong number of the operation");
    }

    long SessionFinish = System.currentTimeMillis() - SessionStart;
    System.out.println(
        "Total time of work - " + SessionFinish / 1000 + "." + SessionFinish % 1000 + "s");

  }
}
