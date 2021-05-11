package Chips_001_실행;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import junit.framework.Assert;
import unit.Chips_TestCase;

public class 실행과로그인테스트 extends Chips_TestCase {
	
	public void 로그인() throws Exception {

		test.log(Status.INFO, "실행");
        Thread.sleep(10000);

	}
    
}
