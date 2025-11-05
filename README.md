# Travel Organizer with Calendar Sync

Full-stack project using **Spring Boot (Java)**, **MySQL**, and your **HTML/CSS frontend** (no JS).  
Includes Docker for one-command startup and GitHub-ready structure.

## Run (Docker)
```bash
cp .env.example .env   # already provided here; edit if desired
# Your credentials are prefilled as per your message
docker-compose up --build
```
- Frontend: http://localhost:3000
- Backend:  http://localhost:8080
- MySQL:    localhost:3306

## Frontend
- A minimal integrated `index.html` and `add-trip.html` are in `frontend/`.
  - `add-trip.html` posts to `http://localhost:8080/addTrip` (no JS).

## Backend (Spring Boot)
- Endpoints
  - `POST /addTrip` — save a trip
  - `GET  /trips`   — list trips (server-rendered)
  - `GET  /calendar`— calendar view (by start date)
  - `GET  /delete/{id}` — delete by id

- DB table is auto-created (`spring.jpa.hibernate.ddl-auto=update`):
```sql
CREATE TABLE trips (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  trip_name VARCHAR(100),
  destination VARCHAR(100),
  start_date DATE,
  end_date DATE,
  transport_mode VARCHAR(50),
  notes TEXT
);
```

## Change DB Credentials
Edit `.env`:
```
MYSQL_DATABASE=travelorganizer
MYSQL_USER=traveluser
MYSQL_PASSWORD=YOUR_PASSWORD
MYSQL_ROOT_PASSWORD=YOUR_ROOT_PASSWORD
```

## Dev Tips
- If MySQL starts slow, Docker healthcheck ensures backend waits.
- To reset DB, `docker volume rm travel-organizer_db_data` then re-up.
- For production, do **not** commit `.env` with real passwords.
