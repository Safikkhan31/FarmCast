# FarmCast
This is a project which sends regular updates on weather to farmers in their local language via WhatsApp.  

---


### 🛠️ Environment Configuration

This project requires some environment variables to run. These variables must be provided in a `.env` file at the **root of the project** (in the same directory as the `.jar` file, if running from a compiled version).


#### 📄 Create a `.env` File

Create a file named `.env` in the root directory and add the following keys:

```ini
TWILIO_ACCOUNT_SID=your_twilio_account_sid
TWILIO_AUTH_TOKEN=your_twilio_auth_token
TWILIO_NUMBER=your_twilio_whatsapp_number
APIKEY=your_openweathermap_or_other_api_key
```

> ⚠️ **Do not share your `.env` file publicly.** This file is excluded from version control using `.gitignore`.


#### 🧪 Example `.env` (DO NOT USE THESE VALUES)

```ini
TWILIO_ACCOUNT_SID="ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
TWILIO_AUTH_TOKEN="your_twilio_auth_token"
TWILIO_NUMBER="+14155238886"
APIKEY="your_api_key_here"
```


#### ▶️ Running the Project

If running the `.jar` file manually, make sure the `.env` file is present in the **same directory**:

```bash
java -jar FarmCast.jar
```

---


