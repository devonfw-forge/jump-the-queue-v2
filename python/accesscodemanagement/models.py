from django.db import models
from enum import Enum
from queuemanagement.models import Queue

class AccessCodeStatus(Enum):
    WAITING = 'WAITING'
    ATTENDING = 'ATTENDING'
    ATTENDED = 'ATTENDED'
    SKIPPED = 'SKIPPED'
    NOTSTARTED = 'NOTSTARTED'

class AccessCode(models.Model):
        modificationCounter = models.IntegerField(default=0)
        code = models.CharField(max_length=4)
        uuid = models.CharField(max_length=36)
        createdDate = models.DateTimeField(auto_now_add=True)
        startTime = models.DateTimeField(null=True, blank=True)
        endTime = models.DateTimeField(null=True, blank=True)
        status = models.CharField(
                max_length=10,
                choices=[(key, key.value) for key in AccessCodeStatus]
        )
        queueId = models.ForeignKey(Queue, on_delete=models.CASCADE)

        class Meta:
            ordering = ('createdDate',)
