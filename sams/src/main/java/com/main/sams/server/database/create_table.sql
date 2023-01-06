create table if not exists studentgroup (
    groupid int not null autoincrement,
    groupName varchar(255) not null,
    groupYear int not null,
    studentID int not null,
    primary key (groupid),
    foreign key (studentid) references student(studentid)
);

create table if not exists student (
    studentid int not null autoincrement,
    studentname varchar(25) not null,
    studentsurname varchar(25) not null,
    studentnumber int not null,
    attendanceid int not null,
    primary key (studentid),
    foreign key (attendanceID) references Attendance(attendanceID)
);

create table if not exists attendance (
    attendanceid int not null autoincrement,
    attendancetype int not null,
    classtimeid int not null,
    primary key (attendanceid),
    foreign key (classtimeid) references classtime(classtimeid)
);

create table if not exists classtime (
    classtimeid int not null autoincrement,
    classtimename varchar(255) not null,
    durationinminutes int not null,
    classtimedate varchar(50) not null,
    starttime varchar(50) not null,
    endtime varchar(50) not null,
    location varchar(255) not null,
    description varchar(255) not null,
    primary key (classtimeid)
);