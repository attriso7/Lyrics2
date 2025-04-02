plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt") // Required for Room
}

android {
    namespace = "com.eminem.lyrics"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.eminem.lyrics"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    // Room Database
    implementation("androidx.room:room-runtime:2.5.0")
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    kapt("androidx.room:room-compiler:2.5.0") // Use kapt for annotation processing
    implementation("androidx.room:room-ktx:2.5.0") // Coroutines support

    // Core dependencies
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
