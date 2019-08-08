from ownermanagement.models import Owner
from rest_framework import serializers

class OwnerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Owner
        fields = ['id', 'modificationCounter', 'username', 'password', 'userType']

