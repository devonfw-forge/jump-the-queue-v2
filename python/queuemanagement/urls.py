from django.urls import path
from queuemanagement import views

urlpatterns = [
        path('daily/', views.daily_queue),
        path('queues/', views.queue_list),
        path('queue/<int:pk>', views.queue_detail),
]

