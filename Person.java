/**
* @Author Rithvik Allamaneni
*1/19/2023
*Person class that has all the information of a Person such as name and table number
**/

/*

*/
class Person {
  private String name;
  private int company;
  private int table = -1;
  private int position = -1;

  /*
  constructor without table and position
  returns a Person and takes name and company as argument
  */
  public Person(String name, int company) {
    this.name = name;
    this.company = company;
  }
  /*
  constructor with table and position
  returns a Person and takes name and company as argument
  */
  public Person(String name, int company, int table, int position) {
    this.name = name;
    this.company = company;
    this.table = table;
    this.position = position;
  }
  /*
  prints name of person
  returns String and no args
  */
  public String gName() {//
    return name;
  }
  /*
  prints company id
returns int and no returns
  */
  public int gc() {// 
    return company;
  }
  /*
  prints company name
returns String and takes array of company names as args
  */
  public String gc(String[] nCompany){
    return nCompany[company-1];
  }
  /*
  prints table variable
returns int and no args 
  */
  public int gt() {
    return table;
  }
  /*
  prints position of person
returns it and no args 
  */
  public int gp(){
    return position;
  }
  /*
  returns the person details and Company ID and no args
  */
  public String print() {
    if (table != -1 && position != -1) {
      return "Name: " + name + "\tCompany: " + company + "\tTable: " + table + "\tPosition: " + position;
    }
    return "Name: " + name + "\tCompany: " + company;
  }
  /*
  returns details of the person and company name
  */
  public String print(String[] nCompany) {
    if (table != -1 && position != -1) {
      return "Name: " + name + "\tCompany: " + nCompany[company-1] + "\tTable: " + table + "\tPosition: " + position;
    }
    return "Name: " + name + "\tCompany: " + nCompany[company-1];
  }

}