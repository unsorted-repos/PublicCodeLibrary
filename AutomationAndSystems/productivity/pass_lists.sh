#!/bin/bash
## Create blacklist
echo "Parsing personal blacklist"

# remove the newline characters from a line that is read
remove_newline_chars=' \t\r'

# define the name of the output file
output_blacklist="EnergizedProtection/blacklist"
personal_blacklist="EnergizedProtection/personal_blacklist.txt"
domain_list="EnergizedProtection/domain_extensions.txt"
touch $output_blacklist # create blacklist file

# read personal blacklist
while read line; do    
	# read domain extensions
	while read domain; do	
		
		# remove newline character and convert blacklist entry and domain into website
		website="$(<<<"$line$domain" tr -d "$remove_newline_chars")"
		wwwWebsite="www."$website
		echo $wwwWebsite
		# check if output file exists
		if ! grep -q $website $output_blacklist ; then
			if [ -f "$output_blacklist" ]; then 
				echo "$website" >> "$output_blacklist"
			fi
		fi
		if ! grep -q $wwwWebsite $output_blacklist ; then
			if [ -f "$output_blacklist" ]; then 
				echo "$wwwWebsite" >> "$output_blacklist"
			fi
		fi
	done < $domain_list
done < $personal_blacklist
echo "Done"


