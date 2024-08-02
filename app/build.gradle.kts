plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.symbols)
    alias(libs.plugins.hiltAndroid)

    id("org.jetbrains.kotlin.plugin.parcelize")
}

android {
    namespace = "sample.gituserappcomposed"
    compileSdk = 34

    defaultConfig {
        applicationId = "sample.gituserappcomposed"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        buildTypes {
            debug {
                buildConfigField("String", "BASE_URL", "${properties["BASE_URL_VALUE"]}")
            }
            release {
                buildConfigField("String", "BASE_URL", "${properties["BASE_URL_VALUE"]}")
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }

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
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Hilt
    implementation(libs.hilt.android.v250)
    implementation(libs.hilt.androidx.nav)
    implementation(libs.androidx.material3.android)
    ksp(libs.hilt.compiler)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.logging.interceptor)

    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation(libs.retrofit2.kotlin.coroutines.adapter)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.truth)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}