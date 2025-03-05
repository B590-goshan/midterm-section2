plugins {
    kotlin("android") version "2.0.21" // Using Kotlin Android plugin
    id("com.android.application") // Android application plugin
    id("com.google.gms.google-services") // Firebase Google Services plugin
}

android {
    namespace = "com.example.midterm_section2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.midterm_section2"
        minSdk = 24
        targetSdk = 35
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11" // Ensure Kotlin JVM target is set to 11
    }

    buildFeatures {
        viewBinding = true // Enable View Binding
    }
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:33.10.0")) // Firebase BOM
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore")// Firebase Analytics

    implementation(libs.androidx.core.ktx) // Android Core KTX
    implementation(libs.androidx.appcompat) // Android AppCompat
    implementation(libs.material) // Material Design
    implementation(libs.androidx.activity) // Android Activity
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.firebase.auth.ktx) // ConstraintLayout

    testImplementation(libs.junit) // JUnit testing
    androidTestImplementation(libs.androidx.junit) // Android JUnit testing
    androidTestImplementation(libs.androidx.espresso.core) // Espresso UI testing

    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit2 core
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson converter for Retrofit
    implementation("com.squareup.retrofit2:adapter-kotlin-coroutines:2.9.0")



    implementation("io.coil-kt:coil:2.0.0-rc02")


}
