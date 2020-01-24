package echo;

import java.util.*;
import java.io.*;
import java.net.*;

public class Server {

	protected ServerSocket mySocket;
	protected int myPort;
	public static boolean DEBUG = true;
	protected Class<?> handlerType;

	public Server(int port, String handlerType) {
		try {
			myPort = port;
			mySocket = new ServerSocket(myPort);
			this.handlerType = (Class.forName(handlerType));
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} // catch
	}


	public void listen() {
		try {
			while(true) {
				
				RequestHandler reqHandler = makeHandler(mySocket.accept());
				
				Thread thread = new Thread(reqHandler);
				thread.start();
				
				// accept a connection returns a socket by mysocket.accept
				// make handler call makeHandler(pass the created socket)
				// start handler
			} // while
		} catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public RequestHandler makeHandler(Socket s) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		RequestHandler reqHandler = (RequestHandler) handlerType.newInstance();
		reqHandler.setSocket(s);
		// handler = a new instance of handlerType
		// set handler's socket to s
		// RequestHandler handler = null;
		return reqHandler;
	}



	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		int port = 5555;
		String service = "echo.RequestHandler";
		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			port = Integer.parseInt(args[1]);
		}
		Server server = new Server(port, service);
		server.listen();
	}
}