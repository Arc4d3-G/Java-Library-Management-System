# Library Management System - By Dewald Breed

## About

This is a CLI app written in Java for a school project. Its primary purpose is to manage a library's books and members in a simple and user-friendly manner. In its current state it offers the following functionality:

- Add books to Library records by providing the book's ISBN number, Title and Author.
- Add new members to the library records by providing the member's name and Email.
- View all recorded books & registered members in an easy-to-understand table format.
- Check in/out books by associating the book with a registered member, which is reflected in their record.
- Search for recorded books by title or author.
- Search for recorded members by name or email.
- Data persistance via JSON local file storage.
- Implements a due date and late fee system.


## Objectives v2

### Task 1: Implement Due Dates and Overdue Fines
- Each book is assigned a due date on checkout, based on the library's set check out period (currently set to 7 days).
- Late fees are set at 10 (currency) per day late.
- The book class has two methods to determine if the book is overdue, and to calculate the total fee the book has incurred.

### Task 2: Background Notifications and Fine Processing
- A background process (or thread) runs every 1 minute to check if any books are overdue, calculates any late fees members incurred, and adds notifications to the notifications log.
- Notifications are added to a list which be viewed at any time by navigating to the "View Notifications" menu option.
- I've opted to only have a single thread responsible for due date calculations and notifications to avoid potential synchronization issues.

### Task 3: Persistence Using File Streams
- I've chosen to store all data in JSON format, with both the book & member classes containing a `toJSON()` method.
- The program writes all library book/member instances to `libraryData.json` via the `save()` method. The method is called after each iteration of the menu loop, meaning it will overwrite the json file every time the user returns to the main menu, as well as on exit.
- The program is also set to read from the json file upon startup and the `load()` method is responsible for parsing json back into objects. If the file cannot be parsed (data corrupt or missing), the program will continue to function, but all previous data will be overwritten and lost.

### Task 4: User Interface Improvements
- The menu has been enhanced by organizing similar options into their own sub-menu (e.g. options to view books/members/notifications are all under the `View...` menu option.)
- Viewing all books now also shows the book's due date if it is checked out.
- Viewing all members now displays their fees owed, as well as their borrowed books & their respective due dates.
- Viewing notifications will display all notifications generated during the current runtime (notifications are not saved).

# How to Use

Navigation in the app is handled by a simple numeric menu system. Input the corresponding number for each option listed in the menu to start the desired process.

### Option 0 - Exit Program

1. Input "0" in the main menu to quit the application.

### Option 1 - Add New Book

1. Input "1" to start the process.
2. When prompted, input the Book's ISBN number. ISBN number can only consist of digits and needs to be 10-13 digits in length.
3. Next, input the book's title followed by the author. The title/author cannot be empty.
4. Note that duplicate titles and authors are allowed, but unique ISBN numbers are required.
5. If all inputs are valid, the book will be added to the records and can be viewed by Option 4.

### Option 2 - Register New Member

1. Input "2" to start the process.
2. The user is then prompted to input a name. Names can consist of single or multiple words but has a character limit of 50. Additionally, Names cannot be empty.
3. Next the member's email address is required. Note that the input must be a valid email address.
4. If all inputs are valid, the member will be added to the records, which can be viewed by Option 4.


### Option 3 - Check In/Out

1. Input "3" to start the process.
2. User will be prompted to specify which operation they'd like to perform.

- Check In
1. Input "1" to start the process.
2. User will be prompted to provide the email of the member returning a book. Note that books cannot be returned if the member is not registered. 
3. Next input the ISBN number of the book being returned. Note that checkout cannot complete if the book being returned is not associated with the member's record (i.e. the book was not checked out by this member, and thus they can't return it).

- Check Out
1. Input "2" to start the process.
2. User will be prompted to input the email of a registered member. This is the member the book being checked out will be associated with. If no member with the provided email is found, the process will abort and return to the main menu.
3. Next the ISBN number of the book to be checked out is requested. If the corresponding book is either not available for checkout, or does not exist in the records, the process is aborted.
4. If check out is successful, the checked out book will be displayed along with the member's details when viewing all registered members, or when searching for the member.


### Option 4 - View...

1. Input "4" to start the process.
2. User will be prompted to specify which operation they'd like to perform.

- All Books - Input "1" to view all books in the library, along with the checked out status.

- Checked Out Books - Input "2" to specifically view only checked out books, along with details about the borrowing.

- All Members - Input "3" to view all members in the library, along with the books they have checked out.

- Notification Log - Input "4" to view all notifications generated during the current runtime.

### Option 5 - Search...

1. Input "5" to start a new search.
2. The user will be prompted to specify whether the search will be by title or author. Input either "1" or "2" to proceed.
3. Proceed to enter the search keyword. Note that the key is not case sensitive and can contain the entire or partial title/author name. 
4. If any matches are found, their details will be displayed like Option 2's book table.
5. If no matches are found, a message will be printed stating so.

