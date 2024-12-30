package api.test;

import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.Endpoints.BlockEndpoints;
import api.Endpoints.Routes;
import io.restassured.response.Response;

public class BlockTest {
	
	public static String blockHash;
	
	@Test(priority=1)
	public void getHashInfo() {
		
		Response response=BlockEndpoints.getHash();
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
	    
		 blockHash=response.getBody().asString() ;	
		 System.out.println(blockHash);
	}
	
	 @Test(priority=2)
	 public void validateBlockInfo() {
		
		Response response=BlockEndpoints.getBlockInfo(blockHash);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("tx_count"),2875);
		
	}
	 
	 
	 @Test(priority=3)
	 public void getTxid() {
		 
		 int offset=0;
		 int interval=25;
	   
	     int totalpages=115;
	     boolean searchtx1=false;
	     boolean searchtx2=false;
	     
	     String[] txids= {"96d92f03000f625a38bf8cb91c01188a02b7972238cc6c4e0c6f334cf755004d","6dd68336c085d5b7b694e2bf6f6c11bca589aea07b6f1c0232bd627c3d217074"};
	     
	
    while(searchtx1==false && searchtx2==false) {
	     
	     
	     for(int i=0;i<=totalpages;i++) {
    	
    	 String offStr=String.valueOf(offset);
		 Response response=given()
						.pathParam("blockHash",blockHash)
						.pathParam("index", offStr)
						.when()
						  .get(Routes.gettransaction);
			 
			 System.out.println(response.getBody().asString());
			 
		     Assert.assertEquals(response.statusCode(), 200);
		     
		    JSONArray jrr=new JSONArray(response.asString());
		    
		    for(int j=0;j<jrr.length();j++) {
		    	
			String txid=jrr.getJSONObject(j).getString("txid");
		    
				if(txid.contains(txids[0])) {
				searchtx1=true;
				break;
			}
			

			if(txid.contains(txids[1])) {
					searchtx2=true;
					break;
				}
			}		
		 }

	 offset+=interval;
    }
	}
 }
