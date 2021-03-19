# jump-the-queue-v2

> :warning: **Archived! This work is discontinued. Please find the latest version back in the original repository https://github.com/devonfw/jump-the-queue**


New jump the queue version.

New Features:

* Completely new frontend design
* Better user experience (visitors don't need to enter credentials)
* Real time experience by using Server Sent Events
* Updated to Angular 8
* New backend technology choice: Python

## Important endpoints

* Owner
  * http://localhost:4200/owner

* Visitor
  * http://localhost:4200/my-code

* CXF Endpoint
  * http://localhost:8081

The app uses localstorage to keep track of visitor codes. In order to simulate several visitors open a normal browser window and an incognito browser window!

## Technologies

* Java
  * devon4j 3.2.0
* Angular
  * Angular 8
* Python
  * Django 2.2.4

### Python setup

Python is not currently included in the devonfw distribution, so the following software has to be installed manually:

* Python 3.7
* Pip 19.2.1

```sh
cd python
pip install -r requirements.txt
python manage.py migrate
python manage.py loaddata initial_owner_data
python manage.py runserver 0.0.0.0:8081
```

### Java setup

Java and all required build tools (Maven) are a part of the devonfw distribution, so no software has to be installed manually. Just execute the following commands inside the devonfw distribution workspace:

```sh
cd java/jtqj
devon build
cd server/target
java -jar jtqj-server-bootified.war
```

### Angular setup

Angular and all required build tools (Node.js/npm) are a part of the devonfw distribution, so no software has to be installed manually. Just execute the following commands inside the devonfw distribution workspace:

```sh
cd angular
devon npm install
devon ng serve -o
```

### Note

Internet Explorer and Microsoft Edge do not support Server Send Events. Therefore app will not work under such browsers.
