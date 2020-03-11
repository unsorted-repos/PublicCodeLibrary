package org.leores.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.leores.plot.JGnuplot;
import org.leores.plot.JGnuplot.Plot;
import org.leores.util.U;
import org.leores.util.able.Processable2;
import org.leores.util.data.DataTable;
import org.leores.util.data.DataTableSet;

public class CallDemo2 {
	Plot plot1;

	/**
	 * Set the working directory to be ${workspace_loc:leotask/demo} in Eclipse.
	 * In other IDEs set the working directory to the "demo" folder.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("This exports a single plot to the same dir as src with name plot2dOutput.png");
		U.tLog("Generating output plots.");
		U.tLog("------------------");
	
		CallDemo2 jgd = new CallDemo2();
//		jgd.plot2d();
		jgd.plot2d("test");
		System.out.println("Ran demo 2");
	}
	
	public CallDemo2() {
		prepPlot();
	}
	
	public void prepPlot() {
		plot1 = new Plot("plot1") {
			{
				xlabel = "x axis";
				ylabel = "y axis";
				extra2 = "set key top left";
			}
		};
	}
	
//	public static void plot2d() {
//		JGnuplot jg = new JGnuplot() {
//			{
//				terminal = "pngcairo enhanced dashed";
//				output = "plot2dOutput.png";
//			}
//		};
//		Plot plot = new Plot("") {
//			{
//				xlabel = "x";
//				ylabel = "y";
//			}
//		};
//		double[] x = { 1, 2, 3, 4, 5 }, y1 = { 2, 4, 6, 8, 10 }, y2 = { 3, 6, 9, 12, 15 };
//		DataTableSet dts = plot.addNewDataTableSet("2D Plot Title");
//		dts.addNewDataTable("y=2x dataseries1", x, y1);
//		dts.addNewDataTable("y=3x dataseries2", x, y2);
//		System.out.println("The string command ="+jg.plot2d);
//		jg.execute(plot, jg.plot2d);
//	}
	
	public static void dataToPlot2d(double[] x, double[][] y,String plotTitle,final String xAxisLabel, final String yAxisLabel,String xLegend, String[] yLegend,final String plotType,final String plotFileName) {
		JGnuplot jg = new JGnuplot() {
			{
				//terminal = "pngcairo enhanced dashed";
				terminal = plotType;
				output = plotFileName+".png";
			}
		};
		Plot plot = new Plot("") {
			{
				xlabel = xAxisLabel;
				ylabel = yAxisLabel;
			}
		};
		
		DataTableSet dts = plot.addNewDataTableSet(plotTitle);
		for (int ySeries = 0; ySeries <y.length; ySeries++) {
			System.out.println("y="+y[0]);
			dts.addNewDataTable(yLegend[ySeries], x, y[ySeries]);
		}
		
		System.out.println("The command ="+jg.plot2d);
		
		jg.execute(plot, jg.plot2d);
	}
	
	public void plot2d(String test) {
		JGnuplot jg = new JGnuplot() {
			{
				terminal = "pngcairo enhanced dashed";
				output = "plot2dOutput.png";
			}
		};
		Plot plot = new Plot("") {
			{
				xlabel = "x";
				ylabel = "y";
			}
		};
		double[] x = { 1, 2, 3, 4, 5 }, y1 = { 2, 4, 6, 8, 10 }, y2 = { 3, 6, 9, 12, 15 };
		DataTableSet dts = plot.addNewDataTableSet("2D Plot Title");
		dts.addNewDataTable("y=2x dataseries1", x, y1);
		dts.addNewDataTable("y=3x dataseries2", x, y2);
		System.out.println("The string command ="+jg.plot2d);
		jg.execute(plot, jg.plot2d);
	}
}
