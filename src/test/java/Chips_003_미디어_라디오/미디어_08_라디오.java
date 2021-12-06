package Chips_003_미디어_라디오;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 미디어_08_라디오 extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0341")
	public void TC_0341_Chips_라디오_재생_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "W, SBS 파워FM 라디오 들려줘 - 발화");
		util.SWFsendPost("SBS 파워FM 라디오 들려줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(7000);
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SBS 파워FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "라디오 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.라디오타이틀), "라디오"));
		
		test.log(Status.INFO, "라디오 채널 목록 ");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.라디오채널목록)));
		
		test.log(Status.INFO, "카드 하단 발화가이드 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0342")
	public void TC_0342_Chips_라디오_좋아요재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 좋아요 한 라디오 들려줘 - 발화");
		util.SWFsendPost("좋아요 한 라디오 들려줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "좋아요한 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "즐겨찾기 한 라디오 채널이 없습니다. 누구 앱에서 자주 듣는 채널을 즐겨찾기로 등록해주세요."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0343")
	public void TC_0343_Chips_라디오_즐겨찾기있음_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(3000);
		
		test.log(Status.INFO, "W, 라디오 들려줘- 발화");
		util.SWFsendPost("라디오 들려줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(7000);
		
		test.log(Status.INFO, "최근에 재생한 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SBS 파워FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "W, 다음 라디오 채널 - 발화");
		util.SWFsendPost_playStatus("다음 라디오 채널", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "다음 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 강원교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		Thread.sleep(5000);
		
		test.log(Status.INFO, "다음 라디오 재생중 버튼 활성화");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn강원_일시정지_web)));
		
		test.log(Status.INFO, "W, 즐겨찾기 해줘 - 발화");
		util.SWFsendPost_playStatus("즐겨찾기 해줘", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "즐겨찾기 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "즐겨찾기 리스트에 추가했습니다."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0344")
	public void TC_0344_Chips_라디오_즐겨찾기있음_재생_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "W, 즐겨찾기 한 라디오 틀어줘  - 발화");
		util.SWFsendPost("즐겨찾기 한 라디오 틀어줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(7000);
		
		test.log(Status.INFO, "즐겨찾기한 재생한 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 강원교통방송 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "W, 즐겨찾기 해제 - 발화");
		util.SWFsendPost_playStatus("즐겨찾기 해제", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "즐겨찾기 해제 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "즐겨찾기 리스트에서 삭제하였습니다."));
	
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0345")
	public void TC_0345_Chips_라디오_듣고있는라디오_채널_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "W, 이 라디오 이름 알려줘  - 발화");
		util.SWFsendPost_playStatus("이 라디오 이름 알려줘", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "즐겨찾기한 재생한 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "이 라디오는  TBN 강원교통방송  입니다."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0346")
	public void TC_0346_Chips_라디오_듣고있는라디오_채널_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "W, 지금 듣고 있는 방송이 뭐야?  - 발화");
		util.SWFsendPost_playStatus("지금 듣고 있는 방송이 뭐야", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "즐겨찾기한 재생한 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "이 라디오는  TBN 강원교통방송  입니다."));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0347")
	public void TC_0347_Chips_라디오_다음라디오_채널_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "W, 다음 라디오 채널  - 발화");
		util.SWFsendPost_playStatus("다음 라디오 채널", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(7000);
		
		test.log(Status.INFO, "다음 채널 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SBS 파워FM 라디오를 들려드릴게요."));
		Thread.sleep(5000);
		
		test.log(Status.INFO, "다음 라디오 재생중 버튼 활성화");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.라디오1st_일시정지_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0348")
	public void TC_0348_Chips_라디오_이전라디오_채널_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "W, 이전 라디오 채널  - 발화");
		util.SWFsendPost_playStatus("이전 라디오 채널", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(7000);
		
		test.log(Status.INFO, "이전 채널 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBN 강원교통방송 라디오"));
		Thread.sleep(5000);
		
		test.log(Status.INFO, "이전 라디오 재생중 버튼 활성화");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.tbn강원_일시정지_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0349")
	public void TC_0349_Chips_라디오_즐겨찾기1개재생중_다음_확인(Method method) throws Exception {
		 
		util.switchContext("NATIVE_APP");
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000);
		
		test.log(Status.INFO, "W, KBS Cool FM 라디오 들려줘- 발화");
		util.SWFsendPost_playStatus("KBS Cool FM 라디오 들려줘", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "채널 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS CoolFM 라디오"));
		
		test.log(Status.INFO, "W, 즐겨찾기 해줘 - 발화");
		util.SWFsendPost_playStatus("즐겨찾기 해줘", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "즐겨찾기 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "즐겨찾기 리스트에 추가했습니다."));
		
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000);
	    
	    test.log(Status.INFO, "W, 즐겨찾기한 라디오 들려줘 - 발화");
		util.SWFsendPost("즐겨찾기한 라디오 들려줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(7000);
		
		test.log(Status.INFO, "즐겨찾기한 채널 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS CoolFM 라디오"));
		
		test.log(Status.INFO, "W, 다음 라디오 채널 - 발화");
		util.SWFsendPost_playStatus("다음 라디오 채널", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(7000);
		
		test.log(Status.INFO, "다음 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SBS 파워FM 라디오를 들려드릴게요."));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		Thread.sleep(5000);
		
		test.log(Status.INFO, "다음 라디오 재생중 버튼 활성화 - 라디오 리스트 두번째 활성화 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.라디오2st_일시정지_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0350")
	public void TC_0350_Chips_라디오_즐겨찾기2개재생중_다음_확인(Method method) throws Exception {
		 
		util.switchToNative();
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000);
		
		test.log(Status.INFO, "W, CBS 라디오 들려줘- 발화");
		util.SWFsendPost("CBS 라디오 들려줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(7000);
		
		test.log(Status.INFO, "채널 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "CBS 음악FM 라디오"));
		
		test.log(Status.INFO, "W, 즐겨찾기 해줘 - 발화");
		util.SWFsendPost_playStatus("즐겨찾기 해줘", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "즐겨찾기 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "즐겨찾기 리스트에 추가했습니다."));
		
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000);
	    
	    test.log(Status.INFO, "W, 즐겨찾기한 라디오 들려줘 - 발화");
		util.SWFsendPost("즐겨찾기한 라디오 들려줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(7000);
		
		test.log(Status.INFO, "즐겨찾기한 채널 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "CBS 음악FM 라디오"));
		
		test.log(Status.INFO, "W, 다음 라디오 채널 - 발화");
		util.SWFsendPost_playStatus("다음 라디오 채널", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(7000);
		
		test.log(Status.INFO, "다음 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS CoolFM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		Thread.sleep(5000);
		
		test.log(Status.INFO, "다음 라디오 재생중 버튼 활성화 - 라디오 리스트 두번째 활성화 ");
		util.switchToWindwosIndex(1);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.라디오2st_일시정지_web)));
		
		test.log(Status.INFO, "W, 다음 라디오 채널 - 발화");
		util.SWFsendPost_playStatus("다음 라디오 채널", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(7000);
		
		test.log(Status.INFO, "다음 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SBS 파워FM 라디오를 들려드릴게요."));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		Thread.sleep(5000);
		
		test.log(Status.INFO, "다음 라디오 재생중 버튼 활성화 - 라디오 리스트 세번째 활성화 ");
		util.switchToWindwosIndex(1);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.라디오3st_일시정지_web)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0351")
	public void TC_0351_Chips_라디오_종료_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000); 
		
		test.log(Status.INFO, "W, CBS 라디오 들려줘 - 발화");
		util.SWFsendPost("CBS 라디오 들려줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(7000);
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "CBS 음악FM 라디오"));
		
		test.log(Status.INFO, "W, 즐겨찾기 해제 - 발화");
		util.SWFsendPost_playStatus("즐겨찾기 해제", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "즐겨찾기 해제 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "즐겨찾기 리스트에서 삭제하였습니다."));
		
		test.log(Status.INFO, "W, 그만 - 발화");
		util.SWFsendPost("그만", Chips_001, ServerName, AccessToken);
		util.SWFsendPost("그만", Chips_001, ServerName, AccessToken);
		Thread.sleep(3000);
		
		test.log(Status.INFO, "라디오카드 종료되고 메인화면 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature_format")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("location")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("weatherImageView")));

		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0352")
	public void TC_0352_Chips_라디오_종료_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000); 
		
		test.log(Status.INFO, "W, KBS 라디오 들려줘 - 발화");
		util.SWFsendPost("KBS 라디오 들려줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(7000);
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "KBS CoolFM 라디오"));
		
		test.log(Status.INFO, "W, 즐겨찾기 해제 - 발화");
		util.SWFsendPost_playStatus("즐겨찾기 해제", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "즐겨찾기 해제 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "즐겨찾기 리스트에서 삭제하였습니다."));
		
		test.log(Status.INFO, "W, 라디오 종료 - 발화");
		util.SWFsendPost_playStatus("라디오 종료", Chips_001, ServerName, AccessToken, "radio");
		util.SWFsendPost_playStatus("라디오 종료", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(3000);
		
		util.switchToNative();
		test.log(Status.INFO, "라디오카드 종료되고 메인화면 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature_format")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("location")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("weatherImageView")));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0353")
	public void TC_0353_Chips_라디오_일시정지상태_채널재생_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, SBS파워FM 라디오 들려줘 - 발화");
		util.SWFsendPost("SBS 파워FM 라디오 들려줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(7000);
		
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SBS 파워FM 라디오를 들려드릴게요."));
		Thread.sleep(3000);
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SBS 파워FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost_playStatus("일시정지", Chips_001, ServerName, AccessToken, "radio");
		util.SWFsendPost_playStatus("일시정지", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(5000);
		
		test.log(Status.INFO, "일시정지 중 플레이 버튼 활성화 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.sbs파워FM_재생_web)));
		
		test.log(Status.INFO, "W, 라디오 다시 틀어줘 - 발화");
		util.SWFsendPost_playStatus("라디오 다시 틀어줘", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(7000);
		
		test.log(Status.INFO, "플레이 중 일시정지 버튼 활성화 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.sbs파워FM_일시정지_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0354")
	public void TC_0354_Chips_라디오_일시정지상태_채널재생_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, SBS파워FM 라디오 들려줘 - 발화");
		util.SWFsendPost("SBS 파워FM 라디오 들려줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(7000);
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SBS 파워FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost_playStatus("일시정지", Chips_001, ServerName, AccessToken, "radio");
		util.SWFsendPost_playStatus("일시정지", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(5000);
		
		test.log(Status.INFO, "일시정지 중 플레이 버튼 활성화 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.sbs파워FM_재생_web)));
		
		test.log(Status.INFO, "W, 이거 무슨 채널이야? - 발화");
		util.SWFsendPost_playStatus("이거 무슨 채널이야", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "채널 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "이 라디오는  SBS 파워FM  입니다."));
		
		test.log(Status.INFO, "일시정지 유지 확인 ");
		util.switchContextURL("WEBVIEW_com.skt.aidev.nugufriends", xPath.Webview_URL);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.sbs파워FM_재생_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0355")
	public void TC_0355_Chips_라디오_대기상태_채널목록_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, 라디오 종료 - 발화");
		util.SWFsendPost("라디오 꺼줘", Chips_001, ServerName, AccessToken);
		util.SWFsendPost("라디오 꺼줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(5000);
		
		test.log(Status.INFO, "W, 라디오 뭐뭐 있어? - 발화");
		util.SWFsendPost("라디오 뭐뭐 있어", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "채널 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "8개 방송국, 27개의 채널이 있습니다. SBS, KBS, CBS, TBS, 아리랑, 불교방송, 극동방송, TBN 라디오를 들을 수 있어요."));
		
		test.log(Status.INFO, "라디오 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.라디오타이틀), "라디오"));
		
		test.log(Status.INFO, "라디오카드 7초뒤 종료되고 메인화면 확인");
		Thread.sleep(8000);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature_format")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("location")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("weatherImageView")));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0356")
	public void TC_0356_Chips_라디오_대기상태_채널목록_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, 라디오 리스트 - 발화");
		util.SWFsendPost("라디오 리스트", Chips_001, ServerName, AccessToken);
		util.SWFsendPost("라디오 리스트", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "채널 안내 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "8개 방송국, 27개의 채널이 있습니다. SBS, KBS, CBS, TBS, 아리랑, 불교방송, 극동방송, TBN 라디오를 들을 수 있어요."));
		
		test.log(Status.INFO, "라디오 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.라디오타이틀), "라디오"));
		
		test.log(Status.INFO, "라디오카드 7초뒤 종료되고 메인화면 확인");
		Thread.sleep(8000);
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature_format")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("location")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("weatherImageView")));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0357")
	public void TC_0357_Chips_라디오_실행중_즐겨찾기_확인(Method method) throws Exception {
		
		util.switchToNative();
		test.log(Status.INFO, "W, SBS파워FM 라디오 들려줘 - 발화");
		util.SWFsendPost("SBS파워FM 라디오 들려줘", Chips_001, ServerName, AccessToken);
		Thread.sleep(7000);
		
		test.log(Status.INFO, "라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SBS 파워FM 라디오"));
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.음악시작_set));
		
		test.log(Status.INFO, "W, 즐겨찾기 해줘 - 발화");
		util.SWFsendPost_playStatus("즐겨찾기 해줘", Chips_001, ServerName, AccessToken, "radio");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "즐겨찾기 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "즐겨찾기 리스트에 추가했습니다."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0358")
	public void TC_0358_Chips_라디오_즐겨찾기완료_즐겨찾기실행_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 즐겨찾기 해줘 - 발화");
		util.SWFsendPost_playStatus("즐겨찾기 해줘", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "즐겨찾기 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "이미 즐겨찾기로 등록된 채널입니다."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0359")
	public void TC_0359_Chips_라디오_실행중_즐겨찾기해제_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 즐겨찾기 해제 - 발화");
		util.SWFsendPost_playStatus("즐겨찾기 해제", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "즐겨찾기해제 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "즐겨찾기 리스트에서 삭제하였습니다."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0360")
	public void TC_0360_Chips_라디오_즐겨찾기해제완료_즐겨찾기해제_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 즐겨찾기 해제 - 발화");
		util.SWFsendPost_playStatus("즐겨찾기 해제", Chips_001, ServerName, AccessToken, "radio");
		
		test.log(Status.INFO, "즐겨찾기해제 라디오 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "즐겨찾기 한 채널이 아닙니다."));
		
	}

}
