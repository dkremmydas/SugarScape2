package gr.kremmydas.sugarscape.loaders.agents;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentProperties;
import gr.kremmydas.sugarscape.agents.rules.consumption.ConsumeAbility;
import gr.kremmydas.sugarscape.agents.rules.movement.MoveAbility;
import gr.kremmydas.sugarscape.agents.rules.vision.VisionAbility;
import gr.kremmydas.sugarscape.products.ProductAgentProperties;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelAgentLoader implements AgentLoader {
	
	private String excelFile = "C:\\Users\\jkr\\Dropbox\\CurrentProjects\\Phd Proposal\\03. Work on progress\\SugarScape Sensitivity\\sugarscape_data.xlsx";
	
	private Workbook excelWB; 
	
	public ExcelAgentLoader() {
		try {
			excelWB = WorkbookFactory.create(new File(excelFile));
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAgents(SimulationContext sc)  {
		String rulesBase = "gr.kremmydas.sugarscape.rules.";
		
		Sheet sh = this.excelWB.getSheet("agents");
		Iterator<Row> rowItr = sh.iterator(); 
		Row row = rowItr.next(); //skip header row
		
		while(rowItr.hasNext()) {
			row = rowItr.next();
			
			int id = (int) row.getCell(0).getNumericCellValue();
			int initSugar = (int)row.getCell(1).getNumericCellValue();
			int metabSugar = (int)row.getCell(2).getNumericCellValue();
			int vis = (int)row.getCell(5).getNumericCellValue();
			int x = (int)row.getCell(6).getNumericCellValue();
			int y = (int)row.getCell(7).getNumericCellValue();
			String vr = row.getCell(8).getStringCellValue();
			String mr = row.getCell(9).getStringCellValue();
			String cr = row.getCell(10).getStringCellValue();
			
			Agent a;
			try {
				a = new Agent((ConsumeAbility) Class.forName(rulesBase+cr).newInstance(),
						(MoveAbility) Class.forName(rulesBase+vr).newInstance(), 
						(VisionAbility) Class.forName(rulesBase+mr).newInstance(), 
						x, y, 
						new ProductAgentProperties(initSugar, metabSugar), 
						null,
						new AgentProperties(vis)
				);
				a.setId(id);
				sc.add(a);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}	

	}

}
