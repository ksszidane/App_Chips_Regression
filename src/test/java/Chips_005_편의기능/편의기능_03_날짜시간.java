package Chips_005_편의기능;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 편의기능_03_날짜시간 extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0585")
	public void TC_0585_Chips_시간_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 시간 - 발화");
		util.SWFsendPost("시간", ServerName, AccessToken);
		
		test.log(Status.INFO, "시간 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.시간_set));
		
		test.log(Status.INFO, "schedule Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "schedule"));
		
		test.log(Status.INFO, "ask.clock intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "ask.clock"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0586")
	public void TC_0586_Chips_날짜_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 며칠 - 발화");
		util.SWFsendPost("며칠", ServerName, AccessToken);
		
		test.log(Status.INFO, "날짜 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.날짜_set));
		
		test.log(Status.INFO, "play_nugu_calendar Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "play_nugu_calendar"));
		
		test.log(Status.INFO, "ask.date intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "ask.date"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0587")
	public void TC_0587_Chips_명절날짜_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 추석이 무슨 요일이야 - 발화");
		util.SWFsendPost("추석이 무슨 요일이야", ServerName, AccessToken);
		
		test.log(Status.INFO, "날짜 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.날짜_set));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "추석은"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "요일"));
		
		test.log(Status.INFO, "play_nugu_calendar Domain 확인");;
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "play_nugu_calendar"));
		
		test.log(Status.INFO, "ask.date.weekday intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "ask.date.weekday"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0588")
	public void TC_0588_Chips_명절날짜_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 이번 주 일요일 며칠이야 - 발화");
		util.SWFsendPost("이번 주 일요일 며칠이야", ServerName, AccessToken);
		
		test.log(Status.INFO, "날짜 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "이번 주 일요일은"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "일"));
		
		test.log(Status.INFO, "play_nugu_calendar Domain 확인");
		Assert.assertTrue(util.Domain_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "play_nugu_calendar"));
		
		test.log(Status.INFO, "ask.date intent 확인");
		Assert.assertTrue(util.intent_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "ask.date"));
	}

}
