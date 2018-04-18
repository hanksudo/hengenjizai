# 01 - Hello World - https://youtu.be/cKxRvEZd3Mw
from sklearn import tree

# Training data
# features = [[140, "smooth"], [130, "smooth"], [150, "bumpy"], [170, "bumpy"]]  # Input to classifier
features = [[140, 1], [130, 1], [150, 0], [170, 0]]

# labels = ["apple", "apple", "orange", "orange"]  # Desired output
labels = [0, 0, 1, 1]

# Train Classifier
clf = tree.DecisionTreeClassifier()  # Decision Tree classifier
clf = clf.fit(features, labels)      # Find patterns in data

# Make Predictions
print(clf.predict([[150, 0]]))

# Output: 0-apple, 1-orange
# Correct output is: 1-orange