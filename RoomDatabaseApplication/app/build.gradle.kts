plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.roomdatabaseapplication"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.roomdatabaseapplication"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Room database
    def room_version:String = "2.5.0"
    implementation "androidx.room:room-ktx:$room_version"
    kapt "androidx.room:room-compiler:$room_version"

    // Navigation Component
    implementation 'androidx.navigation:navigation-fragment-ktx:2.2.2'
    implementation 'androidx.navigation:navigation-ui-ktx:2.2.2'

    // Room components
    implementation "androidx.room:room-runtime:2.2.5"
    kapt "androidx.room:room-compiler:2.2.5"
    implementation "androidx.room:room-ktx:2.2.5"
    androidTestImplementation "androidx.room:room-testing:2.2.5"

    // Lifecycle components
    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
    implementation "androidx.lifecycle:lifecycle-common-java8:2.2.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"

    // Kotlin components
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72"
    api "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5"
    api "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5"
}