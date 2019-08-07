from accesscodemanagement.models import AccessCode
from rest_framework import serializers

class AccessCodeSerializer(serializers.ModelSerializer):
    class Meta:
        model = AccessCode
        fields = ['id', 'modificationCounter', 'code', 'uuid', 'createdDate', 'startTime', 'endTime', 'status', 'queueId']

