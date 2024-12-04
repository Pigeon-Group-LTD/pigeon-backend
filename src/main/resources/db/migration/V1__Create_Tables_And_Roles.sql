CREATE TABLE `Role` (
	RoleId INT AUTO_INCREMENT PRIMARY KEY,
    RoleName VARCHAR(15) NOT NULL
);

CREATE TABLE `User` (
	UserId INT AUTO_INCREMENT PRIMARY KEY,
    Email VARCHAR(320) NOT NULL,
    PasswordHash VARBINARY(100) NOT NULL,
    Salt VARBINARY(16) NOT NULL,
    RoleId INT NOT NULL DEFAULT 2, # Defaults to User Role
    FOREIGN KEY (RoleId) REFERENCES Role(RoleId)
);

CREATE TABLE Room (
	RoomId INT AUTO_INCREMENT PRIMARY KEY,
    RoomName VARCHAR(20) NOT NULL
);

CREATE TABLE Booking (
	BookingId INT AUTO_INCREMENT PRIMARY KEY,
    UserId INT NOT NULL,
    RoomId INT NOT NULL,
    GroupSize INT NOT NULL,
    BookingDate DATE NOT NULL,
    StartTime TIME NOT NULL,
    EndTime TIME NOT NULL,
    FOREIGN KEY (UserId) REFERENCES User(UserId),
    FOREIGN KEY (RoomId) REFERENCES Room(RoomId)
);

INSERT INTO `Role` (RoleId, RoleName) VALUES
(1, "Admin"),
(2, "User");