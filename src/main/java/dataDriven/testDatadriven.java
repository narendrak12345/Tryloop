package dataDriven;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

public class testDatadriven {

	public String readCsv(String path) {

		try {

			FileReader filereader = new FileReader(path);

			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord;

			while ((nextRecord = csvReader.readNext()) != null) {
				for (String cell : nextRecord) {
					return cell;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("Something went wrong. Path to file is wrong or cell not found.");
	}
}