package com.bsb.hike.update_test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class TestClass {
	public static void main(String[] args)
	{
		File folder = new File("/Users/yashpreet/.jenkins/jobs/internal/workspace/build/outputs/apk");
		System.out.println(folder);

		File str = new File("/Users/yashpreet/.jenkins/jobs/internal/workspace/build/outputs/apk");
		File[] listOfOldBuilds = folder.listFiles();
		ArrayList<String> newBuildNames = new ArrayList<String>();
		for (int i = 0; i < listOfOldBuilds.length; i++) {
			if (listOfOldBuilds[i].isFile()) {

				String buildName =listOfOldBuilds[i].getName();
				String newBuildName = listOfOldBuilds[i].getName();
				if (newBuildName.contains("android-client-arm-debug-")) {
					System.out.println("line...."+newBuildName);
					newBuildName = newBuildName.split("-")[4];
					newBuildName = newBuildName.replace("_", ".");
					System.out.println("newwwww..."+newBuildName);
					newBuildNames.add(newBuildName);		
					String newBuildName2 = buildName.replace(buildName, "android-client-"+newBuildName+".apk");

					//					newBuildName.renameTo(newBuildName2);
					//					File old_file=new File(str+"/"+buildName);
					//					File new_file=new File(newBuildName2);
					//					old_file.renameTo(new_file);
					//					System.out.println(newBuildName2);

					String newFilePath = listOfOldBuilds[i].getAbsolutePath().replace(listOfOldBuilds[i].getName(), "") + newBuildName2;
					File oldFile = new File(listOfOldBuilds[i].getAbsolutePath());
					File newFile = new File(newFilePath);
					System.out.println(listOfOldBuilds[i].getAbsolutePath());
					oldFile.renameTo(newFile);

					System.out.println(newFile.getAbsolutePath());
					System.out.println(newFile.getName());
				}
			}
		}
	}
}
