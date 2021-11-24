package Chips_001_기본기능;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 기본기능_02_홈화면최초진입 extends Chips_TestCase {
	

	@Test(description = "칩스 리그레이션 TC : 실행_0000")
	public void TC_0000_앱실행과AccessToken얻기(Method method) throws Exception {

		test.log(Status.INFO, "퍼미션 오디오 녹음 권한 허용 (ADB MiC permission On)");
		adb.ChipsApp_permission_MIC_On(udid);
		
		test.log(Status.INFO, "퍼미션 위치 권한 허용 (ADB LOCATION permission On)");
		adb.ChipsApp_permission_LOCATION_On(udid);
		
		test.log(Status.INFO, "AppActivity으로 화면 확인");
		util.context("NATIVE_APP");
		
		//test.log(Status.INFO, "Chips 업데이트 팝업 확인");
	    //util.chips_update_check(ServerName);
		
		test.log(Status.INFO, "접근권한 허용 버튼 클릭");
		util.click(By.xpath(xPath.접근권한허용버튼));
	    
	    test.log(Status.INFO, "NUGU CHIPS 빠른 설정 가이드 [X]버튼 닫기 "); 
	    util.click(By.id("btn_close"));
	    Thread.sleep(5000);
	    
	    test.log(Status.INFO, "세션 만료 후 로그인 시도"); 
	    util.click(By.id("loginButton"));
	    Thread.sleep(1000);
	    
	    test.log(Status.INFO, "WEBVIEW로 화면 전환");
        util.switchContext("WEBVIEW");
        
        test.log(Status.INFO, "저장된 간편로그인 유효성 체크 및 클릭");
        String id = util.getText(By.xpath("//ul[@class='account-list']/li[1]/a/span[1]"));
        if (id.equals("nuguqa001@sk.com")) {
        	util.click(By.xpath(xPath.간편로그인_1st));
        	System.out.println("[일치] 로그인id : nuguqa001@sk.com");
        } else {
        	util.click(By.xpath(xPath.간편로그인_2st));
        	System.out.println("[불일치] 로그인id : nuguqa001@sk.com");
        }
	    
	    test.log(Status.INFO, "NATIVE로 화면 확인");
	    util.context("NATIVE_APP");
	    util.ProgressBar_Loading();
	    
	    test.log(Status.INFO, "위치 서비스 사용 설정 안내 팝업 확인"); 
		boolean coach_mark = util.isElementPresent(By.id("negativeButton"));
		if(coach_mark == true) {
			util.click(By.id("negativeButton"));
			test.log(Status.INFO, "위치 서비스 사용 설정 안내 팝업 취소 클릭"); 
			System.out.println("위치 서비스 사용 설정 안내 팝업 취소 클릭");
		} else { 
			Thread.sleep(1000);
		}
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0138")
	public void TC_0138_홈화면최초진입확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "CHIPS 로고/설정 노출 확인"); 
		Thread.sleep(2000);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("commonAppBarLayout")));
		
		test.log(Status.INFO, "현재 기온 노출 확인"); 
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature")));
		
		test.log(Status.INFO, "현재 위치 노출 확인"); 
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("location")));
		
		test.log(Status.INFO, "날씨별 캐릭터 노출 확인"); 
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("weatherImageView")));
		
		test.log(Status.INFO, "CHIPS 예문 노출 확인"); 
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));
		
		test.log(Status.INFO, "호출어 안내 코치 마크 노출 확인"); 
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("coach_mark")));
		
		test.log(Status.INFO, "호출어 안내 코치 마크 텍스트 확인 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.코치마크문구), "‘아리아’라고 불러보세요!"));
	}

}
