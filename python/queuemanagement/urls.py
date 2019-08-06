from django.urls import path
from queuemanagement import views

urlpatterns = [
        path('daily/', views.daily_queue),
        path('start', views.queue_start),
        path('queues/', views.queue_list),
]

