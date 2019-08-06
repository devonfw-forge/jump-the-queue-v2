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


@csrf_exempt
def queue_list(request):
    """
    List all code snippets, or create a new snippet.
    """
    if request.method == 'GET':
        queues = Queue.objects.all()
        serializer = QueueSerializer(queues, many=True)
        return JsonResponse(serializer.data, safe=False)

    elif request.method == 'POST':
        data = JSONParser().parse(request)
        serializer = QueueSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status=201)
        return JsonResponse(serializer.errors, status=400)

@csrf_exempt
def queue_detail(request, pk):
    """
    Retrieve, update or delete a code snippet.
    """
    try:
        queue = Queue.objects.get(pk=pk)
    except Queue.DoesNotExist:
        return HttpResponse(status=404)

    if request.method == 'GET':
        serializer = QueueSerializer(snippet)
        return JsonResponse(serializer.data)

    elif request.method == 'PUT':
        data = JSONParser().parse(request)
        queue = QueueSerializer(snippet, data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data)
        return JsonResponse(serializer.errors, status=400)

    elif request.method == 'DELETE':
        queue.delete()
        return HttpResponse(status=204)

