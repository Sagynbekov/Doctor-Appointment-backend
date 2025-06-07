# Doctor Appointment Backend

**Doctor Appointment Backend** is a Spring Boot REST API that powers the Doctor Appointment system. It provides endpoints for user authentication, doctor and specialization management, booking appointments, reviews, and user favorites. This backend is designed to work seamlessly with the [Doctor Appointment Frontend](https://github.com/Sagynbekov/Doctor-Appointment-frontend).

---

## Features

- **User Authentication:** Register and login users.
- **Doctors Management:** CRUD operations for doctors.
- **Specializations Management:** CRUD operations for doctor specializations.
- **Appointments:** Book, view, and manage appointments.
- **Favorites:** Add/remove doctors to user's favorites.
- **Reviews:** Leave and retrieve reviews for doctors.

---

## Main Endpoints

- **Authentication & Users**
  - `POST /register` — Register a new user
  - `POST /login` — User login
  - `GET /user?username={username}` — Get user info by username

- **Doctors**
  - `GET /doctors` — List all doctors
  - `POST /doctors` — Add a new doctor
  - `PUT /doctors/{id}` — Update doctor info
  - `DELETE /doctors/{id}` — Delete doctor

- **Specializations**
  - `GET /specializations` — List all specializations
  - `POST /specializations` — Add a new specialization
  - `PUT /specializations/{id}` — Update specialization
  - `DELETE /specializations/{id}` — Delete specialization

- **Appointments**
  - `POST /api/books` — Book an appointment
  - `GET /api/books/appointments?userId={userId}` — List appointments for a user
  - `GET /api/books/busy-times?doctorId={doctorId}&date={date}` — Get busy time slots for a doctor on a given date

- **Favorites**
  - `GET /api/favorits?userId={userId}` — Get user's favorite doctors
  - `POST /api/favorits` — Add doctor to favorites
  - `DELETE /api/favorits?userId={userId}&doctorId={doctorId}` — Remove doctor from favorites

- **Reviews**
  - `POST /api/reviews` — Add a review for a doctor
  - `GET /api/reviews?doctorId={doctorId}` — Get all reviews for a doctor


---

## How to Run

### Prerequisites

- Java 17 or newer
- Maven

### Steps

1. **Clone the repository**
    ```bash
    git clone https://github.com/Sagynbekov/Doctor-Appointment-backend.git
    cd Doctor-Appointment-backend
    ```

2. **Build and Run**
    ```bash
    mvn spring-boot:run
    ```
    By default, the application runs on `http://localhost:8080`.

3. **Configuration**
    - Database and other settings can be configured in `src/main/resources/application.properties`.
    - Make sure your database is running and credentials are correct (if applicable).

4. **Testing**
    - You can use tools like Postman or the [Doctor Appointment Frontend](https://github.com/Sagynbekov/Doctor-Appointment-frontend) to interact with the API.

---

## Project Structure

- `src/main/java/com/example/demo/` — Main source code: controllers, services, models, repositories.
- `src/main/resources/` — Configuration files.
- `src/test/java/` — Unit and integration tests.

---

## Notes

- This backend is intended to be used with the mobile frontend app.
- The `/hello` endpoint can be used to verify that the server is up.
- All endpoints are RESTful and use JSON for data transfer.

---

## License

MIT
