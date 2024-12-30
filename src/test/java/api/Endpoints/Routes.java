package api.Endpoints;

public class Routes {
	
	public static String base_Url="https://blockstream.info/api";
	
	// Block Info
	
	public static String getHash=base_Url+ "/block-height/680000";
	public static String blockInfo=base_Url+"/block/{blockHash}";
	public static String gettransaction=base_Url+"/block/{blockHash}/txs/{index}";
	
	
}
