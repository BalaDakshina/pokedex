
## Architecture and Technologies

- **Architecture**: Implemented using the Model-View-ViewModel (MVVM) architecture pattern to ensure a unidirectional data flow and enhance testability.
- **UI Framework**: Built with Jetpack Compose for a modern, declarative UI approach.
- **Navigation**: Compose Navigation is used to manage navigation between screens.
- **Networking**: Retrofit is used for making API calls.
- **Asynchronous Programming**: Kotlin Coroutines are used for asynchronous operations.

## Things Yet to be done

- Marking a pokemon as favorite in details screen
- Implementing the favorites screen 
- Implement StateIn so that we don't have to trigger the api call from composable manually
- Create separate modules for features for scalability in the future.