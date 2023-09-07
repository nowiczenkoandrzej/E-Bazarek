plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.an.e_bazarek.shared_resources"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api("androidx.core:core-ktx:1.7.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    api("androidx.activity:activity-compose:1.3.1")
    api("androidx.compose.material:material:1.2.0")
    api("androidx.compose.ui:ui:1.2.0")
    api("androidx.compose.ui:ui-tooling-preview:1.2.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    api("androidx.lifecycle:lifecycle-runtime-compose:2.6.0")

    // Compose Navigation
    api("androidx.navigation:navigation-compose:2.6.0")

    // Hilt
    api("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    // Firestore
    api("com.google.firebase:firebase-firestore-ktx:24.7.1")

    // Firebase Authentication
    api("com.google.firebase:firebase-auth:22.1.1")

    // Gms Play Services
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Coil Compose
    api("io.coil-kt:coil-compose:2.2.2")

}

kapt {
    correctErrorTypes = true
}