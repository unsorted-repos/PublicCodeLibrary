#!/bin/bash
# Source: https://unix.stackexchange.com/questions/232421/why-is-my-function-call-not-working-when-returning-a-boolean
# says that you can only return integers 0 to 255 from shell functions.
# Source: https://stackoverflow.com/questions/5431909/returning-a-boolean-from-a-bash-function 
# The 2nd answer says this is bad protocol. (cause it is unnatural to return 1= false, 0=false)

# Define your function here
Hello () {
   echo "Hello World, The first argument that is passed to this function is: $1 and the second argument that is passed to this function is: $2"
   #return 1 # 1 is false
   return 0 # 0 is true
}

# if hello returns 0 the if statement is evaluated to true
# if hello returns 1 the if statement is evaluated to false
if Hello; then
	echo "Something went wrong with useradd"
fi

exit