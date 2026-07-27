# CottonCure - Cotton Plant Disease Detection App

CottonCure is an Android application designed for cotton plant disease detection and related functionalities.

## 🛠️ Step-by-Step Setup Guide (For Beginners)

If you are new to Android development, follow these steps carefully to get the app running on your computer.

### Step 1: Install Required Software
1. Download and install **Android Studio** from the official website: [Download Android Studio](https://developer.android.com/studio)
2. During installation, leave all the default settings (this will install the necessary Android software automatically).

### Step 2: Download the Project
1. Go to this repository on GitHub.
2. Click the green **Code** button and select **Download ZIP**.
3. Extract the ZIP file to a folder on your computer (e.g., `Documents/CottonCure`).

### Step 3: Open the Project in Android Studio
1. Open **Android Studio**.
2. Click on **Open** (or go to `File > Open` at the top if you are already inside another project).
3. Browse to the folder where you extracted the project, select the `CottonCure` folder, and click **OK**.
4. Wait for the project to load. You will see a loading bar at the bottom right saying "Gradle Sync". **Wait until this finishes completely.** (This might take a few minutes the first time).

### Step 4: Add Firebase Configuration (Crucial Step)
This app uses Firebase for user login and database. You must connect your own free Firebase account for the app to work properly.

1. Go to the [Firebase Console](https://console.firebase.google.com/) and log in with your Google account.
2. Click **Add project** and give it a name (like "CottonCure App").
3. On the project overview page, click the **Android icon** to add an Android app.
4. In the "Android package name" field, enter exactly this: **`com.example.classification`**
5. Click **Register app**.
6. Click **Download google-services.json**.
7. Copy the downloaded `google-services.json` file.
8. Go back to Android Studio. In the top-left corner, change the project view from "Android" to **"Project"** from the dropdown menu.
9. Expand the `CottonCure` folder, then open the `app` folder.
10. Paste the `google-services.json` file directly inside the `app` folder.
11. **Enable Authentication:** In the Firebase console, go to **Authentication** (on the left menu) > click **Get Started** > go to the **Sign-in method** tab > enable **Email/Password**.
12. **Enable Realtime Database:** In the Firebase console, go to **Realtime Database** > click **Create Database** > start in **Test Mode**.

> **Note on APIs:** There are no other API keys (like Gemini or OpenAI) required! The plant disease detection uses an offline machine learning model (`model.tflite`) that is already included in the app.

### Step 5: Run the App
1. **To run on your phone:** 
   - Connect your Android phone to your computer with a USB cable.
   - Go to your phone's Settings > About Phone, and tap "Build Number" 7 times to unlock Developer Options.
   - Go back to Settings > Developer Options and enable **USB Debugging**.
2. **To run on an Emulator:** 
   - Click the **Device Manager** icon in Android Studio (top right).
   - Click **Create Device** and follow the prompts to create a virtual phone.
3. Finally, click the green **Play (Run)** button at the top of Android Studio. The app will install and open on your device/emulator!
