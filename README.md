
## Version Catalogs
In this repo, i have published all my most commonly used dependencies as a binary on maven central. 

### Why did I do this
Well to begin with, I like experimenting a lot, which means creating lots of projects sometimes and throwing them away after experimentation.  I got bored of having to look up dependencies whenever I needed them and decided to publish my own combination of libraries to maven instead. 

### Installation
Add the following to your `settings.gradle.kts` file

```gradle
dependencyResolutionManagement {  
  repositories {  
  mavenCentral()  
  google()  
  gradlePluginPortal()  
}  
  
versionCatalogs {  
  create("libs") {  
    from("io.github.rafsanjani:versions:0.3.3")
  }  
}
```

You can replace specific versions of the dependency by specifying them in the `create` block

```gradle
dependencyResolutionManagement {  
  repositories {  
  mavenCentral()  
  google()  
  gradlePluginPortal()  
}  
  
versionCatalogs {  
  create("libs") {  
    from("io.github.rafsanjani:versions:0.3.3")
    version("kotlin","1.8.22") //override kotlin version
  }  
}  
```

You can reference a specific library from the `libs` object that is created after the project is configured. 

```gradle
dependencies{
  implementation(libs.androidx.core)
}
```

You can rename your catalogs object so that it doesn't clash with your local local `libs.versions.toml` file. 

```gradle
dependencyResolutionManagement {  
  repositories {  
  mavenCentral()  
  google()  
  gradlePluginPortal()  
}  
  
versionCatalogs {  
  create("remoteLibs") {  
    from("io.github.rafsanjani:versions:0.3.3")
  }  
}  
```

There reference will change according during dependency declaration
```gradle
dependencies{
  implementation(remoteLibs.androidx.core)
}
```


You can also reference plugins using the newer gradle syntax

```gradle
 alias(libs.plugins.android.application)
```

Code completion can be leveraged for discovering other libraries and plugins. 


You can see a complete usage by referencing the sample app


##### Note: I'm updating these dependencies very rapidly so I wouldn't recommend using it in a production environment. 
