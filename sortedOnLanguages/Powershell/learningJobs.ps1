#Source: https://www.howtogeek.com/138856/geek-school-learn-how-to-use-jobs-in-powershell/
#Goal: learn how to run some code/method in the backgound while other code is continuously
#   executed in paralel.

# the example code is creating a list of files that exist in the c:/ folder:
#Get-ChildItem C:\ –Recurse #starts printing lists of all the files in the c:/ drive per folder.

# 0. Learn how to create a job.
#Start-Job automatically shows an overview of the jobs that are running after the job started
Start-Job –Name GetFileList –Scriptblock {Get-ChildItem C:\ –Recurse} 

# the name of the job is "GetFileList"
# the code that is executed consists of the code between brackets.

# 1. Learn how to do something else while that job is running.
Write-Host "`n hello world, this is something else than the job that is running in the background"

# allow the job to run in the background for 5 seconds so that it can build a file list of C:/
Start-Sleep -s 3

# 1.5 Learn how to see which jobs are currently running:
Get-Job

# 2. Learn how to stop the job.
Get-Job –Name GetFileList | Stop-Job
# please note that stopping a job does not mean terminate (the job is still saved untill you close the powershell session)
Write-Host "`n Show that the job is stopped"
Get-Job

# 3. Learn how to receive any output of the job.
    # 3.b Get the output of the job and keep the job:
Write-Host "`n Show the first 2 entries of the command that is being run:"
Get-Job –Name GetFileList | Receive-Job –Keep | Select -First 2
Write-Host "`n Show that the job still exists:"
Get-Job
### Problem, the data is not shown (expected a list of the first 10 items on the C:drive, but there is no such output).
###     Perhaps the job gets terminated so fast that it did not yet have time to add an item to the list.
### Solution: That was indeed the case, hence the `Start-Sleep -s 6`  command that waits 6 seconds before executing the next line/stop command.

    # 3.a Get the output of the job and delete the job (-Wait is needed to use AutoRemoveJob):
Write-Host "`n Show entire output of the command and delete the command:"
Get-Job –Name GetFileList | Receive-Job -AutoRemoveJob -Wait
Write-Host "`n Show that the job is removed:"
Get-Job


# 4. Learn how to delete the job (without receiving any output).
    # 4.a first create a command since the previous ones are deleted:
Start-Job –Name DifferentJob –Scriptblock {Get-ChildItem C:\ –Recurse} 
Write-Host "`n Show that the job `"DifferentJob`" is exists"
Get-Job
    # 4.b remove the job without getting the output:
            # 4.b.a First stop the job:
            Get-Job -Name DifferentJob | Stop-Job
            # 4.b.a Then stop the job:
            Get-Job –Name DifferentJob | Remove-Job
            # 4.b.c Or force it to delete without stopping
            #Get-Job –Name DifferentJob | Remove-Job -Force
Write-Host "`n Show that the job `"DifferentJob`" does not exist anymore:"
Get-Job