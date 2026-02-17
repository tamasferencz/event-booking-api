# Event Booking API 📅

A RESTful API built with **Java Spring Boot** for managing events. This project demonstrates full CRUD operations, in-memory database management, and API documentation using OpenAPI (Swagger).

## 🚀 Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.2.8
* **Database:** H2 In-Memory Database
* **Build Tool:** Gradle
* **Documentation:** SpringDoc OpenAPI (Swagger UI)
* **Tools:** Lombok, Spring Data JPA

## 🛠️ Features

* **Create Event:** Add new events with validation.
* **Read Events:** List all events or get details by ID.
* **Update Event:** Modify existing event details.
* **Delete Event:** Remove events from the system.
* **Auto-Documentation:** Interactive API documentation via Swagger UI.

---

## 🏁 Getting Started

### Prerequisites
* JDK 21 installed
* Git installed

### Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/YOUR-USERNAME/event-booking-api.git](https://github.com/YOUR-USERNAME/event-booking-api.git)
    cd event-booking-api
    ```

2.  **Run the application:**
    * **Mac/Linux:**
        ```bash
        ./gradlew bootRun
        ```
    * **Windows:**
        ```bash
        gradlew.bat bootRun
        ```

The application will start on `http://localhost:8080`.

---

## 📖 API Documentation (Swagger UI)

Once the application is running, you can access the interactive API documentation here:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

You can test all endpoints (GET, POST, PUT, DELETE) directly from the browser.

---

## 💾 Database Console (H2)

The project uses an in-memory H2 database. You can access the raw database console here:

👉 **[http://localhost:8080/h2-console](http://localhost:8080/h2-console)**

**Login Credentials:**
* **Driver Class:** `org.h2.Driver`
* **JDBC URL:** `jdbc:h2:mem:testDb`
* **User Name:** `eventBooking`
* **Password:** `qwerty` (or check `application.properties`)

---

## 🔌 API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/events` | List all events |
| `GET` | `/api/events/{id}` | Get event by ID |
| `POST` | `/api/events` | Create a new event |
| `PUT` | `/api/events/{id}` | Update an existing event |
| `DELETE` | `/api/events/{id}` | Delete an event |

### Example JSON for /POST:
```json
{
  "name": "Spring Boot Workshop",
  "location": "Budapest, Hungary",
  "date": "2026-05-20T10:00:00",
  "totalSeats": 50
}