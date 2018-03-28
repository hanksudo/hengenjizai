#!/usr/bin/env bash

# This is a file to demonstrate shell script.

# print
WORD1=Car
WORD2=Fish

echo "This word $WORD1 contains ${#WORD1} characters."
echo "This word $WORD2 contains ${#WORD2} characters."
echo "I am here."; echo "you are there."
echo $(( 2#111 ))
echo $(( 16#FF ))

# lower and upper case (> Bash 4.0)
WORD3="WHATsup"
echo ${WORD3^^}
echo ${WORD3,,}

# escape
echo "\"Linux is awesome!\""

# if condition
number=10

if [ "$number" -gt 0 ]; then echo "YES"; else echo "NO"; fi
if [ "$number" -le 0 ]; then
    echo "$number less than or equal zero"
else
    echo "$number greater than zero"
fi


# loop statement
colors="red yellow orange"

for color in $colors
do
    echo $color
done

# comma
let "y=((x=20, 10/2))"
echo $y, $x

let v=500/2
echo $v

v2=`echo $v`
echo $v2

echo 100*10
echo 10**3
