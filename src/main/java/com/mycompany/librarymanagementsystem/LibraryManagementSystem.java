/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarymanagementsystem;

import java.util.Scanner;

/**
 *
 * @author brdde
 */
public class LibraryManagementSystem {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int choice;
        
        String introMessage = """
        -------------------------------------------------------------
                __...--~~~~~-._   _.-~~~~~--...__
              //    LIBRARY    `V'      BY       \\\\ 
             //     MANAGER     |  DEWALD BREED   \\\\ 
            //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\ 
           //__.....----~~~~._\\ | /_.~~~~----.....__\\\\
           ===================\\\\|//====================
                              `---`
        -------------------------------------------------------------""";
     
        System.out.println(introMessage);
        System.out.println("What Would You Like To Do?");
        
        do {
            showMenu();
            choice = input.nextInt();
        } while (choice != 0);
    }
    
    public static void showMenu(){
        
        System.out.println("-------------------------------------------------------------\n"
                + "Press 0 to Exit \n"
                + "Press 1 to Add New Books \n"
                + "Press 2 to View All Books \n"
                + "Press 3 to Search For Book \n"
                + "Press 4 to Register New Member \n"
                + "Press 5 to View Members \n"
                + "Press 6 to Check Out Book \n"
                + "Press 7 to Check In Book \n"
                + "-------------------------------------------------------------");
    }
}
