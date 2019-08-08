from django.db import models

class Owner(models.Model):
    modificationCounter = models.IntegerField(default=0)
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=100)
    userType = models.BooleanField(default=True)

