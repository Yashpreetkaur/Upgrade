package com.bsb.hike.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;




public class ExecuteShell {


	public String ExecuteShellCommand(String ... parameters){

		String command= "";

		for(int counter=0; counter < parameters.length ; counter++){
			command = command + parameters[counter] + " ";
		}
		System.out.println("COMMAND "+ command);
		return executeCommand(command);
	}

	private String executeCommand(String command) {
System.out.println("private");
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			System.out.println("Testing");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));		
			String line = "";			
			while ((line = error.readLine())!= null) {
				output.append(line + "\n");
			}		
		} catch (Exception e) {
			System.out.println("Unable to Execute Command "+ command);
			e.printStackTrace();
		}
		return output.toString();     
	}
	
	public String executeCommands(String command) {
		System.out.println("public");

		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String line = "";			
			while ((line = input.readLine())!= null) {
				output.append(line + "\n");
			}
		} catch (Exception e) {
			System.out.println("Unable to Execute Command "+ command);
			e.printStackTrace();
		}
		System.out.println(output.toString());
		return output.toString();
	}
}
