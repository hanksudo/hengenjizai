import json
from pygments import highlight
from pygments.formatters import Terminal256Formatter
from pygments.lexers.web import JsonLexer


def highlight_json(json_str_or_obj):
    """http://pygments.org/docs/"""
    if isinstance(json_str_or_obj, dict):
        json_str_or_obj = json.dumps(json_str_or_obj, indent=4)
        json_str_or_obj = json_str_or_obj.decode(
            "unicode_escape").encode("utf8")
        return highlight(json_str_or_obj, JsonLexer(), Terminal256Formatter())
