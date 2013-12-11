package com.ccc.sendalyzeit.textanalytics.algorithms.deeplearning.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.jblas.DoubleMatrix;
import org.springframework.core.io.ClassPathResource;

import com.ccc.sendalyzeit.deeplearning.berkeley.Pair;


public class IrisUtils {

	public static Pair<DoubleMatrix,DoubleMatrix> loadIris() throws IOException {
		ClassPathResource resource = new ClassPathResource("/iris.dat");
		List<String> lines = IOUtils.readLines(resource.getInputStream());
		Collections.shuffle(lines);
		Collections.rotate(lines, 3);
		
		DoubleMatrix ret = DoubleMatrix.ones(lines.size(), 4);
		List<String> outcomeTypes = new ArrayList<String>();
	    double[][] outcomes = new double[lines.size()][3];
		for(int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] split = line.split(",");
			
			addRow(ret,i,split);
			
			String outcome = split[split.length - 1];
			if(!outcomeTypes.contains(outcome))
				outcomeTypes.add(outcome);
			double[] rowOutcome = new double[3];
			rowOutcome[outcomeTypes.indexOf(outcome)] = 1;
			outcomes[i] = rowOutcome;
		}
		return new Pair<>(ret,new DoubleMatrix(outcomes));
	}
	
	
	public static Pair<DoubleMatrix,DoubleMatrix> loadIris(int rows) throws IOException {
		ClassPathResource resource = new ClassPathResource("/iris.dat");
		List<String> lines = IOUtils.readLines(resource.getInputStream());
		Collections.shuffle(lines);
		Collections.rotate(lines, 3);
		Random rand = new Random(1);
		DoubleMatrix ret = DoubleMatrix.ones(rows, 4);
		List<String> outcomeTypes = new ArrayList<String>();
	    double[][] outcomes = new double[rows][3];
		for(int i = 0; i < rows; i++) {
			String line = i >= lines.size() ? lines.get(rand.nextInt(lines.size())) : lines.get(i);
			String[] split = line.split(",");
			
			addRow(ret,i,split);
			
			String outcome = split[split.length - 1];
			if(!outcomeTypes.contains(outcome))
				outcomeTypes.add(outcome);
			double[] rowOutcome = new double[3];
			rowOutcome[outcomeTypes.indexOf(outcome)] = 1;
			outcomes[i] = rowOutcome;
		}
		return new Pair<>(ret,new DoubleMatrix(outcomes));
	}

	
	private static void addRow(DoubleMatrix ret,int row,String[] line) {
		double[] vector = new double[4];
		for(int i = 0; i < 4; i++) 
			vector[i] = Double.parseDouble(line[i]);
		
		ret.putRow(row,new DoubleMatrix(vector));
	}
}
