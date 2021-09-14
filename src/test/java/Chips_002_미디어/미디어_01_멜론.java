package Chips_002_미디어;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 미디어_01_멜론 extends Chips_TestCase {
	
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
	    String tid = util.TransactionID_JsonParsing(ksszidane, Chips_did, ServerName, Place);
	    
	    test.log(Status.INFO, "acceesToken 얻기"); 
	    String actn = util.acceesToken_JsonParsing(ServerName, Place, tid);
	    
	    test.log(Status.INFO, "acceesToken : " + actn); 
	    System.out.println(actn);
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
	    util.click(By.xpath(xPath.간편로그인_1st));
	    
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0146")
	public void TC_0146_Chips_멜론미지원_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 멜론에서 음악 들려줘 - 발화");
		util.SWFsendPost("멜론에서 음악 들려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "멜론 미지원 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(util.dataCheck_Equals(tts, data.OOS_set));
		
		test.log(Status.INFO, "oos action_type 확인");
		String usd = util.action_type_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(usd.contains("oos"));
		
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0235")
	public void TC_0235_Chips_멜론미지원_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 멜론에서 음악 재생 - 발화");
		util.SWFsendPost("멜론에서 음악 재생", ServerName, AccessToken);
		
		test.log(Status.INFO, "멜론 미지원 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(util.dataCheck_Equals(tts, data.OOS_set));
		
		test.log(Status.INFO, "oos action_type 확인");
		String usd = util.action_type_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(usd.contains("oos"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0236")
	public void TC_0236_Chips_멜론미지원_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 멜론에서 플레이리스트 틀어줘 - 발화");
		util.SWFsendPost("멜론에서 플레이리스트 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "멜론 미지원 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(util.dataCheck_Equals(tts, data.OOS_set));
		
		test.log(Status.INFO, "oos action_type 확인");
		String usd = util.action_type_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(usd.contains("oos"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0237")
	public void TC_0237_Chips_멜론미지원_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 멜론에서 좋아요 한 음악 틀어줘 - 발화");
		util.SWFsendPost("멜론에서 좋아요 한 음악 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "멜론 미지원 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(util.dataCheck_Equals(tts, data.OOS_set));
		
		test.log(Status.INFO, "oos action_type 확인");
		String usd = util.action_type_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(usd.contains("oos"));
		
	}

}
