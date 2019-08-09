from django.http import HttpResponse, JsonResponse
from rest_framework.decorators import api_view
from rest_framework.parsers import JSONParser
from ownermanagement.models import Owner
from ownermanagement.serializers import OwnerSerializer

@api_view(['POST'])
def owner_login(request):
    """
    Returns user that match request.data
    """
    if request.method == 'POST':
        criteria = JSONParser().parse(request)
        try:
            owner = Owner.objects.get(username=criteria['username'], password=criteria['password'])
            ownerSerializer = OwnerSerializer(owner)
            response = {
                    "content" : []
            }
            response['content'].append(ownerSerializer.data)
            return JsonResponse(response, status=200)
        except Owner.DoesNotExist:
            return HttpResponse(status=401)
