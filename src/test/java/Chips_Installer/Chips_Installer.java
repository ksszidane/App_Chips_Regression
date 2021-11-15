package Chips_Installer;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestNG_Set.App_Installer;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class Chips_Installer extends App_Installer {
	
	//private static String filePath = "C:\\Users\\user\\.jenkins\\workspace\\Chips_Installer\\apk\\nugu-friends-engRTG-vc10101-v1.1.1.apk"; //RTG빌드
	private static String filePath = "C:\\Users\\user\\.jenkins\\workspace\\Chips_Installer\\apk\\nugu-friends-eng-vc10101-v1.1.1.apk"; //STG빌드
	
	
	@Test(description = "인스톨러 ")
	public void Chips_Installer_실행(Method method) throws Exception {
		
		adb.runCommand("adb -s " + udid + " install -r " + filePath);
		System.out.println("adb -s " + udid + " install -r " + filePath);
		Thread.sleep(1000);

	}

}
