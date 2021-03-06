# Generated by Django 2.2.4 on 2019-08-08 10:45

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Queue',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('modificationCounter', models.IntegerField(default=0)),
                ('minAttentionTime', models.IntegerField(default=120000)),
                ('started', models.BooleanField(default=False)),
                ('createdDate', models.DateTimeField(auto_now_add=True)),
            ],
        ),
    ]
