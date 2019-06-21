# IMDB_Test

Evaluation app for Blue Coding job application by Nelson Pantaleon

# Usage
You must provide an API KEY for the REST calls to work (I've included one in the email that was sent). In order to keep the key private, the app currently looks into your `user/.gradle/` folder for a `gradle.properties` file
It must contain the property `imdb_api_key`. You may also alter the code in the `TokenInterceptor.kt` class and hardcode the token

## Description
This app makes use of the following:
- Kotlin
- Coroutines
- Google recommended architecture
- Retrofit with OkHttp3
- Dagger 2
- Glide
- Android Architecture Components / Jetpack / Androidx
- IE. New Navigation graph
- Room database
- DataBinding and Data adapters including SafeArgs
- ViewModels
- LiveData
- Kotlin Extensions
- ConstraintLayout
- Jackson

## Functionalities and Considerations

The app was developed over the course of two and a half days during my free time. 
Due to time contraints, I had to leave out a couple of features: 
- The local databse cache is incomplete. 
For now it caches the data from the remote API and all subsequent requests are made using the Room unless you use pull down to refresh
which will force the repository to fetch fresh data from the remote API. The idea was to mark local data as 'stale' after a set amount of time
- The Favorites functionality only works offline and its stored in the local database. Movies marked as favorite will be reset if data is refreshed from remote API again
- The UI will display a grid of 2 columns on phones in portrait mode and 4 columns in landscape and tablets.
- The app was tested to work using a virtual emulator for a `Pixel 2` phone and a `Nexus 7` tablet as well as my own personal device, a Razer Phone.
All of them running on `Android 8.1 Oreo (API 27)`
