package Chips_006_생활정보_날씨;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Chips_000_xPath.xPath;
import TestNG_Set.Chips_TestCase;
import junit.framework.Assert;

public class 생활정보_12_날씨_세차지수 extends Chips_TestCase {
	
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
	    String tid = util.TransactionID_JsonParsing(nuguqa001, Chips_001, ServerName, Service);
	    
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
	
	@Test(description = "칩스 리그레이션 TC : 실행_0787")
	public void TC_0787_Chips_날씨_세차지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
		
		test.log(Status.INFO, "현재위치 정보값 저장");
		String str = util.getText(By.id("location"));
		String[] array = str.split(" ");
		현재위치 = array[2];
		
		test.log(Status.INFO, "W, 세차하기 어때? - 발화");
		util.SWFsendPost("세차하기 어때?", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  현재위치 + " 지역"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "세차"));
	
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0788")
	public void TC_0788_Chips_날씨_오늘세차지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 오늘 세차지수 알려줘 - 발화");
		util.SWFsendPost("오늘 세차지수 알려줘", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  현재위치 + " 지역"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "세차"));
	
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0789")
	public void TC_0789_Chips_날씨_지정위치읍면동_세차지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 강원도 강릉시 세차지수 어때 - 발화");
		util.SWFsendPost("강원도 강릉시 세차지수 어때", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "지정위치 세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "강원도 강릉시 지역"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "세차"));
	
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0790")
	public void TC_0790_Chips_날씨_지정위치읍면동_세차지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 제주시 애월읍 세차지수 - 발화");
		util.SWFsendPost("제주시 애월읍 세차지수", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "지정위치 세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "제주시 애월읍 지역"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "세차"));
	
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0791")
	public void TC_0791_Chips_날씨_지정위치읍면동_세차지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 중랑구 신내동 세차지수 - 발화");
		util.SWFsendPost("중랑구 신내동 세차지수", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "지정위치 세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "중랑구 신내동 지역"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "세차"));
	
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0792")
	public void TC_0792_Chips_날씨_지정위치특별광역시_세차지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 세종특별자치시 세차지수 몇이야? - 발화");
		util.SWFsendPost("세종특별자치시 세차지수 몇이야?", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "지정위치 세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "세종특별자치시 지역"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "세차"));
	
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0793")
	public void TC_0793_Chips_날씨_지정위치특별광역시_세차지수_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 울산광역시 세차해도 돼? - 발화");
		util.SWFsendPost("울산광역시 세차해도 돼?", Chips_001, ServerName, AccessToken);
		
		test.log(Status.INFO, "지정위치 세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "울산광역시 지역"));
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "세차"));
	
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0796")
	public void TC_0796_Chips_날씨_세차지수부재_지역외_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 로스앤젤레스 세차지수 알려줘 - 발화");
		util.SWFsendPost("로스앤젤레스 세차지수 알려줘", Chips_001, ServerName, AccessToken);

		test.log(Status.INFO, "지역외 세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "말씀하신 지역의 세차지수 정보는 가지고 있지 않습니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0797")
	public void TC_0797_Chips_날씨_세차지수부재_지역아님_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 하이마트 세차지수 어때? - 발화");
		util.SWFsendPost("하이마트 세차지수 어때?", Chips_001, ServerName, AccessToken);

		test.log(Status.INFO, "지역아닌곳 세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "말씀하신 지역의 세차지수 정보는 가지고 있지 않습니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0798")
	public void TC_0798_Chips_날씨_세차지수부재_이번주_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 이번주 세차지수 정보 - 발화");
		util.SWFsendPost("이번주 세차지수 정보", Chips_001, ServerName, AccessToken);

		test.log(Status.INFO, "지역아닌곳 세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "저는 오늘의 세차지수 정보만 가지고 있습니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0799")
	public void TC_0799_Chips_날씨_세차지수부재_다음주_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 다음주 세차지수 정보 - 발화");
		util.SWFsendPost("다음주 세차지수 정보", Chips_001, ServerName, AccessToken);

		test.log(Status.INFO, "지역아닌곳 세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "저는 오늘의 세차지수 정보만 가지고 있습니다."));
	}
	
	@Test(description = "칩스 리그레이션 TC : 실행_0800")
	public void TC_0800_Chips_날씨_세차지수부재_모레_확인(Method method) throws Exception {
		
		test.log(Status.INFO, "W, 모레 세차 할까? - 발화");
		util.SWFsendPost("모레 세차 할까?", Chips_001, ServerName, AccessToken);

		test.log(Status.INFO, "지역아닌곳 세차지수 TTS 확인");
		Assert.assertTrue(util.TTS_Assertfunc(nuguqa001, Chips_001, ServerName, Service,  "저는 오늘의 세차지수 정보만 가지고 있습니다."));
	}

}
