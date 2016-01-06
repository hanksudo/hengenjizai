import json
import collections


def trail_end_space(string):
    return ("\n").join(map(lambda x: x.rstrip(), out.split("\n")))


with open("data.json", "r+") as f:
    # print f.read()
    # f.write(f.read())

    words = json.JSONDecoder(
        object_pairs_hook=collections.OrderedDict
    ).decode(f.read())

    # print words

    out = json.JSONEncoder(indent=4).encode(words)
    out = trail_end_space(out)
    out = out.decode("unicode_escape").encode("utf8")
    print out
    # print f.write(out.decode("unicode_escape").encode("utf8"))
    # # f.write(json.dumps(
    # #     out.decode('unicode_escape').encode("utf8"),
    # #     indent=4,
    # #     # ensure_ascii=False,
    # #     separators=(",", ": ")
    # # ))
