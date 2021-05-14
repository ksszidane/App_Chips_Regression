package Chips_001_실행;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import junit.framework.Assert;
import unit.Chips_TestCase;

public class 실행과로그인테스트 extends Chips_TestCase {
	
	String AccessToken;
	
	public void accessToken얻기() throws Exception {
		
		test.log(Status.INFO, "coach_mark_close 클릭"); 
		util.click(By.id("coach_mark_close"));
		
		test.log(Status.INFO, "chips 선택"); 
	    util.click(By.xpath("//android.support.v7.widget.RecyclerView"
	    		+ "/android.view.ViewGroup"));
	  
	    test.log(Status.INFO, "play카드 닫기"); 
	    util.view_close_btn_check();
	    
	    test.log(Status.INFO, "transaction id 얻기"); 
	    String tid = util.TransactionID_JsonParsing(ksszidane, Chips_did, ServerName, Place);
	    
	    test.log(Status.INFO, "acceesToken 얻기"); 
	    String actn = util.acceesToken_JsonParsing(ServerName, Place, tid);
	    
	    AccessToken = actn;
		
	}

	
	@Test(description = "칩스 리그레이션 TC : 실행_000")
	public void TC_앱실행_000(Method method) throws Exception {

		test.log(Status.INFO, "AppActivity으로 화면 확인");
	    util.switchContext("NATIVE_APP");
		
		test.log(Status.INFO, "접근권한 허용 버튼 클릭");
		util.click(By.xpath("//android.widget.Button[@text='접근 권한 허용']"));
		
		test.log(Status.INFO, "퍼미션 오디오 녹음 권한 허용 "); 
	    util.switchTo().alert().accept();
	    
	    test.log(Status.INFO, "퍼미션 위치 권한 허용 "); 
	    util.switchTo().alert().accept();
	    
	    test.log(Status.INFO, "NUGU CHIPS 빠른 설정 가이드 [X]버튼 닫기 "); 
	    util.click(By.id("btn_close"));
	    
	    test.log(Status.INFO, "세션 만료 후 로그인 시도"); 
	    util.click(By.id("loginButton"));
	    
	    test.log(Status.INFO, "WEBVIEW로 화면 전환");
        util.switchContext("WEBVIEW");
        
        test.log(Status.INFO, "저장된 간편로그인 유효성 체크 및 클릭");
	    util.click(By.xpath("//ul[@class='account-list']/li[1]"));
	    
	    test.log(Status.INFO, "NATIVE로 화면 확인");
	    util.switchContext("NATIVE_APP");

	    accessToken얻기();
	    
	    util.SWFsendPost("몇시야", ServerName, AccessToken);
	    
	}
    
}
