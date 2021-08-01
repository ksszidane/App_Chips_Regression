package Chips_004_생활정보;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_11_날씨_일몰시간 extends Chips_TestCase {
	
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
	    util.click(By.xpath(xPath.간편로그인_1st));
	    
	    test.log(Status.INFO, "NATIVE로 화면 확인");
	    util.context("NATIVE_APP");
	    util.ProgressBar_Loading();
	    
	    accessToken얻기();
	    
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0781")
	public void TC_0781_Chips_날씨_일몰시간_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재위치 정보값 저장");
		String str = util.getText(By.id("location"));
		String[] array = str.split(" ");
		String 현재위치 = array[2];
		
		test.log(Status.INFO, "W, 일몰 시간 - 발화");
		util.SWFsendPost("일몰 시간", ServerName, AccessToken);
		
		test.log(Status.INFO, "일몰시간 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("오늘 " + 현재위치 + "일몰시각은"));
	
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0782")
	public void TC_0782_Chips_날씨_지역_일몰시간_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 부산 해 지는 시간 알려줘 - 발화");
		util.SWFsendPost("부산 해 지는 시간 알려줘", ServerName, AccessToken);

		test.log(Status.INFO, "일몰시간 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("오늘 부산일몰시각은"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0783")
	public void TC_0783_Chips_날씨_오늘_일몰시간_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재위치 정보값 저장");
		String str = util.getText(By.id("location"));
		String[] array = str.split(" ");
		String 현재위치 = array[2];
		
		test.log(Status.INFO, "W, 오늘 일몰 시간 - 발화");
		util.SWFsendPost("오늘 일몰 시간", ServerName, AccessToken);
		
	
		test.log(Status.INFO, "오늘 일몰시간 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("오늘 " + 현재위치 + "일몰시각은"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0784")
	public void TC_0784_Chips_날씨_오늘지역_일몰시간_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 오늘 부산 해 지는 시간 알려줘 - 발화");
		util.SWFsendPost("오늘 부산 해 지는 시간 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "오늘 일몰시간 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("오늘 부산일몰시각은"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0785")
	public void TC_0785_Chips_날씨_내일_일몰시간_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재위치 정보값 저장");
		String str = util.getText(By.id("location"));
		String[] array = str.split(" ");
		String 현재위치 = array[2];
		
		test.log(Status.INFO, "W, 내일 일몰 시간 - 발화");
		util.SWFsendPost("내일 일몰 시간", ServerName, AccessToken);
		
	
		test.log(Status.INFO, "내일 일몰시간 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("내일 " + 현재위치 + "일몰시각은"));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0786")
	public void TC_0786_Chips_날씨_내일지역_일몰시간_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 내일 부산 해 지는 시간 알려줘 - 발화");
		util.SWFsendPost("내일 부산 해 지는 시간 알려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "내일 일몰시간 TTS 확인");
		String tts = util.TTS_JsonParsing_most_recent(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("내일 부산일몰시각은"));
		
	}

}
