package repast.simphony.demos.sugarscape2.landscape.loaders;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p30;
import repast.simphony.demos.sugarscape2.landscape.rules.growback.GrowbackAbility;
import repast.simphony.demos.sugarscape2.products.ProductGridProperties;

public class ExcelLandscapeChapter2Loader implements LandscapeLoader {
	
	private String excelFile = "C:\\Users\\jkr\\Dropbox\\CurrentProjects\\Phd Proposal\\03. Work on progress\\Sugarscape Java Power for ABM\\sugarscape_data.Chapter2.xlsx";
	
	private String rules_root = "gr.kremmydas.sugarscape.landscape.rules.";
	
	private LandscapeChapter2_p30 ls;
	private Workbook excelWB; 
	
	@Override
	public LandscapeChapter2_p30 load() {
		try {
			excelWB = WorkbookFactory.create(new File(excelFile));
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		this.createGrid();
		this.loadSugar();
		return ls;
	}
	
	
	private void createGrid() {
		Sheet sh = this.excelWB.getSheet("landscape_properties");
		Iterator<Row> rowItr = sh.iterator(); 
		
		//create landscape
		Row row = rowItr.next(); 
		int x = (int)row.getCell(1).getNumericCellValue();
		row = rowItr.next(); 
		int y = (int)row.getCell(1).getNumericCellValue();
		this.ls = new LandscapeChapter2_p30();
		this.ls.init(x, y);
		
		//load growback rule
		row = rowItr.next(); 
		String gbr = row.getCell(1).getStringCellValue();
		try {
			this.ls.setGrowbackRule( (GrowbackAbility) Class.forName(rules_root+gbr).newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("Could not find class (" + this.getClass().toString()+ ")");
			e.printStackTrace();
		}
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
	
	
}
