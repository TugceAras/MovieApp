# <p align="center"> Movies Catalog 🎥 🍿 </p>

<!-- Screenshots -->
## 📸 Screenshots
<p align="center">
  <img src="https://user-images.githubusercontent.com/79931228/226363870-5a445ecb-03d4-4481-a2fd-421d271571aa.png"/>
  <img src="https://user-images.githubusercontent.com/79931228/226363872-de856000-124b-4c6e-9ccd-c9937fa64d18.png"/> 
  <img src="https://user-images.githubusercontent.com/79931228/226363848-fb814560-bd28-4f6d-9654-683cd1511f7a.png"/> <br>
  <img src="https://user-images.githubusercontent.com/79931228/226363863-9e33febf-7436-4885-a587-d2deb8cf6ba1.png"/>
  <img src="https://user-images.githubusercontent.com/79931228/226363865-92f5f4f1-c25f-496d-ad9c-801c478f0465.png"/>
  <img src="https://user-images.githubusercontent.com/79931228/226363868-f3bb7a89-95c8-40ab-9e60-3f786e1775c7.png"/> <br>
</p>

<br>

## 📽 Video
<!-- Video -->
<div align="left">
  <video src="https://user-images.githubusercontent.com/79931228/226417765-964f13a3-801c-4f3e-a4ef-27aaac3b2a3a.mp4"/>
</div>

<br>

<!-- Technologies -->
## :point_down: Structures Used
- MVVM
- WebView
- Dependency Injection | Hilt
- Coroutine
- Retrofit
- Room
- View Binding | Data Binding
- Parcelable
- Glide

For animation : Lottie used
<br>

<!-- Screens -->
## 📱 Screens
- Login Screen
- Popular Movie Screen
- Search Screen
- Detail Screen
- Watchlist Screen
- Favorites Screen


## :pencil2: Dependency
```
    // Navigation
    def nav_version = "2.5.3"
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

    // Coroutine
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Coroutine Scope
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.6.0'

    // Room
    def room_version = "2.5.0"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"

    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    // Glide
    implementation 'com.github.bumptech.glide:glide:4.14.2'
    kapt  'com.github.bumptech.glide:compiler:4.14.2'

    // DI-Hilt
    implementation "com.google.dagger:hilt-android:2.44"
    kapt "com.google.dagger:hilt-compiler:2.44"

    // Lottie
    implementation 'com.airbnb.android:lottie:5.2.0'
```

app build.gradle:

```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-parcelize'
    id 'androidx.navigation.safeargs.kotlin'
    id 'kotlin-kapt'
    id 'com.google.dagger.hilt.android'
}

buildFeatures {
      viewBinding true
      dataBinding true
 }
```
project build.gradle:

```
plugins {
    id 'com.android.application' version '7.4.2' apply false
    id 'com.android.library' version '7.4.2' apply false
    id 'org.jetbrains.kotlin.android' version '1.8.0' apply false
    id 'androidx.navigation.safeargs' version '2.5.3' apply false
    id 'com.google.dagger.hilt.android' version '2.44' apply false
}
```

<!-- Manifest File -->
## :exclamation: Manifest File
```
<uses-permission android:name="android.permission.INTERNET" />
```

<!-- API -->
## :point_down: API
- https://www.themoviedb.org/
