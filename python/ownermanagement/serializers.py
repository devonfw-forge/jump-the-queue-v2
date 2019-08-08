from ownermanagement.models import Owner
from rest_framework import serializers

class OwnerSerializer(serializers.ModelSerializer):
    class Meta:
        model = Owner
        fieldss = ['id', 'modificationCounter', 'username', 'password', 'userType']

