from queuemanagement.models import Queue
from rest_framework import serializers

class QueueSerializer(serializers.ModelSerializer):
    class Meta:
        model = Queue
        fields = ['id', 'modificationCounter', 'minAttentionTime', 'started', 'createdDate']
