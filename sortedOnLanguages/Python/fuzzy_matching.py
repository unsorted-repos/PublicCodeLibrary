# pip install levenshtein
import Levenshtein as lev

# Specify two random strings.
a="Some title with words that have some order"
b="Some title with some word order"

# Compute similarity between the two titles.
lev_ratio = lev.ratio(
                a.lower(),
                b.lower(),
            )
            
# Check if the two titles are (roughly) the same or not.
if lev_ratio > 0.7:
	print(f"Title a:\n    {a}\nis considered the same title as b:\n    {b}\neven though they are an inexact match.")