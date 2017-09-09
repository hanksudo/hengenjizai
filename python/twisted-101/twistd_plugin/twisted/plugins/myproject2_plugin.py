# Demonstrate use tap to create plugin.
from twisted.application.service import ServiceMaker

MyProject2 = ServiceMaker(
    "My second service",
    "myproject.tap",
    "A simple service.",
    "myproject2")