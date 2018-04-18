# 02 - Visualing a Decision Tree - https://youtu.be/tNa99PG8hR8
# scikit-learn datasets: http://scikit-learn.org/stable/datasets/
# https://en.wikipedia.org/wiki/Iris_flower_data_set

import numpy as np
from sklearn.datasets import load_iris
from sklearn import tree
from sklearn.externals.six import StringIO
import pydot

# 1. Import dataset
iris = load_iris()
# print(iris.feature_names)
# print(iris.target_names)
# print(iris.data[0])
# print(iris.target[0])
# for i in range(len(iris.target)):
#     print("Example {}: label {}, features {}".format(i, iris.target[i], iris.data[i]))

test_idx = [0, 50, 100]

# 2. Train a classifier
train_target = np.delete(iris.target, test_idx)
train_data = np.delete(iris.data, test_idx, axis=0)

# testing data
# - Example userd to "test" the classifier's accuracy.
# - Not part of the training data.
test_target = iris.target[test_idx]
test_data = iris.data[test_idx]

# 3. Predict label for new flower
clf = tree.DecisionTreeClassifier()
clf.fit(train_data, train_target)

print(test_target)
print(clf.predict(test_data))

# 4. Visualize the tree
dot_data = StringIO()
tree.export_graphviz(
    clf,
    out_file=dot_data,
    feature_names=iris.feature_names,
    class_names=iris.target_names,
    filled=True, rounded=True,
    impurity=False
)
graph = pydot.graph_from_dot_data(dot_data.getvalue())
graph[0].write_pdf("iris.pdf")

# print(test_data[0], test_target[0])
# print(iris.feature_names, iris.target_names)
