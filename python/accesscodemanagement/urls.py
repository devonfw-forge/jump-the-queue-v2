from django.urls import path
from accesscodemanagement import views

urlpatterns = [
        path('uuid', views.accesscode_by_uuid),
        path('current', views.accesscode_current),
        path('accesscodes/', views.accesscode_list),
]
