source: https://tex.stackexchange.com/questions/289385/workflow-for-including-jupyter-aka-ipython-notebooks-as-pages-in-a-latex-docum

0. Open Anaconda
1. Browse to path with `test.ipynb`
2. Convert to markdown with:
```
jupyter nbconvert --to markdown test.ipynb
```
3. Convert markdown to tex with: 
```
pandoc --listings -f markdown -t latex test.md -o test.tex
```
4. Put/copy that `test.tex` in latex/overleaf.
5. Include the tex file with:
```
\input{latex/hw1/Appendices/text.tex}
```
6. You can also inspect the pdf of what the .tex file will look like in the latex document with:
```
pandoc --listings -f markdown -t latex test.md -s -o test.tex
pdflatex test.tex
```