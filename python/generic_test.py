import dataclasses
from abc import abstractmethod
from typing import TypeVar, Generic

Request = TypeVar('Request')
Response = TypeVar('Response')

class UseCase(Generic[Request, Response]):
    @abstractmethod
    def execute(self, request: Request) -> Response:
        raise NotImplementedError

@dataclasses.dataclass
class MyRequest(Generic[Request]):
    name: str
    age: int

class MyResponse(Generic[Response]):
    pass

class ConcreteUseCase(UseCase[MyRequest, MyResponse]):
    def execute(self, request: MyRequest) -> MyResponse:
        print(request)
        return MyResponse()

if __name__ == "__main__":
    request: MyRequest = MyRequest(name="Hank", age=34)
    ConcreteUseCase().execute(request)
