# 08 - Let's Write a Decision Tree Classifier from Scratch - https://youtu.be/LDRbO9a6XPU

from __future__ import print_function

# The last column is the label.
# The first two colimns are features.
training_data = [
    ["Green", 3, "Apple"],
    ["Yellow", 3, "Apple"],
    ["Red", 1, "Grape"],
    ["Red", 1, "Grape"],
    ["Yellow", 3, "Lemon"]
]

# Column labels
header = ["color", "diameter", "label"]

def unique_vals(rows, col):
    """Find the unique values for a column in a dataset.

    unique_vals(training_data, 0)
    unique_vals(training_data, 1)
    """

    return set([row[col] for row in rows])


def class_counts(rows):
    """Counts the number of each type of example in a dataset."""
    counts = {}  # a dictionary of label -> count.
    for row in rows:
        # in our dataset format, the label is always the last column
        label = row[-1]
        if label not in counts:
            counts[label] = 0
        counts[label] += 1
    return counts

def partition(rows, question):
    """Partitions a dataset.

    For each row in the dataset, check if it matches the question.
    If so, add it to 'true rows', otherwise, add it to 'false rows'.
    """
    true_rows, false_rows = [], []
    for row in rows:
        if question.match(row):
            true_rows.append(row)
        else:
            false_rows.append(row)
    return true_rows, false_rows

def gini(rows):
    """Calculate the Gini Impurity for a list of rows.
    There are a few different ways to do this, I thought this one was
    the most concise. See:
    https://en.wikipedia.org/wiki/Decision_tree_learning#Gini_impurity
    """
    counts = class_counts(rows)
    impurity = 1
    for lbl in counts:
        prob_of_lbl = counts[lbl] / float(len(rows))
        impurity -= prob_of_lbl**2
    return impurity

#######
# Demo:
# Let's look at some example to understand how Gini Impurity works.
#
# First, we'll look at a dataset with no mixing.
# no_mixing = [['Apple'],
#              ['Apple']]
# this will return 0
# gini(no_mixing)
#
# Now, we'll look at dataset with a 50:50 apples:oranges ratio
# some_mixing = [['Apple'],
#               ['Orange']]
# this will return 0.5 - meaning, there's a 50% chance of misclassifying
# a random example we draw from the dataset.
# gini(some_mixing)
#
# Now, we'll look at a dataset with many different labels
# lots_of_mixing = [['Apple'],
#                  ['Orange'],
#                  ['Grape'],
#                  ['Grapefruit'],
#                  ['Blueberry']]
# This will return 0.8
# gini(lots_of_mixing)
#######

def info_gain(left, right, current_uncertainty):
    """Information Gain.
    The uncertainty of the starting node, minus the weighted impurity of
    two child nodes.
    """
    p = float(len(left)) / (len(left) + len(right))
    return current_uncertainty - p * gini(left) - (1 - p) * gini(right)

#######
# Demo:
# Calculate the uncertainy of our training data.
# current_uncertainty = gini(training_data)
#
# How much information do we gain by partioning on 'Green'?
# true_rows, false_rows = partition(training_data, Question(0, 'Green'))
# info_gain(true_rows, false_rows, current_uncertainty)
#
# What about if we partioned on 'Red' instead?
# true_rows, false_rows = partition(training_data, Question(0,'Red'))
# info_gain(true_rows, false_rows, current_uncertainty)
#
# It looks like we learned more using 'Red' (0.37), than 'Green' (0.14).
# Why? Look at the different splits that result, and see which one
# looks more 'unmixed' to you.
# true_rows, false_rows = partition(training_data, Question(0,'Red'))
#
# Here, the true_rows contain only 'Grapes'.
# true_rows
#
# And the false rows contain two types of fruit. Not too bad.
# false_rows
#
# On the other hand, partitioning by Green doesn't help so much.
# true_rows, false_rows = partition(training_data, Question(0,'Green'))
#
# We've isolated one apple in the true rows.
# true_rows
#
# But, the false-rows are badly mixed up.
# false_rows
#######

def find_best_split(rows):
    """Find the best question to ask by iterating over every feature / value
    and calculating the information gain."""
    best_gain = 0  # keep track of the best information gain
    best_question = None  # keep train of the feature / value that produced it
    current_uncertainty = gini(rows)
    n_features = len(rows[0]) - 1  # number of columns

    for col in range(n_features):  # for each features
        values = set([row[col] for row in rows])  # unique values in the column

        for val in values:  # for each values
            question = Question(col, val)

            # try splitting the dataset
            true_rows, false_rows = partition(rows, question)

            # Skip this split if it doesn't divide the dataset.
            if len(true_rows) == 0 or len(false_rows) == 0:
                continue

            # Calculate the information gain from this split
            gain = info_gain(true_rows, false_rows, current_uncertainty)

            # You actually can use '>' instead of '>=' here
            # but I wanted the tree to look a certain way for our
            # toy dataset.
            if gain >= best_gain:
                best_gain, best_question = gain, question

        return best_gain, best_question

