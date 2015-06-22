a = [{
    "name": "aaa",
    "count": 134
}, {
    "name": "bbb",
    "count": 521
}, {
    "name": "ccc",
    "count": 394
}]

print sorted(a, key=lambda k: k['count'], reverse=True)
