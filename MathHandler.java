package echo;

public class MathHandler extends RequestHandler {
	
	public MathHandler()
	{
		super();
	}
	
	@Override
	public String response(String received)
	{ 
		String[] tokens = received.split("\\s+");
		String operator = tokens[0];
		Double result = 0.0;
		
		try {
			if(operator.equalsIgnoreCase("add")) {
				result = 0.0;
				for(int i = 1; i< tokens.length; i++)
				{
					result += Double.parseDouble(tokens[i]);
				}
			}
			
			else if(tokens[0].equals("mul")) {
				result = 1.0;
				for(int i = 1; i< tokens.length; i++)
				{
					result *= Double.parseDouble(tokens[i]);
				}
			}
			
			else if(tokens[0].equals("sub"))
			{
				result = Double.parseDouble(tokens[1]);
				for(int i = 2; i< tokens.length; i++)
				{
					result -= Double.parseDouble(tokens[i]);
				}
			}
			
			else if(tokens[0].equals("div"))
			{
				result = Double.parseDouble(tokens[1]);
				for(int i = 2; i< tokens.length; i++)
				{
					result /= Double.parseDouble(tokens[i]);
				}
			}
			else {
				return "Invalid format";
			}
		}catch(Exception e){
			
			return "Invalid arguments";
		}
		return String.valueOf(result);

	}
}