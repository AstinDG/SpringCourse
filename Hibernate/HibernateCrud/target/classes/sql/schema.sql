CREATE TABLE Person(
    id int GENERATED By DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(100) NOT NULL,
    age int check(age > 0),
    email varchar(100) UNIQUE
);

INSERT INTO Person(name, age, email) VALUES ('ruslan', 15, 'asdasd@test.com');
INSERT INTO Person(name, age, email) VALUES ('Yevgenia', 23, 'bystra2020@gmail.com');