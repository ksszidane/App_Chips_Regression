package Chips_002_음원;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 음원_07_ASMR extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0315")
	public void TC_0315_Chips_ASMR_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, ASMR 플레이 - 발화");
		util.SWFsendPost("ASMR 플레이", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("ASMR"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "ASMR");
		
		test.log(Status.INFO, "앨범아트 확인");
		boolean 앨범아트 = util.isElementPresent(By.xpath(xPath.FLO카드앨범아트));
		Assert.assertTrue(앨범아트);
		
		test.log(Status.INFO, "곡명 확인");
		boolean 곡명 = util.isElementPresent(By.xpath(xPath.FLO제목));
		Assert.assertTrue(곡명);
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		String 일시정지 = util.getText(By.xpath(xPath.FLO일시정지버튼));
		Assert.assertEquals(일시정지, "재생/일시정지");
		
		boolean 이전 = util.isElementPresent(By.xpath(xPath.FLO이전버튼));
		Assert.assertTrue(이전);

		boolean 다음 = util.isElementPresent(By.xpath(xPath.FLO다음버튼));
		Assert.assertTrue(다음);
	
		test.log(Status.INFO, "프로그래스 바 확인");
		boolean progress = util.isElementPresent(By.xpath(xPath.FLO프로그레스바));
		Assert.assertTrue(progress);
		
		test.log(Status.INFO, "반복 버튼 확인");
		boolean 반복 = util.isElementPresent(By.xpath(xPath.FLO반복버튼));
		Assert.assertTrue(반복);
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		boolean 랜덤 = util.isElementPresent(By.xpath(xPath.FLO랜덤버튼));
		Assert.assertTrue(랜덤);
		
		test.log(Status.INFO, "재생시간 확인");
		boolean 재생시간 = util.isElementPresent(By.xpath(xPath.FLO재생곡전체시간));
		Assert.assertTrue(재생시간);
		util.context("NATIVE_APP");
		
		boolean chipListView = util.isElementPresent(By.id("chipListView"));
		Assert.assertTrue(chipListView);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0316")
	public void TC_0316_Chips_ASMR_과자먹는소리재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 과자 먹는 소리 틀어줘 - 발화");
		util.SWFsendPost("과자 먹는 소리 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("과자 먹는 소리"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "ASMR");
		
		test.log(Status.INFO, "곡명 확인");
		String 곡명 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertTrue(곡명.contains("과자 먹는 소리"));
		
		test.log(Status.INFO, "과자먹는 소리 이미지커버 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		String src = util.getAttribute(By.xpath(xPath.이미지커버src), "src");
		Assert.assertTrue(src.contains("30_thumbnail.png"));
		
		test.log(Status.INFO, "한곡 반복 버튼 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		boolean 한곡반복 = util.isElementPresent(By.xpath(xPath.한곡반복_web));
		Assert.assertTrue(한곡반복);
		util.context("NATIVE_APP");
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0317")
	public void TC_0317_Chips_ASMR_빗소리_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 빗소리 들려줘 - 발화");
		util.SWFsendPost("빗소리 들려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("빗소리"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "ASMR");
		
		test.log(Status.INFO, "곡명 확인");
		String 곡명 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertTrue(곡명.contains("빗소리"));
		
		test.log(Status.INFO, "빗소리 이미지커버 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		String src = util.getAttribute(By.xpath(xPath.이미지커버src), "src");
		Assert.assertTrue(src.contains("01_thumbnail.png"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0318")
	public void TC_0318_Chips_ASMR_힐링사운드_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 힐링 사운드 재생 - 발화");
		util.SWFsendPost("힐링 사운드 재생", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("힐링사운드"));
		Assert.assertTrue(util.dataCheck_Contains(tts, data.음악시작_set));
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "ASMR");
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0319")
	public void TC_0319_Chips_ASMR_자연의소리_반복확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 자연의 소리 반복해서 틀어줘 - 발화");
		util.SWFsendPost("자연의 소리 반복해서 틀어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "ASMR 재생 TTS 확인");
		String tts = util.TTS_JsonParsing(ksszidane, Chips_did, ServerName, Place);
		Assert.assertTrue(tts.contains("자연의 소리 반복해드릴게요."));
		
		test.log(Status.INFO, "ASMR 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "ASMR");
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0321")
	public void TC_0321_Chips_ASMR_다음ASMR_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "곡명 확인");
		String 이전곡명 = util.getText(By.xpath(xPath.FLO제목));
		
		test.log(Status.INFO, "W, 다음거 - 발화");
		util.SWFsendPost("다음 곡", ServerName, AccessToken);
		Thread.sleep(3000);

		test.log(Status.INFO, "곡명 확인");
		String 다음곡명 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertFalse(이전곡명.contains(다음곡명));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0322")
	public void TC_0322_Chips_ASMR_이전ASMR_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "곡명 확인");
		String 이전곡명 = util.getText(By.xpath(xPath.FLO제목));
		
		test.log(Status.INFO, "W, 이전거 - 발화");
		util.SWFsendPost("이전 곡", ServerName, AccessToken);
		Thread.sleep(3000);

		test.log(Status.INFO, "곡명 확인");
		String 다음곡명 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertFalse(이전곡명.contains(다음곡명));
		
	}

}
