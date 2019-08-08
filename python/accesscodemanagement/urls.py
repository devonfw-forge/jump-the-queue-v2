from django.urls import path
from accesscodemanagement import views

urlpatterns = [
        path('uuid', views.accesscode_by_uuid),
        path('current', views.accesscode_current),
        path('remaining', views.accesscode_remaining),
        path('next', views.accesscode_nextCode),
        path('estimated', views.accesscode_estimatedTime),
        path('accesscodes/', views.accesscode_list),
]
