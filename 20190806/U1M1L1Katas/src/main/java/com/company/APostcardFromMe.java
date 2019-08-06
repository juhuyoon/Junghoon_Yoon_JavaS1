package com.company;

public class APostcardFromMe {
    //private String padded = String.format("|%-66s|", "John Smith");
    private String lines = "*------------------------------------------------------------------*";

    public static void main(String[] args) {
        APostcardFromMe program = new APostcardFromMe();

        System.out.println(program.lines);
        System.out.println("| John Smith                                                       |");
        System.out.println("| 123 Main Street                                                  |");
        System.out.println("| Hometown, MN 55555                                               |");
        System.out.println("|                                                                  |");
        System.out.println("|                        Jane Doe                                  |");
        System.out.println("|                        345 Mockingbird Ln.                       |");
        System.out.println("|                        Smalltown, OR 99999                       |");
        System.out.println("|                                                                  |");
        System.out.println("|                                                                  |");
        System.out.println(program.lines);
    }
}
