package Chips_002_미디어_멜론FLO;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 미디어_02_FLO_미로그인 extends Chips_TestCase {
	
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
	    String tid = util.TransactionID_JsonParsing(nuguqa002, Chips_002, ServerName, Place);
	    
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
		util.switchToContextName("NATIVE_APP");
	    
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
        if (id.equals("nuguqa002@sk.com")) {
        	util.click(By.xpath(xPath.간편로그인_1st));
        	System.out.println("[일치] 로그인id : nuguqa002@sk.com");
        } else {
        	util.click(By.xpath(xPath.간편로그인_2st));
        	System.out.println("[불일치] 로그인id : nuguqa002@sk.com");
        }
        
	    
	    test.log(Status.INFO, "NATIVE로 화면 확인");
	    util.switchToContextName("NATIVE_APP");
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0213")
	public void TC_0213_Chips_FLO미로그인_뮤직메이트로실행_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W,  뮤직메이트에서 노래 틀어줘 - 발화");
		util.SWFsendPost("뮤직메이트에서 노래 틀어줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "FLO 미로그인 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa002, Chips_002, ServerName, Place, "설정에서 Flo에 로그인하면 전곡을 들으실 수 있어요. 지금은, 1분만 들려드릴게요."));
		
		test.log(Status.INFO, "FLO 이용권 안내 배너 닫기");
		boolean closeSnackbar = util.isElementPresent(By.id("closeSnackbar"));
		if(closeSnackbar == true) {
			util.click(By.id("closeSnackbar"));
		} else { 
			Thread.sleep(1000);
		}
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "FLO"));
		
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
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO반복버튼)));
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO랜덤버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0214")
	public void TC_0214_Chips_FLO미로그인_음악실행_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W,  음악 재생- 발화");
		util.SWFsendPost("음악 재생", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "FLO 미로그인 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa002, Chips_002, ServerName, Place, "설정에서 Flo에 로그인하면 전곡을 들으실 수 있어요. 지금은, 1분만 들려드릴게요."));
		
		test.log(Status.INFO, "FLO 이용권 안내 배너 닫기");
		boolean closeSnackbar = util.isElementPresent(By.id("closeSnackbar"));
		if(closeSnackbar == true) {
			util.click(By.id("closeSnackbar"));
		} else { 
			Thread.sleep(1000);
		}
		  
		test.log(Status.INFO, "FLO 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "FLO"));
		
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
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO반복버튼)));
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO랜덤버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0215")
	public void TC_0215_Chips_FLO미로그인_뮤직플레이_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 뮤직 플레이- 발화");
		util.SWFsendPost("뮤직 플레이", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "FLO 미로그인 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa002, Chips_002, ServerName, Place, "설정에서 Flo에 로그인하면 전곡을 들으실 수 있어요. 지금은, 1분만 들려드릴게요."));
		
		test.log(Status.INFO, "FLO 이용권 안내 배너 닫기");
		boolean closeSnackbar = util.isElementPresent(By.id("closeSnackbar"));
		if(closeSnackbar == true) {
			util.click(By.id("closeSnackbar"));
		} else { 
			Thread.sleep(1000);
		}
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "FLO"));
		
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
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO반복버튼)));
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO랜덤버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		util.switchToContextName("NATIVE_APP");
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0216")
	public void TC_0216_Chips_FLO미로그인_방송음악_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 놀면뭐하니 노래 들려줘- 발화");
		util.SWFsendPost("놀면뭐하니 노래 들려줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "FLO 미로그인 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa002, Chips_002, ServerName, Place, "설정에서 Flo에 로그인하면 전곡을 들으실 수 있어요. 지금은, 1분만 들려드릴게요."));
		
		test.log(Status.INFO, "FLO 이용권 안내 배너 닫기");
		boolean closeSnackbar = util.isElementPresent(By.id("closeSnackbar"));
		if(closeSnackbar == true) {
			util.click(By.id("closeSnackbar"));
		} else { 
			Thread.sleep(1000);
		}
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "FLO"));
		
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
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO반복버튼)));
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO랜덤버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		util.switchToContextName("NATIVE_APP");
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0217")
	public void TC_0217_Chips_FLO미로그인_최신음악_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 플로에서 최신 곡 틀어줘 - 발화");
		util.SWFsendPost("플로에서 최신 곡 틀어줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "FLO 미로그인 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa002, Chips_002, ServerName, Place, "설정에서 Flo에 로그인하면 전곡을 들으실 수 있어요. 지금은, 1분만 들려드릴게요."));
		
		test.log(Status.INFO, "FLO 이용권 안내 배너 닫기");
		boolean closeSnackbar = util.isElementPresent(By.id("closeSnackbar"));
		if(closeSnackbar == true) {
			util.click(By.id("closeSnackbar"));
		} else { 
			Thread.sleep(1000);
		}
		
		test.log(Status.INFO, "FLO 타이틀 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO카드타이틀), "FLO"));
		
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
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO반복버튼)));
		
		test.log(Status.INFO, "랜덤 버튼 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO랜덤버튼)));
		
		test.log(Status.INFO, "재생시간 확인");
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.xpath(xPath.FLO재생곡전체시간)));
		util.switchToContextName("NATIVE_APP");
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0218")
	public void TC_0218_Chips_FLO미로그인_아티스트곡_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 봄여름가을겨울의 거리의 악사 들려줘 - 발화");
		util.SWFsendPost("봄여름가을겨울의 거리의 악사 들려줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "FLO 미로그인 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa002, Chips_002, ServerName, Place, "설정에서 Flo에 로그인하면 전곡을 들으실 수 있어요. 지금은, 1분만 들려드릴게요."));
		
		test.log(Status.INFO, "FLO 이용권 안내 배너 닫기");
		boolean closeSnackbar = util.isElementPresent(By.id("closeSnackbar"));
		if(closeSnackbar == true) {
			util.click(By.id("closeSnackbar"));
		} else { 
			Thread.sleep(1000);
		}
		
		test.log(Status.INFO, "가수 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO제목), "거리의 악사"));
		
		test.log(Status.INFO, "제목 확인");
		Assert.assertTrue(util.getText_Assertfunc(By.xpath(xPath.FLO아티스트), "봄여름가을겨울"));
		
		util.switchToContextName("NATIVE_APP");
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0219")
	public void TC_0219_Chips_FLO미로그인_음원없음_확인(Method method) throws Exception {
		 
		test.log(Status.INFO, "W, 세군데 식당 노래 틀어줘 - 발화");
		util.SWFsendPost("세군데 식당 노래 틀어줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "FLO 미로그인 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa002, Chips_002, ServerName, Place, "원하시는 음악을 찾지 못했습니다. 다시 말씀해 주세요."));

	}

}
