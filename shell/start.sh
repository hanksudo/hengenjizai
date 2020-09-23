#!/bin/sh

# comment

echo "Hello, World!"

# Variables

variable="hello"
echo $variable
PRICE=100

# blackslash "\" is used to escape special character
echo "The price is \$$PRICE"


# encapsulating the variable name with ${} used to avoid ambiguity
echo "The price is \$${PRICE}"

# encapsulating the variable name with "" will preserve white spaces
greeting='Hello        world!'
echo $greeting" now with spaces: $greeting"

# Variable can assigned the value of a command output. This is referred to as substitution.
# Substitution can be done by encapsulating with `` or $()
FILELIST=`ls`
echo ${FILELIST}
echo $(ls)