%% clear console and data
clear all
close all
clc

% declare and initialise parameters
mp = 1000;
mq = 1;
alpha_p = [-1; 0];
alpha_q = [1000; 0];
G = 1;
mu_p = G * mp;
mu_q = G * mq;
d_p = 1;
d_q = 1000;
n = sqrt((mu_p + mq)/(d_p + d_q)^3);

% write down integration span
t_span = [0:1:500000];

% create initial state (a_1,a_1_dot,a_2,a_2_dot):
alpha_init = [-1001; 0; 0;0];

% call differential equations with ODE45
% [t,alpha] = ode45(@(t,alpha) odefcn18_3(t,alpha,alpha_p,alpha_q,mu_p,mu_q,d_p,d_q,n), t_span, alpha_init);

%% plot to latex example
% dataseries_1 = alpha(:,1);
% dataseries_2 = alpha(:,3);

% filename = "plot_g_altitudes";
% obj_mult = PlotMultipleLines;
% plot_altitudes(obj_mult,dataseries_1,dataseries_2,filename);

%plot(alpha(:,1),alpha(:,3)),axis([-1080 1080 -1080 1080]),xlabel("AU"),ylabel("AU");

% Working plot:
% plot(alpha(:,1),alpha(:,3)),axis([-1080 1080 -1080 1080]);
% disp("plotted")

%% Create plot object

% declare dataseries (in this case 3, can be more)
x_series = java.util.ArrayList(); %omg can has java in matlab
y_series = java.util.ArrayList();
z_series = java.util.ArrayList();

% declare axis scales
axisScales = java.util.ArrayList();

% declare and initialise plot parameters
currentFolder = "/code/";
latexDestination ="latex/images/"
fileName = 'plot_1d';
relativePath = '../latex/Images/'; % the ../ goes up one folder
exportType = 'jpeg';
lineColours = 'blue';
nrOfDimensions = 2;
y_axis_label = ['$\displaystyle\frac{X{g_0}}{C_{eff}^2}$','Interpreter','latex']
axisLabels = ["example x axis label", y_axis_label];

y_series1_label = "halo orbit path of 3rd body";
y_series2_label = "another line"
y_series3_label = "third line"
legend = [y_series1_label;y_series2_label;y_series3_label];
legendLocation = 'best';
plotType = "lines";

% create custom axis scales
x_axis_scale = [0,10,-4,5];
y_axis_scale = ['a';'b';'c'];
z_axis_scale = [0:1:10];
axisScales.add(x_axis_scale);
axisScales.add(y_axis_scale);
axisScales.add(z_axis_scale); % can also do this in loop

% create x-series (can be as much as you like)
x_series1 = [1,2,3];
x_series2 = [2,3,4];
x_series.add(x_series1);
x_series.add(x_series2);

% create y-series (can be as much as you like)
y_series1 = [1,2,5];
y_series2 = [6,7,6];
y_series3 = [6,7,7];
y_series.add(y_series1);
y_series.add(y_series2);
y_series.add(y_series3);

% put data series in java ArrayList() object
dataSeries = java.util.ArrayList();
dataSeries.add(x_series);
dataSeries.add(y_series);



disp(dataSeries.get(0))

% create plot object containing all info for plot
plotData = PlotData(fileName,relativePath,exportType,...
        dataSeries,lineColours, nrOfDimensions,axisLabels,legend,...
        legendLocation, plotType,axisScales,currentFolder,latexDestination);

% plot the dataseries automatically to latex
obj_mult = PlotMultipleLines;
plot_altitudes(obj_mult,plotData);

    
%% ODE equations
function dalphadt = odefcn18_3(t,alpha,alpha_p,alpha_q, mu_p,mu_q,d_p,d_q,n)
    % declare and initialise parameters
    dalphadt = zeros(4,1);
    r_p = sqrt((alpha(1)-alpha_p(1))^2+(alpha(3)-alpha_p(2))^2);
    r_q = sqrt((alpha(1)-alpha_q(1))^2+(alpha(3)-alpha_q(2))^2);

    % Implement ODE
    dalphadt(1) = alpha(2);
    dalphadt(2) = -mu_p*((alpha(1) + d_p)/(r_p)^3) - mu_q*((alpha(1) - d_q)/((r_q)^3)) + 2*n*alpha(4) + n^2*alpha(1);
    dalphadt(3) = alpha(4);
    dalphadt(4) = -mu_p*((alpha(3))/(r_p)^3) - mu_q*((alpha(3))/((r_q)^3)) - 2*n*alpha(2) + n^2*alpha(3);
end