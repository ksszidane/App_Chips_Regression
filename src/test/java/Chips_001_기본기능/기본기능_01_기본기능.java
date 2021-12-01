package Chips_001_기본기능;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;
import Chips_000_xPath.xPath;

public class 기본기능_01_기본기능 extends Chips_TestCase {
	
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
	    System.out.println(actn);
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
        if (id.equals("nuguqa001@sk.com")) {
        	util.click(By.xpath(xPath.간편로그인_1st));
        	System.out.println("[일치] 로그인id : nuguqa001@sk.com");
        } else {
        	util.click(By.xpath(xPath.간편로그인_2st));
        	System.out.println("[불일치] 로그인id : nuguqa001@sk.com");
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
	
	
	@Test(description = "칩스 리그레이션 TC : 실행_0009")
	public void TC_0009_MIC버튼호출(Method method) throws Exception {
		
		test.log(Status.INFO, "마이크 버튼 터치"); 
		util.click(By.id("iv_start_listening"));
		 
		test.log(Status.INFO, "보이스크롬 노출 확인"); 
		Assert.assertTrue(util.isElementPresent_Assertfunc(By.id("voiceChromeFragment")));
		
		test.log(Status.INFO, "보이스크롬 노출 문구 확인"); 
	    Assert.assertTrue(util.getText_Assertfunc(By.id("asrHint"), "말씀해주세요"));
	    
	    test.log(Status.INFO, "Android BackKey로 보이스크롬 닫기"); 
	    util.Android_BackKey();
		
	}
	
	
	@Test(description = "칩스 리그레이션 TC : 실행_0068")
	public void TC_0068_베터리잔량_발화체크(Method method) throws Exception {

		test.log(Status.INFO, "W, 배터리 잔량 - 발화");
		util.SWFsendPost("배터리 잔량", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "배터리 잔량 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Place, data.CHIPS_USD_set));
		
		test.log(Status.INFO, "usd action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "usd"));
		
		

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0069")
	public void TC_0069_베터리잔량_발화체크(Method method) throws Exception {

		test.log(Status.INFO, "W, 밧데리 얼마나 남았어? - 발화");
		util.SWFsendPost("밧데리 얼마나 남았어", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "배터리 잔량 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Place, data.CHIPS_USD_set));
		
		test.log(Status.INFO, "usd action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "usd"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0079")
	public void TC_0079_전원제어_발화체크(Method method) throws Exception {

		test.log(Status.INFO, "W, 전원 꺼 - 발화");
		util.SWFsendPost("전원 꺼", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "전원 꺼 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Place, data.OOS_set));
		
		test.log(Status.INFO, "oos action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "oos"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0091")
	public void TC_0091_환경설정_발화체크(Method method) throws Exception {

		test.log(Status.INFO, "W, 환경 설정 보여줘- 발화");
		util.SWFsendPost("환경 설정 보여줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "홈 화면 변경 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Place, data.CHIPS_USD_set));
		
		test.log(Status.INFO, "usd action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "usd"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0094")
	public void TC_0094_미디어재생중_환경설정_발화체크(Method method) throws Exception {

		test.log(Status.INFO, "미디어 재생상태 전환 W, SK텔레콤 뉴스 - 발화");
		util.SWFsendPost("SK텔레콤 뉴스", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "미디어 재생상태 전환 대기 (미디어재생전 TTS 송출 대기)");
		Thread.sleep(4500);
		
		test.log(Status.INFO, "W, 환경 설정 보여줘 - 발화");
		util.SWFsendPost("환경 설정 보여줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "홈 화면 변경 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Place, data.CHIPS_USD_set));
		
		test.log(Status.INFO, "usd action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "usd"));
		
		test.log(Status.INFO, "미디어 재생 종료");
		util.SWFsendPost("그만", Chips_001, ServerName, AccessToken);

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0098")
	public void TC_0098_홈화면변경_발화체크(Method method) throws Exception {

		test.log(Status.INFO, "W, 홈 화면 변경 - 발화");
		util.SWFsendPost("홈 화면 변경", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "홈 화면 변경 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Place, data.CHIPS_USD_set));
		
		test.log(Status.INFO, "usd action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "usd"));

	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0115")
	public void TC_0115_나이트모드_발화체크(Method method) throws Exception {

		test.log(Status.INFO, "W, 나이트 모드 실행해줘 - 발화");
		util.SWFsendPost("나이트 모드 실행해줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "나이트 모드 미지원 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc_EqualsSet(nuguqa001, Chips_001, ServerName, Place, data.CHIPS_USD_set));
		
		test.log(Status.INFO, "usd action_type 확인");
		Assert.assertTrue(util.actionType_Assertfunc(nuguqa001, Chips_001, ServerName, Place, "usd"));

	}
    
}
