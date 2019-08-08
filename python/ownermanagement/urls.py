from django.urls import path
from ownermanagement import views

urlpatterns = [
        path('search', views.owner_login),
]
