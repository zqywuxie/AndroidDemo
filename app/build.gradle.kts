plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.classdemo3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.classdemo3"
        minSdk = 24
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


}


dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation(files("libs\\AMap3DMap_10.0.700_AMapSearch_9.7.2_AMapLocation_6.4.5_20240508.jar"))
//    implementation(files("libs/AMap3DMap_10.0.700_AMapSearch_9.7.2_AMapLocation_6.4.5_20240508.jar"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.squareup.okhttp3:okhttp:3.2.0")
    implementation("com.google.code.gson:gson:2.8.8")
//    implementation("com.amap.api:3dmap:9.8.2")
    implementation(fileTree("libs"))
}