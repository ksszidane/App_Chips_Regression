package Chips_007_생활정보_뉴스;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_20_MBN뉴스 extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0856")
	public void TC_0856_Chips_MBN뉴스_시작_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, MBN 시작 - 발화");
		util.SWFsendPost("MBN 시작", ServerName, AccessToken);
		
		test.log(Status.INFO, "MBN 시작 TTS 확인");
		String tts = util.TTS_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("MBN 뉴스에요. 뉴스 들려줘 라고 말씀해 보세요."));
		
		test.log(Status.INFO, "MBN 시작 도메인확인 확인");
		String Domian = util.Domain_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(Domian.contains("play_mbn"));
		
		test.log(Status.INFO, "MBN 시작 인텐트 확인 확인");
		String intent = util.intent_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(intent.contains("start"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0857")
	public void TC_0857_Chips_MBN뉴스_실행_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, MBN 실행해줘 - 발화");
		util.SWFsendPost("MBN 실행해줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "MBN 시작 TTS 확인");
		String tts = util.TTS_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("MBN 뉴스에요. 뉴스 들려줘 라고 말씀해 보세요."));
		
		test.log(Status.INFO, "MBN 시작 도메인확인 확인");
		String Domian = util.Domain_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(Domian.contains("play_mbn"));
		
		test.log(Status.INFO, "MBN 시작 인텐트 확인 확인");
		String intent = util.intent_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(intent.contains("start"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0868")
	public void TC_0868_Chips_MBN뉴스_대기상태실행_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, MBN 오늘 뉴스 기사 읽어줘 - 발화");
		util.SWFsendPost("MBN 오늘 뉴스 기사 읽어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "MBN 실행 TTS 확인");
		String tts = util.TTS_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("김주하 AI 앵커가 전해 드리는 이 시각 MBN 주요뉴스입니다."));
		
		test.log(Status.INFO, "MBN 실행 도메인확인 확인");
		String Domian = util.Domain_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(Domian.contains("play_mbn"));
		
		test.log(Status.INFO, "MBN 실행 인텐트 확인 확인");
		String intent = util.intent_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(intent.contains("play.news"));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "MBN 뉴스");
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		boolean 미디어이미지 = util.isElementPresent(By.xpath(xPath.FLO카드앨범아트));
		Assert.assertTrue(미디어이미지);
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		String 뉴스컨텐츠제목 = util.getText(By.xpath(xPath.FLO제목));
		Assert.assertTrue(뉴스컨텐츠제목.contains("MBN"));
		
		test.log(Status.INFO, "뉴스날짜 확인");
		String 오늘날짜 = util.getKoreaDate();
		String 뉴스제공처 = util.getText(By.xpath(xPath.FLO아티스트));
		Assert.assertTrue(뉴스제공처.contains("뉴스"));
		Assert.assertTrue(뉴스제공처.contains(오늘날짜));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		String 일시정지 = util.getText(By.xpath(xPath.FLO일시정지버튼));
		Assert.assertEquals(일시정지, "재생/일시정지");
		
		boolean 이전 = util.isElementPresent(By.xpath(xPath.FLO이전버튼));
		Assert.assertTrue(이전);

		boolean 다음 = util.isElementPresent(By.xpath(xPath.FLO다음버튼));
		Assert.assertTrue(다음);
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0870")
	public void TC_0870_Chips_MBN뉴스_실행중다음_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
		test.log(Status.INFO, "W, MBN 오늘 뉴스 기사 읽어줘 - 발화");
		util.SWFsendPost("MBN 오늘 뉴스 기사 읽어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "MBN 뉴스");
		
		test.log(Status.INFO, "W, 다음 - 발화");
		util.SWFsendPost("다음", ServerName, AccessToken);
		
		test.log(Status.INFO, "MBN 실행 TTS 확인");
		String tts = util.TTS_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("다음 뉴스가 없어요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0871")
	public void TC_0871_Chips_MBN뉴스_실행중이전_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
		test.log(Status.INFO, "W, MBN 오늘 뉴스 기사 읽어줘 - 발화");
		util.SWFsendPost("MBN 오늘 뉴스 기사 읽어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "MBN 뉴스");
		
		test.log(Status.INFO, "W, MBN 뉴스 이전 - 발화");
		util.SWFsendPost("MBN 뉴스 이전", ServerName, AccessToken);
		
		test.log(Status.INFO, "MBN 실행 TTS 확인");
		String tts = util.TTS_JsonParsing(nuguqa001, Chips_001, ServerName, Place);
		Assert.assertTrue(tts.contains("다음 뉴스가 없어요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0872")
	public void TC_0872_Chips_MBN뉴스_일시정지_확인(Method method) throws Exception {
		
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		
		test.log(Status.INFO, "W, MBN 뉴스 일시정지버튼 클릭 - 발화");
		util.click(By.xpath(xPath.일시정지버튼_web));
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		boolean 재생버튼 = util.isElementPresent(By.xpath(xPath.재생버튼_web));
		Assert.assertTrue(재생버튼);
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0873")
	public void TC_0873_Chips_MBN뉴스_뉴스계속_확인(Method method) throws Exception {
		
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		
		test.log(Status.INFO, "W, MBN 뉴스 일시정지버튼 클릭 - 발화");
		util.click(By.xpath(xPath.재생버튼_web));
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		boolean 재생버튼 = util.isElementPresent(By.xpath(xPath.일시정지버튼_web));
		Assert.assertTrue(재생버튼);
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0876")
	public void TC_0876_Chips_MBN뉴스_그만_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 그만 - 발화");
		util.SWFsendPost("그만", ServerName, AccessToken);
		
		test.log(Status.INFO, "뉴스카드 종료되고 메인화면 확인");
		boolean temperature = util.isElementPresent(By.id("temperature"));
		Assert.assertTrue(temperature);
		boolean temperature_format = util.isElementPresent(By.id("temperature_format"));
		Assert.assertTrue(temperature_format);
		boolean location = util.isElementPresent(By.id("location"));
		Assert.assertTrue(location);
		boolean weatherImageView = util.isElementPresent(By.id("weatherImageView"));
		Assert.assertTrue(weatherImageView);
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0877")
	public void TC_0877_Chips_MBN뉴스_종료_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 종료 - 발화");
		util.SWFsendPost("종료", ServerName, AccessToken);
		
		test.log(Status.INFO, "W, MBN 오늘 뉴스 기사 읽어줘 - 발화");
		util.SWFsendPost("MBN 오늘 뉴스 기사 읽어줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		String FLOtxt = util.getText(By.xpath(xPath.FLO카드타이틀));
		Assert.assertEquals(FLOtxt, "MBN 뉴스");
		
		test.log(Status.INFO, "뉴스카드 종료되고 메인화면 확인");
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
