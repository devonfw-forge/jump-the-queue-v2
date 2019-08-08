from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from django.db.models import F
from django.utils import timezone
from rest_framework.parsers import JSONParser
from rest_framework.decorators import api_view
from queuemanagement.models import Queue
from queuemanagement.serializers import QueueSerializer
from accesscodemanagement.models import AccessCode, AccessCodeStatus



def get_or_create_today_queue_serializer():
    """
    Returns daily queue serializer if exists else create it and return
    """
    now = timezone.now()
    try:
        todaysQueue = Queue.objects.get(
            createdDate__year=now.year,
            createdDate__month=now.month,
            createdDate__day=now.day
            )
        serializer = QueueSerializer(todaysQueue)
        return serializer
    except Queue.DoesNotExist:
        newQueue = Queue()
        newQueue.save()
        newSerializer = QueueSerializer(newQueue)
        return newSerializer

@api_view(['GET'])
def daily_queue(request):
    serializer = get_or_create_today_queue_serializer()
    return JsonResponse(serializer.data, status=200)

@api_view(['POST'])
def queue_start(request):
    """
    Given a queue will change it to started
    """
    if request.method == 'POST':
        queue = JSONParser().parse(request)
        try:
            queue = Queue.objects.get(pk=queue['id'])
            if not queue.started:
                queue.started = True
                queue.modificationCounter += 1
                queue.save()
                # Update all codes associated with such queue
                AccessCode.objects.filter(queueId=queue.id).update(status=AccessCodeStatus.WAITING.value, modificationCounter=F('modificationCounter')+1)
                newSerializer = QueueSerializer(queue)
                return JsonResponse(newSerializer.data, status=200)
            else:
                return HttpResponse(status=200)
        except Queue.DoesNotExist:
            return HttpResponse(status=404)

@csrf_exempt
def queue_list(request):
    """
    List all queues
    """
    if request.method == 'GET':
        queues = Queue.objects.all()
        serializer = QueueSerializer(queues, many=True)
        return JsonResponse(serializer.data, safe=False)








