# CottonCure

CottonCure is an Android application designed for cotton plant disease detection and related functionalities.

## Prerequisites

- **Android Studio** (Latest version recommended)
- **Android SDK**
- **Firebase Account** (for authentication/database functionality)

## Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/AvishkarKedar/CottonCure.git
   ```

2. **Open the project in Android Studio:**
   - Launch Android Studio.
   - Select `Open an existing project` (or `File > Open`).
   - Navigate to the cloned `CottonCure` directory and open it.
   - Wait for the Gradle sync to complete.

3. **Firebase Configuration (Manual Setup Required):**
   This project relies on Firebase services. You will need to replace the existing `google-services.json` with your own if you want to use your own Firebase backend.
   - Go to the [Firebase Console](https://console.firebase.google.com/).
   - Create a new project or select an existing one.
   - Add an Android app to the project with the package name `com.example.cottoncure` (or match it with your `build.gradle` applicationId).
   - Download the `google-services.json` file.
   - Place the downloaded `google-services.json` file into the `app/` folder of this project, replacing any existing one.

4. **Build and Run:**
   - Connect a physical Android device with USB Debugging enabled, or start an Android Emulator from the AVD Manager.
   - Click the **Run** button (green play icon) in the Android Studio toolbar.
   - Alternatively, build the APK from the command line using Gradle:
     ```bash
     ./gradlew assembleDebug
     ```

## Features
- ML-based disease detection (Check the ML model integrated)
- User Authentication (Registration/Login)
- Helpful links and resources

