package gr.kremmydas.sugarscape.loaders.landscapes;

import gr.kremmydas.sugarscape.landscape.Landscape;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import repast.simphony.valueLayer.GridValueLayer;

public class ExcelLandscapeLoader implements LandscapeLoader {
	
	private String excelFile = "C:\\Users\\jkr\\Dropbox\\CurrentProjects\\Phd Proposal\\03. Work on progress\\SugarScape Sensitivity\\sugarscape_data.xlsx";
	
	private Landscape ls;
	private Workbook excelWB; 
	
	@Override
	public Landscape load() {
		try {
			excelWB = WorkbookFactory.create(new File(excelFile));
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

		this.createGrid();
		this.loadPepper();
		this.loadSugar();
		return ls;
	}
	
	
	private void createGrid() {
		Sheet sh = this.excelWB.getSheet("landscape_properties");
		Iterator<Row> rowItr = sh.iterator(); 
		Row row = rowItr.next(); 
		int x = (int)row.getCell(1).getNumericCellValue();
		row = rowItr.next(); 
		int y = (int)row.getCell(1).getNumericCellValue();
		this.ls = new Landscape(x, y);
	}
	
	private void loadSugar() {
		Sheet sh = this.excelWB.getSheet("landscape_sugar");
		this.ls.setSugar(new GridValueLayer("Sugar", true, this.ls.getDimensions().toIntArray(null)));
		for(int x=0;x<this.ls.getDimensions().getWidth(); x++) {
			for(int y=0;y<this.ls.getDimensions().getHeight(); y++) {
				int s = (int)sh.getRow(y).getCell(x).getNumericCellValue();
				this.ls.getSugar().set((double)s, x,y);
			}
		}
	}
	
	private void loadPepper() {
		Sheet sh = this.excelWB.getSheet("landscape_pepper");
		this.ls.setSugar(new GridValueLayer("Pepper", true, this.ls.getDimensions().toIntArray(null)));
		for(int x=0;x<this.ls.getDimensions().getWidth(); x++) {
			for(int y=0;y<this.ls.getDimensions().getHeight(); y++) {
				int s = (int)sh.getRow(y).getCell(x).getNumericCellValue();
				this.ls.getSugar().set((double)s, x,y);
			}
		}
	}

}
