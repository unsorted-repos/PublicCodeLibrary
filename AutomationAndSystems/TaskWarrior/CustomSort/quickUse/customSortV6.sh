#!/bin/bash
################################################################################
##
## Copyright 2006 - 2017, Paul Beckingham, Federico Hernandez.
##
## Permission is hereby granted, free of charge, to any person obtaining a copy
## of this software and associated documentation files (the "Software"), to deal
## in the Software without restriction, including without limitation the rights
## to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
## copies of the Software, and to permit persons to whom the Software is
## furnished to do so, subject to the following conditions:
##
## The above copyright notice and this permission notice shall be included
## in all copies or substantial portions of the Software.
##
## THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
## OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
## FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
## THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
## LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
## OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
## SOFTWARE.
##
## http://www.opensource.org/licenses/mit-license.php
##
################################################################################
# Part I of This script generates a 2 arrays that contain the task id's and urgencies of tasks with urgency above 6.
# TODO 18-08-24: Give it a parametric urgency threshhold iso a numerical threshold.
# Part II That list then gets sorted on urgency in ArrSortId and ArrSortUr
# Part III Another script gets the task ids of all the ArrNotUrgentId ArrNotUrgentProject 
# Part IV sorts the ArrNotUrgentId ArrNotUrgentProject on project and puts the sorted array in ArrSortNotUrgentId ArrSortNotUrgentProject
# Part V combines  ArrSortNotUrgentId ArrSortId in to final list
# Part VI puts a new line after every project to distinguish projects and gives the report command for the given list.
##########################################################################################################################################
#Part I
#First install bc to do arthmatic computations:
#sudo apt-get install bc

if [ ! -d output/ ]; then
  mkdir -p output/;
fi


# Warn that this is not a quick report.
echo 'Finding most urgent tasks in each project...'

# UUIDS is a string that will contain the UUID of every task we wish to see.
# UUIDS is a string that will contain the urgency of every task we wish to see.
UUIDS=
PROJ=
PROJECT=
URGENCIES=
TASKID=
store_urgencies=
store_projec=


# create 2 arrays that will contain the id's and the urgencys of the tasks with urgency greater than 6:
declare -a ArrId #This defines an array named arr
declare -a ArrUr #This defines an array named arr
declare -a AllArrUr #This defines an array named arr
declare -a AllArrUrUuid #This defines an array named arr
declare -a combinedArrUrUuid #This defines an array named arr

#Redo for project
declare -a AllArrProj #This defines an array named arr
declare -a AllArrProjUuid #This defines an array named arr

#Get the list sorted on project alphabet:

# Step 0, create a custom report that sorts on project
# task config report.standardlist.description 'List of low priority tasks'
# task config report.standardlist.columns     'id,depends,due,priority,urgency,duration,project,recur,tags,description,start'
# task config report.standardlist.labels      'id,dep,due,prio, urgy,dura,proj,again,tag, descr,start'
# task config report.standardlist.sort        'project+/,entry+'
# task config report.standardlist.filter      'status:pending'

#step 1 Requiest all the ids in order of listing

# for uuid in $(task standardlist  _uuids)
# do
#   UUIDS="$UUIDS $uuid"
#   #echo "UUID=" UUIDS
# done
# #echo "Quicklista = " ${UUIDS[@]} #This prints the complete erray

# for proj in $(task status='pending' _project)
# do
#   PROJ="$PROJ $project"
# done

# Enumerate pending task UUIDs having no project.
# Mod: Enumerate all tasks having no date
i=2 #Initialize parameter i as integer
store=0 #Initialize boolean that is used to check if the urgency is higher than X(X=6)
echo "Id proj" > output/idProj.txt # Fill header of datafile
for project in $(task status='pending' _id) #Get the urgency statement for filter: pending tasks, and again request to add the "_urgency" parameter
do
  for projectName in $(task $project _project) #Get the urgency statement for filter: pending tasks, and again request to add the "_urgency" parameter
  do
    store_projectName="$projectName"
    AllArrProj=("${AllArrProj[@]}" "$projectName") #This adds  a new element at the end
      if [ $(((i-2)/4-1)) -eq 0 ]; then
        AllArrProj=("$projectName") #This adds  a new element at the end
      fi
      if [ $(((i-2)/4-1)) -gt 0 ]; then
        AllArrProj=("${AllArrProj[@]}" "$projectName") #This adds  a new element at the end
      fi
      
    echo "i=$project and projectName = $projectName" 
    echo "$project"";""$projectName" >> output/idProj.txt
  done
