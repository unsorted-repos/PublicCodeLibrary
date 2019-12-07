classdef PlotData
    properties
        plotName
        relativePath
        exportType
        nrOfLines
        dataSeries
        lineColours
        nrOfDimensions
        axisLabels
        legend
        legendLocation
        plotType
    end
    
    methods
        function obj = PlotData(plotName,relativePath,exportType,nrOfLines,... 
            dataSeries,lineColours, nrOfDimensions,axisLabels,legend,...
            legendLocation, plotType)
            obj.plotName = plotName
            obj.relativePath = relativePath;
            obj.exportType  = exportType;
            obj.nrOfLines = nrOfLines;
            obj.dataSeries = dataSeries;
            obj.lineColours = lineColours;
            obj.nrOfDimensions = nrOfDimensions;
            obj.axisLabels = axisLabels;
            obj.legend = legend;
            obj.legendLocation = legendLocation;
            obj.plotType = plotType;
        end
        
      function plotName = getPlotName(obj)
         plotName = obj.plotName;
      end
% function plotName = getPlotName(obj)
%      plotName = obj.plotName;
%  end
        function relativePath = getRelativePath(obj)
             relativePath = obj.relativePath;
        end
        function exportType = getExportType(obj)
             exportType = obj.exportType;
        end
        function nrOfLines = getNrOfLines(obj)
             nrOfLines = obj.nrOfLines;
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


    end
end