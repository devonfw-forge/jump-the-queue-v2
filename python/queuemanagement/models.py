from django.db import models

class Queue(models.Model):
    modificationCounter = models.IntegerField(default=0)
    minAttentionTime = models.IntegerField(default=120000)
    started = models.BooleanField(default=False)
    createdDate = models.DateTimeField(auto_now_add=True)
