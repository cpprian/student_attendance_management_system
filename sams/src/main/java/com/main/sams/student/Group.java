package com.main.sams.student;

import java.util.ArrayList;

/**
 * Group class is used to create a group of Student objects.
 *
 * It contains group name, group year and a list of students.
 * Also, it contains methods to add and remove students from the list.
 *
 * @author Cyprian
 * @version 1.0
 * @since 2023-01-04
 */
public class Group {
    /**
     * name is a String that represents the group's name
     */
    private String name;

    /**
     * year is an int that represents the group's year
     */
    private int year;

    /**
     * students is an ArrayList of Student objects that represents the group's students
     */
    private ArrayList<Student> students;

    /**
     * This is a constructor that creates a Group object with the given name and year.
     * While creating the object, it also creates an empty ArrayList of Student objects.
     *
     * @param name is a String that represents the group's name
     * @param year is an int that represents the group's year
     */
    public Group(String name, int year) {
        this.name = name;
        this.year = year;
        this.students = new ArrayList<>();
    }

    /**
     * addStudent method adds a Student object to the students ArrayList
     *
     * @param student is a Student object that represents the student to be added
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * removeStudent method removes a Student object from the students ArrayList based on the given Student object
     *
     * @param student is a Student object that represents the student to be removed
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    /**
     * removeStudent method removes a Student object from the students ArrayList based on the given index
     *
     * @param index is an int that represents the index of the student in the students ArrayList
     */
    public void removeStudent(int index) {
        this.students.remove(index);
    }

    /**
     * removeStudent method removes a Student object from the students ArrayList based on the given name and surname
     *
     * @param name is a String that represents the name of the student to be removed
     * @param surname is a String that represents the surname of the student to be removed
     */
    public void removeStudent(String name, String surname) {
        for (Student student : this.students) {
            if (student.getName().equals(name) && student.getSurname().equals(surname)) {
                this.students.remove(student);
                break;
            }
        }
    }

    /**
     * getName method returns the name of the group
     *
     * @return a String that represents the group's name
     */
    public String getName() {
        return name;
    }

    /**
     * getYear method returns the year of the group
     *
     * @return an int that represents the group's year
     */
    public int getYear() {
        return year;
    }

    /**
     * getStudents method returns list of students in the group
     *
     * @return an ArrayList of Student objects that represents the group's students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }
}
