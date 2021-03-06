package Chips_007_생활정보_뉴스;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_15_뉴스_재생컨트롤러 extends Chips_TestCase {
	
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

	@Test(description = "칩스 리그레이션 TC : 실행_0844")
	public void TC_0844_Chips_뉴스_연예키워드뉴스재생_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 연예 뉴스 알려줘 - 발화");
		util.SWFsendPost("연예 뉴스 알려줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "연예 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "연예 관련, 최근 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "연예 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.switchToNative();
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0845")
	public void TC_0845_Chips_뉴스_컨트롤러_일시정지_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost_playStatus("일시정지",  Chips_001, ServerName, AccessToken, "news");
		util.SWFsendPost_playStatus("일시정지",  Chips_001, ServerName, AccessToken, "news");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.재생버튼_web)));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0846")
	public void TC_0846_Chips_뉴스_컨트롤러_일시정지취소_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "W, 일시정지 취소 - 발화");
		util.SWFsendPost_playStatus("일시정지 취소",  Chips_001, ServerName, AccessToken, "news");
		util.SWFsendPost_playStatus("일시정지 취소",  Chips_001, ServerName, AccessToken, "news");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "플레이어의 재생 버튼 일시정지으로 전환 확인");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.일시정지버튼_web)));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0847")
	public void TC_0847_Chips_뉴스_컨트롤러_일시정지_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost_playStatus("일시정지",  Chips_001, ServerName, AccessToken, "news");
		Thread.sleep(2000);
		
		test.log(Status.INFO, "일시정지 중 플레이 버튼 활성화 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.재생버튼_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0848")
	public void TC_0848_Chips_뉴스_컨트롤러_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 재생 - 발화");
		util.SWFsendPost_playStatus("재생",  Chips_001, ServerName, AccessToken, "news");
		util.SWFsendPost_playStatus("재생",  Chips_001, ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "일시정지 중 플레이 버튼 활성화 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.일시정지버튼_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0849")
	public void TC_0849_Chips_뉴스_컨트롤러_좋아요해줘_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 좋아요 해줘 - 발화");
		util.SWFsendPost_playStatus("좋아요 해줘",  Chips_001, ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "스포츠 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "뉴스 서비스에서는 사용할 수 없는 기능입니다."));
		

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0850")
	public void TC_0850_Chips_뉴스_컨트롤러_좋아요취소_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 좋아요 취소 - 발화");
		util.SWFsendPost_playStatus("좋아요 취소",  Chips_001, ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "스포츠 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "뉴스 서비스에서는 사용할 수 없는 기능입니다."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0851")
	public void TC_0851_Chips_뉴스_컨트롤러_다음뉴스_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "현재 기사제목 저장");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		String 현재기사 = util.getText(By.xpath(xPath.키워드뉴스타이틀_web));
		
		test.log(Status.INFO, "W, 다음 뉴스 - 발화");
		util.SWFsendPost_playStatus(" 다음 뉴스",  Chips_001, ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "다음 기사제목 저장");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		String 다음기사 = util.getText(By.xpath(xPath.키워드뉴스타이틀_web));
		
		test.log(Status.INFO, "다음기사 이동 확인");
		Assert.assertFalse(현재기사.contentEquals(다음기사));
		

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0852")
	public void TC_0852_Chips_뉴스_컨트롤러_이전뉴스_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "현재 기사제목 저장");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		String 현재기사 = util.getText(By.xpath(xPath.키워드뉴스타이틀_web));
		
		test.log(Status.INFO, "W, 이전 뉴스 - 발화");
		util.SWFsendPost_playStatus("이전 뉴스",  Chips_001, ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "이전 기사제목 저장");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		String 다음기사 = util.getText(By.xpath(xPath.키워드뉴스타이틀_web));
		
		test.log(Status.INFO, "이전기사 이동 확인");
		Assert.assertFalse(현재기사.contentEquals(다음기사));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0853")
	public void TC_0853_Chips_뉴스_컨트롤러_다음_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "현재 기사제목 저장");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		String 현재기사 = util.getText(By.xpath(xPath.키워드뉴스타이틀_web));
		
		test.log(Status.INFO, "W, 다음 - 발화");
		util.SWFsendPost_playStatus("다음",  Chips_001, ServerName, AccessToken, "news");
		util.SWFsendPost_playStatus("다음",  Chips_001, ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "다음 기사제목 저장");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		String 다음기사 = util.getText(By.xpath(xPath.키워드뉴스타이틀_web));
		
		test.log(Status.INFO, "다음기사 이동 확인");
		Assert.assertFalse(현재기사.contentEquals(다음기사));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0854")
	public void TC_0854_Chips_뉴스_컨트롤러_이전_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "현재 기사제목 저장");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		String 현재기사 = util.getText(By.xpath(xPath.키워드뉴스타이틀_web));
		
		test.log(Status.INFO, "W, 이전 - 발화");
		util.SWFsendPost_playStatus("이전",  Chips_001, ServerName, AccessToken, "news");
		util.SWFsendPost_playStatus("이전",  Chips_001, ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "이전 기사제목 저장");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		String 다음기사 = util.getText(By.xpath(xPath.키워드뉴스타이틀_web));
		
		test.log(Status.INFO, "이전기사 이동 확인");
		Assert.assertFalse(현재기사.contentEquals(다음기사));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0855")
	public void TC_0855_Chips_뉴스_종료_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "W, 뉴스 꺼줘 - 발화");
		util.SWFsendPost_playStatus("뉴스 꺼줘",  Chips_001, ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "뉴스카드 종료되고 메인화면 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature_format")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("location")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("weatherImageView")));

	}
}
