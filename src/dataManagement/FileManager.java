package dataManagement;

import java.io.*;
public class FileManager {
	
	//VARIABLES
	private BufferedReader br;
	private BufferedWriter writer;

	//CONSTRUCTORS
	public FileManager(){
		
	}
	
	//METHODS
	//Method to retrieve the necessary data from file system.
	public String getConfig(){
		try{
			FileInputStream fstream = new FileInputStream("config.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			String strLine = "";
			strLine += br.readLine();
			return strLine;
			}catch (Exception e){//Catch exception if any
				return "Error: " + e.getMessage();
			}
	}
	//Method to save the necessary data to the file system.
	public void setConfig( String s) throws FileNotFoundException{
		try{
			writer = new BufferedWriter( new FileWriter( "config.txt"));
		    writer.write( s);
		}catch ( Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		finally{
		    try{
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e){
		    }
		}
	}
}
