# S3-Lite – Lightweight Object Storage Service

A lightweight S3-like object storage service built using **Spring Boot**.  
It supports uploading, downloading, and deleting files while storing metadata in a database and files on disk.

---

## 🚀 Features

- Upload files (PDF, images, etc.)
- Download files using bucket + object key
- Delete stored files
- Metadata stored in MySQL
- File storage abstracted (local now, cloud-ready later)
- UUID-based object keys (safe & scalable)
- Centralized exception handling
- Log4j2 logging

---

## 🏗️ Architecture Overview

Client
|
Controller
|
ObjectService
|

| Metadata (MySQL)       |
| Storage (File System) |

- **Metadata**: stored in DB (`bucketName`, `objectKey`, file info)
- **Files**: stored outside DB using generated object keys
- **Storage layer** is replaceable (Local → AWS S3)

---

## 📦 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Log4j2
- Maven

---

## 📂 Project Structure


src/main/java/com/murtaza/s3lite
├── controller
├── service
│   └── impl
├── repository
├── entity
├── dto
├── exception
├── aspect
└── util
 
---

🛡️ Notes
	•	Files are stored using object keys, not original filenames
	•	Original filename is preserved as metadata
	•	Application is stateless and horizontally scalable
	•	Designed to be extended to AWS S3 or cloud storage

⸻

🧠 Future Improvements
	•	Streaming uploads/downloads
	•	Cloud storage (AWS S3)
	•	Authentication & authorization
	•	Bucket management APIs
	•	File versioning

⸻

👨‍💻 Author

Murtaza Sogiyawala
Backend Developer | Spring Boot | Java
