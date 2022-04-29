package com.eb.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.eb.model.EBConnection;
import com.eb.util.ElectricityBoard;
import com.eb.exception.InvalidEBConnectionException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ElectricityBoardTest {
	
    @Rule
    public ExpectedException exceptionRule=ExpectedException.none();
	private static ElectricityBoard ebObj;
	private static List<EBConnection> ebConnectionList = new ArrayList<>();
	private static EBConnection eb1;
	private static EBConnection eb2;
	private static EBConnection eb3;
	private static EBConnection eb4;
	


	@BeforeClass
	public static void setUp() throws Exception {

		ebObj = new ElectricityBoard();
		eb1=new EBConnection(123,"Aviral","Domestic","av123","Three phase");
		eb2=new EBConnection(111,"Avi","Commercial","av111","Three phase");
		eb3=new EBConnection(100,"Anand","Industrial","av100","Three phase");
		eb4=new EBConnection(101,"ani","Domestic","av101","Single phase");
		
		ebConnectionList.add(eb1);
		ebConnectionList.add(eb2);
		ebConnectionList.add(eb3);
		ebConnectionList.add(eb4);
		
		ebObj.setEbConnectionList(ebConnectionList);
	
		//Create few  objects for EBConnection class and add to a list.
		//Set that list to the ebConnectionList using the setEbConnectionList method in ElectricityBoard class 
	}
	
	
	//Test the validateConnectionType method when the value is domestic
	@Test
	public void test11ValidateConnectionTypeWhenDomestic() throws InvalidEBConnectionException{
		//Fill the code
		assertTrue(ebObj.validateConnectionType("Domestic"));
	}
	
	//Test the validateConnectionType method when the value is commertial
	@Test
	public void test12ValidateConnectionTypeWhenCommercial() throws InvalidEBConnectionException{
		//Fill the code
		assertTrue(ebObj.validateConnectionType("Commercial"));
	}
	
	//Test the validateConnectionType method when the value is industrial
	@Test
	public void test13ValidateConnectionTypeWhenIndustrial() throws InvalidEBConnectionException {
		//Fill the code
		assertTrue(ebObj.validateConnectionType("Industrial"));
	}
	
	//Test the validateConnectionType method when the value is invalid
	@Test
	public void test14ValidateConnectionTypeWhenInvalid() throws InvalidEBConnectionException{
		//Fill the code
		exceptionRule.expect(InvalidEBConnectionException.class);
		exceptionRule.expectMessage("Connection type invalid");
		assertTrue(ebObj.validateConnectionType("dom"));
	}
	
	//Test the viewEBConnectionById method when the value is valid
	@Test
	public void test15ViewEBConnectionByIdWhenValid() throws InvalidEBConnectionException {
        //Fill the code
        assertEquals(eb1,ebObj.viewEBConnectionById(123));
	}
	
	//Test the viewEBConnectionById method when the value is invalid
	@Test
	public void test16ViewEBConnectionByIdWhenInvalid() throws InvalidEBConnectionException {
        //Fill the code
        exceptionRule.expect(InvalidEBConnectionException.class);
		exceptionRule.expectMessage("Connection ID is invalid");
        assertEquals(eb1,ebObj.viewEBConnectionById(120));
	}

    //Test the viewEBConnectionsByConnectionType method
    @Test
	public void test17ViewEBConnectionsByConnectionType() throws InvalidEBConnectionException{
	    List<EBConnection> eblist=new ArrayList<>();
	    eblist.add(eb1);
	    eblist.add(eb4);
	    assertEquals(eblist,ebObj.viewEBConnectionsByConnectionType("Domestic"));
	}

    //Test the viewEBConnectionsConnectionTypeWise method
    @Test
	public void test18ViewEBConnectionsConnectionTypeWise() throws InvalidEBConnectionException{
        //Fill the code
        Map<String,List<EBConnection>> result = new LinkedHashMap<>();
        List<EBConnection> eblist1=new ArrayList<>();
        List<EBConnection> eblist2=new ArrayList<>();
        List<EBConnection> eblist3=new ArrayList<>();
        eblist1.add(eb1);
        eblist1.add(eb4);
        eblist2.add(eb2);
        eblist3.add(eb3);
        result.put("Domestic",eblist1);
        result.put("Commercial",eblist2);
        result.put("Industrial",eblist3);
        assertEquals(result,ebObj.viewEBConnectionsConnectionTypeWise());
	}
	
	//Test the countTotalConnectionForEachPhase method
	@Test
	public void test19CountTotalConnectionForEachPhase() throws InvalidEBConnectionException{
		//Fill the code
		Map<String,Integer> result = new LinkedHashMap<>();
		result.put("Three phase",3);
		result.put("Single phase",1);
		assertEquals(result,ebObj.countTotalConnectionForEachPhase());
	}

    //Test the viewEBConnectionsByConnectionType method when the list is empty
    @Test
	public void test20ViewEBConnectionsByConnectionTypeForEmptyList() throws InvalidEBConnectionException {
		//Fill the code
		List<EBConnection> eblist=new ArrayList<>();
		ebObj.setEbConnectionList(eblist);
		exceptionRule.expect(InvalidEBConnectionException.class);
		exceptionRule.expectMessage("List is empty");
		assertEquals(eblist,ebObj.viewEBConnectionsByConnectionType("Domestic"));
	}

    //Test the viewEBConnectionsConnectionTypeWise method when the list is empty
    @Test
	public void test21ViewEBConnectionsConnectionTypeWiseForEmptyList() throws InvalidEBConnectionException {
        //Fill the code
        Map<String,List<EBConnection>> result = new LinkedHashMap<>();
        List<EBConnection> eblist=new ArrayList<>();
		ebObj.setEbConnectionList(eblist);
		exceptionRule.expect(InvalidEBConnectionException.class);
		exceptionRule.expectMessage("List is empty");
		assertEquals(result,ebObj.viewEBConnectionsConnectionTypeWise());
	}
    //Test the countTotalConnectionForEachPhase method when the list is empty
    @Test
	public void test22CountTotalConnectionForEachPhaseForEmptyList() throws InvalidEBConnectionException{
        //Fill the code
        Map<String,Integer> result = new LinkedHashMap<>();
    	List<EBConnection> eblist=new ArrayList<>();
		ebObj.setEbConnectionList(eblist);
		exceptionRule.expect(InvalidEBConnectionException.class);
		exceptionRule.expectMessage("List is empty");
		assertEquals(result,ebObj.countTotalConnectionForEachPhase());
	}
}

