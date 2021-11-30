package ADB_Reboot;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestNG_Set.App_Installer;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class Chips_Reboot extends App_Installer {
	
	@Test(description = "단말기 리부트")
	public void Chips_Reboot_실행(Method method) throws Exception {
		
		adb.runCommand("adb -s " + udid + " reboot");
		System.out.println("adb -s " + udid + " reboot");
		Thread.sleep(1000);

	}

}
