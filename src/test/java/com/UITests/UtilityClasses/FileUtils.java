package com.UITests.UtilityClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.tools.ant.DirectoryScanner;

public class FileUtils {
	
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		boolean fileExists = file.exists();

		if(fileExists){
			try{
				boolean fileDeleted = file.delete();

				if (fileDeleted) {
					System.out.println("File " + filePath + " has been deleted");
				}
				else {
					System.out.println("File found but not deleted");
				}
			}
			catch(Exception e){
				
				e.printStackTrace()   ;
			}
		}
		else {
			System.out.println("File does not exist");
		}
	}

	public static String getFileLastModified(String partialMatch, String baseDir) {
		String separator = System.getProperty("file.separator");
		
		DirectoryScanner scanner = new DirectoryScanner();
		baseDir = (baseDir.substring(baseDir.length()-1) != separator) ? baseDir + separator : baseDir;
		String[] includes = {partialMatch};
		scanner.setIncludes(includes);
		scanner.setBasedir(baseDir);
		scanner.setCaseSensitive(true);
		scanner.scan();
		String[] files = scanner.getIncludedFiles();

		if (files == null || files.length == 0) {
			throw new NullPointerException("No files found!");
		}

		File lastModifiedFile = new File(baseDir + files[0]);
		for (String file : files) {
			File nextFile = new File(baseDir + file);
			if (lastModifiedFile.lastModified() < nextFile.lastModified()) {
				lastModifiedFile = nextFile;
			}
		}

		return lastModifiedFile.getName();
	}
	
	public static void replaceTextInFile(String filePath, String textToReplace, String replacementText) {
		File fileToBeModified = new File(filePath);        
        StringBuilder oldContent = new StringBuilder("");         
        BufferedReader reader = null;         
        FileWriter writer = null;
        
        try
        {
        	 reader = new BufferedReader(new FileReader(fileToBeModified));
        	 String line = reader.readLine();
        	 
        	 while (line != null) 
             {
                 oldContent.append(line + System.lineSeparator());                 
                 line = reader.readLine();
             }
        	 
        	 
        	 String newContent = oldContent.toString().replaceAll(textToReplace, replacementText);
        	 writer = new FileWriter(fileToBeModified);            
             writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
        	try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
                   
        
        }
        
	}
	
	public static void copyFile(String sourcePath, String destPath) {
		File source = new File(sourcePath);
		File dest = new File(destPath);
		try {
			org.apache.commons.io.FileUtils.copyFile(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	

}
