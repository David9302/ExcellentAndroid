plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.it.excellent.architecture"
    compileSdk = 35

    defaultConfig {
        minSdk = 28

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    api(project(":common"))

    // 常用基础组件.
    api(libs.androidx.appcompat.v161)
    api(libs.annotations)
    api(libs.androidx.navigation.runtime)
    api(libs.material.v190)
    api(libs.androidx.constraintlayout)
    api(libs.androidx.recyclerview)

    // 常用架构组件，已按功能提取分割为多个独立库。 可按需选配.
    api(libs.mvi.dispatcher)
    api(libs.unpeek.livedata)
    api(libs.smooth.navigation)
    api(libs.binding.recyclerview)
    api(libs.binding.state)
    api(libs.strict.databinding)

    // 常用数据、媒体组件.
    api(libs.glide)

    api(libs.gson)
    api(libs.retrofit)
    api(libs.converter.gson)
    api(libs.logging.interceptor)
    api(libs.okhttp)
    api(libs.rxandroid)
    api(libs.rxjava)
}