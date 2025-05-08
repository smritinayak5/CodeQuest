# CodeQuest - Coding Challenges Android App

🚀 **CodeQuest** is an Android application designed to provide users with coding challenges in Java, C++, and Python across various difficulty levels. It aims to help programmers improve their skills through structured practice.

## ✨ Features

- 👤 User Registration and Login (using Firebase Authentication)
- 📝 Edit Profile
- 🔐 Change Password
- 📊 Progress Tracking
- 🧩 Coding Challenges in:
  - Java (Basic, Moderate, Advanced)
  - C++ (Basic, Moderate, Advanced)
  - Python (Basic, Moderate, Advanced)
- 🎯 Test and Submission modules
- 📚 FAQ, Terms, Privacy Policy, Help section
- 📲 Clean and intuitive UI

## 🏗️ Project Structure

app/
├── src/
│ ├── main/
│ │ ├── java/com/example/codingchallengesapp/
│ │ │ └── (all Java activities)
│ │ ├── res/
│ │ │ └── (layouts, drawables, values)
│ │ └── AndroidManifest.xml
├── build.gradle


## 🛠️ Built With

- Java
- Android Studio
- XML
- **Firebase Authentication** (for user login/signup)
- **Firebase Realtime Database** / **Firebase Firestore** (for storing user data)

## 🖥️ Backend Hosting

✅ Fully integrated with **Firebase** for authentication and data storage.  
No external PHP or server required.

## 💾 Installation

1. Clone this repo:
   ```bash
   git clone https://github.com/smritinayak5/CodeQuest.git
2. Open in Android Studio
3. Sync Gradle and build project
4. Set up your Firebase project:
   - Go to Firebase Console
   - Create a new project
   - Add Android app (use your app’s package name)
   - Download google-services.json and place it in /app folder
   - Enable Authentication (Email/Password) and Realtime Database or Firestore
5. Run the app!
