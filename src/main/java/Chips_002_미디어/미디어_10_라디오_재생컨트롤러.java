package Chips_002_미디어;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 미디어_10_라디오_재생컨트롤러 extends Chips_TestCase {
	
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

		test.log(Status.INFO, "AppActivity으로 화면 확인");
		util.context("NATIVE_APP");
	    
	    //test.log(Status.INFO, "Chips 업데이트 팝업 확인");
	    //util.chips_update_check(ServerName);
		
		test.log(Status.INFO, "접근권한 허용 버튼 클릭");
		util.click(By.xpath(xPath.접근권한허용버튼));
		
		test.log(Status.INFO, "퍼미션 오디오 녹음 권한 허용 "); 
	    util.switchTo().alert().accept();
	    
	    test.log(Status.INFO, "퍼미션 위치 권한 허용 "); 
	    util.switchTo().alert().accept();
	    
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0401")
	public void TC_0401_Chips_라디오_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, SBS파워FM 라디오 틀어줘 - 발화");
		util.SWFsendPost("SBS파워FM 라디오 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("SBS 파워FM 라디오"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "라디오 타이틀 확인");
		String 타이틀 = util.getText(By.xpath(xPath.라디오타이틀));
		Assert.assertEquals(타이틀, "라디오");
		
		test.log(Status.INFO, "라디오 채널 목록 ");
		boolean 채널목록 = util.isElementPresent(By.xpath(xPath.라디오채널목록));
		Assert.assertTrue(채널목록);
		
		test.log(Status.INFO, "카드 하단 발화가이드 확인");
		boolean chipListView = util.isElementPresent(By.id("chipListView"));
		Assert.assertTrue(chipListView);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0402")
	public void TC_0402_Chips_라디오_일시정지_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost_playStatus("일시정지", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "일시정지 중 플레이 버튼 활성화 확인 ");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 일시정지확인 = util.isElementPresent(By.xpath(xPath.sbs파워FM_재생_web));
		Assert.assertTrue(일시정지확인);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0403")
	public void TC_0403_Chips_라디오_일시정지취소_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 일시정지 취소- 발화");
		util.SWFsendPost_playStatus("일시정지 취소", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "일시정지 중 플레이 버튼 활성화 확인 ");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 일시정지확인 = util.isElementPresent(By.xpath(xPath.sbs파워FM_일시정지_web));
		Assert.assertTrue(일시정지확인);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0404")
	public void TC_0404_Chips_라디오_일시정지_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost_playStatus("일시정지", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "일시정지 중 플레이 버튼 활성화 확인 ");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 일시정지확인 = util.isElementPresent(By.xpath(xPath.sbs파워FM_재생_web));
		Assert.assertTrue(일시정지확인);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0405")
	public void TC_0405_Chips_라디오_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 재생 - 발화");
		util.SWFsendPost_playStatus("재생", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "일시정지 중 플레이 버튼 활성화 확인 ");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 재생확인 = util.isElementPresent(By.xpath(xPath.sbs파워FM_일시정지_web));
		Assert.assertTrue(재생확인);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0406")
	public void TC_0406_Chips_라디오_다음라디오채널_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 다음 라디오 채널 - 발화");
		util.SWFsendPost_playStatus("다음 라디오 채널", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "다음 채널 라디오 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("TBN 강원교통방송 라디오"));
		
		test.log(Status.INFO, "다음 라디오 재생중 버튼 활성화");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 강원방송재생중확인 = util.isElementPresent(By.xpath(xPath.tbn강원_일시정지_web));
		Assert.assertTrue(강원방송재생중확인);
		boolean SBS정지확인 = util.isElementPresent(By.xpath(xPath.sbs파워FM_재생_web));
		Assert.assertTrue(SBS정지확인);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0407")
	public void TC_0407_Chips_라디오_이전라디오채널_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 이전 라디오 채널 - 발화");
		util.SWFsendPost_playStatus("이전 라디오 채널", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "이전 채널 라디오 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("SBS 파워FM 라디오"));
		
		test.log(Status.INFO, "이전 라디오 재생중 버튼 활성화");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean SBS재생중확인 = util.isElementPresent(By.xpath(xPath.sbs파워FM_일시정지_web));
		Assert.assertTrue(SBS재생중확인);
		boolean 강원방송정지확인 = util.isElementPresent(By.xpath(xPath.tbn강원_재생_web));
		Assert.assertTrue(강원방송정지확인);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0408")
	public void TC_0408_Chips_라디오_다음_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 다음 - 발화");
		util.SWFsendPost_playStatus("다음", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "다음 채널 라디오 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("TBN 강원교통방송 라디오"));
		
		test.log(Status.INFO, "다음 라디오 재생중 버튼 활성화");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 강원방송재생중확인 = util.isElementPresent(By.xpath(xPath.tbn강원_일시정지_web));
		Assert.assertTrue(강원방송재생중확인);
		boolean SBS정지확인 = util.isElementPresent(By.xpath(xPath.sbs파워FM_재생_web));
		Assert.assertTrue(SBS정지확인);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0409")
	public void TC_0409_Chips_라디오_이전_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 이전 - 발화");
		util.SWFsendPost_playStatus("이전", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "이전 채널 라디오 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("SBS 파워FM 라디오"));
		
		test.log(Status.INFO, "이전 라디오 재생중 버튼 활성화");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean SBS재생중확인 = util.isElementPresent(By.xpath(xPath.sbs파워FM_일시정지_web));
		Assert.assertTrue(SBS재생중확인);
		boolean 강원방송정지확인 = util.isElementPresent(By.xpath(xPath.tbn강원_재생_web));
		Assert.assertTrue(강원방송정지확인);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0410")
	public void TC_0410_Chips_라디오_다음채널_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 다음 채널 - 발화");
		util.SWFsendPost_playStatus("다음 채널", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "다음 채널 라디오 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("TBN 강원교통방송 라디오"));
		
		test.log(Status.INFO, "다음 라디오 재생중 버튼 활성화");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 강원방송재생중확인 = util.isElementPresent(By.xpath(xPath.tbn강원_일시정지_web));
		Assert.assertTrue(강원방송재생중확인);
		boolean SBS정지확인 = util.isElementPresent(By.xpath(xPath.sbs파워FM_재생_web));
		Assert.assertTrue(SBS정지확인);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0411")
	public void TC_0411_Chips_라디오_이전라디오방송_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 이전 라디오 방송 - 발화");
		util.SWFsendPost_playStatus("이전 라디오 방송", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "이전 채널 라디오 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("SBS 파워FM 라디오"));
		
		test.log(Status.INFO, "이전 라디오 재생중 버튼 활성화");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean SBS재생중확인 = util.isElementPresent(By.xpath(xPath.sbs파워FM_일시정지_web));
		Assert.assertTrue(SBS재생중확인);
		boolean 강원방송정지확인 = util.isElementPresent(By.xpath(xPath.tbn강원_재생_web));
		Assert.assertTrue(강원방송정지확인);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0410")
	public void TC_0412_Chips_라디오_다음라디오_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 다음 라디오 - 발화");
		util.SWFsendPost_playStatus("다음 라디오", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "다음 채널 라디오 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("TBN 강원교통방송 라디오"));
		
		test.log(Status.INFO, "다음 라디오 재생중 버튼 활성화");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 강원방송재생중확인 = util.isElementPresent(By.xpath(xPath.tbn강원_일시정지_web));
		Assert.assertTrue(강원방송재생중확인);
		boolean SBS정지확인 = util.isElementPresent(By.xpath(xPath.sbs파워FM_재생_web));
		Assert.assertTrue(SBS정지확인);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0413")
	public void TC_0413_Chips_라디오_종료예약_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 2분 뒤에 라디오 꺼줘 - 발화");
		util.SWFsendPost_playStatus("2분 뒤에 라디오 꺼줘", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "종료예약 미지원 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(util.dataCheck_Contains(tts, data.CHIPS_USD_set));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0413")
	public void TC_0414_Chips_라디오_종료_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 라디오 꺼줘 - 발화");
		util.SWFsendPost_playStatus("라디오 꺼줘", ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "라디오카드 종료되고 메인화면 확인");
		boolean temperature = util.isElementPresent(By.id("temperature"));
		Assert.assertTrue(temperature);
		boolean temperature_format = util.isElementPresent(By.id("temperature_format"));
		Assert.assertTrue(temperature_format);
		boolean location = util.isElementPresent(By.id("location"));
		Assert.assertTrue(location);
		boolean weatherImageView = util.isElementPresent(By.id("weatherImageView"));
		Assert.assertTrue(weatherImageView);

	}

}
