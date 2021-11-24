package Chips_004_생활정보;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_02_감성대화 extends Chips_TestCase {
	
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
	    String tid = util.TransactionID_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
	    
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
	    
	    accessToken얻기();
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0710")
	public void TC_0710_Chips_감성대화_안부인사_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 안녕 - 발화");
		util.SWFsendPost("안녕", ServerName, AccessToken);
		
		test.log(Status.INFO, "chitchatView / motionView 카드 노출 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chitchatView")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("motionView")));
		
		test.log(Status.INFO, "chitchat Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "chitchat"));
		
		test.log(Status.INFO, "greet_001 intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "greet_001"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0711")
	public void TC_0711_Chips_감성대화_이벤트절기상황1_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 월요일이 또 왔어 - 발화");
		util.SWFsendPost("월요일이 또 왔어", ServerName, AccessToken);
		
		test.log(Status.INFO, "chitchatView / motionView 카드 노출 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chitchatView")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("motionView")));
		
		test.log(Status.INFO, "chitchat Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "chitchat"));
		
		test.log(Status.INFO, "company.day_001 intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "company.day_001"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0712")
	public void TC_0712_Chips_감성대화_이벤트절기상황2_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 불금이야 - 발화");
		util.SWFsendPost("불금이야", ServerName, AccessToken);
		
		test.log(Status.INFO, "chitchatView / motionView 카드 노출 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chitchatView")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("motionView")));
		
		test.log(Status.INFO, "chitchat Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "chitchat"));
		
		test.log(Status.INFO, "event.date_002 intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "event.date_002"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0713")
	public void TC_0713_Chips_감성대화_피곤질병_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 피곤해 죽겠어 - 발화");
		util.SWFsendPost("피곤해 죽겠어", ServerName, AccessToken);
		
		test.log(Status.INFO, "chitchatView / motionView 카드 노출 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chitchatView")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("motionView")));
		
		test.log(Status.INFO, "chitchat Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "chitchat"));
		
		test.log(Status.INFO, "sickness.tired_001 intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "sickness.tired_001"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0714")
	public void TC_0714_Chips_감성대화_감사_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 고마워 - 발화");
		util.SWFsendPost("고마워", ServerName, AccessToken);
		
		test.log(Status.INFO, "chitchatView / motionView 카드 노출 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chitchatView")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("motionView")));
		
		test.log(Status.INFO, "chitchat Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "chitchat"));
		
		test.log(Status.INFO, "thanks_001 intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "thanks_001"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0721")
	public void TC_0721_Chips_감성대화_고객센터번호_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 고객센터 번호 알려줘 - 발화");
		util.SWFsendPost("고객센터 번호 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "고객센터 번호미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Place, data.CHIPS_USD_set));
		
		test.log(Status.INFO, "usd action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "usd"));
		
	
	}

}
