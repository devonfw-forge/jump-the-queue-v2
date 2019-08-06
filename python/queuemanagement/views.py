from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from rest_framework.decorators import api_view
from queuemanagement.models import Queue
from queuemanagement.serializers import QueueSerializer
import datetime

@api_view(['GET'])
def daily_queue(request):
    """
    Returns daily queue if exists else create it and return
    """
    if request.method == 'GET':
        now = datetime.datetime.now()
        try:
            todaysQueue = Queue.objects.get(
                    createdDate__year=now.year,
                    createdDate__month=now.month,
                    createdDate__day=now.day
                    )
            serializer = QueueSerializer(todaysQueue)
            return JsonResponse(serializer.data, status=200)
        except Queue.DoesNotExist:
            newQueue = Queue()
            newQueue.save()
            newSerializer = QueueSerializer(newQueue)
            return JsonResponse(newSerializer.data, status=201)
        except Queue.MultipleObjectsReturned:
            return HttpResponse(status=500)

@api_view(['POST'])
def queue_start(request):
    """
    Given a queue will change it to started
    """
    if request.method == 'POST':
        queue = JSONParser().parse(request)
        try:
            queue = Queue.objects.get(pk=queue['id'])
            queue.started = True
            queue.modificationCounter += 1
            queue.save()
            newSerializer = QueueSerializer(queue)
            return JsonResponse(newSerializer.data, status=200)
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
