package api.Endpoints;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class BlockEndpoints {
	
	
	public static Response getHash() {
		
		Response response=given()
				.when()
				  .get(Routes.getHash);
		
		return response;
			
	}
	
	
	public static Response getBlockInfo(String blockHash) {
		Response response=given()
				.pathParam("blockHash",blockHash)
				.when()
				  .get(Routes.blockInfo);
		
		return response;
	}
	
	
//	public static Response getTxidInfo(String blockHash) {
//		
//		
//		 int start=0;
//		 int interval=25;
//		 int max=100;
//		 Response response=null;
//		 for(int value=start;value<=max;value+=interval) {
//			 response=given()
//						.pathParam("blockHash",blockHash)
//						.pathParam("index", value)
//						.when()
//						  .get(Routes.gettransaction);
//			
//				
//		 }
//		 return response;
//	
//	}

}
