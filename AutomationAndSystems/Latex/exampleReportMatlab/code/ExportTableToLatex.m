%% clear console and data
clear all
close all
clc

%% declare and initialise parameters
filename = "table_1.csv"
latexDestination = "../latex/tables/";
createFatHeaders =true; 

% create your own table
Header = ["item", "amount", "id"];
fries = [10,2,3.];
Potatoes = [1.4 5 "hangryy"];
ultramarijn = ["deep purple" "ultraviolent" "yellowish"];
swag = ["swag" "swagga" "swaggalini"];
table =[Header;fries;Potatoes;ultramarijn;swag];

putTableInLatex(table,filename,latexDestination,createFatHeaders)

%% create and export the table to latex
function putTableInLatex(table,filename,latexDestination,createFatHeaders)
    
    % initialise row offset
    start_offset =1;
    
    % get dimensions of table
    nrOfRows = size(table,1);
    nrOfColumns = size(table,2);
    tableColumns = zeros(nrOfRows ,nrOfColumns);
    rows = strings(nrOfRows,1);

    % parse the matrix to lines of latex
    if (createFatHeaders)
        rows(1) = "\textbf{"+ table(1,1)+"}";
        for column = 2:nrOfColumns-1
            rows(1) = rows(1) + " & \textbf{"+ table(1,column)+"}";
        end
        rows(1) = rows(1) + " & \textbf{" + table(1,column+1)+"} \\ \hline" 
        start_offset = start_offset +1
    end
    
    for row = start_offset:nrOfRows
        rows(row) = table(row,1);
        for column = 2:nrOfColumns-1
            rows(row) = rows(row) + " & "+ table(row,column);
        end
        rows(row) = rows(row) + " & " + table(row,column+1)+" \\ \hline" 
    end 
    
    % write the table to file
    fileID = fopen(latexDestination+filename+'','w');
    nbytes = fprintf(fileID,'%s\n',rows)
    fclose(fileID);
end

%% python code:
% self.tableCols = np.zeros((3,len(j)));
% #export table to report
% for i in range(0,len(self.j)):
%     print("j="+str(self.j[i])+",a="+str(self.a_store[i,self.q_max_arr[i],self.r_max_arr[i]])+",e="+str(self.e[self.r_max_arr[i]]))
%     self.tableCols[1][i] = self.a_store[i,self.q_max_arr[i],self.r_max_arr[i]]
%     self.tableCols[2][i] = self.e[self.r_max_arr[i]]
% self.tableCols[0] = self.j
% np.savetxt("latex/hw2/tables/approach3.csv", np.transpose(self.tableCols, axes=None), delimiter=' & ', fmt='%2.8f', newline='  \\\\ \hline \n')