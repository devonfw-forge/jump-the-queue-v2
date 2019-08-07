from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from rest_framework.decorators import api_view
from accesscodemanagement.models import AccessCode, AccessCodeStatus
from accesscodemanagement.serializers import AccessCodeSerializer
from queuemanagement import views
from queuemanagement.models import Queue
import datetime

@api_view(['POST'])
def accesscode_by_uuid(request):
    """
    Returns code by given uuid if exists else creates it
    """
    if request.method == 'POST':
        todayQueueSerializer = views.get_or_create_today_queue_serializer()
        try:
            visitorCode = AccessCode.objects.get(uuid=request.data['uuid'], queueId=todayQueueSerializer.data['id'])
            accessCodeSerializer = AccessCodeSerializer(visitorCode)
            return JsonResponse(accessCodeSerializer.data, status=200)
        except AccessCode.DoesNotExist:
            visitorCodeStatus = AccessCodeStatus.WAITING.value if todayQueueSerializer.data['started'] else AccessCodeStatus.NOTSTARTED.value
            newVisitorCode = AccessCode(
                    code='K888',
                    status=visitorCodeStatus,
                    uuid=request.data['uuid'],
                    queueId=todayQueueSerializer.instance
                    )
            newVisitorCode.save()
            newVisitorCodeSerializer = AccessCodeSerializer(newVisitorCode)
            return JsonResponse(newVisitorCodeSerializer.data, status=200)

@csrf_exempt
def accesscode_list(request):
    """
    List all codes
    """
    if request.method == 'GET':
        codes = AccessCode.objects.all()
        serializer = AccessCodeSerializer(codes, many=True)
        return JsonResponse(serializer.data, safe=False)

