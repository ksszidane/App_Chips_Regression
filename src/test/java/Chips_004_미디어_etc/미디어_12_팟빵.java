package Chips_004_미디어_etc;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 미디어_12_팟빵 extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0442")
	public void TC_0442_Chips_팟캐스트_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 팟캐스트 - 발화");
		util.SWFsendPost("팟캐스트", ServerName, AccessToken);
		
		test.log(Status.INFO, "팟캐스트 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최근에 들은"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "이어서"));
		
		test.log(Status.INFO, "팟캐스트 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "최근들은 팟캐스트"));
		
		test.log(Status.INFO, "앨범아트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "방송명 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "에피소드 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO아티스트)));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO일시정지버튼)));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
		test.log(Status.INFO, "구독 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.팟캐스트구독버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		
		util.context("NATIVE_APP");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0443")
	public void TC_0443_Chips_팟빵_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 팟빵 - 발화");
		util.SWFsendPost_playStatus("팟빵", ServerName, AccessToken, "podcast");
		
		test.log(Status.INFO, "팟캐스트 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최근에 들은"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "이어서"));
		
		test.log(Status.INFO, "팟캐스트 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "최근들은 팟캐스트"));
		
		test.log(Status.INFO, "앨범아트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "방송명 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "에피소드 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO아티스트)));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO일시정지버튼)));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
		test.log(Status.INFO, "구독 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.팟캐스트구독버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		
		util.context("NATIVE_APP");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0444")
	public void TC_0444_Chips_팟빵_방송명으로팟캐스트_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 김어준 뉴스 공장 팟캐스트 - 발화");
		util.SWFsendPost_playStatus("김어준 뉴스 공장 팟캐스트", ServerName, AccessToken, "podcast");
		
		test.log(Status.INFO, "김어준의 뉴스공장 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBS 김어준의 뉴스공장"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "들려드릴게요"));
		
		test.log(Status.INFO, "팟캐스트 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "팟캐스트"));
		
		test.log(Status.INFO, "앨범아트 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "방송명 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "에피소드 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO아티스트)));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO일시정지버튼)));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
		test.log(Status.INFO, "구독버튼 비활성화 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.팟캐스트구독off_web)));
		util.context("NATIVE_APP");
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("chipListView")));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0445")
	public void TC_0445_Chips_팟빵_구독_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 팟캐스트 구독 - 발화");
		util.SWFsendPost_playStatus("팟캐스트 구독", ServerName, AccessToken, "podcast");
		
		test.log(Status.INFO, "구독 팟캐스트 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "구독 리스트에 추가했습니다."));
		Thread.sleep(3000);
		
		test.log(Status.INFO, "구독 버튼 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.팟캐스트구독on_web)));
		util.context("NATIVE_APP");
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0446")
	public void TC_0446_Chips_팟빵_구독삭제_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 팟캐스트 삭제해줘 - 발화");
		util.SWFsendPost_playStatus("팟캐스트 삭제해줘", ServerName, AccessToken, "podcast");
		
		test.log(Status.INFO, "구독 팟캐스트 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "구독 리스트에서 삭제 했습니다."));
		Thread.sleep(3000);
		
		test.log(Status.INFO, "구독 버튼 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.팟캐스트구독off_web)));
		util.context("NATIVE_APP");
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0447")
	public void TC_0447_Chips_팟빵_재생시간이동_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 3분 다음으로 이동해줘 - 발화");
		util.SWFsendPost_playStatus("3분 다음으로 이동해줘", ServerName, AccessToken, "podcast");
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0448")
	public void TC_0448_Chips_팟빵_종료예약_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 1분 뒤에 팟캐스트 꺼줘 - 발화");
		util.SWFsendPost_playStatus("1분 뒤에 팟캐스트 꺼줘", ServerName, AccessToken, "podcast");
		
		test.log(Status.INFO, "종료예약 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.CHIPS_USD_set));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0449")
	public void TC_0449_Chips_팟빵_종료예약_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 오후 11시 59분에 팟캐스트 꺼줘 - 발화");
		util.SWFsendPost_playStatus("오후 11시 59분에 팟캐스트 꺼줘", ServerName, AccessToken, "podcast");
		
		test.log(Status.INFO, "종료예약 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_ContainsSet(nuguqa001, Chips_001, ServerName, Place, data.CHIPS_USD_set));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0455")
	public void TC_0455_Chips_팟빵_컨트롤러_에피소드_재생_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 팟빵에서 김어준의 뉴스공장 틀어줘 - 발화");
		util.SWFsendPost("팟빵에서 김어준의 뉴스공장 틀어줘", ServerName, AccessToken);
		Thread.sleep(2000);
		
		test.log(Status.INFO, "팟캐스트 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "팟캐스트"));
		
		test.log(Status.INFO, "팟빵에서 김어준의 뉴스공장 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "김어준의 뉴스공장"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "들려드릴게요."));
		
		test.log(Status.INFO, "방송명 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO제목), "뉴스공장"));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0456")
	public void TC_0456_Chips_팟빵_컨트롤러_일시정지_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost_playStatus("일시정지", ServerName, AccessToken, "podcast");
		util.SWFsendPost_playStatus("일시정지", ServerName, AccessToken, "podcast");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.재생버튼_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0457")
	public void TC_0457_Chips_팟빵_컨트롤러_일시정지취소_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 일시정지 취소 - 발화");
		util.SWFsendPost_playStatus("일시정지 취소", ServerName, AccessToken, "podcast");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.일시정지버튼_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0458")
	public void TC_0458_Chips_팟빵_컨트롤러_일시정지_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 일시정지 - 발화");
		util.SWFsendPost_playStatus("일시정지", ServerName, AccessToken, "podcast");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.재생버튼_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0459")
	public void TC_0459_Chips_팟빵_컨트롤러_일시정지취소_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 재생 - 발화");
		util.SWFsendPost_playStatus("재생", ServerName, AccessToken, "podcast");
		
		test.log(Status.INFO, "김어준의 뉴스공장 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최근에 들은"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "TBS 김어준의 뉴스공장"));
		
		test.log(Status.INFO, "플레이어의 일시정지 버튼 재생버튼으로 전환 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.일시정지버튼_web)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0460")
	public void TC_0460_Chips_팟빵_구독_확인(Method method) throws Exception {
		 
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 구독해줘 - 발화");
		util.SWFsendPost_playStatus("구독 해줘", ServerName, AccessToken, "podcast");
		util.SWFsendPost_playStatus("구독 해줘", ServerName, AccessToken, "podcast");

		test.log(Status.INFO, "구독 팟캐스트 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "구독 리스트에 추가했습니다."));
		Thread.sleep(2000);
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0461")
	public void TC_0461_Chips_팟빵_구독해제_확인(Method method) throws Exception {
		 
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 구독 해제 - 발화");
		util.SWFsendPost_playStatus("구독 해제", ServerName, AccessToken, "podcast");
		util.SWFsendPost_playStatus("구독 해제", ServerName, AccessToken, "podcast");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "구독 팟캐스트 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "구독 리스트에서 삭제 했습니다."));
		
		test.log(Status.INFO, "구독 버튼 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.팟캐스트구독off_web)));
		util.context("NATIVE_APP");
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0462")
	public void TC_0462_Chips_팟빵_이전_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 팟캐스트 꺼줘 - 발화");
		util.SWFsendPost_playStatus("팟캐스트 꺼줘", ServerName, AccessToken, "podcast");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "팟캐스트카드 종료되고 메인화면 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature_format")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("location")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("weatherImageView")));
		
		test.log(Status.INFO, "W, 팟빵에서 두시탈출 컬투쇼 틀어줘 - 발화");
		util.SWFsendPost("팟빵에서 두시탈출 컬투쇼 틀어줘", ServerName, AccessToken);
		Thread.sleep(3000);
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "현재 에피소드 저장");
		String 현재에피소드 = util.getText(By.xpath(xPath.FLO아티스트));
		
		test.log(Status.INFO, "W, 이전 - 발화");
		util.SWFsendPost_playStatus("이전", ServerName, AccessToken, "music");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "W, 이전 - 발화");
		util.SWFsendPost_playStatus("이전", ServerName, AccessToken, "music");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "W, 이전 - 발화");
		util.SWFsendPost_playStatus("이전", ServerName, AccessToken, "music");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "변경된 에피소드 확인");
		String 변경에피소드 = util.getText(By.xpath(xPath.FLO아티스트));
		Assert.assertFalse(현재에피소드.contains(변경에피소드));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0463")
	public void TC_0463_Chips_팟빵_다음_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재 에피소드 저장");
		String 현재에피소드 = util.getText(By.xpath(xPath.FLO아티스트));
		
		test.log(Status.INFO, "W, 다음 - 발화");
		util.SWFsendPost_playStatus("다음", ServerName, AccessToken, "music");
		util.SWFsendPost_playStatus("다음", ServerName, AccessToken, "music");
		Thread.sleep(4000);
		
		test.log(Status.INFO, "변경된 에피소드 확인");
		String 변경에피소드 = util.getText(By.xpath(xPath.FLO아티스트));
		Assert.assertFalse(현재에피소드.contains(변경에피소드));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0463")
	public void TC_0464_Chips_팟빵_이전팟캐스트_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 팟빵에서 김어준 뉴스 공장 틀어줘 - 발화");
		util.SWFsendPost("팟빵에서 김어준 뉴스 공장 틀어줘", ServerName, AccessToken);
		Thread.sleep(3000);
		
		test.log(Status.INFO, "현재 에피소드 저장");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		String 현재에피소드 = util.getText(By.xpath(xPath.팟빵에피소트명_web));
		System.out.println(현재에피소드);
		
		test.log(Status.INFO, "W, 이전 팟캐스트 - 발화");
		util.SWFsendPost_playStatus("이전 팟캐스트", ServerName, AccessToken, "podcast");
		Thread.sleep(4000);
		
		util.SWFsendPost_playStatus("이전 팟캐스트", ServerName, AccessToken, "podcast");
		Thread.sleep(4000);
		
		test.log(Status.INFO, "변경된 에피소드 확인");
		String 변경에피소드 = util.getText(By.xpath(xPath.팟빵에피소트명_web));
		System.out.println(변경에피소드);
		Assert.assertFalse(현재에피소드.contains(변경에피소드));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0465")
	public void TC_0465_Chips_팟빵_다음팟캐스트__확인(Method method) throws Exception {
		
		test.log(Status.INFO, "현재 에피소드 저장");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		String 현재에피소드 = util.getText(By.xpath(xPath.팟빵에피소트명_web));
		System.out.println(현재에피소드);
		
		test.log(Status.INFO, "W, 다음 팟캐스트 - 발화");
		util.SWFsendPost_playStatus("다음 팟캐스트", ServerName, AccessToken, "podcast");
		Thread.sleep(4000);
		
		util.SWFsendPost_playStatus("다음 팟캐스트", ServerName, AccessToken, "podcast");
		Thread.sleep(4000);
		
		util.SWFsendPost_playStatus("다음 팟캐스트", ServerName, AccessToken, "podcast");
		Thread.sleep(4000);
		
		test.log(Status.INFO, "변경된 에피소드 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		String 변경에피소드 = util.getText(By.xpath(xPath.팟빵에피소트명_web));
		System.out.println(변경에피소드);
		Assert.assertFalse(현재에피소드.contains(변경에피소드));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0466")
	public void TC_0466_Chips_팟빵_재생시간앞으로이동_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "현재 재생시간 저장");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		String 현재재생시간 = util.getText(By.xpath(xPath.팟캐스트재생시간_web));
		
		test.log(Status.INFO, "W, 2분 앞으로 이동 - 발화");
		util.SWFsendPost_playStatus("2분 앞으로 이동", ServerName, AccessToken, "podcast");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "변경된 재생시간 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		String 변경재생시간 = util.getText(By.xpath(xPath.팟캐스트재생시간_web));
		Assert.assertFalse(현재재생시간.contains(변경재생시간));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0467")
	public void TC_0467_Chips_팟빵_재생시간뒤으로이동_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "현재 재생시간 저장");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		String 현재재생시간 = util.getText(By.xpath(xPath.팟캐스트재생시간_web));
		
		test.log(Status.INFO, "W, 1분 뒤로 이동 - 발화");
		util.SWFsendPost_playStatus("1분 뒤로 이동", ServerName, AccessToken, "podcast");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "변경된 재생시간 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		String 변경재생시간 = util.getText(By.xpath(xPath.팟캐스트재생시간_web));
		Assert.assertFalse(현재재생시간.contains(변경재생시간));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0468")
	public void TC_0468_Chips_팟빵_종료_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 팟캐스트 꺼줘 - 발화");
		util.SWFsendPost_playStatus("팟캐스트 꺼줘", ServerName, AccessToken, "podcast");
		Thread.sleep(3000);
		
		test.log(Status.INFO, "음악카드 종료되고 메인화면 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("temperature_format")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("location")));
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("weatherImageView")));

	}

}
