# LostItFoundIt

An app to help members of the Duke community find lost items. 

## Overall Design Architecture

### Object-Oriented Design

<p><img width="589" alt="screen shot 2018-12-12 at 5 17 24 pm" src="https://user-images.githubusercontent.com/22549537/49902399-d37f7b80-fe31-11e8-91a0-d47c20c8324c.png"></p>

We built this app using [Android Studio](https://developer.android.com/studio/). The front-end and UI was designed using XML. The app lifecycle and activities were coded in Java. On the backend, we used [Firebase](https://firebase.google.com/) to host our database and the [Android java.sql](https://developer.android.com/reference/java/sql/package-summary) API for the interactions between our app and the database.

### Data Structure
Our data is structured in the form of a JSON tree:

```
{
  "found" : {
    "clothing" : {
      "East Campus" : {
        "-LTUre_BXjXiStaMnnKw" : {
          "description" : "ysg",
          "location" : "East Campus",
          "name" : "hahs",
          "time" : "Tue Dec 11 19:43:48 EST 2018",
          "type" : "clothing"
        }
      }
    },
    "electronic" : {
      "Divinity School" : {
        "-LTUvltJDMgFL343CGnC" : {
          "description" : "y",
          "location" : "Divinity School",
          "name" : "gs",
          "time" : "Tue Dec 11 20:01:46 EST 2018",
          "type" : "electronic"
        }
      }
    }...
      --- other JSON objects ommitted ---
  }
}
```

Whenever a user reports a found item, this item will be stored as a JSON object with the attributes ```name``` (name of item), ```type``` (type of item), ```description``` (description of item), ```location``` (location where item was found) and ```time``` (time of report) as shown above. The user can also upload a picture of the item. 

Whenever a user reports a lost item, instead of storing lost item reports on the database, the information from the lost report will be used to query the data table of found items to determine if the item has been found, and the user will be presented with a list of found items that match the query that they entered. 

Our JSON tree has **4 layers**:
1. Found items
2. Type of item
3. Location where item was found
4. Item object

We chose to structure it this way as whenever we query the data table of found items, we first query by ```type```, then by ```location```. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine or device for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Deployment on Android device
We have generated an APK (Android Application Package) file. Follow these steps:
1. Connect your Android smartphone or tablet to your computer
2. Download the APK file
3. Once it's downloaded, open Downloads, tap on the APK file, and tap Yes when prompted
4. The app will begin installing on your device

## Testing on Android Studio

If you don't own an Android device or if you just want to run the app on your local machine, you can also test it on Android Studio. You can download Android Studio [here](https://developer.android.com/studio/). 

In this GitHub Repository, click 'Clone or Download':
<p><img width="432" alt="screen shot 2018-12-11 at 9 27 40 pm" src="https://user-images.githubusercontent.com/22549537/49843002-a5476080-fd8b-11e8-9046-a00785a4d511.png"></p>

If you're not familiar with Github and cloning repositories, you can simply click 'Download ZIP', unzip the folder, and open the directory on Android Studio. Thereafter, follow [this](https://developer.android.com/studio/run/) tutorial to build and run the app. 

## Limitations of our current implementation
Our app is currently only Android-compatibile. 

Our app currently does not store information on lost reports that users have made, the only way for a user to find out if his/her lost item has been found is to keep querying the database to see the results. In the future, we can improve this by storing lost item reports, and implementing a scheduler that scans through the found items in the database periodically, and send mobile push notifications to a given user if his/her item has been found i.e. there has been a match with found item(s) in the database. 

## Team

- Elizabeth Bartusiak
- Alethea Toh
- Scott Heng
- Ryan Hill
- Sophia Ellingham
