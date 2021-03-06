# LostItFoundIt

An app to help members of the Duke community find their lost items. 

## Overall Design Architecture

### Object-Oriented Design

<p><img width="574" alt="screen shot 2018-12-12 at 6 38 15 pm" src="https://user-images.githubusercontent.com/22549537/49905744-ecd9f500-fe3c-11e8-8c0c-6011fb1ea3ee.png"></p>

We built this app using [Android Studio](https://developer.android.com/studio/). The front-end and UI was designed using XML. The app lifecycle and activities were coded in Java. On the backend, we used [Firebase](https://firebase.google.com/) to host our database and the Firebase API for the interactions between our app and the database.

### Data Structure
Our data is structured in the form of a JSON tree:

```
{
  "found" : {
    "clothing" : {
      "West Union" : {
        "-LT_1NdPk5WmeZURh9-P" : {
          "contact" : "me @gmail.com",
          "description" : "left at the edge",
          "imageName" : "JPEG_12-12-2018-07-48-26_",
          "location" : "West Union",
          "name" : "scarf ",
          "type" : "clothing"
        }
      }
    },
    "miscellaneous" : {
      "Marketplace" : {
        "-LTZx17wzY6wY7xq8Xlh" : {
          "contact" : "anne.hathaway@gmail.com",
          "description" : "Stuffed fox plushie. ",
          "imageName" : "JPEG_12-12-2018-07-24-42_",
          "location" : "Marketplace",
          "name" : "Stuffed animal",
          "type" : "miscellaneous"
        }
      }
    }
  }
}
```

Whenever a user reports a found item, this item will be stored as a JSON object with the attributes:
- ```name``` (name of item)
- ```imageName``` (to be used to query Firebase Storage to retrieve image)
- ```type``` (type of item)
- ```description``` (description of item) 
- ```location``` (location where item was found)
- ```contact``` (so that a user who has lost an item can contact the person who found it) 

Whenever a user reports a lost item, instead of storing lost item reports on the database, the information from the lost report will be used to query the data table of found items to determine if the item has been found, and the user will be presented with a list of found items that match the query that they entered. 

Our JSON tree has **4 layers**:
1. Found items
2. Type of item
3. Location where item was found
4. Item object

We chose to structure it this way as whenever we query the data table of found items, we first query by ```type```, then by ```location```. 

## Code Structure

All code Java class definitions, e.g. ```FoundReport.java``` and app flow code is managed in the ```<ActivityName>Activity.java``` classes found in  ```app/src/main/java/edu/duke/compsci316/lostitfoundit```.

All UI code is found in ```app/src/main/res/layout```, where each Activity corresponds to a like-named XML file in ```layout```.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

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
