#!/bin/bash
echo "Parsing personal blacklist"

# remove the newline characters from a line that is read
remove_newline_chars=' \t\r'

# define the name of the output file
OUTPUT_FILE="blacklist.txt"

# read personal blacklist
while read line; do    
	# read domain extensions
	while read domain; do	
		
		# remove newline character and convert blacklist entry and domain into website
		WEBSITE="$(<<<"$line$domain" tr -d "$remove_newline_chars")"
		
		# check if output file exists
		if ! grep -q $WEBSITE $OUTPUT_FILE ; then
			touch $OUTPUT_FILE # create blacklist file
			if [ -f "$OUTPUT_FILE" ]; then 
				echo "$WEBSITE" >> "$OUTPUT_FILE"
			fi
		fi
	done < domain_extensions.txt
done < personal_blacklist.txt
echo "Done"