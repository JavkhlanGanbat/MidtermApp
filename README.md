# FlashCardApp

Welcome to the FlashCardApp! This Android application is designed to help users learn new words through flashcards. Below is a detailed explanation of how the app works, its architecture, and how to get started.

---

## Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
  - [MVVM Pattern](#mvvm-pattern)
  - [Room Database](#room-database)
  - [Jetpack Compose for UI](#jetpack-compose-for-ui)
  - [WorkManager & AlarmManager](#workmanager--alarmmanager)
- [Project Structure](#project-structure)
- [How It Works](#how-it-works)
  - [MainActivity](#mainactivity)
  - [ViewModel](#viewmodel)
  - [Repository](#repository)
  - [Database and DAO](#database-and-dao)
  - [UI Components](#ui-components)
  - [Notifications](#notifications)
- [Installation and Running the App](#installation-and-running-the-app)
- [Future Improvements](#future-improvements)
- [Conclusion](#conclusion)

---

## Overview
The FlashCardApp is an Android application that lets you add, edit, delete, and review flashcards for language learning. It provides a simple and interactive interface for managing words and their translations, and even allows you to mark favorite ones. The app also reminds you to review words with timely notifications.

---

## Architecture

### MVVM Pattern
- **Model:** Contains the data classes (such as the `Word` class) representing your flashcards.
- **ViewModel:** Manages the UI-related data, interacts with the Repository, and updates the UI. (See `WordViewModel.kt`.)
- **View:** Built with Jetpack Compose. UI components display the words, dialogs for editing, and navigation between screens. (See files under `ui` folder.)

### Room Database
- Uses Room to store flashcards locally on your device.
- The `WordDB` handles database creation and migrations, and `WordAppDao` handles the database operations.

### Jetpack Compose for UI
- The entire interface is built using Compose.
- Screens such as the main screen (showing words), settings screen, and favorites screen are composable functions.

### WorkManager & AlarmManager
- **WorkManager:** Schedules periodic notifications to remind users to review words.
- **AlarmManager:** A backup scheduling mechanism ensures notifications occur even if WorkManager is delayed.

---

## Project Structure
- **ViewModel:** Located in `ViewModel/WordViewModel.kt`. It manages UI data and interacts with the Repository.
- **Repository:** Found in `Repository/WordRepository.kt`. It is the single source of truth for data operations.
- **Database:** The `Room` database is set up in `room/WordDB.kt`, with the DAO in `room/WordAppDao.kt` and the model in `room/Word.kt`.
- **UI Components:** Include screens (e.g., `MainScreen.kt`, `SettingsScreen.kt`, and `FavoritesScreen.kt`) and smaller components (e.g., word display, editing dialogs).
- **Notifications:** Implemented in `DailyNotificationWorker.kt`, and managed with helper classes.

---

## How It Works

### MainActivity
- Entry point of the app.
- Initializes the database, Repository, and ViewModel.
- Sets up navigation using Compose’s `NavHost` to switch between screens.
- Calls `NotificationHelper` when the app is destroyed to schedule notifications.

### ViewModel
- `WordViewModel.kt` connects the UI and Repository.
- Provides functions to add, update, and delete words.
- Uses Kotlin coroutines to work with asynchronous data operations.

### Repository
- `WordRepository.kt` acts as an intermediary between the ViewModel and the Room database.
- Provides methods to perform CRUD operations on flashcards.

### Database and DAO
- **Database (WordDB.kt):** Creates and migrates the Room database.
- **DAO (WordAppDao.kt):** Contains functions for querying, inserting, updating, and deleting words.

### UI Components
- **MainScreen:** Displays words using `WordDisplay` and allows navigation.
- **WordEditDialog:** A dialog component that lets users add or edit words.
- **WordDisplay:** Shows a flashcard with both the foreign word and its translation.
- **Navigation Buttons:** Allow users to move between different flashcards.
- **Settings Screen:** Lets users choose display preferences for words.
- **Favorites Screen:** Lists favorite words that have been marked by the user.

### Notifications
- **DailyNotificationWorker.kt:** Implements the notification logic.
- **NotificationHelper.kt and NotificationReceiver.kt:** Schedule and trigger notifications using both WorkManager and AlarmManager.
- Provides periodic reminders for users to learn new words.

---

## Installation and Running the App

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/FlashCardApp.git
   ```
2. **Open in Android Studio:**
   - Open Android Studio and select "Open an existing project".
   - Navigate to the downloaded folder (e.g., `c:\Users\User\Downloads\FlashCardApp-main\MidtermApp`).
3. **Build the Project:**
   - Ensure that you have the required Android SDK and necessary Compose libraries installed.
   - Click on “Build” and then “Make Project”.
4. **Run on an Emulator or Device:**
   - Connect an Android device or start an emulator.
   - Run the app by clicking on the Run button.

---

## Future Improvements
- **Enhanced UI:** Adding animations and more themes.
- **Additional Features:** Possibility to add images or audio to flashcards.
- **Customization:** Allowing users to set custom notification intervals.
- **Performance:** Enhancing data synchronization for larger datasets.

---

## Conclusion
The FlashCardApp is built using modern Android development practices. It employs the MVVM pattern, Room for local storage, and Jetpack Compose for UI, making it a clean and maintainable project ideal for learning or expanding your Android development skills. Whether you're a beginner or an experienced developer, this app provides a practical example of combining several Android components into one cohesive application.

Happy coding and enjoy learning with FlashCardApp!
