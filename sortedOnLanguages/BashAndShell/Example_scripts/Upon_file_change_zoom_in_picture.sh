#!/bin/bash

# sudo apt install inotify-tools

# Directory to monitor
dir_to_monitor="$PWD/"
FILE_NAME="ZZ3"
THE_FILENAME="$FILE_NAME.uml"

# Function to crop and zoom an SVG image
crop_and_zoom_svg() {
  input_file="$1"
  output_file="$2"
  x_fraction_from="$3"
  x_fraction_to="$4"
  y_fraction_from="$5"
  y_fraction_to="$6"

  # Get image dimensions outer () to map to array.
  dimensions=($(identify -format "%w %h" "$input_file"))

  # Calculate cropping dimensions
  the_cmd="print(round(${dimensions[0]} * ($x_fraction_to - $x_fraction_from) / 1))"
  width=$(python -c "$the_cmd")

  the_cmd="print(round(${dimensions[1]} * ($y_fraction_to - $y_fraction_from) / 1))"
  height=$(python -c "$the_cmd")

  the_cmd="print(round(${dimensions[0]} * $x_fraction_from))"
  x=$(python -c "$the_cmd")

  the_cmd="print(round(${dimensions[1]} * $y_fraction_from))"
  y=$(python -c "$the_cmd")

  # Crop and resize the image
  convert "$input_file" -crop "${width}x${height}+${x}+${y}" -resize "${width}x${height}" "$output_file"
}

# Function to run PlantUML command
run_plantuml() {
  plantuml "$THE_FILENAME"
}

# Start monitoring the directory
# shellcheck disable=SC2034
inotifywait -m -e close_write "$dir_to_monitor" |
  while read -r directory events filename; do
    if [ "$filename" == "$THE_FILENAME" ]; then
      echo "File $THE_FILENAME has been updated. Running PlantUML..."
      run_plantuml

      crop_and_zoom_svg "$FILE_NAME.png" "zoomed_$FILE_NAME.png" 0.0 0.9 0.2 0.8
      # rm "$FILE_NAME.png"
    fi
  done
