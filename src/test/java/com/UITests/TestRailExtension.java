package com.UITests;

import com.Misc.MyPasswords;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class TestRailExtension implements TestWatcher, AfterAllCallback, 
BeforeAllCallback, BeforeEachCallback, AfterEachCallback {
	
	private String caseID;
	private APIClient client;
	private final int PROJECT_ID=2;
	private final String TESTRAIL_BASE_URL="https://paytronix.testrail.net/";
	private long runID;
	private final String SUITE_ID = "201"; //ID 201 is for test suite "Jeff Test Suite"
	private boolean abortReportingForTestMethod;
	private HashMap<String,String> results = new HashMap<String,String>();

	
    private enum TestResultStatus {
        PASSED("1"), BLOCKED("2"), FAILED("5");
    	
    	private final String label;

        private TestResultStatus(String label) {
            this.label = label;
        }
        
        public String valueAsString() {
            return this.label;
        }
    }
    
    @Override
    public void testSuccessful(ExtensionContext context) {
    	
    	if (!abortReportingForTestMethod) { //if the method has a testrail caseID
    		System.out.println("Test Successful for test {}: " + context.getDisplayName());

    		results.put("status_id",TestResultStatus.PASSED.valueAsString());
    		results.put("custom_testing_environment", "1"); //Josh is making this not required


    		try {
    			client.sendPost("add_result_for_case/" + runID + "/" + caseID, results);
    		} catch (IOException e) {
    			e.printStackTrace();
    		} catch (APIException e) {
    			e.printStackTrace();
    		}
    	}      
    } 
    
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
    	
    	if (!abortReportingForTestMethod) { //if the method has a testrail caseID
    		System.out.println("Test Failed for test {}: " + context.getDisplayName());

    		results.put("status_id", TestResultStatus.FAILED.valueAsString());
    		results.put("custom_testing_environment", "1"); //Josh is making this not required

    		try {
    			client.sendPost("add_result_for_case/" + runID + "/" + caseID, results);
    		} catch (IOException e) {
    			e.printStackTrace();
    		} catch (APIException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
  	
    	Object runIDFromGlobal = context.getRoot().getStore(GLOBAL).get("runID");
    	//System.out.println("runIDFromGlobal is " + runIDFromGlobal);   	

    	try {
    		client = new APIClient(TESTRAIL_BASE_URL);
    		client.setUser(MyPasswords.TESTRAIL_USERNAME.toString());
    		client.setPassword(MyPasswords.TESTRAIL_PASSWORD.toString());

    		//delete some test runs
    		//ArrayList<String> runIDs = new ArrayList<String>();
    		//runIDs.add("188");
    		//for (String r : runIDs) {
    		//	Map data = new HashMap();
    		//	client.sendPost("delete_run/"+r,data);
    		//}

    		if (runIDFromGlobal == null) {
    			//Create Test Run
    			HashMap<String,Object> data = new HashMap<String,Object>();
    			data.put("include_all",true);
    			data.put("LoginTest1","Test Run "+System.currentTimeMillis());
    			data.put("suite_id",SUITE_ID);
    			JSONObject c = (JSONObject)client.sendPost("add_run/"+PROJECT_ID,data);
    			
    			runID = (Long)c.get("id");
    			context.getRoot().getStore(GLOBAL).put("runID", runID);
    			System.out.println("runID is set to " + runID);
    		}
    		else {
    			//System.out.println("runIDFromGlobal is " + runIDFromGlobal);
    			runID = (Long)runIDFromGlobal;
    			System.out.println("runID is set to " + runID);
    		}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Override
    public void afterAll(ExtensionContext context) throws Exception {}
    
    @Override
	public void beforeEach(ExtensionContext context) throws Exception {
    	
    	abortReportingForTestMethod = false;
    	Method m = context.getRequiredTestMethod();
    	String className = context.getRequiredTestClass().getSimpleName();
    	String methodName = context.getRequiredTestMethod().getName();
               
        if (m.isAnnotationPresent(TestRail.class) && m.getAnnotation(TestRail.class).id() != "none") {
	        TestRail tr = m.getAnnotation(TestRail.class);
	        caseID = tr.id();
	        
	        //update the test suite in case the method or class name changed
	        HashMap<String,String> data = new HashMap<String,String>();
			data.put("case_id",caseID);
			data.put("title", className + " :: " + methodName);
	        try {
	            client.sendPost("update_case/" + caseID, data);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (APIException e) {
	            e.printStackTrace();
	        }
	        //System.out.println("The testrail case id for method " + m + " is set to " + caseID);
	    }
        else {
        	abortReportingForTestMethod = true;
        	System.out.println("There is no testrail annotation for method " + m);
        }	
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {}
 
    
  }