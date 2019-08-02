# Job-Application-Tracker
Helps the user to keep track and manage their Job applications by grouping, sorting and displaying them. 

## Input Requirements
The following inputs are mandatory and are given by the user:
* Position Name or/and Company Name
* Closing Date

Invalid inputs result into Alert Boxes as showing the image below.

![requirements](https://user-images.githubusercontent.com/52593360/62356777-3060ab00-b555-11e9-9d66-56ff54c102c7.gif)

### Adding Job Application

When a Job is added, it's status is automatically set to '**In Progress**' (or '**Urgent**') depending on the number of days until closing date. When the number of days to closing date becomes less then 5, the application status is changed to 'Urgent' as shown in the GIF image below. 

![Add job](https://user-images.githubusercontent.com/52593360/62354981-6c920c80-b551-11e9-9ffe-ab0143eb8beb.gif)

### Marking Application as Applied

A application can be marked as applied by selecting it and hitting the 'Mark Selected Jobs Applied' button as showing in the GIF images below. The date on which the application is marked applied is set as applying date. 

![mark applied](https://user-images.githubusercontent.com/52593360/62354988-70be2a00-b551-11e9-81fc-ca78c073edad.gif)

### Searching 

Job applications can be searched by directly typing in the search bar. If the passed keyword is contained in an application it is displayed in the searched results.

![search](https://user-images.githubusercontent.com/52593360/62354998-74ea4780-b551-11e9-90f7-6c208f54be75.gif)

### Saving Data

The application saves data locally on text files, in the format - __' Application Status ,  Role Name , Company Name , Closing Date '__ or __' Application Status ,  Role Name , Company Name , closing Date , Applied date'__.
When the program loads, it reads the saved files , if present , and creates and loads the applications in memory. 

### Whats Next?
I am planning to add more features to this application such as:

* Database integration 
* Option to delete an application 
* More eventlistners
* A detailed report which gives you satistics about your applications.
* Better GUI 
* An installer or jar file.
