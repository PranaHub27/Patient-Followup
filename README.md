# Web-Based Patient Follow-Up Application

A Java-based web application designed to enhance patient care and ensure proper medication follow-up after hospital discharge. The system enables doctors, pharmacists, and patients to interact seamlessly for effective post-treatment monitoring.

## 🚀 Project Overview

In the current healthcare system, patients are often left unsupervised after being discharged. This project bridges the gap by providing a structured follow-up mechanism through which:

- Doctors generate prescriptions
- Pharmacists initiate follow-up calls
- Patients provide real-time responses to prescribed treatments

The application records and stores all interactions, allowing for better diagnosis, tracking, and care continuity.

## 👥 Roles & Functionalities

### Admin
- Add and manage doctors and pharmacists
- View user records (patients, doctors, pharmacists)

### Pharmacist
- Add patients
- Create and manage follow-up questionnaires
- Record patient responses through scheduled calls

### Doctor
- View patient records and questionnaire feedback
- Respond with treatment suggestions or further diagnosis

### Patient
- Login and view responses provided by doctors

## 📌 Core Features

- Patient call scheduling & reminders
- Dynamic questionnaire generation (radio, checkbox, and text-based inputs)
- Resume incomplete calls from the dropped question
- Option to reschedule/terminate follow-up calls
- Real-time prescription and feedback exchange
- Robust admin dashboard to manage user roles

## 🛠️ Technologies Used

- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Java (Servlets, JSP)
- **Database:** Oracle 10g
- **Server:** Apache Tomcat 6.x
- **IDE:** Eclipse

## 🧪 Testing Strategy

The application has been validated with:
- Unit Testing
- Integration Testing
- System Testing
- Regression Testing

Comprehensive test cases include login validation, form submission, user management, and session flow control.

## 🖥️ Screenshots

Check the `/screenshots` directory or project PDF for UI walkthroughs including:
- Login Pages
- Questionnaire Forms
- Admin/Doctor/Pharmacist Dashboards
- Feedback Panels

## 📈 Future Enhancements

- SMS-based reminders and alerts
- Direct patient-to-doctor query panel
- Admin dashboard analytics for disease trends and doctor performance
- Real-time notifications

## 📂 Repository Structure

