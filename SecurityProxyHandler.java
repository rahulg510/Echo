package echo;

import java.util.HashMap;
import java.util.Map;

public class SecurityProxyHandler extends ProxyHandler {
	 
	private static Map<String,String> credentials = new HashMap<>();
	private boolean validated = false;
	private boolean stopUser = false;
	
	@Override
	protected String response(String request)
	  { 
		if(stopUser)
		{
			return "Please type \"quit\" to end session. Create account";
		}
		else if(validated)
		{
			peer.send(request);
			String msg = peer.receive();
			return msg;
		}
			if(!validated) 
			{
				String[] tokens = request.split("\\s+");
				String firstInput = tokens[0];
				if(firstInput.equalsIgnoreCase("new"))
				{
					credentials.put(tokens[1], tokens[2]);
					validated = true;
					return "validated";
				}
				else
				{
					if(credentials.containsKey(firstInput))
					{
						String password = credentials.get(firstInput);
						if(password.equals(tokens[1]))
						{
							validated = true;
							return "validated";
						}
					
					}
					else
					{
						stopUser = true;
					}
				}
		
		
		
			}

			return "Please type \"quit\" to end session. Create an account";
	  }
	  
	 
}

