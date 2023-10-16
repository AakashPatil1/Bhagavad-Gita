# Bhagavad-Gita
Create an app for the Bhagavad Gita by integrating a free API to fetch verses and related content. Utilize SharedPreferences to allow users to personalize their experience by storing preferences like language selection and favorite verses within the app.


## Overview

The Bhagavad Gita App is a mobile application that provides access to the timeless wisdom of the Bhagavad Gita, a sacred Hindu scripture. The app fetches Bhagavad Gita verses and related content using the [Bhagavad Gita API](https://bhagavadgita.io/api) and allows users to customize their experience.

## Features

- Retrieve Bhagavad Gita verses and translations using the [Bhagavad Gita API](https://bhagavadgita.io/api) and save the data in SharedPreferences.
- Display verses and translations from SharedPreferences in a user-friendly format within the app without making redundant API calls.


## Usage

1. Sign up on RapidAPI and obtain your API key from [Bhagavad Gita API on RapidAPI](https://rapidapi.com/bhagavad-gita-bhagavad-gita-default/api/bhagavad-gita3).

2. Add your API key to the HTTP request headers when making API calls using the following code:

    ```java
    Request authenticatedRequest = originalRequest.newBuilder()
            .addHeader("X-RapidAPI-Key", "**Enter your API key**")
            .addHeader("X-RapidAPI-Host", "bhagavad-gita3.p.rapidapi.com")
            .build();
    ```

    Replace "**Enter your API key**" with the API key you obtained from RapidAPI.

3. Run the app on a mobile device or emulator.

4. Explore Bhagavad Gita verses, translations, and customize the app's settings based on your preferences. The app will use the data saved in SharedPreferences, minimizing API calls.


## Installation

To run the app locally, follow these steps:

1. Ensure you have the necessary development environment set up, including Android Studio and an Android emulator or a physical Android device.

2. Clone the repository using the following command:

    ```bash
    git clone https://github.com/your_username/bhagavad-gita-app.git
    ```

3. Open the project in Android Studio.

4. Build and run the app on your emulator or device.

## Contributing

Contributions are welcome! If you'd like to contribute to the project, please follow the [Contributing Guidelines](CONTRIBUTING.md).

## License

This project is licensed under the [MIT License](LICENSE).
