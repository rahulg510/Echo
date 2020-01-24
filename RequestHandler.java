package echo;

import java.net.Socket;

public class RequestHandler extends Correspondent implements Runnable {
	public RequestHandler(Socket s) { super(s); }
	public RequestHandler() { }
	// override in a subclass:
	protected String response(String msg) {
		return "echo: " + msg;
	}
	public void run() {
		while(true) {
			String received = receive();
			if(received == null) continue;
			if(received.equals("quit") ) {
				break;
			}
		String response = response(received);
			send(response);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			// receive request
			// send response
			// sleep
		}
		// close
	}
}
