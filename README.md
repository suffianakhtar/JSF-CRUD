# JSF Student CRUD Application

A simple **JavaServer Faces (JSF) + Maven + JDBC** based CRUD web application for managing student records.  
This project demonstrates clean JSF architecture, JDBC integration using JNDI DataSource, and standard Maven-based project layout.

---

## 📌 Features

- Add new students
- Update existing students
- Delete students
- Search students
- List all students
- Clean MVC-based JSF structure
- JNDI-based database connectivity
- Maven-based build and dependency management

---

## 🛠 Technologies Used

- Java 17+
- Jakarta EE (JSF)
- Maven
- JDBC
- MySQL
- Apache Tomcat 10+
- Eclipse IDE

---

## 📂 Project Structure

```
JSF-CRUD/
 ├── src/
 │   ├── main/
 │   │   ├── java/
 │   │   ├── resources/
 │   │   └── webapp/
 │   │       ├── add-student-form.xhtml
 │   │       ├── list-students.xhtml
 │   │       ├── update-student-form.xhtml
 │   │       └── WEB-INF/
 │   │           ├── web.xml
 │   │           ├── faces-config.xml
 │   │           └── beans.xml
 ├── pom.xml
 ├── .gitignore
 └── README.md
```

---

## 🧰 Prerequisites

- Java 17 or newer
- Maven 3.8+
- Apache Tomcat 10+
- MySQL / MariaDB
- Eclipse IDE (recommended)

---

## 📥 Clone Project

```
git clone https://github.com/suffianakhtar/JSF-CRUD.git
```

```
cd JSF-CRUD
```

---

## 🛢 Database Setup

Two SQL script files are required:

1. `create-user.sql`
2. `create-student-database.sql`

These scripts are available in the root directory.
Note: MySQL is required to be installed.
Execute these scripts to setup the database.

---

## ⚙ Build Project

```
mvn clean package
```

WAR file will be generated in:

```
target/
```

---

## 🚀 Deploy & Run

1. Copy generated WAR file to:

```
TOMCAT_HOME/webapps/
```

2. Start Tomcat

3. Open browser:

```
http://localhost:8080/JSF-CRUD/
```

---

## 🖥 Application Pages

| Page | Description |
|--------|--------------|
| list-students.xhtml | Displays all students |
| add-student-form.xhtml | Add new student |
| update-student-form.xhtml | Update student |
| Search | Search by name or email |

---

## 📌 Screens

- Student List  
- Add Student  
- Update Student  

---

## 📜 License

This project is licensed under the **MIT License**.

---

## 👨‍💻 Author

**Suffian Akhtar**  
GitHub: https://github.com/suffianakhtar

---

## ⭐ Contributions

Pull requests are welcome. For major changes, please open an issue first.

---
