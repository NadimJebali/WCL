# 🎓 WCL - Web Classroom Learning

**WCL** is a full-stack educational platform designed to support students, teachers, and administrators. Students can log in to complete homework, teachers can assign tasks by subject and study level, and an AI assistant helps validate student answers. Admins manage users and all homework content.

---

## ✨ Features

### 👩‍🎓 Students
- Login and view available homework
- Upload answers (text or file)
- Get instant feedback via AI assistant

### 👨‍🏫 Teachers
- Assign homework by subject or level
- View and manage homework lists

### 👩‍💼 Admins
- Create/delete users (students & teachers)
- View and manage all homework content

---

## 🧰 Tech Stack

| Layer      | Technology       |
|------------|------------------|
| Frontend   | Angular           |
| Backend    | Spring Boot (Java) |
| Database   | MySQL             |
| AI Assistant | Gemini API       |
| Hosting    | AWS (EC2 + RDS)   |

---

## 🚀 Getting Started

### Prerequisites

- Node.js and Angular CLI
- Java 17+ and Maven
- MySQL server
- Gemini API key

### Backend Setup

```bash
cd backend
./mvnw spring-boot:run
