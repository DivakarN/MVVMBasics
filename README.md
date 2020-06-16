# MVVMBasics

MVVM:
-----

MVVM architecture is a Model-View-ViewModel architecture that removes the tight coupling between each component. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables.

Advantages:
-----------

Writing clean code.
Having a flexible architecture.
Testing the code.
Creating a great user experience.
Developing features faster than product management can think of them. 

Model: 
------

It represents the data and the business logic of the Android Application. It consists of the business logic - local and remote data source, model classes, repository.

View: 
-----

It consists of the UI Code(Activity, Fragment), XML. It sends the user action to the ViewModel but does not get the response back directly. To get the response, it has to subscribe to the observables which ViewModel exposes to it.

ViewModel: 
----------

It is a bridge between the View and Model(business logic). It interacts with the Model and exposes the observable that can be observed by the View.

OnCleared:
----------

If created activity removed from the stack then viewmodel will call OnCleared(). For activity recreation, viewmodel won't call OnCleared() method in viewmodel.

SharedViewModel:
----------------

It's very common that two or more fragments in an activity need to communicate with each other.
This common pain point can be addressed by using ViewModel objects. These fragments can share a ViewModel using their activity scope to handle this communication.

LiveData:
---------

LiveData is an observable data holder class. LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

1) Once a data is modified/changed, the observers will be notified about the change
2) But before notifying the observers, LiveData will check if the observer is live or not. If it is live, then the notification will be sent otherwise not. In this way, the crash due to stopped activities will be stopped.
3) While using LiveData, you need not worry about unsubscribing any observers.
4) If the inactive observer will be resumed in future, then the latest data will be sent to that observer.
5) No need to worry about activity recreation due to screen rotation because only the updated data will be sent.

Advantages:

1) Memory leaks
2) Crashes
3) Outdated UI

DataBinding:
------------

Data Binding is used to update the View from the ViewModel.

Advantages:

1) Remove boilerplate code
2) Stronger readability
3) Even faster than findViewById

Disadvantages:

1) Auto generated class - Increase app size
2) Hard to debug 

MVP vs MV vs MVVM:
-------------------

MVP:
----

Model- It is business logic and Data State. Getting and manipulating the data, communicates with the presenter, interacts with the database. It doesn't interact with the view.
View - Consists of UI, activity, and fragment. It interacts with the presenter.
Presenter- It presents the data from the model. Control all the behavior that want to display from the app. It drives the view. It tells view what to do. Any interaction between the model and the view is handled by the presenter. Saves the data back to the model.

Advantages:

It makes view dumb so that you can swap the view easily.
Reusable of View and Presenter
Code is more readable and maintainable
Easy testing as business logic separated from UI

Disadvantages:

Tight coupling between View and Presenter
Huge amount of interfaces for interaction between layers.
The code size is quite excessive.

MVC:
----

Model- It is business logic and Data State. Getting and manipulating the data, communicates with the controller, interacts with the database, sometimes update the views.
View- What we see. User Interface consists of HTML/CSS/XML. It communicates with the controller and sometimes interacts with the model. It passed some dynamic views through the controller.
Controller- It is Activity/Fragment. It communicates with view and model. It takes the user input from view/REST services. Process request Get data from the model and passes to the view.

Advantages:

It keeps business logic separate in the model.
Support asynchronous techniques
The modification does not affect the entire model
Faster development process

Disadvantages:

Due to large code controller is unmanageable.
Hinders the Unit testing
Increased Complexity

MVVM:
-----

Model- It has business logic, local and remote data source and repository.Repository: communicate with local or remote data sources according to the request from ViewModel.
View- Only user interaction i.e.XML, no business logic. Direct send user action to view model but does not directly get a response. To get a response view observes some data which ViewModel exposes.
ViewModel- Most of the user interface logic center it here. It is a bridge between a view and a business logic. It does not have any clue which view has to use it. As it does not have a direct reference to the view. Thus, good in testing and has loose coupling. But still, it needs to update the UI this interaction done by observables. When data changes observable notifies.

Advantages:

No tight coupling between the view and view model
No interfaces between view and model.
Easy to unit testing and code is event-driven.

Disadvantage:

You have to create observables for each UI component.
The code size is quite excessive.
