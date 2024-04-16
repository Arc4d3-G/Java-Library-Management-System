package com.mycompany.librarymanagementsystem;

import java.util.Scanner;

/**
 * Book class containing relevant public properties.
 *
 * @author brdde
 */
public class Book {

    public long ISBN;
    public String title;
    public String author;
    public boolean isAvailible;

    Scanner scan = new Scanner(System.in);

    /**
     * Book constructor contains input validation for each property
     */
    public Book() {

        System.out.println(
                "\n---- Adding New Book ----\n");
        System.out.println(
                "Please provide the book's ISBN number, Title & Author."
        );

        /**
         * Validation loop with error handling for book ISBN number. ISBN needs
         * to be 10-13 digits to be valid.
         */
        while (true) {

            System.out.print("ISBN Number (10-13 digits): ");

            try {
                String input = scan.nextLine();
                long inputAsLong = Long.parseLong(input);
                int length = input.length();

                if (length >= 10 && length <= 13) {
                    this.ISBN = inputAsLong;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid ISBN. Please ensure the ISBN cosists"
                        + " of 10-13 digits without any letters/symbols."
                );
            }
        }

        /**
         * Validation loop with error handling for book Title number. Title is
         * valid so long as it's not empty.
         */
        while (true) {

            System.out.print("Enter Book Title: ");

            try {
                String input = scan.nextLine();

                if (input.length() > 0) {
                    this.title = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid Title. Please ensure the Title is not empty."
                );
            }
        }

        /**
         * Validation loop with error handling for book Author number. Author is
         * valid so long as it's not empty.
         */
        while (true) {

            System.out.print("Enter Book Author: ");

            try {
                String input = scan.nextLine();

                if (input.length() > 0) {
                    this.author = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid Author. Please ensure the Author is not empty."
                );
            }
        }

        /**
         * Validation loop with error handling for book isAvailible prop. User
         * needs to input "Y"/"N" to set isAbailible to true/false.
         */
        while (true) {

            System.out.print(
                    "Is the Book currently availible for checkout? Y/N: "
            );

            try {
                String input = scan.nextLine();

                if ((input.toUpperCase()).equals("Y")) {
                    this.isAvailible = true;
                    break;
                } else if ((input.toUpperCase()).equals("N")) {
                    this.isAvailible = false;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid Input. Please enter either \"Y\" or \"N\"."
                );
            }
        }

    }

}
