# jump-the-queue-v2

New jump the queue version

New Features:

* Completely new frontend design
* Better user experience, visitors do not need to give credentials
* Real time experience by using Server Sent Events technology
* Updated to angular 8
* New backend choice technology: Python

## Important endpoints
- Owner
    - localhost:4200/owner

- Visitor
    - localhost:4200/my-code

App uses localstorage to keep track of visitor codes in order to simulate several visitors open normal browser window and incognito browser window

## Technologies
- Java
    - devon4j 3.0
- Angular
    - Angular 8
- Python
    - Django 2.2.4

### Python setup
- Requirements
    - Python 3.7
    - Pip 19.2.1

```sh
pip install -r requirements.txt
python manage.py migrate
python manage.py loaddata initial_owner_data
python manage.py runserver 0.0.0.0:8081
```


### Note
Internet explorer and microsoft edge does not support Server Send Events therefore app will not work under such browsers.




