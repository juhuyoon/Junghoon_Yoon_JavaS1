package com.company;

import java.util.Scanner;

public class IsJavaKeyword {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String userInput = scan.nextLine();

        if(userInput.matches("abstract|assert|boolean|break|byte|" +
                "case|catch|char|class|const|continue|" +
                "default|double|do|" +
                "else|enum|extends|false|" +
                "final|finally|float|for|goto|if" +
                "|implements|import|instanceof|int" +
                "|interface|long|native|new|null|package|private|protected" +
                "|public|return|short|static|strictfp|super|switch|synchronized|this" +
                "|throw|throws|transient|true|try|void|volatile|while")) {
            System.out.println("is a java keyword");
        } else {
            System.out.println("not a java keyword");
        }

    }
}
