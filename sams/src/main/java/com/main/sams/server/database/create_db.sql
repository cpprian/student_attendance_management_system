create table if not exists StudentGroup (
    groupID int not null primary key autoincrement,
    groupName varchar(255) not null,
    groupYear int not null,
    studentID int not null,
    foreign key (studentID) references Student(studentID)
);

create table if not exists Student (
    studentID int not null primary key autoincrement,
    studentName varchar(25) not null,
    studentSurname varchar(25) not null,
    studentNumber int not null,
    attendanceID int not null,
    foreign key (attendanceID) references Attendance(attendanceID)
);

create table if not exists Attendance (
    attendanceID int not null primary key autoincrement,
    attendanceType int not null,
    classTimeID int not null,
    foreign key (classTimeID) references ClassTime(classTimeID)
);

create table if not exists ClassTime (
    classTimeID int not null primary key autoincrement,
    classTimeName varchar(255) not null,
    durationInMinutes int not null,
    classTimeDate varchar(50) not null,
    startTime varchar(50) not null,
    endTime varchar(50) not null,
    location varchar(255) not null,
    description varchar(255) not null
);