# 🌾 FarmCast

FarmCast is a Java-based project that sends weather updates to farmers via WhatsApp in their **local language**. It aims to support uneducated or remote-area farmers who don’t use weather apps by delivering critical weather info such as temperature, humidity, and wind speed directly to their phones.

---

## 📦 Features

- 🌤️ Gets real-time weather data  
- 📲 Sends WhatsApp messages using Twilio API  
- 🌍 Supports local language customization (e.g., Hindi, Gujarati)  
- 🗃️ Uses MySQL to manage users and location data  
- 🔒 Loads all credentials securely from `.env`  

---

## 🛠️ Technologies Used

- **Java 17**
- **Maven** (Build tool)
- **MySQL** (Database)
- **Twilio API** (For sending WhatsApp messages)
- **OpenWeatherMap API** (For weather data)
- **dotenv-java** (To load environment variables)
- **JUnit** (Testing)
- **SLF4J + Logback** (Logging)

---

## 🌐 APIs Used

- [Twilio WhatsApp API](https://www.twilio.com/whatsapp)
- [OpenWeatherMap API](https://openweathermap.org/api)

---

## ⚙️ Environment Configuration

This project uses a `.env` file for configuration. Keep it in the **root directory**.

### 📄 `.env` Example

```ini
# Twilio Configuration
ACCOUNT_SID="your_twilio_account_sid"
AUTH_TOKEN="your_twilio_auth_token"
NUMBER="your_twilio_whatsapp_number"

# Weather API Key
APIKEY="your_openweathermap_api_key"

# MySQL Configuration
url="jdbc:mysql://localhost:3306/farmcastdb"
user="your_mysql_username"
password="your_mysql_password"
```

> ⚠️ **Do not share your real credentials publicly.** The `.env` file is included in `.gitignore` for safety.

---

## 🗄️ MySQL Database Setup

This project includes an `init.sql` file to set up your database and tables.

### 🚀 Setup Steps

1. Ensure MySQL is installed and running.
2. From your terminal, run:

```bash
mysql -u your_mysql_username -p < init.sql
```

This will:
- Create a database called `farmcastdb`
- Create `locations` and `people` tables

### 🔍 `init.sql` Reference

```sql
CREATE DATABASE IF NOT EXISTS farmcastdb;
USE farmcastdb;

CREATE TABLE IF NOT EXISTS locations (
  address VARCHAR(255) PRIMARY KEY,
  latitude DOUBLE,
  longitude DOUBLE,
  weather_json TEXT
);

CREATE TABLE IF NOT EXISTS people (
  phone_no VARCHAR(15) PRIMARY KEY,
  name VARCHAR(255),
  address VARCHAR(255),
  FOREIGN KEY (address) REFERENCES locations(address)
);
```

---

## ▶️ Running the Project

Make sure `.env` is configured and the MySQL database is initialized.

### ✅ With Maven

```bash
mvn exec:java -Dexec.mainClass="com.farmcast.App"
```

### ✅ From JAR

```bash
java -jar FarmCast.jar
```

> Make sure the `.env` file is in the **same folder** as the `.jar`.

---

## 🚀 Scalability & Extensibility

- The system is modular and can be extended to support SMS, IVR, or email.
- Database design supports multiple users and locations.
- Can be dockerized for easier deployment.

---

## 🧪 Testing

Unit tests use **JUnit 4.13.2**. Run:

```bash
mvn test
```

---

## 📁 Project Structure

```
farmcast/
│
├── src/
│   └── main/java/com/farmcast/
│       ├── data_generetor
│       ├── ddata
│       ├── messenger
│       ├── weather_message
│       └── App.java
│
├── init.sql
├── .env
├── .gitignore
├── pom.xml
└── README.md
```

---

## 📬 Contact

For any queries, suggestions, or contributions, feel free to open an issue or pull request.

---

