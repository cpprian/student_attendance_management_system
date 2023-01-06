package com.main.sams.student;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Student is a class that represents a student.
 *
 * It contains the following fields:
 * name - student's name
 * surname - student's surname
 * index - student's index
 *
 * @author Cyprian
 * @version 1.0
 * @since 2023-01-04
 */
public class Student implements Serializable {
    /**
     * name is a String that represents the student's name
     */
    private String name;

    /**
     * surname is a String that represents the student's surname
     */
    private String surname;

    /**
     * index is an int that represents the student's index
     */
    private int index;

    /**
     * attendance is an ArrayList of Attendance objects that represents the student's attendance on a given day
     *
     * @see Attendance
     */
    private ArrayList<Attendance> attendance;

    /**
     * Student is a constructor that creates a new Student object.
     * It also creates an empty ArrayList of Attendance objects.
     *
     * @param name - student's name
     * @param surname - student's surname
     * @param index - student's index
     */
    public Student(String name, String surname, int index) {
        this.name = name;
        this.surname = surname;
        this.index = index;
        this.attendance = new ArrayList<>();
    }

    /**
     * getName is a getter that returns the student's name.
     *
     * @return student's name
     */
    public String getName() {
        return name;
    }

    /**
     * getSurname is a getter that returns the student's surname.
     *
     * @return student's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * getIndex is a getter that returns the student's index.
     *
     * @return student's index
     */
    public int getIndex() {
        return index;
    }

    /**
     * getAttendance is a getter that returns the student's attendance.
     *
     * @return student's attendance
     */
    public ArrayList<Attendance> getAttendance() {
        return attendance;
    }


    /**
     * setName is a setter that sets the student's name.
     *
     * @param name - student's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setSurname is a setter that sets the student's surname.
     *
     * @param surname - student's surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * setIndex is a setter that sets the student's index.
     *
     * @param index - student's index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * setAttendance is a setter that sets the student's attendance.
     *
     * @param attendance - student's attendance
     */
    public void setAttendance(ArrayList<Attendance> attendance) {
        this.attendance = attendance;
    }

    /**
     * addAttendance is a method that adds a new Attendance object to the student's attendance.
     *
     * @param attendance - student's attendance
     */
    public void addAttendance(Attendance attendance) {
        this.attendance.add(attendance);
    }
}
