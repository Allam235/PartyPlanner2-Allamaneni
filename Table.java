/**
*@Author: Rithvik Allamaneni
*1/19/2023
*Table class that creates list from names.csv and creates 2d array list of people and organizes them into tables
**/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
 /*
creates and organizes the 2d array of tables
no args
*/
class Table {
  int ppt = 10; // people per table
  int npo = 90; // number of people in the orignl list/csv file
  int np = 100;// number of total people allowed in the list
  int nc = 16; // number of companies
  int nt = 10; // number of tables
  String[] company = new String[16];
  // {"Wal-Mart", "Kroger", "Amazon", "Lowes", "Best Western", "KMart", "Fusian",
  // "Heinz", "Gucci", "Prada", "Nike", "Dodge", "Maserati", "Razor", "AMD",
  // "Razer"};

  ArrayList<Person> people = new ArrayList<Person>();
  ArrayList<Person> kicked = new ArrayList<Person>();
  int[] nopc = new int[nc]; // number of people per commpany

  Person[][] table = new Person[10][ppt];
  /*
  creates an array from companies.txt
 no returns and args
  */
  public void getCFile() {
    File myObj = new File("companies.txt");
    try {// read file
      Scanner myReader = new Scanner(myObj);
      String line;
      int i = 0;
      while (myReader.hasNextLine()) {// loop until their are no more lines of data
        line = myReader.nextLine();// moves to next line of data
        String[] data = line.split(",", 2); // splits the S
        company[i] = data[1];
        i++;
      } // close while
      myReader.close();
    } // close try
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } // close catch
  }// close getCfile

  /*
  creates a names arrayList from the names.csv
no args or returns
  */
  public void getNFile() {
    File myObj = new File("names.csv");
    try {// read file
      Scanner myReader = new Scanner(myObj);
      String line;
      while (myReader.hasNextLine()) {// loop until their are no more lines of data
        line = myReader.nextLine();// moves to next line of data
        String[] data = line.split(",", 4); // splits the S
        if (nopc[Integer.parseInt(data[3])] < 10 && Integer.parseInt(data[0]) <= npo) {//checks if person's company already has 10 people
          people.add(new Person(data[1] + " " + data[2], Integer.parseInt(data[3])));// adds person if there are less than 10 people in the person's company
          nopc[Integer.parseInt(data[3])]++;

        } // close if
        else {
          kicked.add(new Person(data[1] + " " + data[2], Integer.parseInt(data[3])));
        }
      } // close while
      myReader.close();
    } // close try
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } // close catch
  }// close getNFile
  /*
  asks the user if they wat to add a person to the people list manuelly and does it
no returns or args
  */
  public int manuelEnter() {
    if (people.size() >= 90) {// there can be no more people added
      System.out.println("There are more than " + np + " so this person can not be added");
      return 0;
    }
    Scanner scan = new Scanner(System.in);
    Person p;
    System.out.println("What is the First and last name of the person you want to add");
    String name = scan.nextLine();
    System.out.println("What is the company of the person you want to add(Type 1 for Wal-Mart 2 for Kroger, 3 for Amazon, 4 for Lowes, 5 for Best Western, 6 for KMart, 7 for Fusian, 8 for Heinz, 9 for Gucci, 10 for Prada, 11 for Nike, 12 for Dodge, 13 for Maserati, 14 for Razor, 15 for AMD and, 16 for Razer)");
    int company = scan.nextInt();
    while (company > 16 || company < 1) {//checks i comany number exists
      System.out.print("This is not an acceptable company number \nPlease try again:");
      company = scan.nextInt();
      System.out.println();
    }
    if (nopc[company] >= 10) {//checks if there are too many people registered with the company
      System.out.println("There are too any people in the company so this person is unable to be added");
      return 1;
    }
    nopc[company]++;
    people.add(new Person(name, company));
    System.out.println(name + " has been added to the list");
    return 1;

  }
  /*
  sorts the people arrayList by people whose company has the most number of people
no returns or args
  */
  public void sort() {
    int min;
    int[] replace = new int[nc];
    for (int i = 0; i < nc; i++) {//creates a duplicate of nopc. You have to copy each element or else the pointer gets copied
      replace[i] = nopc[i];
    }
    int[] order = new int[nc];
    for (int i = 0; i < nc; i++) {//orders the array order from lowest nopc to highest
      min = 100;// any number above 
      for (int j = 0; j < nc; j++) {//finds the company with least people in the company
        if (replace[j] < min) {
          min = replace[j];
        }
      }
      for (int j = 0; j < nc; j++) {//creates order array with least nopc to most
        if (order[i] == 0 && replace[j] == min) {
          order[i] = j;
          replace[j] = 100;
        } // close if
      } // close j for loop
    }
    int n = 0;
    boolean found;
    Person p;
    //orders the people arraylist by people whose company has the most amount of people
    for (int i = 0; i < nc; i++) {//loops through every company
      for (int k = 0; k < nopc[order[i]]; k++) {//loops the number of people a certain company has
        found = false;
        for(int j = n; j < people.size() && found == false; j++){//loops through the people arrayList, trying to find a person that belongs to the company
          if (people.get(j).gc() == order[i]) {
            //moves person to front of the list
            p = people.get(j);
            p.print();
            people.remove(j);
            people.add(0, p);
            n++;
            found = true;
          } // close if
        } // close for j loop
      } // close for k loop
    } // close for i loop

  }// close sort
 /*
  fills the 2d array table with Person objects from the arrayList people
no returns or args
  */
  public void fill() {
    boolean check;
    boolean placed;
    for (int n = 0; n < people.size(); n++) {// loops through every person in arrayList
      check = true;
      placed = false;
      for (int j = 0; j < 10 && placed == false; j++) {// loops through each table to check
        check = true;
        for (int i = 0; i < ppt && check == true; i++) {// loops through a table to check if a company exists
          if (table[j][i] != null && table[j][i].gc() == people.get(n).gc()) {// checks if there is already a person in the table that has the smae company
            check = false;
          } // close for i
        } // close for i loop
        if (check == true) {//if the table has no one from the same company
          for (int k = 0; k < ppt; k++) {
            if (table[j][k] == null && check == true) {
              table[j][k] = new Person(people.get(n).gName(), people.get(n).gc(), j + 1, k + 1);//adds Person to 2d table array
              people.set(n, new Person(people.get(n).gName(), people.get(n).gc(), j + 1, k + 1));//reads Person to people arrayList but with the table ID
              check = false;
              placed = true;
            } // close if
          } // close k for loop
        } // close second if
      } // close j for loop
    } // close for n loop
    System.out.println("The following people have been kicked: ");
    print(kicked);
  }// close fill
 /*
  prints a table
no returns takes table id as argument
  */
  public void tprint(int n) {//prints entire table
    n--;
    for (int i = 0; i < ppt; i++) {
      if (table[n][i] == null) {
        System.out.println("null");
      } else {
        System.out.println(table[n][i].print());
      }
    }
    System.out.println();
  }// close tprint
 /*
  prints an entire company
no returns and takes company id as arg
  */
  public void cprint(int c){
    System.out.println("The following people have signed up with " + company[c-1] + " Id:" + c);
    for (int i = 0; i < people.size(); i++) {
      if (people.get(i).gc() == c) {
        System.out.println(people.get(i).print(company));
      }
    }
    System.out.println();
  }//close cprint
 /*
  searches for a person and prints them
  returns int and takes a Person name as arg
  */
  public int pprint(String name) {
    for (int i = 0; i < people.size(); i++) {
      if (people.get(i).gName().equals(name)) {
        System.out.println(name + "'s table number is " + people.get(i).gt() + " at position " + people.get(i).gp());
        System.out.println();
        return 0;// ends program
      }
    }
    System.out.println("Person could not be found");
    System.out.println();
    return 0;// ends program
  }//close pprint

   /*
  Prints an array list of Person objects
  returns nothing and takes person arraylist as arg
  */
  public void print(ArrayList<Person> arr) {
    for (int i = 0; i < arr.size(); i++) {
      System.out.println(arr.get(i).print(company));
    } // close for loop
    System.out.println();
  }// close print
}// close class