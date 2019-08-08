from django.http import HttpResponse, JsonResponse
from rest_framework.decorators import api_view
from rest_framework.parsers import JSONParser
from ownermanagement.models import Owner

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
            return JsonResponse(ownerSerializer.data, code=200)
        except owner.DoesNotExist:
            return HttpResponse(403)
