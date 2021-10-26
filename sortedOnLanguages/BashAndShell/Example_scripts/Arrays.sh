#!/bin/bash
# Source: https://www.cyberciti.biz/faq/bash-for-loop/
# This script needs to print an array from 1 to 8 with the numbers 1 to 8
# The path of this script currently is: C:\Users\a\Documents\fun\taskwarrior\taskwarrior custom report
# In Linux that path is found with:
# cd "/mnt/c/Users/a/Documents/fun/taskwarrior/taskwarrior custom report/"
# After navigating to the script folder, the script is executed with the command:
# ./PrintAnArray.sh
echo "Bash version ${BASH_VERSION}..."
for i in {0..10..2}
  do
     echo "Welcome $i times"
  done
# Script Verified untill here.
declare -a arr #This defines an array named arr
arr=("a") #This adds the first element
arr=("${arr[@]}" "new") #This adds  a new element at the end
echo ${arr[@]} #This prints the last element in the erray
#Repeat adding and printing last element
arr=("${arr[@]}" "newest")
echo ${arr[@]}
echo arr
echo ${#arr[@]} #length of array

# How to call i-th element.
# Source: https://stackoverflow.com/questions/15685736/how-to-extract-a-particular-element-from-an-array-in-bash
# set ${arr[2]}
echo ${arr[0]}
echo ${arr[1]}
echo ${arr[2]} #WHOOOHOO!

##############################################################################
# Extra: if you have an array of sentences where each line is an element, e.g.:
# Lines.txt contains:
# hello big world!
# how are you
# where am I

# First put the lines in an array:
declare -a myarr #This defines an array named arr
myarr=("hello big world!") #This adds the first element
myarr=("${myarr[@]}" "how are you") #This adds  a new element at the end
myarr=("${myarr[@]}" "where am I") #This adds  a new element at the end
echo ${myarr[@]} #This prints the complete/entire erray

# I think this sets the last array (has index 2, since it starts at 0) to memory
set ${myarr[2]}
# Then call the 3rd element from the string in memory:
echo $3 #output = I

#Verify 2nd line = element 1, 2nd sub-element of element = are
set ${myarr[1]}
echo $2 #output = are
echo "ERROR HANDLING" #This erases the memory called with $
###Important: in sub elements from memory, the index starts at 1 iso 0!
echo $0 # = the last known echo if memory was called in previous command,
# else it is empty.
##############################################################################
#Import/read/absorb text file per line to element of array:
while read line
do
   myarr[$index]=$line
   index=$(($index+1))
done < lines.txt
##############################################################################
# Change a specific element in array:
i=2
myarr[$i]="value2"
echo myarr[2]
# Remove a specific element in array:
unset 'array[1]' #This usually removes the 2nd element of an array
# Copy an entire array:
b=("${a[@]}")