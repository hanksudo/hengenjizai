# prefer
def _addErr1(errors, field, message):
    errors.setdefault(field, []).append(message)

# not prefer
def _addErr2(errors, field, message):
    if not field in errors:
        errors[field] = []
    errors[field].append(message)


errors1 = {}
errors2 = {}

_addErr1(errors1, 'test', 'msg1')
_addErr2(errors2, 'test', 'msg1')

print errors1
print errors2
