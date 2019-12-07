classdef PlotData
    properties
        fileName
        relativePath
        exportType
        nrOfLinesPerDim
        dataSeries
        lineColours
        nrOfDimensions
        axisLabels
        legend
        legendLocation
        plotType
        axisScales
        currentFolder
        latexDestination
    end
    
    methods
        function obj = PlotData(fileName,relativePath,exportType,... 
            dataSeries,lineColours, nrOfDimensions,axisLabels,legend,...
            legendLocation, plotType,axisScales,currentFolder,...
            latexDestination)
            disp("HI")
            disp(dataSeries)
            disp("BY")
            obj.fileName = fileName
            obj.relativePath = relativePath;
            obj.exportType  = exportType;
            obj.nrOfLinesPerDim = zeros(1,nrOfDimensions)
            obj.nrOfDimensions = nrOfDimensions;
            for dim = 0:obj.nrOfDimensions-1 % java
                obj.nrOfLinesPerDim(dim+1) = dataSeries.get(dim).size() % java
            end
            obj.dataSeries = dataSeries;
            obj.lineColours = lineColours;
            obj.axisLabels = axisLabels;
            obj.legend = legend;
            obj.legendLocation = legendLocation;
            obj.plotType = plotType;
            obj.axisScales = axisScales;
            obj.currentFolder = currentFolder;
            obj.latexDestination =latexDestination;
        end
        
        function fileName = getFileName(obj)
            fileName = obj.fileName;
        end
        
        function relativePath = getRelativePath(obj)
             relativePath = obj.relativePath;
        end
        function exportType = getExportType(obj)
             exportType = obj.exportType;
        end
        function nrOfLinesPerDim = getNrOfLinesPerDim(obj)
             nrOfLinesPerDim = obj.nrOfLinesPerDim;
        end
        function dataSeries = getDataSeries(obj)
             dataSeries = obj.dataSeries;
        end
        function lineColours = getLineColours(obj)
             lineColours = obj.lineColours;
        end
        function nrOfDimensions = getNrOfDimensions(obj)
             nrOfDimensions = obj.nrOfDimensions;
        end
        function axisLabels = getAxisLabels(obj)
             axisLabels = obj.axisLabels;
        end
        function legend = getLegend(obj)
             legend = obj.legend;
        end
        function legendLocation = getLegendLocation(obj)
             legendLocation = obj.legendLocation;
        end
        function plotType = getPlotType(obj)
             plotType = obj.plotType;
        end
        function axisScales = getAxisScales(obj)
             axisScales = obj.axisScales;
        end
        function currentFolder = getCurrentFolder(obj)
            currentFolder = obj.currentFolder;
        end
        function latexDestination = getLatexDestination(obj)
            latexDestination = obj.latexDestination;
        end

    end
end