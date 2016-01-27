import json
import uuid
from flask import Flask, Response, request

app = Flask(__name__, static_url_path="", static_folder="public")


@app.route("/")
def index():
    return app.send_static_file("index.html")


@app.route("/api/comments", methods=["GET", "POST"])
def comments():
    with open("comments.json", "r") as f:
        comments = json.loads(f.read())

    if request.method == "POST":
        new_comment = request.form.to_dict()
        new_comment["id"] = str(uuid.uuid4())
        comments.append(new_comment)

    with open("comments.json", "w") as f:
        f.write(json.dumps(comments, indent=4, separators=(",", ": ")))

    return Response(json.dumps(comments), status=200, mimetype="application/json")

if __name__ == "__main__":
    app.run(debug=True)
