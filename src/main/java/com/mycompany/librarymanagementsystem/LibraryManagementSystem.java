package com.mycompany.librarymanagementsystem;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author brdde
 */
public class LibraryManagementSystem {

    public static void main(String[] args) {
        
        // Intro image for fun :)
        String introMessage = """
              __...--~~~~~-._   _.-~~~~~--...__
            //    LIBRARY    `V'      BY       \\\\ 
           //     MANAGER     |  DEWALD BREED   \\\\ 
          //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\ 
         //__.....----~~~~._\\ | /_.~~~~----.....__\\\\
         ===================\\\\|//====================
                            `---`
        -----------------------------------------------""";
     
        System.out.println(introMessage); 
        Scanner scan = new Scanner(System.in);
        // Choice used for switch case below
        int choice; 
        // Instanciate a new Library object.
        Library library = new Library(); 
        
        /**
         * This loop continues to display the menu, prompting the user for an
         * option until "0" is input, at which point the loop exits and the 
         * program ends.
         */
        do {
            // Print the navigation menu at the start of each loop through.
            showMenu();
            
            // Prompt and validate input before passing choice to the switch.
            while(true){
                
                System.out.print("Option: ");
                
                try {
                    choice = Integer.parseInt(scan.nextLine());
                    if(choice > 8 || choice < 0){
                        throw new Exception();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(
                            "Invalid Choice. Please input a valid digit (0 to 8)"
                    );
                }
            }
            

            /**
             * Switch statement with corresponding menu choice procedures.
             * See the Book, Member and Library classes for further documentation
             * on each method.
             */
            switch (choice) {
                /**
                 * Add new books to the library by creating a new Book and
                 * passing it to the library.addBook method.
                 */
                case 1: 
                    Book newBook = new Book();
                    library.addBook(newBook);
                    break;
                /**
                 * View all books by passing the libraryBooks object to the 
                 * viewBooks() method.
                 */    
                case 2:
                    library.viewBooks(library.libraryBooks);
                    break;
                /**
                 * Search for books by calling searchBooks() and
                 * passing the returned List to viewBooks().
                 */    
                case 3:
                    List<Book> bookSearchResults = library.searchBooks();
                    library.viewBooks(bookSearchResults);
                    break;
                /**
                 * Add new members by creating a new Member and passing it to
                 * the addMember() method.
                 */    
                case 4: 
                    Member newMember = new Member();
                    library.addMember(newMember);
                    break;

                // View all Members by passing libraryMembers to viewMembers().
                case 5: 
                    library.viewMembers(library.libraryMembers);
                    break;
                /**
                 * Search for Members by calling searchMembers() and passing
                 * the returned List to viewBooks().
                 */    
                case 6: 
                    List<Member> memberSearchResults = library.searchMembers();
                    library.viewMembers(memberSearchResults);
                    break;

                //Checkout books by calling checkOut() method.
                case 7:
                    library.checkOut();
                    break;

                //Checkin books by calling checkIn() method. 
                case 8:
                    library.checkIn();
                    break;
            }
        } while (choice != 0);// Program exit condition
    }
    
    /**
     * Method prints the navigation menu.
     */
    public static void showMenu(){
        
        System.out.println("""
                           What Would You Like To Do?
                           -----------------------------------------------
                           Press 0 to Exit 
                           Press 1 to Add New Book 
                           Press 2 to View All Books 
                           Press 3 to Search For Book 
                           Press 4 to Register New Member 
                           Press 5 to View All Members 
                           Press 6 to Search for Member
                           Press 7 to Check Out Book 
                           Press 8 to Check In Book 
                           -----------------------------------------------""");
    }
}
