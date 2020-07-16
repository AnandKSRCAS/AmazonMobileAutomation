package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigProperty {
	

	public static Properties readConfigProperty(String path) throws IOException 
	{ 
	    FileInputStream fis = null;
		Properties prop = null;
		
		try
		{
		fis = new FileInputStream(path);
		prop = new Properties();
		prop.load(fis);
		}
		
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch(IOException ioexception)
		{
			ioexception.printStackTrace();
		}
		finally
		{
		  fis.close();
		}
		return prop;
	}

}
