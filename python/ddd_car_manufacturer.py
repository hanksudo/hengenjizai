from abc import ABCMeta
from dataclasses import dataclass
from enum import Enum
from typing import List

# DDD
@dataclass(frozen=True)
class ValueObject:
    pass

class Entity:
    pass


# Car models

class CarColor(Enum):
    RED = "red"

class CarType(Enum):
    PRIUS = "prius"

@dataclass(frozen=True)
class Car(ValueObject):
    brand: str
    color: str
    name: str

    colors = CarColor
    types = CarType

class CarFactory(metaclass=ABCMeta):
    def create(self):
        pass

class CarManufacturer(Entity):
    _cars: List[Car] = []

    def __init__(self, car_factory: CarFactory):
        self.car_factory = car_factory

    def construct_cars(self, num_of_cars: int):
        for _ in range(num_of_cars):
            self._cars.append(self.car_factory.create())

    @property
    def cars(self):
        return self._cars

class PriusCarFactory(CarFactory):
    brand = Car.types.PRIUS
    color = Car.colors.RED
    name = "My favorite car"

    def create(self):
        return Car(self.brand, self.color, self.name)

if __name__ == "__main__":
    manufacturer = CarManufacturer(PriusCarFactory())
    manufacturer.construct_cars(num_of_cars=40)
    print(manufacturer.cars)
