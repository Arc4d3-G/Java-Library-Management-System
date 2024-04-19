# Library Management System - By Dewald Breed

## About

This is a CLI app written in Java for a school project. It's primary purpose is to manage a library's books and members in a simple and user-friendly manner. In its current state it offers the following functionality:

- Add books to Library records by providing the book's ISBN number, Title and Author.
- Add new members to the library records by providing the member's name and Email.
- View all recorded books & registered members in an easy to understand table format.
- Check in/out books by associating the book with a registered member, which is reflected in their record.
- Search for recorded books by title or author.
- Search for recorded members by name or email.

## Objectives

### Task 1: Implementing the Book and Member Classes
- This project successfully implements Book and Member Classes with their relevant properties.

### Task 2: Managing Collections
- This project utilises collections for storing and managing Book and Member objects.

### Task 3: Implementing Search Functionality
- This project includes both a book and member search functionality by title/author or name/email.

### Task 4: Handling Book Checkouts
- This project has multiple exeption checks and assertions to ensure books cannot be checked out if the book is not availible.

### Task 5: Assertions and Input Validation
- This project utilised both regular expressions, exceptions and assertions to ensure all data input is valid.

# How to Use

Navigation in the app is handled by a simple numeric menu system. Input the corresponding number for each option listed in the menu to start the desired process.

### Option 0 - Exit Program

1. Input "0" in the main menu to quit the application.

### Option 1 - Add New Book

1. Input "1" to start the process.
2. When prompted, input the Book's ISBN number. ISBN number can only consist of digits, and needs to be 10-13 digits in length.
3. Next, input the book's title followed by the author. The title/author can not be empty.
4. Finally, set the book's availability status by inputting either the character "Y" for yes or "N"  for no(case insensitive).
5. Note that duplicate titles and authors are allowed, but unique ISBN numbers are required.
6. If all inputs are valid, the book will be added to the records and can be viewed by Option 2.

### Option 2 - View All books

1. Input "2" to start the process.
2. This option will immediately print all the recorded books in a table format.
3. If no books have been recorded, It states that no results are found.

### Option 3 - Search For Book

1. Input "3" to start a new search.
2. The user will be prompted to specify wether the search will be by title or author. Input either "1" or "2" to proceed.
3. Proceed to enter the search keyword. Note that the key is not case sensitive, and can contain the entire or partial title/author name. 
4. If any matches are found, their details will be displayed similar to Option 2's book table.
5. If no matches are found, a message will be printed stating so.

### Option 4 - Register New Member

1. Input "4" to start the process.
2. The user is then prompted to input a name. Names can consist of single or multiple words, but has a character limit of 50. Additionally, Names cannot be empty.
3. Next the member's email address is required. Note that the input has to be a valid email address.
4. If all inputs are valid, the member will be added to the records, which can be viewed by Option 5.

### Option 5 - View All Members

1. Input "5" to start the process.
2. This option will immediately print all the recorded members in a table format. Additionally it will display any books the member is currently borrowing.
3. If no members have been recorded, It states that no results are found.

### Option 6 - Search for Member

1. Input "6" to start a new search.
2. The user will be prompted to specify wether the search will be by Name or Email. Input either "1" or "2" to proceed.
3. Proceed to enter the search keyword. Note that the key is not case sensitive, and may contain the entire or partial name/email name. 
4. If any matches are found, their details will be displayed similar to Option 5's member table.
5. If no matches are found, a message will be printed stating so.

### Option 7 - Check Out Book

1. Input "7" to start the process.
2. User will be prompted to input the email of a registered member. This is the member the book being checked out will be associated with. If no member with the provided email is found, the process will abort and return to the main menu.
3. Next the ISBN number of the book to be checked out is requested. If the corresponding book is either not available for checkout, or does not exist in the records, the process is aborted.
4. If Check out is successful, the checked out book will be displayed along with the member's details when viewing all registered members (Option 5), or when searching for the member (Option 6).

### Option 8 - Check In Book

1. Input "8" to start the process.
2. User will be prompted to provide the email of the member returning a book. Note that books cannot be returned if the member is not registered. 
3. Next input the ISBN number of the book being returned. Note that checkout cannot complete if the book being returned is not associated with the member's record (i.e. the book was not checked out by this member, and thus they can't return it).


