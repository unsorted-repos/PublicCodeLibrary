One can auto generate docstrings for python files using Pyment.
https://github.com/dadadel/pyment
Pyment can be installed with:
```
pip install pyment
```
You can create an uncommented python script and then open terminal in the directory of that script.
Then you can automatically generate the comments for the python script using command:
```
pyment -w test.py
```
Or you could first inspect the generation/update of the documentation with a command that generates a `test.py.patch` file: 
```
pyment -w test.py
```

Next you can generate fancy html documentation based on those docstring comments in your `test.py` file using pdoc:
https://github.com/pdoc3/pdoc
Pdoc can be installed with:
```
pip install pdoc3
```
Pdoc can generate the html documentation with:
```
pdoc --html test
```
Or it can generate a pdf with documentation with:
```
pdoc --pdf test
```
But that did not actually generate the pdf. Therefore one could also use:
```
chromium --headless --disable-gpu --print-to-pdf=output.pdf input.html
```
