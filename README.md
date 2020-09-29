# Translate

This for finding word translation to Russian and reading the definition in English.

## Architecture

**Presentation layer**
The presentation folder for displaying data from data and interacting with user.

**Domain layer**
The domain folder bridge between data and presentation folders. It performs any business logic getting data from data to view.

**Data layer**
The data folder for serving network requests or accessing to the database. Providing the data source for the many features that require it.

**Common folder**
The common folder used for constances, utilities.

## Dependencies

- Jetpack:
  * Android KTX - provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
  * AndroidX - major improvement to the original Android Support Library, which is no longer maintained.
  * Navigation - provide a navigating between 'destinations' within an Android.
  * LiveData - lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
  * ViewModel - designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
- Coroutines - managing background threads with simplified code and reducing needs for callbacks.
- Dagger2 - dependency injector for replacement all FactoryFactory classes.
- Retrofit2 - type-safe HTTP client.
- Glide - image loading library for Android.
- Gson - makes it easy to parse JSON into Kotlin objects.

##Instruction
Donnload project from github with using with direc donloading zip file or cloning with using ssh link.
Open with Android Studio.
Press button start.

**Whan I don't do**
I don't add a Unit tests because of rush and I work only with UI.
