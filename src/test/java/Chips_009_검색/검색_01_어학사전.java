package Chips_009_검색;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 검색_01_어학사전 extends Chips_TestCase {
	
	String AccessToken;
	
	public void accessToken얻기() throws Exception {
		
		test.log(Status.INFO, "coach_mark_close 클릭"); 
		boolean coach_mark = util.isElementPresent(By.id("coach_mark_close"));
		if(coach_mark == true) {
			util.click(By.id("coach_mark_close"));
		} else { 
			Thread.sleep(1000);
		}
		
		test.log(Status.INFO, "chips 선택"); 
	    util.click(By.xpath(xPath.chips_1st));
	    
	    util.ProgressBar_Loading();
	  
	    test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    
	    test.log(Status.INFO, "transaction id 얻기"); 
	    String tid = util.TransactionID_JsonParsing(nuguqa001, Chips_001, ServerName, Service);
	    
	    test.log(Status.INFO, "acceesToken 얻기"); 
	    String actn = util.acceesToken_JsonParsing(ServerName, Place, tid);
	    
	    test.log(Status.INFO, "acceesToken : " + actn); 
	    System.out.println("토큰 값 : " + actn);
	    AccessToken = actn;
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0000")
	public void TC_0000_앱실행과AccessToken얻기(Method method) throws Exception {

		test.log(Status.INFO, "퍼미션 오디오 녹음 권한 허용 (ADB MiC permission On)");
		adb.ChipsApp_permission_MIC_On(udid);
		
		test.log(Status.INFO, "퍼미션 위치 권한 허용 (ADB LOCATION permission On)");
		adb.ChipsApp_permission_LOCATION_On(udid);
		
		test.log(Status.INFO, "AppActivity으로 화면 확인");
		util.switchToNative();
	    
	    //test.log(Status.INFO, "Chips 업데이트 팝업 확인");
	    //util.chips_update_check(ServerName);
		
		test.log(Status.INFO, "접근권한 허용 버튼 클릭");
		util.click(By.xpath(xPath.접근권한허용버튼));
	    
	    test.log(Status.INFO, "NUGU CHIPS 빠른 설정 가이드 [X]버튼 닫기 "); 
	    util.click(By.id("btn_close"));
	    Thread.sleep(5000);
	    
	    test.log(Status.INFO, "세션 만료 후 로그인 시도"); 
	    util.click(By.id("loginButton"));
	    Thread.sleep(5000);
	    
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
	    util.switchToNative();
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
	    
	    accessToken얻기();
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0897")
	public void TC_0897_Chips_어학사전_도움말_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 한영사전 - 발화");
		util.SWFsendPost("한영사전", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "한영사전 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "궁금한 단어를 찾아보시려면 \"영어로 사과가 뭐야\" 라고 말씀해 주세요."));
		
		test.log(Status.INFO, "한영사전 도메인확인 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "general"));
		
		test.log(Status.INFO, "한영사전 인텐트 확인 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "ask.help"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0898")
	public void TC_0898_Chips_어학사전_도움말_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 어학사전 - 발화");
		util.SWFsendPost("어학사전", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "어학사전 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "궁금한 단어를 찾아보시려면 \"영어로 사과가 뭐야\" 라고 말씀해 주세요."));
		
		test.log(Status.INFO, "어학사전 도메인확인 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "general"));
		
		test.log(Status.INFO, "어학사전 인텐트 확인 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "ask.help"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0899")
	public void TC_0899_Chips_어학사전_단어번역_확인(Method method) throws Exception {
		
		util.switchToNative();
		Thread.sleep(3000);
		test.log(Status.INFO, "W, 영어로 경찰이 뭐야? - 발화");
		util.SWFsendPost("영어로 경찰이 뭐야", Chips_001, ServerName, AccessToken);
		util.SWFsendPost("영어로 경찰이 뭐야", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "어학사전 카드 원문 노출 확인");
		util.switchToContextName("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.한글소스_web), "경찰"));
		
		test.log(Status.INFO, "어학사전 카드 원문 노출 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.영문번역_web), "police"));
		
		test.log(Status.INFO, "영어로 경찰이 뭐야? TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "경찰은 영어로"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0900")
	public void TC_0900_Chips_어학사전_문장번역_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 너는 천재야를 영어로 해줘 - 발화");
		util.SWFsendPost("너는 천재야를 영어로 해줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(1000);
		
		test.log(Status.INFO, "어학사전 카드 원문 노출 확인");
		util.switchToContextName("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.한글소스_web), "너는 천재야"));
		
		test.log(Status.INFO, "어학사전 카드 원문 노출 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.영문번역_web), "you are a genius"));
		
		test.log(Status.INFO, "너는 천재야를 영어로 해줘 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "너는 천재야는 영어로"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0899")
	public void TC_0903_Chips_어학사전_문장번역_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 불어로 사과가 뭐야 - 발화");
		util.SWFsendPost("불어로 사과가 뭐야", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "불어로 사과가 뭐야 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "지금은 영어 단어 검색과 번역만 해드릴 수 있어요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0899")
	public void TC_0904_Chips_어학사전_문장번역_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 불어로 사과가 뭐야 - 발화");
		util.SWFsendPost("불어로 사과가 뭐야", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "불어로 사과가 뭐야 해줘 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service, "지금은 영어 단어 검색과 번역만 해드릴 수 있어요."));
		
	}

}
