# SecureDevelopment
Creation of a mobile application using Android Studio


For this application, I have 2 pages : one login page by using the fingerprint. 
The second page shows the account that the user has selected by using the spinner. 


#Questions : 

## Explain how you ensure user is the right one starting the app : 

To ensure the user is the right one, I add a biometric login for the first page (by using the fingerprint sensor). 

## How do you securely save user's data on your phone ?

First the API url uses the https protocol, therefore all the data is encrypted. Also I check if the phone has a internet connection. If not a message will popup and say to get a connection.  

## How did you hide the API url ?

To hide the API url I write a C++ file to encode the URL. Therefore if we try to do some reverse engineering, we can not see clearly the url. 

## Screenshots of your application : 

Login page : 
![image](https://user-images.githubusercontent.com/63189346/110238712-6fca5700-7f43-11eb-81ce-66d0cd92c3b0.png)
![image](https://user-images.githubusercontent.com/63189346/110247494-db74ea00-7f6c-11eb-805a-b1a8e4b38216.png)

Account 
![image](https://user-images.githubusercontent.com/63189346/110247515-ed568d00-7f6c-11eb-947a-cd3523307de0.png)
![image](https://user-images.githubusercontent.com/63189346/110247524-f9424f00-7f6c-11eb-9b83-2dd6afd0ba2e.png)
