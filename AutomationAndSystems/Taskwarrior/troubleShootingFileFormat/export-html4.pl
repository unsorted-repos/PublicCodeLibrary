#! /usr/bin/perl
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

use strict;
use warnings;

# Give a nice error if the (non-standard) JSON module is not installed.
eval "use JSON";
if ($@)
{
  print "Error: You need to install the JSON Perl module.\n";
  exit 1;
}

# Use the taskwarrior 2.0+ export command to filter and return JSON
my $command = join (' ', ("env PATH='$ENV{PATH}' task rc.verbose=nothing rc.json.array=no export", @ARGV));
if ($command =~ /No matches/)
{
  printf STDERR $command;
  exit 1;
}

# Generate output.
print "<html>\n",
      "  <body>\n",
      "    <table>\n",
      "      <thead>\n",
      "        <tr>\n",
      "          <td>ID</td>\n",
      "          <td>Pri</td>\n",
      "          <td>Description</td>\n",
      "          <td>Project</td>\n",
      "          <td>Due</td>\n",
      "        </tr>\n",
      "      </thead>\n",
      "      <tbody>\n";

## This is my own loop Attempt 0
my $countA = 0;
for my $task (split "\n", qx{$command})
{
  ++$countA;
  print ("hello world".$countA);
  print "Entered hello world,$countA \n";
}

## This is my own loop Attempt 1
my $countB = 0;
for my $task (split "\n", qx{$command})
{
  ++$countB;
  print "Entered hello world,$countB \n";
}

# ## This is my own loop Attempt 2 Contains the "malformed JSON string" error but does not print the index.
# my $countC = 0;
# for my $task (split "\n", qx{$command})
# {
#   ++$countC;
  
#   my $dataC = from_json ($task);

#   print "Entered hello world after data,$countC \n";
# }


# ## This is my own loop Attempt 3 Contains the "malformed JSON string" error but does not print the index.
# my $countD = 0;
# for my $task (split "\n", qx{$command})
# {
#   ++$countD;
#   #print ("hi".$countB)
#   my $dataD = from_json ($task);
  
#   print " ",($countD->{'test'}       || 'testB'),"some";
# }

# ## Loop Attempt 4 based on SO suggestion ysth
# my @task = split "\n", qx{$command};
# for my $index (0..$#task) {
#     my $task = $task[$index];
#     print "Before index,$task";
#     print "The index,$index \n";
# }

## Loop Attempt 5 merge SO suggestion ysth in attempt 4 with the original loop.
my @task = split "\n", qx{$command};
for my $index (0..$#task) {
    my $task = $task[$index];
    print "Before index nr:,$index \n";
    print "Before task content:,$task \n";
    my $data = from_json ($task);
    
    print "After index,$index \n\n";

}


##CONCLUSION: Attempt 5 is successfull. It indicates that the error is in the first line of pending.data
## Furthermore, when inspecting the times such as due in the line after right below the error, we can see that it ends with a
## "Z" in the taskformat. 
## However, since the error indicates it is "before ..."  it suggests it is the "duration:" which is the actual problem in stead of the Z after the due number.

##Solution Attempt A: Check if removing the After index parameter " makes the code skip to the next task. Use command:
#sudo nano ~/.task/pending.data
#Then run this code again with: 
#sudo perl /mnt/c/taskToCSV/export-html12.pl
## Conclusion Attempt A: INDEED! The problem was the duration being unknown!
##  This can be solved by this script by modifying the order in which it expects stuff.
##  And including the "duration parameter"

##Solution Attempt B: 
# If the error for the perl script is the same as the error for the taskwarrior
  # server, then it might be solved by adding the duration parameter either:
    #B.0 just as a UDA of a single newly added task followed by syncing
    #B.2 As a properly defined UDA "duration" somehow followed by syncing
    #B.3 By deleting this corrupted tasklist, restoring a clean backup and syncing it,
    # and then first properly defining the UDA "duration" and syncing.
  # Extra help on uda defining: E:\18-09-19 Document structure\personal\Automation and Systems\taskwarrior
  #task show uda.duration shows the uda definition is already in taskwarrior (synced?) but .label and .type are different from default values

##Problem 2: When syncing it now says: "Bad CA file. Error while reading file"


print "Original loop entering:";

## Original loop
my $count = 0;
for my $task (split "\n", qx{$command})
{
  ++$count;
  my $data = from_json ($task);

  print "        <tr>\n",
        "          <td>", ($data->{'id'}          || ''), "</td>\n",
        "          <td>", ($data->{'priority'}    || ''), "</td>\n",
        "          <td>", ($data->{'description'} || ''), "</td>\n",
        "          <td>", ($data->{'project'}     || ''), "</td>\n",
        "          <td>", ($data->{'due'}         || ''), "</td>\n",
        "        </tr>\n";
}

print "      </tbody>\n",
      "      <tfooter>\n",
      "        <tr>\n",
      "          <td>", $count, " matching tasks</td>\n",
      "        </tr>\n",
      "      </tfooter>\n",
      "    </table>\n",
      "  </body>\n",
      "</html>\n";

exit 0;

################################################################################