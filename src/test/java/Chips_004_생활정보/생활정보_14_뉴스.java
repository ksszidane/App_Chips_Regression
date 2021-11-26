package Chips_004_생활정보;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_14_뉴스 extends Chips_TestCase {
	
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0811")
	public void TC_0811_Chips_뉴스_기본설정_CBS뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 뉴스 - 발화");
		util.SWFsendPost("뉴스", ServerName, AccessToken);
		
		test.log(Status.INFO, "CBS 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최신 CBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "CBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0812")
	public void TC_0812_Chips_뉴스_기본설정_CBS뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 데일리 브리핑 - 발화");
		util.SWFsendPost_playStatus("데일리 브리핑", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "CBS 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최신 CBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "CBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0813")
	public void TC_0813_Chips_뉴스_KBS뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, KBS 뉴스 들려줘 - 발화");
		util.SWFsendPost_playStatus("KBS 뉴스 들려줘", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "KBS 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최신 KBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "KBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "KBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0814")
	public void TC_0814_Chips_뉴스_CBS뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, CBS 뉴스 들려줘 - 발화");
		util.SWFsendPost_playStatus("CBS 뉴스 들려줘 ", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "CBS 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최신 CBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "CBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0812")
	public void TC_0815_Chips_뉴스_SBS뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, SBS 뉴스 들려줘 - 발화");
		util.SWFsendPost_playStatus("SBS 뉴스 들려줘 ", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "SBS 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최신 SBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "SBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "SBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0815_1")
	public void TC_0815_1_Chips_뉴스_MBC뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, MBC 뉴스 들려줘 - 발화");
		util.SWFsendPost_playStatus("MBC 뉴스 들려줘 ", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "MBC 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최신 MBC 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "MBC 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "MBC"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertFalse(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0815_2")
	public void TC_0815_2_Chips_뉴스_TBS뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, TBS 뉴스 들려줘 - 발화");
		util.SWFsendPost_playStatus("TBS 뉴스 들려줘 ", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "TBS 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최신 TBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "TBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "TBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0815_2")
	public void TC_0815_2_Chips_뉴스_연합뉴스뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 연합뉴스 들려줘 - 발화");
		util.SWFsendPost_playStatus("연합뉴스 뉴스 들려줘 ", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "연합뉴스 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최신 연합뉴스 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "연합뉴스 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "연합뉴스"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0816")
	public void TC_0816_Chips_뉴스_오늘뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 오늘 뉴스 들려줘 - 발화");
		util.SWFsendPost_playStatus("오늘 뉴스 들려줘 ", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "CBS 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최신 CBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "CBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0817")
	public void TC_0817_Chips_뉴스_연예특정뉴스_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 연예 뉴스 알려줘 - 발화");
		util.SWFsendPost_playStatus("연예 뉴스 알려줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "연예 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "연예 관련, 최근 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "연예 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0818")
	public void TC_0818_Chips_뉴스_스포츠특정뉴스_확인(Method method) throws Exception {
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, 스포츠 데일리 브리핑 해줘 - 발화");
		util.SWFsendPost_playStatus("스포츠 데일리 브리핑 해줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "스포츠 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "스포츠 관련, 최근 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "스포츠 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0819")
	public void TC_0819_Chips_뉴스_미지원채널_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 매경 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("매경 뉴스 틀어줘", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "매경 뉴스 미지원 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "아쉽게도, 아직 매경 뉴스는 제공하지 않아요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0820")
	public void TC_0820_Chips_뉴스_미지원채널_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 조선일보 뉴스 재생- 발화");
		util.SWFsendPost_playStatus("조선일보 뉴스 재생", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "조선일보 뉴스 미지원 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "아쉽게도, 아직 조선일보 뉴스는 제공하지 않아요."));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0821")
	public void TC_0821_Chips_뉴스_SK텔레콤_키워드뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    Thread.sleep(5000);
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "W, SK텔레콤 뉴스 들려줘 - 발화");
		util.SWFsendPost("SK텔레콤 뉴스 들려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "스포츠 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SK텔레콤 관련, 최근 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "텔레콤"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0822")
	public void TC_0822_Chips_뉴스_세종병원화재_키워드뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 세종병원 화재 뉴스 브리핑 해줘 - 발화");
		util.SWFsendPost_playStatus("세종병원 화재 뉴스 브리핑 해줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "세종병원 화재 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "세종병원 화재 관련"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "세종병원 화재"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0823")
	public void TC_0823_Chips_뉴스_컴퓨터_키워드뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W,  컴퓨터 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("컴퓨터 뉴스 틀어줘", ServerName, AccessToken, "music");
		util.SWFsendPost_playStatus("컴퓨터 뉴스 틀어줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "컴퓨터 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "컴퓨터 관련, 최근 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "컴퓨터"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0824")
	public void TC_0824_Chips_뉴스_NUGU토픽_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, 누구토픽 들려줘 - 발화");
		util.SWFsendPost("NUGU 토픽 들려줘", ServerName, AccessToken);
		
		test.log(Status.INFO, "NUGU 토픽 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "NUGU 토픽 입니다."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "NUGU 토픽"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0825")
	public void TC_0825_Chips_뉴스_키워드뉴스없음_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, 주식회사 티벨 박이슬 박사 뉴스 들려줘 - 발화");
		util.SWFsendPost_playStatus("주식회사 티벨 박이슬 박사 뉴스 들려줘", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "키워드뉴스없음 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "주식회사 티벨 박이슬 박사 관련 뉴스를 찾지 못했습니다."));
		

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0826")
	public void TC_0826_Chips_뉴스_지난주뉴스_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, 지난 주 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("지난 주 뉴스 틀어줘", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "지난주 뉴스 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최근 업데이트된 뉴스를 제공하고 있어요. 최신 CBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
			

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0827")
	public void TC_0827_Chips_뉴스_지난주연예뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 지난 주 연예 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("지난 주 연예 뉴스 틀어줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "연예 뉴스 재생 TTS 확인");
		String 지난주주말 = util.getLastWeekSunday();
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, 지난주주말));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "연예 관련 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), 지난주주말 + " 연예 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0828")
	public void TC_0828_Chips_뉴스_N월첫째주스포츠뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 1월 첫째주 스포츠 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("1월 첫째주 스포츠 뉴스 틀어줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "스포츠 뉴스"));
		
		test.log(Status.INFO, "스포츠 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "스포츠 관련 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0829")
	public void TC_0829_Chips_뉴스_지난주SK텔레콤뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 지난 주 SK텔레콤 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("지난 주 SK텔레콤 뉴스 틀어줘", ServerName, AccessToken, "music");
		util.SWFsendPost_playStatus("지난 주 SK텔레콤 뉴스 틀어줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "SK텔레콤"));
		
		test.log(Status.INFO, "SK텔레콤 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SK텔레콤 관련 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0830")
	public void TC_0830_Chips_뉴스_지난달뉴스_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, 지난달 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("지난달 뉴스 틀어줘", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "지난달 뉴스 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최근 업데이트된 뉴스를 제공하고 있어요. 최신 CBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "CBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0831")
	public void TC_0831_Chips_뉴스_지난달연예뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 지난달 연예 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("지난달 연예 뉴스 틀어줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "연예 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "연예 관련 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "연예 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0831")
	public void TC_0831_Chips_뉴스_지난달스포츠뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 지난달 스포츠 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("지난달 스포츠 뉴스 틀어줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "스포츠 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "스포츠 관련 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "스포츠 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0832")
	public void TC_0832_Chips_뉴스_지난달SK텔레콤뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 지난달 SK텔레콤 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("지난달 SK텔레콤 뉴스 틀어줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "SK텔레콤뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "SK텔레콤 관련 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "SK텔레콤"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0834")
	public void TC_0834_Chips_뉴스_어제뉴스_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, 어제 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("어제 뉴스 틀어줘", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "어제 뉴스 TTS 확인");;
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최근 업데이트된 뉴스를 제공하고 있어요. 최신 CBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "CBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0835")
	public void TC_0835_Chips_뉴스_그제뉴스_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, 그제 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("그제 뉴스 틀어줘", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "그제 뉴스 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최근 업데이트된 뉴스를 제공하고 있어요. 최신 CBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "CBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0836")
	public void TC_0836_Chips_뉴스_그제스포츠뉴스_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 그제 스포츠 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("그제 스포츠 뉴스 틀어줘", ServerName, AccessToken, "music");
		util.SWFsendPost_playStatus("그제 스포츠 뉴스 틀어줘", ServerName, AccessToken, "music");
		
		test.log(Status.INFO, "스포츠 뉴스 재생 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "스포츠 관련 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "스포츠 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 URL 확인");
		util.context("WEBVIEW_com.skt.aidev.nugufriends");
		util.switchToWindwosURL(xPath.Webview_URL);
		util.waitForPageLoaded();
		Assert.assertTrue(util.getAttribute_Assertfunc(By.xpath(xPath.키워드뉴스이미지_web), "src", "news_motion.gif"));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.키워드뉴스타이틀_web)));
		
		util.context("NATIVE_APP");
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0837")
	public void TC_0837_Chips_뉴스_미지원기간뉴스_과거특정일_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, 2018년 1월 1일 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("2018년 1월 1일 뉴스 틀어줘", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "2018년 1월 1일 뉴스 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최근 업데이트된 뉴스를 제공하고 있어요. 최신 CBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "CBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0838")
	public void TC_0838_Chips_뉴스_미지원기간뉴스_내일_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, 내일 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("내일 뉴스 틀어줘", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "내일 뉴스 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최근 업데이트된 뉴스를 제공하고 있어요. 최신 CBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "CBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0839")
	public void TC_0839_Chips_뉴스_미지원기간뉴스_모레_확인(Method method) throws Exception {

		test.log(Status.INFO, "W, 모레 뉴스 틀어줘 - 발화");
		util.SWFsendPost_playStatus("모레 뉴스 틀어줘", ServerName, AccessToken, "news");
		
		test.log(Status.INFO, "모레 뉴스 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "최근 업데이트된 뉴스를 제공하고 있어요. 최신 CBS 주요 뉴스를 들려드릴게요."));
		
		test.log(Status.INFO, "뉴스 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "CBS 뉴스"));
		
		test.log(Status.INFO, "뉴스 이미지 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO카드앨범아트)));
		
		test.log(Status.INFO, "뉴스컨텐츠제목 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO제목)));
		
		test.log(Status.INFO, "뉴스제공처 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "CBS"));
		
		test.log(Status.INFO, "미디어컨트롤러 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO일시정지버튼), "재생/일시정지"));
		
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO이전버튼)));

		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO다음버튼)));
	
		test.log(Status.INFO, "프로그래스 바 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO프로그레스바)));
		
	}

}
