# MusicHub User Manual

Welcome to **MusicHub**, a Java-based application that connects users to music-related information via the Last.fm API. This manual will guide you through the features, usage, and the underlying functionality of MusicHub.

---

## Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [How to Use MusicHub](#how-to-use-musichub)
   - [Main Menu Navigation](#main-menu-navigation)
   - [Artist Information](#artist-information)
   - [Song Information](#song-information)
   - [Top Artists and Songs by Country](#top-artists-and-songs-by-country)
   - [Settings](#settings)
   - [Exiting the Application](#exiting-the-application)
4. [APIs Used](#apis-used)
5. [How It Works](#how-it-works)
6. [Additional Notes](#additional-notes)

---

## Overview

**MusicHub** is a console-based application designed to retrieve and display music-related information. It leverages the Last.fm API to fetch data about artists, songs, and trends. Users can also customize the application's appearance and cache data for offline use.

---

## Features

- **Artist Information**: Get basic or detailed information about any artist.
- **Song Information**: Retrieve details about songs based on artist and track names.
- **Top Artists and Songs by Country**: Discover trending artists and songs for a specific country.
- **Offline Mode**: Access cached data when there's no internet connection.
- **Customizable Colors**: Modify the application's appearance to suit your preferences.
- **Data Caching**: Automatically save the latest API responses for offline use.

---

## How to Use MusicHub

### Main Menu Navigation

When you launch the application, you'll see the main menu with the following options:

```
+----------------------------+
|         MUSIC HUB          |
+----------------------------+
Enter 'artist' to get information about an artist.
Enter 'song' to get information about a song.
Enter 'topArtists' to get top artists from a country.
Enter 'topSongs' to get top songs from a country.
Enter 'S' to open settings.
Enter 'Q' to quit MusicHub.
```

Choose an option by typing the corresponding command in the console.

---

### Artist Information

1. **Choose "artist" from the main menu**:
   ```
   Enter 'artist' to get information about an artist.
   ```
2. **Input the artist's name** when prompted.

3. **Select the level of detail**:
   - `ArtistBasic`: Displays basic information like the number of listeners, play count, and a similar artist.
   - `ArtistExpanded`: Includes additional details like the artist's biography and the published date.

4. The application will retrieve and display the requested information.

---

### Song Information

1. **Choose "song" from the main menu**:
   ```
   Enter 'song' to get information about a song.
   ```
2. **Input the artist's name** when prompted.
3. **Input the song's name** when prompted.
4. **Select the level of detail**:
   - `SongBasic`: Displays basic song information like duration, play count, and tags.
   - `SongExpanded`: Includes additional details like the song's summary and the published date.

5. The application will fetch and display the requested information.

---

### Top Artists and Songs by Country

1. **Choose "topArtists" or "topSongs" from the main menu**:
   ```
   Enter 'topArtists' to get top artists from a country.
   Enter 'topSongs' to get top songs from a country.
   ```
2. **Input the country name** when prompted.
3. The application will display a list of top artists or songs for the specified country.

---

### Settings

1. **Choose "S" from the main menu** to open the settings menu.
2. Navigate through the options to customize colors:
   - Headings
   - Options
   - Outputs
   - Cached Data
3. Reset colors to default if desired.
4. Changes are saved in the `config.properties` file.

---

### Exiting the Application

1. **Choose "Q" from the main menu** to quit MusicHub.
2. Before exiting, the application will save the latest API data to a JSON file for offline use.

---

## APIs Used

MusicHub interacts with the **Last.fm API** to fetch music-related data. The key API methods used are:

1. **Artist Info**:
   - URL: `http://ws.audioscrobbler.com/2.0/?method=artist.getinfo`
   - Fetches detailed information about an artist.

2. **Track Info**:
   - URL: `http://ws.audioscrobbler.com/2.0/?method=track.getInfo`
   - Retrieves details about a song based on artist and track names.

3. **Top Tracks by Country**:
   - URL: `http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks`
   - Provides a list of top songs for a specific country.

4. **Top Artists by Country**:
   - URL: `http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists`
   - Provides a list of top artists for a specific country.

> **Note**: The API key is hardcoded into the application and is required for all API requests.

---

## How It Works

1. **Main Components**:
   - **Model**: Handles API interactions and processes JSON responses.
   - **ConsoleView**: Manages user interaction and displays data.
   - **Presenter**: Acts as a bridge between the Model and ConsoleView.
   - **DataHandler**: Manages caching and retrieval of API data.

2. **Application Flow**:
   - The `MusicHub` class initializes the Model, View, and Presenter.
   - The Presenter coordinates between user input (via ConsoleView) and data fetching (via Model).
   - Data is fetched from the Last.fm API and displayed in the console.
   - Cached data is used when there is no internet connection.

3. **Offline Mode**:
   - Cached data is stored in a JSON file (`last_api_call_cache.json`).
   - If the API is unavailable, the application reads this file and displays the cached data.

---

## Additional Notes

- **Error Handling**:
  - If the API is unavailable or a request fails, the application prompts the user to view cached data.
  - Invalid user inputs are highlighted with error messages.

- **Customization**:
  - The application's appearance can be customized with ANSI color codes.
  - Saved settings are loaded automatically on the next launch.

- **Future Enhancements**:
  - Add support for user authentication with Last.fm.
  - Improve error messages and logging.
  - Enhance the UI with additional features like pagination for long lists.

---

Thank you for choosing MusicHub! We hope you enjoy exploring the world of music with our application.
