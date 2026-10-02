# 🌱 CottonCure — Cotton Plant Disease Detection

CottonCure is an Android app that helps cotton farmers diagnose crop problems in seconds. Snap or upload a photo of a cotton leaf and the app classifies it against **6 common conditions** — pests, fungal diseases, bacterial infection, or a healthy plant — then shows practical treatment tips in **English and Marathi (मराठी)**.

The detection runs on a **TensorFlow Lite model bundled inside the APK**, so it works fully offline with no API keys and no cloud calls. Firebase is used only for user accounts (email/password login and registration).

## ✨ Features

- **Offline AI detection** — a bundled TFLite model (`model.tflite`) classifies leaf photos entirely on-device; no internet required after install.
- **6 conditions recognized** — two pests, two fungal diseases, one bacterial disease, and healthy leaves.
- **Bilingual treatment advice** — every diagnosis includes actionable remedies written in English and Marathi for Indian cotton farmers.
- **Camera & gallery capture** — take a photo directly or pick an existing image, with automatic runtime camera-permission handling.
- **Firebase authentication** — email/password registration, login, and a forgot-password flow backed by Firebase Realtime Database.
- **Farmer resource links** — an in-app links screen pointing to medicinal guides, pet-care resources, and YouTube tutorials.
- **Zero external AI services** — there are no Gemini/OpenAI keys or any other paid APIs. Everything the model needs ships with the app.

## 🔍 Detectable conditions

| # | Condition | मराठी | Type | Example remedy shown in-app |
|---|-----------|-------|------|------------------------------|
| 1 | Aphids | मावा | Pest | Spray neem oil (निंबोळी अर्क फवारणी) |
| 2 | Army worm | लष्करी अळी | Pest | Regular crop monitoring, light traps to catch moths |
| 3 | Bacterial Blight | जिवाणूजन्य करपा | Bacterial | Use resistant varieties; remove and destroy infected plants |
| 4 | Healthy | निरोगी | — | No action needed 🎉 |
| 5 | Powdery Mildew | पावडरी बुरशी | Fungal | Ensure good air circulation; sulphur-based fungicides |
| 6 | Target spot | लक्ष्य स्थान | Fungal | Avoid excess nitrogen and waterlogging; deep ploughing after harvest |

## 🛠️ Tech stack

| Layer | Technology |
|-------|------------|
| Language | Kotlin + Java (mixed codebase) |
| UI | AndroidX, Material components, Edge-to-Edge layouts |
| ML inference | TensorFlow Lite (`org.tensorflow:lite-support`), 32×32 RGB input |
| Backend | Firebase Authentication + Firebase Realtime Database |
| Build | Gradle Kotlin DSL, Android Gradle Plugin |

## 📁 Project structure

```
app/src/main/
├── java/com/example/classification/
│   ├── MainActivity.kt      # Login screen & auth flow
│   ├── Register.kt          # New-user registration
│   ├── detection.java       # Camera/gallery capture + TFLite inference + remedies
│   └── links.kt             # In-app resource links screen
├── ml/model.tflite          # Bundled offline disease-classification model
└── res/                     # Layouts, drawables, strings
```

## 🚀 Getting started (beginner-friendly, step by step)

If you are new to Android development, follow these steps carefully to get the app running on your computer.

### Step 1: Install required software
1. Download and install **Android Studio** from the official website: [Download Android Studio](https://developer.android.com/studio).
2. During installation, leave all the default settings (this will install the necessary Android software automatically).

### Step 2: Download the project
1. Go to this repository on GitHub.
2. Click the green **Code** button and select **Download ZIP**.
3. Extract the ZIP file to a folder on your computer (e.g., `Documents/CottonCure`).

### Step 3: Open the project in Android Studio
1. Open **Android Studio**.
2. Click **Open** (or go to `File > Open` if you are already inside another project).
3. Browse to the folder where you extracted the project, select the `CottonCure` folder, and click **OK**.
4. Wait for the project to load. You will see a loading bar at the bottom right saying **Gradle Sync**. Wait until this finishes completely (it may take a few minutes the first time).

### Step 4: Add your Firebase configuration (crucial)
The app uses Firebase for user login and accounts, so you must connect your own free Firebase project for it to work.

1. Go to the [Firebase Console](https://console.firebase.google.com/) and log in with your Google account.
2. Click **Add project** and give it a name (e.g., "CottonCure App").
3. On the project overview page, click the **Android icon** to add an Android app.
4. In the **Android package name** field, enter exactly: **`com.example.classification`**.
5. Click **Register app**, then **Download google-services.json**.
6. Copy the downloaded `google-services.json` file.
7. Back in Android Studio, switch the project view from **Android** to **Project** using the dropdown in the top-left corner.
8. Expand the `CottonCure` folder, open the `app` folder, and paste `google-services.json` directly inside it.
9. **Enable Authentication:** in the Firebase console go to **Authentication** → **Get started** → **Sign-in method** tab → enable **Email/Password**.
10. **Enable Realtime Database:** go to **Realtime Database** → **Create Database** → start in **Test Mode**.

> **Note on API keys:** no other API keys (Gemini, OpenAI, etc.) are required! The disease detection uses the offline machine-learning model (`model.tflite`) that is already included in the app.

### Step 5: Run the app
1. **On a real phone:**
   - Connect your Android phone to your computer with a USB cable.
   - Go to **Settings > About Phone** and tap **Build Number** 7 times to unlock Developer Options.
   - Go back to **Settings > Developer Options** and enable **USB Debugging**.
2. **On an emulator:**
   - Click the **Device Manager** icon in Android Studio (top right).
   - Click **Create Device** and follow the prompts to create a virtual phone.
3. Finally, click the green **Run ▶** button at the top of Android Studio — the app will install and open on your device.

## 🗺️ Roadmap ideas

- Larger, higher-resolution input images for better accuracy in the field.
- More crops and diseases beyond cotton.
- Full offline account mode (local session without Firebase).
- Regional-language support beyond Marathi.

## 📄 License

This project is personal/portfolio work — feel free to open an issue if you would like to use it or collaborate.
