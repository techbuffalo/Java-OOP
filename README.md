# Java OOP Foundational Skills - CMU Graduate Work

## Lab 1:
  Taking in input from the user into an array. Then sorting that array alphabetically and searching for a name in the array, irregardless of capitilizaiton. 
  After finding the target name, the program returns its placement in the array.
## Lab 2:
  Finding the factoral of a number that is provided by the users input. One lesson learned through this lab is the use of BigInteger to handle the limitation of 
  java in the scenerio of large integers.
## Lab 3:
  Reading the contents of a CSV file or TXT file and returning information on the structure as a slight exploratory data analysis action. The CSV class will 
  return to screen the number of columns and rows in the file. The Regular class will return the number of words in the TXT.
      **This has been edited to accept input of any kind of word document (i.e. docx, pdf, pages, RTF, etc.) and edited to read .xlsx and other CSV type of files**
## Lab 4:
  Implementing various classes to display an ordering menu to the user. When the user makes their choice of food from the menu, they will have a running total of 
  calories consumed displayed and will be prompted to order more food or close out. 
## Lab 5:
  This is a re-working of Lab 3 but utilizing JavaFX to create a grid that the user can interact with. There are two buttons, one for implementing the CSV class and
  the other for the Regular class. When the button is clicked the rows, columns, or word count is provided in the results plane.
## Lab 6:
  This lab was based around creating a calculator through JavaFX. It has input areas where the user can put in numerators and denomenators, then with these two fractions it adds them up, and prints their sum in their reduced-fraction form, and in decimal form. It handles division by zero also via the built in methods from Java Double Float, and the interactive buttons will clear or run the calaculations.
## Lab 7:
  This lab takes a roster of students and then adds all inputs into an array list. From here the roster is sorted from the array into three different outputs, one filtered by First Name, one by Last Name, and one by their game score. The lesson learned here was not to simply move strings or floats into an array list but to utilize a comparator within the file so that the comparator can be called on for each method rather than three different comparisons being done on the same array list.
## Lab 8:
  In this lab we practice maps and I use a file Dictionary.txt that contains words, some with multiple definitions and some with one. The program will take whatever the user inputs regardless of case and find the definitions that correlate and will then do three outputs. One is to return all the dfeinintions one line at a time, next creates a single map connection with the defenition from the word. Finally there is a mulitmap that adds each definition to an array and returns them all. Here I learned the computing value of multipmap for storing and displaing multiple values to one key!
## Lab 9:
  In this lab I implmeneted a comparator in LoanAccounts and then used LoanMaster to read in a CSV file that had four fields. Checking is done on the file to ensure there are four fields and that each has correct inputs such as numbers and no letters. Once done it will calculate the EMI for each valid account and sort them from low to high based on this EMI. The results are then put into an output CSV file.
## HW1:
  The is a simple text editor application that will search through a provided TXT document, return how many words are in the file, search the file for a specific string
  and return how many times it appears, then replace each instance with a new string the user provides. The new TXT is then displayed with the new string in place of the
  old.
## HW2:
  This application is made through JavaFX and does the same function as HW1 just through a GUI. You're able to browse for the target file, get a word count, replace strings, search them, and visually see the first string found highlighted. Whenever you replace a string the new words will be displayed on the screen.
## HW3:
  This application is through JavaFX and builds onto the other two HW files. It uses binding to dynamically update the text editor window. This means if the user were to use the Search method and find all the words 'start' and delete them. If they ran the word count method again it would return the current word count, rather than the original. In this assignment I also added a Save function for when the file is editied and a unique word count since in the previous versions 'the' and 'The' would both be registered as different words even though only their capitalization changes.
