import json
from flask import Flask, Response

app = Flask(__name__, static_url_path="", static_folder="public")


@app.route("/")
def index():
    return app.send_static_file("index.html")


@app.route("/api/comments")
def comments():
    with open("comments.json", "r") as f:
        comments = json.loads(f.read())

    return Response(json.dumps(comments), status=200, mimetype="application/json")

if __name__ == "__main__":
    app.run(debug=True)
