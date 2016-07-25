package gr.kremmydas.sugarscape.loaders.landscape;

import gr.kremmydas.sugarscape.landscape.Landscape;
import gr.kremmydas.sugarscape.products.ProductGridProperties;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

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
		this.loadGridPoints();
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
		Sheet sh_init = this.excelWB.getSheet("landscape_sugar_init");
		Sheet sh_cap = this.excelWB.getSheet("landscape_sugar_capacity");
		Sheet sh_reg = this.excelWB.getSheet("landscape_sugar_regeneration");
		
		ProductGridProperties pgp = this.ls.getSugarGridProperties();
		
		
		for(int x=0;x<this.ls.getDimensions().getWidth(); x++) {
			for(int y=0;y<this.ls.getDimensions().getHeight(); y++) {
				int s1 = (int)sh_init.getRow(y).getCell(x).getNumericCellValue();
				int s2 = (int)sh_cap.getRow(y).getCell(x).getNumericCellValue();
				int s3 = (int)sh_reg.getRow(y).getCell(x).getNumericCellValue();
				pgp.getCurrentQuantity().set((double)s1, x,y);
				pgp.getCapacity().set((double)s2, x,y);
				pgp.getRegenerationRate().set((double)s3, x,y);
			}
		}
	}
	
	private void loadPepper() {
		Sheet sh_init = this.excelWB.getSheet("landscape_pepper_init");
		Sheet sh_cap = this.excelWB.getSheet("landscape_pepper_capacity");
		Sheet sh_reg = this.excelWB.getSheet("landscape_pepper_regeneration");
		
		ProductGridProperties pgp = this.ls.getSugarGridProperties();
		
		
		for(int x=0;x<this.ls.getDimensions().getWidth(); x++) {
			for(int y=0;y<this.ls.getDimensions().getHeight(); y++) {
				int s1 = (int)sh_init.getRow(y).getCell(x).getNumericCellValue();
				int s2 = (int)sh_cap.getRow(y).getCell(x).getNumericCellValue();
				int s3 = (int)sh_reg.getRow(y).getCell(x).getNumericCellValue();
				pgp.getCurrentQuantity().set((double)s1, x,y);
				pgp.getCapacity().set((double)s2, x,y);
				pgp.getRegenerationRate().set((double)s3, x,y);
			}
		}
	}
	
	private void  loadGridPoints() {
		
	}

}
