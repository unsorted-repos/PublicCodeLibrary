import numpy as np
import os
import glob

# Rename files in ascending continuous numeric order
class Create_tables:

    def __init__(self):
        pass

    def order_files(self):
        print(f'hello world')

        counter = 0
        #os.chdir(r"G:\desktop\Project\test")
        for index, oldfile in enumerate(glob.glob("*.png"), start=1):
            counter+=1
            #newfile = '{}.jpg'.format(index)
            newfile =f'ga_{counter}.png'
            print(f'oldfile={oldfile}, and new file = {newfile}')
            os.rename (oldfile,newfile)
        
if __name__ == '__main__':
    main = Create_tables()
    main.order_files()
    