done
#step 2 Verify order
echo "Projects = " ${AllArrProj[@]} #This prints the complete erray
#mapfile -t AllArrProj < output/project.txt



































# Enumerate pending task UUIDs having no project.
# Mod: Enumerate all tasks having no date
# for uuid in $(task status='pending' and Urgency='16.4' _uuids)
# do
#   UUIDS="$UUIDS $uuid"
# done

# # Enumerate pending task UUIDs having no project.
# # Mod: Enumerate all tasks having no date
# i=2 #Initialize parameter i as integer
# urgency_double=3.4 #Initialize double to check the urgency level as double in a if condition.
# store=0 #Initialize boolean that is used to check if the urgency is higher than X(X=6)
# for urgency in $(task status='pending' _urgency) #Get the urgency statement for filter: pending tasks, and again request to add the "_urgency" parameter
# do
#   #TESTUUID="$uuid"
#   let "i+=1" #Count the integer i since the urgency is given in the 2nd piece of information returned per task.
#   if [ $((i%4)) -eq 2 ]; then #If the iteration number of information pieces per task is 2 then:
#     URGENCIES="$urgency" #absorb the urgency of that task
#     echo "The actual urgency =" "$urgency"
#     #urgency_integer="$urgency" #absorb the urgency of that task
#     #echo "See if this is number:" $(("$urgency"))| bc -l #If you want to store a double add | bc -l behind the string to number $(()) command.
#     #Round down: -0.5 : 6.8->6.3 is rounded down, 6.3->5.8= rounded up by awk:
#     #awk  'BEGIN { rounded = sprintf("%.0f", 1.52); print rounded }'
#     #awk  'BEGIN { rounded = sprintf("%.0f", '"$urgency"'); print rounded }'
#     urgency_double=$( awk  'BEGIN { rounded = sprintf("%.0f", '"$urgency"'); print rounded }' )
# 	  if [ $((urgency_double)) -ge 6 ]; then # If the urgency is greater than or equal to 6
#       let "store+=1"
# 	    store_urgencies="$urgency"
#       #echo "urgency: " $store_urgencies " counter:" "i = $i " "remainder=" $((i%4)) #Print to check it is the correct value.
#       #echo "store="$store # Record/store that the ID must be stored.
#       ArrUr=("${ArrUr[@]}" "$urgency") #This adds  a new element at the end
#       echo "The ur array = " ${ArrUr[@]} #This prints the complete erray
#     fi
#     #echo "Hello" $URGENCIES " and " " world " "i = $i " "remainder=" $((i%4
#     #echo "STORE=" $store
#   fi
#   if [ $((i%4)) -eq 0 ]; then #If the iteration number of information pieces per task is 2 then:
#   	URGENCIES="$urgency" #absorb the urgency of that task
#     if [ $((store)) -ge 1 ]; then 
#       TASKID="$urgency" #absorb the urgency of that task
#       echo "TASKID:"$TASKID "counter:" "i = $i " "remainder=" $((i%4)) #Print to check it is the correct value.
#       store=0
#       ArrId=("${ArrId[@]}" "$TASKID") #This adds  a new element at the end
#       echo "The id array = " ${ArrId[@]} #This prints the complete erray
#     fi
#     #echo "Hello" $URGENCIES " and " " world " "i = $i " "remainder=" $((i%4))
#   fi
# done
# ##########################################################################################################################################
# # Part II That list then gets sorted on urgency in ArrSortId and ArrSortUr
# #Multiply by 1000, then round.
# temp=2 #Define a temporary parameter to pass values to ArrMultUr
# declare -a ArrMultUr #This defines an array named arr
# for ((i = 0; i<${#ArrUr[@]}; i++))
# do
# 	#ArrMultUr[$i]="${ArrUr[i]}"
# 	# Source: https://www.linuxquestions.org/questions/programming-9/decimal-numbers-in-bash-script-variables-380924/
# 	# Multiply with 1000 then round:
# 	temp=$(echo "1000 * "${ArrUr[i]}"" | bc -l)
# 	ArrMultUr[$i]=$temp
# 	echo $temp
# 	#echo "3 * 2.19" | bc -l
# 	#echo "1000 * "${ArrUr[i]}"" | bc -l
# 	echo ${ArrMultUr[i]} " and " ${ArrUr[i]}
# done
# echo 'scale=3; 6.5/2.7' | bc

