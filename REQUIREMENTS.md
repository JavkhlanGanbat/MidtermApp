# FlashCardApp Requirements Implementation

This document outlines where each of the specified requirements for the FlashCardApp is implemented in the project.

---

## Core Requirements

1.  **Display words from the database in three different modes based on settings:**

    *   **Implementation:** `WordDisplay.kt` composable displays the word based on the `displayOption` passed to it. The `displayOption` can be "Both", "Eng", or "Mongol".
    *   **Settings:** The `selectedOption` state in `WordViewModel.kt` holds the current display setting. This value is collected in `MainScreen.kt` and passed to `WordDisplay.kt`.
    *   **DataStore:** The `selectedOption` is stored and managed using DataStore.

2.  **Tap on TextView to show the hidden translation:**

    *   **Implementation:** Not directly implemented. The current implementation shows either both, only English, or only Mongolian based on the settings.

3.  **Navigate to the previous/next word:**

    *   **Implementation:** `MainScreen.kt` uses `WordNavigationButtons.kt` to provide "Previous" and "Next" buttons. The `currentIndex` state in `MainScreen.kt` tracks the current word index, and the buttons update this index.

4.  **Edit or delete the currently displayed word:**

    *   **Implementation:** `MainScreen.kt` provides "Edit" and "Delete" buttons.
    *   "Edit" button sets the `editingWord` state and shows the `WordEditDialog.kt`.
    *   "Delete" button shows a confirmation dialog and deletes the word.

5.  **Call the edit screen when the "Add" or "Edit" button is pressed:**

    *   **Implementation:** `MainScreen.kt` uses `WordEditDialog.kt` to add or edit words.
    *   "Add" button sets `editingWord` to `null` and shows the `WordEditDialog.kt`.
    *   "Edit" button sets `editingWord` to the current word and shows the `WordEditDialog.kt`.

6.  **Call the settings screen from the menu:**

    *   **Implementation:** `MainScreen.kt` has an "Settings" icon in the `TopAppBar` that navigates to `SettingsScreen.kt` using `navController.navigate("settingsScreen")`.

7.  **Disable buttons when there are no words in the database:**

    *   **Implementation:** `MainScreen.kt` disables the "Edit" and "Delete" buttons when `words.getOrNull(currentIndex)` is `null`.

8.  **Long press on TextView to call the edit screen:**

    *   **Implementation:** `WordDisplay.kt` uses `combinedClickable` to detect long presses. On a long press, it calls the `onLongPress` callback, which is implemented in `MainScreen.kt` to set the `editingWord` state and show the `WordEditDialog.kt`.

9.  **Confirm deletion with a dialog box:**

    *   **Implementation:** `MainScreen.kt` shows an `AlertDialog` before deleting a word.

10. **Adjust the view in landscape mode:**

    *   **Implementation:** `MainScreen.kt` uses `LocalConfiguration` to determine the screen orientation and adjusts the layout accordingly.

---

## Core Criteria

*   **Each of the above 10 criteria (1 point each):** Implemented as described above.
*   **Store the word list in SQLite or Room (3 points):**
    *   **Implementation:** The app uses Room to store the word list. The entities, DAO, and database are defined in the `room` package.
*   **Correct architectural solution (ViewModel, Repository, Flow) (2 points):**
    *   **Implementation:** The app follows the MVVM architecture.
    *   `WordViewModel.kt` is the ViewModel.
    *   `Repository.kt` is the Repository.
    *   `Flow` is used to observe changes in the database.
*   **Store settings data using DataStore (2 points):**
    *   **Implementation:** The `selectedOption` is stored and managed using DataStore.
*   **Send a notification every 24 hours after exiting the app (2 points):**
    *   **Implementation:** `NotificationHelper.kt` and `DailyNotificationWorker.kt` are used to schedule and send daily notifications.
*   **Switch to the app when the incoming notification is pressed (1 point):**
    *   **Implementation:** The `createNotificationIntent()` method in `DailyNotificationWorker.kt` creates a `PendingIntent` that opens the app when the notification is pressed.

