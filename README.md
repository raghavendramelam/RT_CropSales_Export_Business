# 🌶️ Mirchi Crop Sales & Storage Management System – Backend API

A production-grade backend system developed for **Raghava Traders**, a South Indian Mirchi export business, to digitize crop sales, pricing, commission calculation, and cold storage billing. The system replaces manual bookkeeping with secure, scalable backend APIs built using **Spring Boot**, **JPA**, and **JWT**, and is deployed on **AWS EC2** for 24/7 access.

---

## 🚀 Features

- **Admin & Customer Role Management** (JWT-based authentication)
- **Crop Sales Module**:  
  - Real-time crop intake (100kg units)  
  - Daily price variation input  
  - 3% commission calculation  
  - Auto-generated transaction summary
- **Dynamic Search & Filters**:  
  - Search by name, mobile,location  
  - Fetch and sort previous transaction records
- **Cold Storage (AC Build) Module**:  
  - Handles crops stored for price negotiation  
  - Tracks timestamps for billing  
  - Calculates total dues upon crop withdrawal
- **Email Notifications**:  
  - Sends digital receipts using JavaMailSender
- **Secure RESTful APIs**:  
  - Spring Security 6.0 + JWT for token-based authentication
- **Quality Assurance**:  
  - Tested with **JUnit** & **Mockito**  
  - Load testing via **JMeter**  
  - Code quality analysis using **SonarQube**

---

## 🛠️ Tech Stack

| Layer        | Technology               |
|--------------|---------------------------|
| Language     | Java 11                   |
| Framework    | Spring Boot, Spring Data JPA, Spring Security |
| Database     | MySQL                     |
| Security     | JWT, Role-based access    |
| Testing      | JUnit, Mockito, JMeter    |
| Quality      | SonarQube                 |
| Deployment   | AWS EC2, Maven            |
| Email        | JavaMailSender (SMTP)     |

---

## 📂 Modules Overview

### 1. **User Management API**
- Login / Register with role-based access
- Token generation and validation

### 2. **Crop Sales Module**
- Input crop weight and date-specific price
- Apply commission logic
- Save transaction data and notify customer

### 3. **Cold Storage Module**
- Stores unsold crop records with entry timestamps
- Calculates storage fees over time
- Displays final billing when customer retrieves crop

---

## 🔐 Security & Authentication

- JWT-based login with token expiration
- Spring Security 6.0 integrated with role-based access (Admin, Customer)
- Password encryption using BCrypt

---

## 📤 Deployment

- Application is packaged using Maven
- Hosted on AWS EC2 Linux instance for 24/7 uptime
- Environment-ready with secured port and REST API testing via Postman

---

## 📧 Email Notification (Receipt)

- Auto-triggers upon sale completion
- Uses JavaMailSender to deliver digital receipts to customer email addresses

---

## ✅ Testing

- **JUnit** and **Mockito** for unit and integration testing
- **SonarQube** for static code analysis and maintainability
- **JMeter** for simulating user load and testing performance

---



## 👤 Author

**Raghavendra Melam**  
📧 melamraghava58@gmail.com  
📍 Hammond, IN, USA  
🌐 [LinkedIn](https://linkedin.com/in/your-profile) | [GitHub](https://github.com/your-github) | [Portfolio](https://raghavamelam.netlify.app/)

---

## 📄 License

This project is open for review. Business data has been anonymized for privacy.
