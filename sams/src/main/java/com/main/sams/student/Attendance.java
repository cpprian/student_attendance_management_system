package com.main.sams.student;

/**
 * Attendance class is used to store the attendance of each student for each subject.
 *
 * @author Cyprian
 * @version 1.0
 * @since 2023-01-04
 */
public class Attendance {
    /**
     * classTime is an object that store all the information about the class time
     */
    private ClassTime classTime;

    /**
     * attendanceType is an enum that represents the type of attendance
     * @see AttendanceType
     */
    private AttendanceType attendanceType;

    /**
     * Attendance is a constructor that creates a new Attendance object.
     *
     * @param classTime - class time
     * @param attendanceType - attendance type
     */
    public Attendance(ClassTime classTime, AttendanceType attendanceType) {
        this.classTime = classTime;
        this.attendanceType = attendanceType;
    }

    /**
     * getClassTime is a getter that returns the class time.
     *
     * @return ClassTime object
     */
    public ClassTime getClassTime() {
        return classTime;
    }

    /**
     * getAttendanceType is a getter that returns the attendance type.
     *
     * @return AttendanceType enum
     */
    public AttendanceType getAttendanceType() {
        return attendanceType;
    }

    /**
     * setClassTime is a setter that sets the class time.
     *
     * @param classTime - class time
     */
    public void setClassTime(ClassTime classTime) {
        this.classTime = classTime;
    }

    /**
     * setAttendanceType is a setter that sets the attendance type.
     *
     * @param attendanceType - attendance type
     */
    public void setAttendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
    }
}
