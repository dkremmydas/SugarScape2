package gr.kremmydas.sugarscape.loaders.agents;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.Landscape;

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
	public void addAgents(SimulationContext sc) {
		Sheet sh = this.excelWB.getSheet("agents");
		Iterator<Row> rowItr = sh.iterator(); 
		Row row = rowItr.next(); //skip header row
		
		while(rowItr.hasNext()) {
			row = rowItr.next();
			
			
			Agent a = new Agent(cr, mr, vr, x, y, sugarProperties, pepperProperties);
			sc.add(a);
		}	

	}

}