def is_numeric(value):
    """Test if a value is numeric.

    is_numeric(7)
    is_numeric("Red")
    """

    return isinstance(value, int) or isinstance(value, float)

class Question:
    """A Question is used to partition a dataset.

    This class just records a 'column number' (e.g., 0 for Color) and a
    'column value' (e.g., Green). The 'match' method is used to compare
    the feature value in an example to the feature value stored in the
    question. See the demo below.
    """
    def __init__(self, column, value):
        self.column = column
        self.value = value

    def match(self, example):
        # Compare the feature value in an example to the
        # feature value in this question
        val = example[self.column]
        if is_numeric(val):
            return val >= self.value
        else:
            return val == self.value

    def __repr__(self):
        # This is just a helper method to print
        # the question in a readable format.
        condition = "=="
        if is_numeric(self.value):
            condition = ">="
        return "Is {} {} {}?".format(
            header[self.column], condition, str(self.value))

# print(Question(1, 3))     # Is diameter >= 3?
# q = Question(0, 'Green')
# print(q)                  # Is color == Green?
# example = training_data[0]
# print(example)            # ['Green', 3, 'Apple']
# print(q.match(example))   # True

class Leaf:
    """A Leaf node classifies data.

    This holds a diction ary of class (e.g., "Apple") -> number of times
    it appers in the rows from the training data that reach this leaf.
    """

    def __init__(self, rows):
        self.predictions = class_counts(rows)

class DecisionNode:
    """A Decision Node asks a question.

    This holds a reference to the question, and to the two child nodes.
    """

    def __init__(self,
                 question,
                 true_branch,
                 false_branch):
        self.question = question
        self.true_branch = true_branch
        self.false_branch = false_branch


def build_tree(rows):
    """Build the tree.

    Rules of recursion:
        1) Believe that it works
        2) Start by checking for the base case (no further information gain).
        3) Prepare for gaint stack traces.
    """

    # Tr partitioing the dataset on each of the unique attribute,
    # calculate the information gain,
    # and return the quesetion that produces the highest gain.
    gain, question = find_best_split(rows)

    # Since we can ask no further questions,
    # we'll return a laef.
    if gain == 0:
        return Leaf(rows)

    # If we reach here, we have found a useful feature / value to partition on.
    true_rows, false_rows = partition(rows, question)

    # Recursively build the true branch.
    true_branch = build_tree(true_rows)

    # Recursively build the false branch.
    false_branch = build_tree(false_rows)

    # Return a Question node.
    return DecisionNode(question, true_branch, false_branch)


def print_tree(node, spacing=""):
    """World's most elegant tree printing function."""

    # Reached a leaf
    if isinstance(node, Leaf):
        print(spacing + "Predict", node.predictions)
        return

    # Print the question at this node
    print(spacing + str(node.question))

    # Call this function recursively on the true branch
    print(spacing + '--> True:')
    print_tree(node.true_branch, spacing + " ")

    # Call this function recursively on the false branch
    print(spacing + '--> False:')
    print_tree(node.false_branch, spacing + " ")

def print_leaf(counts):
    """A nicer way to print the predictions at a leaf."""
    total = sum(counts.values()) * 1.0
    probs = {}
    for lbl in counts.keys():
        probs[lbl] = str(int(counts[lbl] / total * 100)) + "%"
    return probs

def classify(row, node):
    """See the 'rules of recursion' above."""

    # Base case: we've reached a leaf
    if isinstance(node, Leaf):
        return node.predictions

    # Decide whether to follow the true-branch or the false-branch.
    # Compare the feature / value stored in the node,
    # to the example we're considering.
    if node.question.match(row):
        return classify(row, node.true_branch)
    else:
        return classify(row, node.false_branch)


def main():
    my_tree = build_tree(training_data)

    print_tree(my_tree)

    # Evaluate
    testing_data = [
        ["Green", 3, "Apple"],
        ["Yellow", 4, "Apple"],
        ["Red", 2, "Grape"],
        ["Red", 1, "Grape"],
        ["Yellow", 3, "Lemon"],
    ]

    for row in testing_data:
        print("Actual: {}. Predicted: {}".format(
            row[-1], print_leaf(classify(row, my_tree))))

if __name__ == "__main__":
    main()


# Next steps
# - add support for missing (or unseen) attributes
# - prune the tree to prevent overfitting
# - add support for regression
