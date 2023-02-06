	package Chips_006_생활정보_날씨;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_08_날씨_습도 extends Chips_TestCase {
	
	String AccessToken;
	String 현재위치;
	
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
	    String tid = util.TransactionID_JsonParsing(nuguqa001, Chips_001, ServerName, Place, Service);
	    
	    test.log(Status.INFO, "acceesToken 얻기"); 
	    String actn = util.acceesToken_JsonParsing(ServerName, Place,  tid);
	    
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0757")
	public void TC_0757_Chips_날씨_습도지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
		test.log(Status.INFO, "현재위치 정보값 저장");
		String str = util.getText(By.id("location"));
		String[] array = str.split(" ");
		현재위치 = array[2];
		System.out.println("현재위치:"+현재위치);
		
		test.log(Status.INFO, "W, 오늘 습도 어때? - 발화");
		util.SWFsendPost("오늘 습도 어때?", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "자외선 지수 A구간[5월~9월] | B구간[10월~4월] 체크");
		String 습도시간대 = util.Month5to9();
		
		if (습도시간대.equals("A구간")) {
			test.log(Status.INFO, "[5월~9월] 습도 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place, Service);
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "현재 " + 현재위치 + " 습도는"));
			Assert.assertTrue(data.습도지수체크(tts));
			
		} else if (습도시간대.equals("B구간")) {
			test.log(Status.INFO, "[10월~4월] 습도 지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "현재 " + 현재위치 + " 습도는"));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0758")
	public void TC_0758_Chips_날씨_지역_습도지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 오늘 부산 습도 정보 - 발화");
		util.SWFsendPost("오늘 부산 습도 정보", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "자외선 지수 A구간[5월~9월] | B구간[10월~4월] 체크");
		String 습도시간대 = util.Month5to9();
		
		if (습도시간대.equals("A구간")) {
			test.log(Status.INFO, "[5월~9월] 습도 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place, Service);
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "현재 부산 습도는"));
			Assert.assertTrue(data.습도지수체크(tts));
			
		} else if (습도시간대.equals("B구간")) {
			test.log(Status.INFO, "[10월~4월] 습도 지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "현재 부산 습도는"));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0759")
	public void TC_0759_Chips_날씨_내일_습도지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 내일 습도 알려줘 - 발화");
		util.SWFsendPost("내일 습도 알려줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "자외선 지수 A구간[5월~9월] | B구간[10월~4월] 체크");
		String 습도시간대 = util.Month5to9();
		
		if (습도시간대.equals("A구간")) {
			test.log(Status.INFO, "[5월~9월] 습도 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place, Service);
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "내일 " + 현재위치 + " 습도는"));
			Assert.assertTrue(data.습도지수체크(tts));
			
		} else if (습도시간대.equals("B구간")) {
			test.log(Status.INFO, "[10월~4월] 습도 지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "내일 " + 현재위치 + " 습도는"));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0760")
	public void TC_0760_Chips_날씨_내일지역_습도지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 내일 부산 습도 얼마야? - 발화");
		util.SWFsendPost("내일 부산 습도 얼마야?", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "자외선 지수 A구간[5월~9월] | B구간[10월~4월] 체크");
		String 습도시간대 = util.Month5to9();
		
		if (습도시간대.equals("A구간")) {
			test.log(Status.INFO, "[5월~9월] 습도 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place, Service);
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "내일 부산 습도는"));
			Assert.assertTrue(data.습도지수체크(tts));
			
		} else if (습도시간대.equals("B구간")) {
			test.log(Status.INFO, "[10월~4월] 습도 지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "내일 부산 습도는"));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0751")
	public void TC_0761_Chips_날씨_모레_습도지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 모레 습도 상태 - 발화");
		util.SWFsendPost("모레 습도 상태", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "습도 지수 A구간[5월~9월] | B구간[10월~4월] 체크");
		String 습도시간대 = util.Month5to9();
		String 모레날짜 = util.getChangePreviousDate(2);
		String 모레요일 = util.getDayOfWeek(2);
		
		if (습도시간대.equals("A구간")) {
			test.log(Status.INFO, "[5월~9월] 습도 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place, Service);
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  모레날짜 + " " + 모레요일 + "요일 " + 현재위치 + " 습도는"));
			Assert.assertTrue(data.습도지수체크(tts));
			
		} else if (습도시간대.equals("B구간")) {
			test.log(Status.INFO, "[10월~4월] 습도 지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  모레날짜 + " " + 모레요일 + "요일 " + 현재위치 + " 습도는"));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0762")
	public void TC_0762_Chips_날씨_모레지역_습도지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 모레 부산 습도 상태 - 발화");
		util.SWFsendPost("모레 부산 습도 상태", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "습도 지수 A구간[5월~9월] | B구간[10월~4월] 체크");
		String 습도시간대 = util.Month5to9();
		String 모레날짜 = util.getChangePreviousDate(2);
		String 모레요일 = util.getDayOfWeek(2);
		
		if (습도시간대.equals("A구간")) {
			test.log(Status.INFO, "[5월~9월] 습도 지수 TTS 확인");
			String tts = util.TTS_JsonParsing_most_recent(nuguqa001, Chips_001, ServerName, Place, Service);
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  모레날짜 + " " + 모레요일 + "요일 부산 습도는"));
			Assert.assertTrue(data.습도지수체크(tts));
			
		} else if (습도시간대.equals("B구간")) {
			test.log(Status.INFO, "[10월~4월] 습도 지수 TTS 확인");
			Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  모레날짜 + " " + 모레요일 + "요일 부산 습도는"));
		}
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0763")
	public void TC_0763_Chips_날씨_습도지수부재_어제_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 어제 습도 알려줘 - 발화");
		util.SWFsendPost("어제 습도 알려줘", Chips_001, ServerName, AccessToken);

		test.log(Status.INFO, "어제 습도지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "저는 오늘부터 모레까지의 습도 정보만 가지고 있어요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0764")
	public void TC_0764_Chips_날씨_습도지수부재_특정일_확인(Method method) throws Exception {
	    
	    test.log(Status.INFO, "그제 날짜 계산");
		String 그제날짜 = util.getChangePreviousDate(-2);
		
		test.log(Status.INFO, "W, "+ 그제날짜 +" 습도 정보 알려줘 - 발화");
		util.SWFsendPost(그제날짜 + " 습도 정보 알려줘", Chips_001, ServerName, AccessToken);

		test.log(Status.INFO, 그제날짜 + " 습도지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "저는 오늘부터 모레까지의 습도 정보만 가지고 있어요."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0765")
	public void TC_0765_Chips_날씨_습도지수부재_지역외_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 로스앤젤레스 습도 알려줘 - 발화");
		util.SWFsendPost("로스앤젤레스 습도 알려줘", Chips_001, ServerName, AccessToken);

		test.log(Status.INFO, "지역외 습도지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "말씀하신 지역의 습도 정보는 가지고 있지 않습니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0766")
	public void TC_0766_Chips_날씨_습도지수부재_지역아님_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 하이마트 습도 어때? - 발화");
		util.SWFsendPost("하이마트 습도 어때?", Chips_001, ServerName, AccessToken);

		test.log(Status.INFO, "지역아닌곳 습도지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Place, Service,  "말씀하신 지역의 습도 정보는 가지고 있지 않습니다."));
	}
	


}
