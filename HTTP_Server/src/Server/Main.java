package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Main
{

	public static void main(String[] args) throws IOException, InterruptedException
	{
		final InetAddress addr = InetAddress.getByName("localhost");
		final int backlog = 3; // Anzahl von Client-Verbindungsanfragen, die gepuffert werden, falls Server
								// bereits eine Anfrage bearbeitet
		try (final ServerSocket listenSocket = new ServerSocket(12345, backlog, addr))
		{
			while (true)
			{
				System.out.println("Server: Waiting for connections on port " + listenSocket.getLocalPort());
				// Auf Verbindung vom Client warten
				final Socket connectionSocket = listenSocket.accept();
				// Neuen Thread starten, der Kommunikation mit Client übernimmt
				Thread t = new Thread()
				{
					@Override
					public void run()
					{
						try
						{
							while (true)
							{
								Map<String, String> resources = new HashMap<>();
								resources.put("/index.html", "<html>\r\n" + "<body>\r\n" + "<h1>Hello, World!</h1>\r\n" + "</body>\r\n" + "</html>");

								System.out.println("Accepted: " + connectionSocket.getLocalPort());
								System.out.println("		  " + connectionSocket.getPort());

								BufferedReader inFromClient = new BufferedReader(//
										new InputStreamReader(connectionSocket.getInputStream()));
								DataOutputStream outToClient = new DataOutputStream(//
										connectionSocket.getOutputStream());
								final String httpRequest = inFromClient.readLine(); // Kommando, das der Client sendet
								System.out.println("Received: " + httpRequest);

								String[] parts = httpRequest.split(Pattern.quote(" "), 3);
								if (parts.length < 2)
									throw new IOException(); // TODO
								String request = parts[0];
								String formular = parts[1];

								System.out.println("REQUEST TYPE: " + request);
								System.out.println("FORMULAR: " + formular);

								if (request.equals("GET"))
								{
									String response = resources.get(formular);
									outToClient.writeBytes(formular);
								}

								if (request.equals("POST"))
								{

								}
//								Server server = new Server();
//								server.startSmtpConversation(connectionSocket);
//								if (clientCommand.equals("sleep"))
//									Thread.sleep(TimeUnit.SECONDS.toMillis(30));
//
//								final String reply = clientCommand.toUpperCase() + '\n'; // Antwort des Servers erstellen
//								outToClient.writeBytes(reply); // Antwort senden
//
//								if (clientCommand.equals("stop"))
//									return;
							}
						} catch (Exception e)
						{
							System.err.println(e.getMessage());
						}
					};
				};
				t.start();
			}
		}
	}

}
