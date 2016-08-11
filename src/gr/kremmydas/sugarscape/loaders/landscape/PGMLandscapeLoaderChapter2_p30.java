package gr.kremmydas.sugarscape.loaders.landscape;



import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.Landscape;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import gr.kremmydas.sugarscape.landscape.rules.growback.GrowbackAbility;
import gr.kremmydas.sugarscape.products.ProductGridProperties;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.RandomGridAdder;


public class PGMLandscapeLoaderChapter2_p30 implements LandscapeLoader {
	
	private String inputFile = "./data/sugarspace.pgm";
	private PGMReader pgmreader;

	public PGMLandscapeLoaderChapter2_p30() {
		pgmreader = new PGMReader(inputFile);
	}
	


	@Override
	public Landscape load() {
		LandscapeChapter2_p30 ls  = new LandscapeChapter2_p30(pgmreader.xSize, pgmreader.ySize);
		GrowbackAbility ga;
		try {
			ga = (GrowbackAbility) Class.forName(RunEnvironment.getInstance().getParameters().getString("growbackRuleClass")).newInstance();
			ls.setGrowbackRule(ga);
		} catch (InstantiationException e) {
			e.printStackTrace();
			RunEnvironment.getInstance().endRun();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			RunEnvironment.getInstance().endRun();
		}
		
		
		ProductGridProperties pgp = ls.getSugarGridProperties();
		for(int x=0;x<ls.getDimensions().getWidth(); x++) {
			for(int y=0;y<ls.getDimensions().getHeight(); y++) {
				int s1 = (int)pgmreader.getMatrix()[x][y];
				int s2 = (int)pgmreader.getMatrix()[x][y];
				pgp.getCurrentQuantity().set((double)s1, x,y);
				pgp.getCapacity().set((double)s2, x,y);
				pgp.getRegenerationRate().set(
						(double)RunEnvironment.getInstance().getParameters().getInteger("regenerationRate"), 
						x,y);
			}
		}
		ls.getGrid().setAdder(new RandomGridAdder<Agent>());
		
		return ls;
	}
	
	
	

	/**
	 * Class for reading PGM files into a 2D array.
	 * From intiial Sugarscape-repast-demo implementation
	 *
	 * @author Eric Tatara
	 * @author Nick Collier
	 * @version 
	 */

	class PGMReader {

		protected int matrix[][];
		protected int xSize;
		protected int ySize;

		public PGMReader(String sugarFile) {
			
			java.io.InputStream stream = null;
			try {
				stream = new FileInputStream(sugarFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
			BufferedReader in = new BufferedReader(new InputStreamReader(stream));

			init(in);
		}

		private void init(BufferedReader in) {
			try {
				StringTokenizer tok;

				String str = in.readLine();

				if (!str.equals("P2")) {
					throw new UnsupportedEncodingException("File is not in PGM ascii format");
				}

				str = in.readLine();
				tok = new StringTokenizer(str);
				xSize = Integer.valueOf(tok.nextToken()).intValue();
				ySize = Integer.valueOf(tok.nextToken()).intValue();

				matrix = new int[xSize][ySize];

				in.readLine();

				str = "";
				String line = in.readLine();

				while (line != null) {
					str += line + " ";
					line = in.readLine();
				}
				in.close();

				tok = new StringTokenizer(str);

				for (int i = 0; i < xSize; i++) 
					for (int j = 0; j < ySize; j++) 
						matrix[i][j] = Integer.valueOf(tok.nextToken());
				
			} catch (IOException ex) {
				System.out.println("Error Reading image file");
				ex.printStackTrace();
				System.exit(0);
			}
		}

		public int[][] getMatrix() {
			return matrix;
		}
	}







}
