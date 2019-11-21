package repast.simphony.demos.sugarscape2.agents.loaders;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.AgentChapter2_p30;
import repast.simphony.demos.sugarscape2.agents.rules.death.DeathAbility;
import repast.simphony.demos.sugarscape2.agents.rules.gathering.GatheringAbility;
import repast.simphony.demos.sugarscape2.agents.rules.metabolism.MetabolismAbility;
import repast.simphony.demos.sugarscape2.agents.rules.movement.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.rules.vision.VisionAbility;
import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p30;
import repast.simphony.demos.sugarscape2.products.ProductAgentProperties;

public class ExcelAgentChapter2Loader implements AgentLoader {
	
	private String excelFile = "C:\\Users\\jkr\\Dropbox\\CurrentProjects\\Phd Proposal\\03. Work on progress\\Sugarscape Java Power for ABM\\sugarscape_data.Chapter2.xlsx";
	
	private Workbook excelWB; 
	
	public ExcelAgentChapter2Loader() {
		try {
			excelWB = WorkbookFactory.create(new File(excelFile));
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAgents(SimulationContext sc)  {
		String rulesBase = "gr.kremmydas.sugarscape.agents.rules.";
		
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
			String dr = row.getCell(11).getStringCellValue();
			String gr = row.getCell(12).getStringCellValue();
			

			try {
				
				AgentChapter2_p30 a = new AgentChapter2_p30.Builder(Integer.valueOf(id))
						.onLandscape((LandscapeChapter2_p30) sc.getLandscape())
						.atLocationX(x)
						.atLocationY(y)
						.withVisionLevel(vis)
						.withMetabolismRule((MetabolismAbility) Class.forName(rulesBase+cr).newInstance())
						.withGatheringRule((GatheringAbility) Class.forName(rulesBase+gr).newInstance())
						.withDeathRule((DeathAbility) Class.forName(rulesBase+dr).newInstance())
						.withMovementRule((MovementAbility) Class.forName(rulesBase+mr).newInstance())
						.withVisionRule((VisionAbility) Class.forName(rulesBase+vr).newInstance())
						.withSugarProperties(new ProductAgentProperties(initSugar, metabSugar))
						.build();
						
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