# # Sorting the array in Bash 
# # using Bubble sort
# # Static input of Array
# echo "Array in original order"
# echo ${ArrUr[*]}
# echo ${#ArrUr[@]} #length of array

# # Performing Bubble sort 
# for ((i = 0; i<${#ArrUr[@]}; i++))
# do
     
#     for((j = i; j<${#ArrUr[@]}-i-1; j++))
#     do
     
#         if ((${ArrUr[j]} > ${ArrUr[$((j+1))]}))
#         then
#             # swap
#             temp = ${ArrUr[$j]}
#             ArrUr[$j] = ${ArrUr[$((j+1))]}  
#             ArrUr[$((j+1))] = $temp
#         fi
#     done
# done
 
# echo "Array in sorted order :"
# echo ${ArrUr[*]}
##########################################################################################################################################
# Part III Another script gets the task ids of all the ArrNotUrgentId ArrNotUrgentProject 
##########################################################################################################################################
# Part IV sorts the ArrNotUrgentId ArrNotUrgentProject on project and puts the sorted array in ArrSortNotUrgentId ArrSortNotUrgentProject
##########################################################################################################################################
# Part V combines  ArrSortNotUrgentId ArrSortId in to final list
##########################################################################################################################################
# Part VI puts a new line after every project to distinguish projects and gives the report command for the given list.
# show the tasklist containg tasks without a due date.
#task $UUIDS

#Try to get a list of all tasks UUID's and accompanying  urgencies
for uuid in $(task status='pending' _uuids)
do
  UUIDS="$UUIDS $uuid"
  #echo "UUID=" UUIDS
done
echo "Quicklist = " ${UUIDS[@]} #This prints the complete erray

for uuid in $(task status='pending' and Urgency='16.4' _uuids)
do
  UUIDS="$UUIDS $uuid"
done


# Enumerate pending task UUIDs having no project.
# Mod: Enumerate all tasks having no date
i=2 #Initialize parameter i as integer
urgency_double=3.4 #Initialize double to check the urgency level as double in a if condition.
store=0 #Initialize boolean that is used to check if the urgency is higher than X(X=6
#echo "id" > output/one.txt
#echo "AllArrUr" > output/two.txt
echo "Id urg" > output/idUrg.txt
for urgency in $(task status='pending' _urgency) #Get the urgency statement for filter: pending tasks, and again request to add the "_urgency" parameter
do
  #TESTUUID="$uuid"
  let "i+=1" #Count the integer i since the urgency is given in the 2nd piece of information returned per task.
  if [ $((i%4)) -eq 2 ]; then #If the iteration number of information pieces per task is 2 then:
    URGENCIES="$urgency" #absorb the urgency of that task
    #echo "The actual urgency =" "$urgency"
    #urgency_integer="$urgency" #absorb the urgency of that task
    #echo "See if this is number:" $(("$urgency"))| bc -l #If you want to store a double add | bc -l behind the string to number $(()) command.
    #Round down: -0.5 : 6.8->6.3 is rounded down, 6.3->5.8= rounded up by awk:
    #awk  'BEGIN { rounded = sprintf("%.0f", 1.52); print rounded }'
    #awk  'BEGIN { rounded = sprintf("%.0f", '"$urgency"'); print rounded }'
    urgency_double=$( awk  'BEGIN { rounded = sprintf("%.0f", '"$urgency"'); print rounded }' )
    
      let "store+=1"
      store_urgencies="$urgency"
      #echo "urgency: " $store_urgencies " counter:" "i = $i " "remainder=" $((i%4)) #Print to check it is the correct value.
      #echo "store="$store # Record/store that the ID must be stored.
      AllArrUr=("${AllArrUr[@]}" "$urgency") #This adds  a new element at the end
      #echo "The All ur array = " ${AllArrUr[@]} #This prints the complete erray
      #echo "Index="$(((i-2)/4))
      echo "The Urgency before = " ${AllArrUr[$(((i-2)/4-1))]} " and i = $(((i-2)/4-1))"
      #echo "$(((i-2)/4-1))" >> one.txt
      #echo ${AllArrUr[$(((i-2)/4-1))]} >> two.txt
      echo "$(((i-2)/4-1+1))"";"${AllArrUr[$(((i-2)/4-1))]} >> output/idUrg.txt

    #echo "Hello" $URGENCIES " and " " world " "i = $i " "remainder=" $((i%4
    #echo "STORE=" $store
  fi


