classdef PlotSingleLine
   methods
        function plot_altitudes(obj, est_alt_km,precise_alt_km,filename)
            % plot altitude with legends etc
            nr_of_dataseries = 2;
            % Create vector with data series length
            data_series_length = zeros(nr_of_dataseries,1);
            data_series_length(1,1) = length(est_alt_km);
            data_series_length(2,1) = length(precise_alt_km);

            % declare dataseries
            for  i = 1:nr_of_dataseries
                x_series= zeros(nr_of_dataseries,data_series_length(i,1));
                y_series= zeros(nr_of_dataseries,data_series_length(i,1));
            end

            % fill x dataseries
            for i = 1:nr_of_dataseries
                x_series(i,:) = 1:data_series_length(i,1)
            end

            % fill y dataseries
            y_series(1,:) = est_alt_km;
            y_series(2,:) = precise_alt_km;

            % create labels
            labels = ["residuals","precise alt."];

            createFigure(x_series,y_series,labels,filename)
        end
    end
end

function createFigure(x_series,y_series,serie_labels,filename)
    % create figure object
    f1 = figure;
    figure(1);clf(1);
    stem(x_series(1,:), y_series(1,:), 'filled', 'b')
    hold on

    % plot dataseries
%     plot(x_series(1,:),y_series(1,:));
%     plot(x_series(2,:),y_series(2,:));

    % define axis domains and ticks
%     axis([0 0.4 0 1.6]);
%     set(gca, 'XTick',0:0.1:0.4);
%     set(gca, 'XTickLabel',[1 2 3 5 6 7 8 9 10 11]);
%     set(gca, 'YTick',0:0.4:1.6);

    % set xlabel
%     xlabel('$\displaystyle\frac{X{g_0}}{C_{eff}^2}$','Interpreter','latex');
    xlabel('epoch')
    xlh = get(gca,'xlabel');
    gyl = get(xlh);   
    xlp = get(xlh, 'Position');
    % disable rotation of yaxis label
%     set(xlh, 'Rotation',0, 'Position',[0.38 -0.03], 'VerticalAlignment','top', 'HorizontalAlignment','right');

    ylabel('residual size [m]');
    % turn y label into object
    ylh = get(gca,'ylabel');
    gyl = get(ylh);
    ylp = get(ylh, 'Position');
    % disable rotation of yaxis label
%     set(ylh, 'Rotation',0, 'Position',[-0.01 1.5], 'VerticalAlignment','top', 'HorizontalAlignment','right');

    % Add arrows with text. the coordinates are fractions of the axis lengths
%     annotation('textarrow', [0.33 0.275], [0.6 0.662],'String' , 'T=const'); 
%     annotation('textarrow', [0.6 0.52], [0.5 0.562],'String' , '\psi=const'); 

    % Add text at random positions in the graph
%     text(0.001,0.49,'0.6')
%     text(0.03,0.33,'0.6')
%     text(0.1,0.6,'0.4')
%     text(0.02,0.77,'0.4')
%     text(0.11,1.4,'$\displaystyle\frac{M}{M_0}=0.2$','Interpreter','latex')
%     text(0.34,1.28,'$\displaystyle\frac{M}{M_0}=0.2$','Interpreter','latex')

    % add legend to plot
    legend(serie_labels(1,:));

    hold off

    exportFigureToLatex(f1,filename)
end

% suppose you have a folder: "code" and a folder "latex" next to
% eachother, then you can save to: "parent dir of this code">latex>images>
function exportFigureToLatex(figureObject,filename)
    currentFolder = pwd;

    % write the path from the folder that contains "Latex" to this code:
    subtractPath = "/code/";
    %+2 to account for begin and end slash
    parentFolder =currentFolder(1:(length(currentFolder)-strlength(subtractPath)+2));
    targetFolder = parentFolder+"latex/images/";


    destinationFile = targetFolder+filename

    figureObject;

%     hgexport(gcf, 'figure1.jpg', hgexport('factorystyle'), 'Format', 'jpeg');
%     hgexport(gcf, destinationFile, hgexport('factorystyle'), 'Format', 'jpeg');
    saveas(gca, destinationFile,'epsc');
end
