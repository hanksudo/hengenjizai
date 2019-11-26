import dataclasses
from abc import abstractmethod
from typing import TypeVar, Generic

class BaseRequest:
    pass

class BaseResponse:
    pass

Request = TypeVar("Request", bound=BaseRequest)
Response = TypeVar("Response", bound=BaseResponse)

class UseCase(Generic[Request, Response]):
    @abstractmethod
    def execute(self, request: Request) -> Response:
        raise NotImplementedError

@dataclasses.dataclass
class MyRequest(BaseRequest):
    name: str
    age: int

class MyResponse(BaseResponse):
    pass

class ConcreteUseCase(UseCase[MyRequest, MyResponse]):
    def execute(self, request: MyRequest) -> MyResponse:
        print(request)
        return MyResponse()

if __name__ == "__main__":
    request: MyRequest = MyRequest(name="Hank", age=34)
    ConcreteUseCase().execute(request)
