package com.houarizegai;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//serializable pour divise objet a des entites
public class Person implements java.io.Serializable {

    String name;
    String surname;
    Date birthdate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    Person() {

    }

    Person(String name, String surname, Date birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public String dateToString(Object date) { //to convert Date to String, use format method of
        return new SimpleDateFormat("dd-mm-yyyy").format(date);
    }

    @Override
    public String toString() {
        return name + " " + surname + " born on " + dateToString(birthdate);
    }

}