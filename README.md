# TGS
<h3>TGS Home Android Application with Android Architecture Components<h3>

<h6>NOTE</h6> It is a relatively more complex and complete example so if you are not familiar with Architecture Components, you are highly recommended to check other examples

Application to monitor IOT devices and Control from their phones
This is a sample app that uses Android Architecture Components with Dagger 2.
NOTE It is a relatively more complex and complete example so if you are not familiar
with Architecture Components, you are highly recommended to check other examples
in this repository first.


Introduction

Functionality
The app is composed of 3 main screens.

SearchFragment
Allows you to search repositories on Flipshop.
Each search result is kept in the database in RepoSearchResult table where
the list of repository IDs are denormalized into a single column.
The actual Repo instances live in the Repo table.
Each time a new page is fetched, the same RepoSearchResult record in the
Database is updated with the new list of repository ids.
NOTE The UI currently loads all Repo items at once, which would not
perform well on lower end devices. Instead of manually writing lazy
adapters, we've decided to wait until the built in support in Room is released.

RepoFragment
This fragment displays the details of a repository and its contributors.

UserFragment
This fragment displays a user and their repositories.

Building
You can open the project in Android studio and press run.


ViewModel 
Each ViewModel is tested using local unit tests with mock Repository
implementations.

Repository Tests
Each Repository is tested using local unit tests with mock web service and
mock database.

Webservice Tests
The project uses MockWebServer project to test REST api interactions.

Libraries

Android Support Library
Android Architecture Components
Android Data Binding

Dagger 2 for dependency injection

Retrofit for REST api communication

Glide for image loading

