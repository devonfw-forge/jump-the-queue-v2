from django.http import JsonResponse
from django.db.models import Q
from django.utils import timezone
from rest_framework.parsers import JSONParser
from rest_framework.decorators import api_view
from accesscodemanagement.models import AccessCode, AccessCodeStatus
from accesscodemanagement.serializers import AccessCodeSerializer
from queuemanagement import views
from queuemanagement.models import Queue

@api_view(['POST'])
def accesscode_by_uuid(request):
    """
    Returns code by given uuid if exists else creates it
    """
    if request.method == 'POST':
        todayQueueSerializer = views.get_or_create_today_queue_serializer()
        uuid = JSONParser().parse(request)
        response = {
                "accessCode" : {},
                "queue": todayQueueSerializer.data
        }
        try:
            visitorCode = AccessCode.objects.get(uuid=uuid['uuid'], queueId=todayQueueSerializer.data['id'])
            accessCodeSerializer = AccessCodeSerializer(visitorCode)
            response['accessCode'] = accessCodeSerializer.data
            return JsonResponse(response, status=200)
        except AccessCode.DoesNotExist:
            visitorCodeStatus = AccessCodeStatus.WAITING.value if todayQueueSerializer.data['started'] else AccessCodeStatus.NOTSTARTED.value
            # Get last code
            lastCode = 'Q001'
            lastAccessCode = AccessCode.objects.filter(queueId=todayQueueSerializer.data['id']).last()
            if lastAccessCode:
                # Parse code & fix leading zeros
                lastCode = lastAccessCode.code[0] + '{0:0>3}'.format(int(lastAccessCode.code[1:], 10) + 1)
                # If Q1000 reset to Q001
                lastCode = 'Q001' if len(lastCode) == 5 else lastCode
            newVisitorCode = AccessCode(
                    code=lastCode,
                    status=visitorCodeStatus,
                    uuid=uuid['uuid'],
                    queueId=todayQueueSerializer.instance
                    )
            newVisitorCode.save()
            newVisitorCodeSerializer = AccessCodeSerializer(newVisitorCode)
            response['accessCode'] = newVisitorCodeSerializer.data
            return JsonResponse(response, status=200)

@api_view(['POST'])
def accesscode_current(request):
    """
    Returns current code being attended
    """
    if request.method == 'POST':
        todaysQueue = views.get_or_create_today_queue_serializer()
        currentCode = AccessCode.objects.filter(queueId=todaysQueue.data['id'], status=AccessCodeStatus.ATTENDING.value).first()
        currentCodeSerializer = AccessCodeSerializer(currentCode)
        return JsonResponse(currentCodeSerializer.data, status=200)

@api_view(['POST'])
def accesscode_remaining(request):
    """
    Returns amount of codes are left to be attended
    """
    if request.method == 'POST':
        todaysQueue = views.get_or_create_today_queue_serializer()
        amount = AccessCode.objects.filter(queueId=todaysQueue.data['id'], status=AccessCodeStatus.WAITING.value).count()
        remainingCodes = {
                'remainingCodes': amount
        }
        return JsonResponse(remainingCodes, status=200)

@api_view(['POST'])
def accesscode_nextCode(request):
    """
    Returns a dictionary with remaining code and next accesscode"
    """
    if request.method == 'POST':
        todaysQueue = views.get_or_create_today_queue_serializer()
        queueId = todaysQueue.data['id']
        # Change currentCode if exists
        currentCode = AccessCode.objects.filter(queueId=queueId, status=AccessCodeStatus.ATTENDING.value).first()
        if currentCode:
                currentCode.modificationCounter += 1
                currentCode.status = AccessCodeStatus.ATTENDED.value
                currentCode.endTime = timezone.now()
                currentCode.save()
        # Check if we have a nextCode & change
        nextCode = AccessCode.objects.filter(queueId=queueId, status=AccessCodeStatus.WAITING.value).first()
        if nextCode:
                nextCode.status = AccessCodeStatus.ATTENDING.value
                nextCode.startTime = timezone.now()
                nextCode.modificationCounter += 1
                nextCode.save()
                # Get remaining codes
                amount = AccessCode.objects.filter(queueId=queueId, status=AccessCodeStatus.WAITING.value).count()
                remainingCodes = {
                        'remainingCodes': amount
                }
                nextCodeSerializer = AccessCodeSerializer(nextCode)
                nextCodeCto = {
                        'remainingCodes': remainingCodes,
                        'accessCode': nextCodeSerializer.data
                }
                return JsonResponse(nextCodeCto, status=200)
        else:
                # There is not next code, therefore no remainingCodes
                nextCodeNullCto = {
                        'remainingCodes': {
                        'remainingCodes': 0
                        },
                        'accessCode': AccessCodeSerializer().data
                }
                return JsonResponse(nextCodeNullCto, status=200)

@api_view(['POST'])
def accesscode_estimatedTime(request):
    """
    Returns a dictionary with estimatedTime for a given accessCode to be attended
    """
    if request.method == 'POST':
        MILISECONDS_PER_USER = (120000,)
        givenCode = JSONParser().parse(request)
        codesAhead = AccessCode.objects.filter(Q(status=AccessCodeStatus.WAITING.value) | Q(status=AccessCodeStatus.ATTENDING.value), queueId=givenCode['queueId'], createdDate__lt=givenCode['createdDate']).count()
        estimatedTime = {
                'miliseconds': codesAhead * MILISECONDS_PER_USER[0],
                'defaultTimeByUserInMs': MILISECONDS_PER_USER[0]
        }
        return JsonResponse(estimatedTime, status=200)

@api_view(['GET'])
def accesscode_list(request):
    """
    List all codes
    """
    if request.method == 'GET':
        codes = AccessCode.objects.all()
        serializer = AccessCodeSerializer(codes, many=True)
        return JsonResponse(serializer.data, safe=False)

