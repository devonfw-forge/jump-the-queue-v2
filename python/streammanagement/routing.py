from django.conf.urls import url
from channels.routing import URLRouter
from channels.http import AsgiHandler
from channels.auth import AuthMiddlewareStack
from streammanagement.enums import StreamChannels
import django_eventstream


urlpatterns = [
    url(r'^stream/subscribe', AuthMiddlewareStack(
        URLRouter(django_eventstream.routing.urlpatterns)
    ), {'channels': [StreamChannels.JTQ_CHANNEL.value]}),
    url(r'', AsgiHandler),
]
