import json
import graphene

# Schema
class Query(graphene.ObjectType):
    hello = graphene.String(name=graphene.String(default_value="stranger"))

    def resolve_hello(self, info, name):
        return "Hello " + name

schema = graphene.Schema(query=Query)

def handler(result):
    if result.invalid:
        print result.errors
    else:
        print json.dumps(result.data)

# Querying
result1 = schema.execute('{ hello }')
result2 = schema.execute('{ hello2 }')

handler(result1)
handler(result2)