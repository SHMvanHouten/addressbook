# addressbook
A small project messing around with technologies like Spring

Tables needed:<br>
CREATE TABLE address(
  ID INT UNIQUE PRIMARY KEY ,
  firstName VARCHAR(20),
  surName VARCHAR(40),
  streetName VARCHAR(40),
  houseNumber INT(10),
  houseAdditional VARCHAR(10),
  city VARCHAR(20),
  country VARCHAR(20),
  postCode VARCHAR(10),
  email VARCHAR(20),
  phoneNumber INT(20),
  additionalNotes VARCHAR(200)
);

CREATE TABLE addressGroup(
  ID INT UNIQUE PRIMARY KEY ,
  addressGroupId INT(10),
  addressGroupName VARCHAR(40),
  addressGroupSequence INT(5),
  addressId INT(20),
  FOREIGN KEY (addressId) REFERENCES address(ID)
);

CREATE TABLE user(
  ID INT UNIQUE PRIMARY KEY NOT NULL ,
  name VARCHAR(15) UNIQUE NOT NULL
);