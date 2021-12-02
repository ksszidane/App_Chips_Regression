package Chips_003_미디어_라디오;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 미디어_09_라디오_채널확인 extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0361")
	public void TC_0361_Chips_라디오_지원채널_SBS러브FM_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, SBS 러브FM 틀어줘 - 발화");
		util.SWFsendPost("SBS 러브FM 틀어줘", Chips_001, ServerName, AccessToken);
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SBS 러브FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "러브FM 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.SBS러브FM_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0362")
	public void TC_0362_Chips_라디오_지원채널_SBS파워FM_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, SBS 파워FM 들려줘 - 발화");
		util.SWFsendPost_playStatus("SBS 파워FM 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SBS 파워FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "파워FM 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.sbs파워FM_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0363")
	public void TC_0363_Chips_라디오_지원채널_교통방송FM_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 교통방송FM 들려줘 - 발화");
		util.SWFsendPost_playStatus("교통방송FM 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBS FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBSFM 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.TBSFM_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0364")
	public void TC_0364_Chips_라디오_지원채널_TBSeFM_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBS eFM 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBS eFM 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBS eFM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBSeFM 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.TBSeFM_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0366")
	public void TC_0366_Chips_라디오_지원채널_TBN강원교통방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBN 강원 교통방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBN 강원 교통방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 강원교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBN강원교통방송 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn강원_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0367")
	public void TC_0367_Chips_라디오_지원채널_TBN경남교통방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBN 경남 교통방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBN 경남 교통방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 경남교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBN경남교통방송 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn경남_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0368")
	public void TC_0368_Chips_라디오_지원채널_TBN경북교통방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBN 경북 교통방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBN 경북 교통방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 경북교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBN경북교통방송 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn경북_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0369")
	public void TC_0369_Chips_라디오_지원채널_TBN경인교통방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBN 경인 교통방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBN 경인 교통방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 경인교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBN경인교통방송 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn경인_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0370")
	public void TC_0370_Chips_라디오_지원채널_TBN광주교통방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBN 광주교통방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBN 광주교통방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 광주교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBN광주교통방송 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn광주_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0371")
	public void TC_0371_Chips_라디오_지원채널_TBN대구교통방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBN 대구교통방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBN 대구교통방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 대구교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBN대구교통방송 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn대구_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0372")
	public void TC_0372_Chips_라디오_지원채널_TBN대전교통방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBN 대전 교통방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBN 대전 교통방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 대전교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBN대전교통방송 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn대전_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0373")
	public void TC_0373_Chips_라디오_지원채널_TBN부산교통방송_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, TBN 부산 교통방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBN 부산 교통방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 부산교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBN부산교통방송 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn부산_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0374")
	public void TC_0374_Chips_라디오_지원채널_TBN울산교통방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBN 울산 교통방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBN 울산 교통방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 울산교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBN울산교통방송 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn울산_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0375")
	public void TC_0375_Chips_라디오_지원채널_TBN전북교통방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBN 전북 교통방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBN 전북 교통방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 전북교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBN전북교통방송 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn전북_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0376")
	public void TC_0376_Chips_라디오_지원채널_TBN제주교통방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBN 제주 교통방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBN 제주 교통방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 제주교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "TBN제주교통방송 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn제주_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0378")
	public void TC_0378_Chips_라디오_지원채널_아리랑FM_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, Arirang FM 들려줘 - 발화");
		util.SWFsendPost_playStatus("Arirang FM 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "Arirang FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "아리랑 FM  재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.아리랑FM_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0379")
	public void TC_0379_Chips_라디오_지원채널_극동방송_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, 극동방송FM 라디오 들려줘 - 발화");
		util.SWFsendPost_playStatus("극동방송FM 라디오 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "극동방송 FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "극동방송 FM 라디오 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.극동방송_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0380")
	public void TC_0380_Chips_라디오_지원채널_불교방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 불교방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("불교방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "BBS 불교방송 FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "BBS 불교방송 FM 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.불교방송_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0381")
	public void TC_0381_Chips_라디오_지원채널_BBSFM_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, BBS FM 들려줘 - 발화");
		util.SWFsendPost_playStatus("BBS FM 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "BBS 불교방송 FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "BBS 불교방송 FM 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.불교방송_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0382")
	public void TC_0382_Chips_라디오_지원채널_CBS음악FM_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, CBS 음악 FM 들려줘 - 발화");
		util.SWFsendPost_playStatus("CBS 음악 FM 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "CBS 음악FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "CBS 음악FM 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.CBS음악FM_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0383")
	public void TC_0383_Chips_라디오_지원채널_CBS표준FM_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, CBS 표준 FM 들려줘 - 발화");
		util.SWFsendPost_playStatus("CBS 표준 FM 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "CBS 표준FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "CBS 표준FM  재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.CBS표준FM_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0384")
	public void TC_0384_Chips_라디오_지원채널_CBS표준FM_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, CBS 표준 FM 들려줘 - 발화");
		util.SWFsendPost_playStatus("CBS 표준 FM 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "CBS 표준FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "CBS 표준FM  재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.CBS표준FM_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0386")
	public void TC_0386_Chips_라디오_지원채널_KBS1라디오_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, KBS 제1라디오 들려줘 - 발화");
		util.SWFsendPost_playStatus("KBS 제1라디오 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS 1 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "KBS 1 라디오 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.KBS1_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0387")
	public void TC_0387_Chips_라디오_지원채널_KBS2라디오_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, KBS 제2라디오 들려줘 - 발화");
		util.SWFsendPost_playStatus("KBS 제2라디오 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS 2 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "KBS 1 라디오 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.KBS2_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0388")
	public void TC_0388_Chips_라디오_지원채널_KBS3라디오_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, KBS 제3라디오 들려줘 - 발화");
		util.SWFsendPost_playStatus("KBS 제3라디오 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS 3 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "KBS 3 라디오 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.KBS3_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0389")
	public void TC_0389_Chips_라디오_지원채널_KBS클래식FM_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, KBS 클래식FM 틀어줘 - 발화");
		util.SWFsendPost_playStatus("KBS 클래식FM 틀어줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS ClassicFM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "KBS ClassicFM 라디오 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.KBS클래식_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0390")
	public void TC_0390_Chips_라디오_지원채널_KBScoolFM_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, KBS CoolFM 들려줘 - 발화");
		util.SWFsendPost_playStatus("KBS CoolFM 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS CoolFM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "KBS CoolFM 라디오 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.KBScool_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0391")
	public void TC_0391_Chips_라디오_지원채널_KBSHappyFM_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, KBS HappyFM 틀어줘 - 발화");
		util.SWFsendPost_playStatus("KBS HappyFM 틀어줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS 2 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "KBS 2 라디오 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.KBS2_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0392")
	public void TC_0392_Chips_라디오_지원채널_KBSworld_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, KBS 월드 라디오 들려줘 - 발화");
		util.SWFsendPost_playStatus("KBS 월드 라디오 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS 월드 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "KBS 월드 라디오 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.KBSworld_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0393")
	public void TC_0393_Chips_라디오_지원채널_KBS한민족방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, KBS 한민족 방송 들려줘 - 발화");
		util.SWFsendPost_playStatus("KBS 한민족 방송 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS 한민족방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		
		test.log(Status.INFO, "KBS 한민족방송 라디오 재생중 버튼 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.KBS한민족_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0396")
	public void TC_0396_Chips_라디오_미지원채널_동양방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 동양방송 라디오 들려줘 - 발화");
		util.SWFsendPost_playStatus("동양방송 라디오 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "현재 디바이스에서는 지원하지 않는 라디오 채널입니다."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0398")
	public void TC_0398_Chips_라디오_미지원채널_가톨릭평화방송_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 가톨릭평화방송 FM - 발화");
		util.SWFsendPost_playStatus("가톨릭평화방송 FM", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "현재 디바이스에서는 지원하지 않는 라디오 채널입니다."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0399")
	public void TC_0399_Chips_라디오_미지원채널_YTN뉴스FM_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, YTN 뉴스 FM 틀어줘 - 발화");
		util.SWFsendPost_playStatus("YTN 뉴스 FM 틀어줘", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "현재 디바이스에서는 지원하지 않는 라디오 채널입니다."));
		
	}

}
