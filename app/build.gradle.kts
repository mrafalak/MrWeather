import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.mrapps.mrweather"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mrapps.mrweather"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val localProperties = rootProject.file("local.properties")
        val properties = Properties()
        properties.load(localProperties.inputStream())

        val apiKey = properties.getProperty("API_KEY") ?: ""

        debug {
            buildConfigField("String", "API_KEY", apiKey)
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", apiKey)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.networking)
    implementation(libs.bundles.di)
    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.testing)
    androidTestImplementation(platform(libs.androidx.compose.bom))
}