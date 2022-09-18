from django.shortcuts import render
from django.http import HttpResponse
import random

# Create your views here.
def random_name(request):
    FIRST_NAMES = ['dirty', 'big', 'stupid']
    LAST_NAMES = ['mouth breather', 'man', 'chungus', 'wumpus']

    first_name = FIRST_NAMES[random.randint(0, len(FIRST_NAMES)-1)]
    last_name = LAST_NAMES[random.randint(0, len(LAST_NAMES)-1)]
    full_name = f"{first_name} {last_name}"

    # return HttpResponse(full_name)
    return render(request, 'index.html')

def say_hello(request):
    return render(request, 'index.html')