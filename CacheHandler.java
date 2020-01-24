package echo;

import java.util.HashMap;
import java.util.Map;

public class CacheHandler extends ProxyHandler
{
	private static Map<String, String> cache = new HashMap<>();
	
	@Override
	protected synchronized String response(String msg) {
		if(cache.containsKey(msg))
		{
			return cache.get(msg) + ", from cache";
		}
		else
		{
			peer.send(msg);
			String response = peer.receive();
			boolean invalidRequest = response.equalsIgnoreCase("Invalid arguments") || response.equalsIgnoreCase("invalid format");
			if(!invalidRequest)
			{ 
				cache.put(msg, response);
				return response + ", added to cache"; 
			}
			else return response;
		}
	}

}