#mapfile -t AllArrUrUuid < four.txt

  #TESTUUID="$uuid"
  
  if [ $((i%4)) -eq 0 ]; then #If the iteration number of information pieces per task is 2 then:
    URGENCIES="$urgency" #absorb the urgency of that task
    #echo "The actual urgency =" "$urgency"
    #urgency_integer="$urgency" #absorb the urgency of that task
    #echo "See if this is number:" $(("$urgency"))| bc -l #If you want to store a double add | bc -l behind the string to number $(()) command.
    #Round down: -0.5 : 6.8->6.3 is rounded down, 6.3->5.8= rounded up by awk:
    #awk  'BEGIN { rounded = sprintf("%.0f", 1.52); print rounded }'
    #awk  'BEGIN { rounded = sprintf("%.0f", '"$urgency"'); print rounded }'
    urgency_double=$( awk  'BEGIN { rounded = sprintf("%.0f", '"$urgency"'); print rounded }' )
    
      let "store+=1"
      store_urgencies="$urgency"
      #echo "urgency: " $store_urgencies " counter:" "i = $i " "remainder=" $((i%4)) #Print to check it is the correct value.
      #echo "store="$store # Record/store that the ID must be stored.
      AllArrUrUuid=("${AllArrUrUuid[@]}" "$urgency") #This adds  a new element at the end
      #echo "The All UUID array = " ${AllArrUrUuid[@]} #This prints the complete erray
      #echo "UUID = " ${AllArrUrUuid[$(((i)/4-1))]}
      echo "The UUID before = " ${AllArrUrUuid[$(((i-2)/4-1))]} " and i = $(((i-2)/4-1))"
      echo "The urgency after = " ${AllArrUr[$(((i-2)/4-1))]} " and i = $(((i-2)/4-1))"
      combinationString=${AllArrUrUuid[$(((i-2)/4-1))]}","${AllArrUr[$(((i-2)/4-1))]}
      echo "combinationString=" $combinationString
      if [ $(((i-2)/4-1)) -eq 0 ]; then
        combinedArrUrUuid=(${AllArrUrUuid[$(((i-2)/4-1))]}","${AllArrUr[$(((i-2)/4-1))]})
      fi
      if [ $(((i-2)/4-1)) -gt 0 ]; then
        combinedArrUrUuid=("${combinedArrUrUuid[@]}" ${AllArrUrUuid[$(((i-2)/4-1))]}","${AllArrUr[$(((i-2)/4-1))]}","${AllArrProj[$(((i-2)/4-1))]})
      fi
      
      echo "combined = " ${combinedArrUrUuid[$(((i-2)/4-1))]}
    #echo "Hello" $URGENCIES " and " " world " "i = $i " "remainder=" $((i%4
    #echo "STORE=" $store
  fi

done
#{ [ "${#combinedArrUrUuid[@]}" -eq 0 ] || printf '%s\n' "${combinedArrUrUuid[@]}"; } > file.txt
#{ printf '%s\n' "${combinedArrUrUuid[@]}"; } > file.txt
#{ [ "${#mtches[@]}" -eq 0 ] || printf '%s\n' "${mtches[@]}"; } > file
#combinedArrUrUuid=( ${AllArrUr[*]} ${AllArrUrUuid[*]} )
echo "The combineD array = " ${combinedArrUrUuid[@]} #This prints the complete erray



#mapfile -t AllArrProj < three.txt
#mapfile -t combinedArrUrUuid < file.txt

apt-get update
yes | apt install default-jre

# [ -f javaCustomSort/src/test0/output.sh ] && echo "Found" || echo "Not found"
if [ -f javaSort.jar ]; then
    echo "File found!"
java -jar javaSort.jar
fi
if [ -f output/output.sh ]; then
    echo "File found!"
    ./output/output.sh
fi
# if [ -f /javaCustomSort/src/test0/output.txt ]; then
#     echo "File found!"
# fi

yes | task config report.nice0.description 'List of low priority tasks'
yes | task config report.nice0.columns     'id,depends,due,priority,urgency,duration,project,recur,tags,description,start'
yes | task config report.nice0.labels      'id,dep,due,prio,urgy,dura,proj,again,tag, descr,start'
yes | task config report.nice0.sort        'secretSort+/'
yes | task config report.nice0.filter      'status:pending'

echo "done"