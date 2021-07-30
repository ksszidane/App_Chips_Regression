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

		test.log(Status.INFO, "AppActivity으로 화면 확인");
		util.context("NATIVE_APP");
		
		test.log(Status.INFO, "접근권한 허용 버튼 클릭");
		util.click(By.xpath(xPath.접근권한허용버튼));
		
		test.log(Status.INFO, "퍼미션 오디오 녹음 권한 허용 "); 
	    util.switchTo().alert().accept();
	    
	    test.log(Status.INFO, "퍼미션 위치 권한 허용 "); 
	    util.switchTo().alert().accept();
	    
	    test.log(Status.INFO, "NUGU CHIPS 빠른 설정 가이드 [X]버튼 닫기 "); 
	    util.click(By.id("btn_close"));
	    
	    test.log(Status.INFO, "세션 만료 후 로그인 시도"); 
	    util.click(By.id("loginButton"));
	    
	    test.log(Status.INFO, "WEBVIEW로 화면 전환");
        util.switchContext("WEBVIEW");
        
        test.log(Status.INFO, "저장된 간편로그인 유효성 체크 및 클릭");
	    util.click(By.xpath(xPath.간편로그인_1st));
	    
	    test.log(Status.INFO, "NATIVE로 화면 확인");
	    util.context("NATIVE_APP");
	    util.ProgressBar_Loading();
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0138")
	public void TC_0138_홈화면최초진입확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "CHIPS 로고/설정 노출 확인"); 
		Thread.sleep(2000);
		boolean commonAppBarLayout = util.isElementPresent(By.id("commonAppBarLayout"));
		Assert.assertTrue(commonAppBarLayout);
		
		test.log(Status.INFO, "현재 기온 노출 확인"); 
		boolean temperature = util.isElementPresent(By.id("temperature"));
		Assert.assertTrue(temperature);
		
		test.log(Status.INFO, "현재 위치 노출 확인"); 
		boolean location = util.isElementPresent(By.id("location"));
		Assert.assertTrue(location);
		
		test.log(Status.INFO, "날씨별 캐릭터 노출 확인"); 
		boolean weatherImageView = util.isElementPresent(By.id("weatherImageView"));
		Assert.assertTrue(weatherImageView);
		
		test.log(Status.INFO, "CHIPS 예문 노출 확인"); 
		boolean chipListView = util.isElementPresent(By.id("chipListView"));
		Assert.assertTrue(chipListView);
		
		test.log(Status.INFO, "호출어 안내 코치 마크 노출 확인"); 
		boolean coach_mark = util.isElementPresent(By.id("coach_mark"));
		Assert.assertTrue(coach_mark);
		
		test.log(Status.INFO, "호출어 안내 코치 마크 텍스트 확인 확인");
		String 코치마크문구 = util.getText(By.xpath(xPath.코치마크문구));
		Assert.assertTrue(코치마크문구.contains("‘아리아’라고 불러보세요!"));
	}

}
