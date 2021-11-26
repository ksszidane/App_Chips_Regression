package Chips_002_미디어;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 미디어_06_자체소싱 extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0300")
	public void TC_0300_Chips_자체소싱_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 부스트 파크 들려줘 - 발화");
		util.SWFsendPost("부스트 파크 들려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "걸그룹 장르 노래 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "부스트파크송"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "자체소싱 타이틀 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드타이틀)));
		
		test.log(Status.INFO, "앨범아트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "곡명 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "아티스트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO아티스트)));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
		test.log(Status.INFO, "반복 버튼 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.한곡반복_web)));
		util.context("NATIVE_APP");
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO랜덤버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		util.context("NATIVE_APP");
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0301")
	public void TC_0301_Chips_자체소싱_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 부스트 파크 노래 들려줘 - 발화");
		util.SWFsendPost("부스트 파크 노래 들려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "걸그룹 장르 노래 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SK텔레콤의 부스트파크송"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "음악"));
		
		test.log(Status.INFO, "앨범아트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "곡명 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "아티스트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO아티스트)));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
		test.log(Status.INFO, "반복 버튼 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.한곡반복_web)));
		util.context("NATIVE_APP");
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO랜덤버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		util.context("NATIVE_APP");
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0302")
	public void TC_0302_Chips_자체소싱_일시정지_확인(Method method) throws Exception {
		 
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost_playStatus("일시정지 ", ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.재생버튼_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0241")
	public void TC_0303_Chips_자체소싱_일시정지취소_확인(Method method) throws Exception {
		 
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 일시정지 취소 - 발화");
		util.SWFsendPost_playStatus("일시정지 취소 ", ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.일시정지버튼_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0304")
	public void TC_0304_Chips_컨트롤러_좋아요_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 좋아요 해줘 - 발화");
		util.SWFsendPost_playStatus("좋아요 해줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "좋아요 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "좋아요 기능을 지원하지 않는 곡이에요."));
		
		test.log(Status.INFO, "좋아요 버튼 비활성화 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.waitForPageLoaded();
		util.switchToWindwosURL(xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.좋아요Off_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0305")
	public void TC_0305_Chips_컨트롤러_좋아요취소_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 좋아요 해제 - 발화");
		util.SWFsendPost_playStatus("좋아요 해제", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "좋아요 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "좋아요 기능을 지원하지 않는 곡이에요."));
		
		test.log(Status.INFO, "좋아요 버튼 비활성화 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.좋아요Off_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0306")
	public void TC_0306_Chips_자체소싱_다음_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 다음 곡 - 발화");
		util.SWFsendPost_playStatus("다음 곡", ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "다음 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "한곡 재생시에는 지원하지 않는 기능입니다."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0307")
	public void TC_0307_Chips_자체소싱_이전_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 이전 곡 - 발화");
		util.SWFsendPost_playStatus("이전 곡", ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "이전 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "한곡 재생시에는 지원하지 않는 기능입니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0308")
	public void TC_0308_Chips_자체소싱_셔플_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "셔플 버튼 클릭");
		util.click(By.xpath(xPath.FLO랜덤버튼));
		Thread.sleep(2000);
		
		test.log(Status.INFO, "이전 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "한곡 재생시에는 지원하지 않는 기능입니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0309")
	public void TC_0309_Chips_자체소싱_이전_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 이전 곡 - 발화");
		util.SWFsendPost_playStatus("이전 곡", ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "이전 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "한곡 재생시에는 지원하지 않는 기능입니다."));
	}

	@Test(description = "칩스 리그레이션 TC : 실행_0310")
	public void TC_0310_Chips_자체소싱_셔플_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "셔플 버튼 클릭");
		util.click(By.xpath(xPath.FLO랜덤버튼));
		Thread.sleep(2000);
		
		test.log(Status.INFO, "셔플 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "한곡 재생시에는 지원하지 않는 기능입니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0311")
	public void TC_0311_Chips_자체소싱_다음_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 다음 곡 - 발화");
		util.SWFsendPost_playStatus("다음 곡", ServerName, AccessToken, "music");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "다음 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "한곡 재생시에는 지원하지 않는 기능입니다."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0312")
	public void TC_0312_Chips_자체소싱_20초뒤로_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 20초 뒤로 이동 - 발화");
		util.SWFsendPost_playStatus("20초 뒤로 이동", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "20초뒤로 이동 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "뮤직 서비스에서는 지원하지 않는 기능이에요."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0313")
	public void TC_0313_Chips_자체소싱_15초앞으로_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 15초 앞으로 이동 - 발화");
		util.SWFsendPost_playStatus("15초 앞으로 이동", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "15초 앞으로 이동 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "뮤직 서비스에서는 지원하지 않는 기능이에요."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0314")
	public void TC_0314_Chips_자체소싱_종료_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 음악 꺼줘 - 발화");
		util.SWFsendPost("꺼줘", ServerName, AccessToken);
		Thread.sleep(5000);
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "음악카드 종료되고 메인화면 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature_format")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("location")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("weatherImageView")));
	}
	
	
}
