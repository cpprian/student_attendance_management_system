package com.main.sams.student;

public class studentPackageMain {
    public static void main(String[] args) {
        ClassTime classTime = new ClassTime("Math", 60, "2019-01-01", "10:00", "11:00", "Room 1", "Math Class");

        classTime.changeClassDuration(90);
    }
}
