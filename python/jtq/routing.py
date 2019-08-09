from channels.routing import ProtocolTypeRouter, URLRouter
from streammanagement.routing import urlpatterns

application = ProtocolTypeRouter({
    'http': URLRouter(urlpatterns),
})
