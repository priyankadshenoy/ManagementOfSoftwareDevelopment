//package userInterface;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//
//import org.junit.Test;
//
//import junit.framework.TestListener;
//import msd.user.inf.UserQuery;
//import msd.user.inf.interfaces.InputCapture;
//import msd.user.inf.ui.QueryOutput;
//import msd.queryEngine.UseCaseTwoOutput;
//import msd.queryEngine.interfaces.OutputFormatter;
//public class TestUserInterface {
//
//	OutputFormatter o = new UseCaseTwoOutput();
//	
//	/**
//	 * Method to check if the user query object recieves data
//	 */
//	@Test
//	public void checkUserQueryObject() {
//		String returnVal = "UserQuery [keyword=keyword, fromTime=1970, toTime=2017, conferenceName=oopsla, count=1, paper=1, confOrCpm=false]";
//		InputCapture userIn = new UserQuery("keyword",	1970, 2017, "oopsla", 1,1,false);
//		assertEquals(returnVal, userIn.toString());
//	}
//	
//	/**
//	 * Method to check if the user query object receives data for keyword
//	 */
//	@Test
//	public void checkKeywordSet() {
//		InputCapture userIn = new UserQuery();
//		userIn.setKeyword("keyword");
//		assertEquals("keyword", userIn.getKeyword());
//	}
//	
//	/**
//	 * Method to check if the user query object receives data for conference
//	 */
//	@Test
//	public void checkConfSet() {
//		InputCapture userIn = new UserQuery();
//		userIn.setConferenceName("conf");
//		assertEquals("conf", userIn.getConferenceName());
//	}
//	
//	/**
//	 * Method to check if the user query object receives data for citation count
//	 */
//	@Test
//	public void checkCountSet() {
//		InputCapture userIn = new UserQuery();
//		userIn.setCount(1);
//		assertEquals(1, userIn.getCount());
//	}
//	
//	/**
//	 * Method to check if the user query object receives data for article count
//	 */
//	@Test
//	public void checkPaperdSet() {
//		InputCapture userIn = new UserQuery();
//		userIn.setPaper(2);
//		assertEquals(2, userIn.getPaper());
//	}
//	
//	/**
//	 * Method to check if the user query object receives data for time
//	 */
//	@Test
//	public void checkTimeSet() {
//		InputCapture userIn = new UserQuery();
//		userIn.setToTime(2017);
//		userIn.setFromTime(2007);
//		assertEquals(2007, userIn.getFromTime());
//		assertEquals(2017, userIn.getToTime());
//	}
//
//	/**
//	 * Method to check if the user query object receives data for boolean
//	 */
//	@Test
//	public void checkConfOrCpmSet() {
//		InputCapture userIn = new UserQuery();
//		userIn.setConfOrCpm(false);
//		assertEquals(false, userIn.isConfOrCpm());
//	}
//
//
//
//}