# How it works

The program consists of a main class (LibraryManagementSystem.java), 3 class files, namely: 
- Book.java 
- Member.java 
- Library.java

Additionally there is a unit test class containg a few unit tests for the Library class (LibraryTests.java)

## LibraManagementSystem.java

### main()
- This is the entry point of the program and is primarily responsible for handling menu navigation via a switch case for each Option. 
- This is also where a new Library instance is created and used for the various Option operations.
- The program will constantly loop through the menu prompt until the exit condition is met (inputting "0").
- When an Option is selected, the corresponding switch case is run, which essentially calls a method from the Library class.

### showMenu()
- Simply prints the string displaying the navigation menu options.

### promptForNewBook()
- This function houses all the input prompts and validation for creating a new Book.
- Once validated, the input is used in the Book() constructor methed, which is then returned by the function.

### promptForNewMember()
- Similarly, it houses input prompts and validation for new Members, and returns the object. 

### promptForBookSearch()
- This function houses all the input prompts and validation for initiating a new Book search.
- It returns a string array containing the type of search (title or author), and the user provided keyword.

### promptForMemberSearch()
- Similarly, it houses input and validation for initiating a new Member search.

## Book.java
Class has 4 public properties, namely: long ISBN, String title, String author, and boolean isAvailable.

### Book()
- Constructor method for new Book objects.
- Main menu Option 1 is responsible for creating new book objects via promptForNewBook() and passing the returned Book as a parameter to library.addBook() method.

## Member.java
Class has 3 public properties, namely: String name, String email, and List borrowedBooks.

### Member()
- Constructor method for new Member objects.
- Similar to the new Book() constructor, Main menu Option 4 is responsible for creating a new Member and passing it to the library.addMember() method.

## Library.java
Class is responsible for storing and manipulating Book and Member objects, and contains two properties, namely: List libraryBooks & List libraryMembers.

### addBook(Book newBook)
- Method is responsible for taking the book object passed as a parameter and adding it to libraryBooks list.
- It first ensures no book with an identical ISBN number exists in libraryBooks, and if it does it will abort the Option 1 (Add New Book) process.

### viewBooks(List<Book> results)
- This method is responsible for printing a table containing Book objects from the list passed as a parameter.
- It is designed to be reusable for both displaying All books (Option 2), and displaying search results (Option 3), and prints different messages depending on wether the passed parameter is the libraryBooks list (which contains All books), or the searchResults list created by the searchBooks() method (Option 3).

### addMember(Member newMember)
- Similar to addBook(), checking for duplicates before storing member objects in libraryMembers.

### viewMembers() 
- Similar to viewBooks(), prints a table displaying either all members or from the member search, depending on the parameter passed.

### List<Book> searchBooks(String[] searchParam)
- This method returns a new list of books which match the search parameters.
- It populates this new list by taking the user provided search key and comparing it to all the elements in libraryBooks (using equalsIgnoreCase() to ensure its case insensitive).
- If a match is found, the matching element is added to the new searchResults list.
- There's two different search loops in a switch statement for either title or author search.

### List<Member> searchMembers(String[] searchParam)
- Similarly, this method returns a new list of members which match the search parameters.
- Similar to searchBooks(), it takes a search key (either name or email) and loops through libraryMembers, adding matches to the returned searchResults.

### checkOut()
- This method has a dual purpose, as it's responsible for changing a book's "isAvailable" value to false, but it also attaches the checked out book to the member's "borrowedBooks" list to keep track of who is borrowing what.
- In order for a book to be checked out, user is first prompted for a registered member's email (the member that's borrowing the book). If the member is not registered, the process is aborted.
- Next it prompts for the book's ISBN number. Again if the book does not exists, checkout is aborted.
- At this point, if both the member and book exists, we check if the book is indeed currently available, if not we abort.
- Finally, if the book is available and everything else is valid, then the book is added to the member's borrowedBooks list and the book's isAvailable is set to false.

### checkIn()
- This method is structurally similar to checkOut()
- The key difference is that in order for a book to be returned, the member returning it has to be the same one that borrowed it. 
- IF everything is in order, the book is removed from the member's borrowedBooks, and its availability is set to true.

## LibraryTests.java
Contains various unit tests and assertions for testing Library methods.
