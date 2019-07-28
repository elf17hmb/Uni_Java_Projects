package Server;

import java.io.IOException;
import java.net.Socket;
import java.util.regex.Pattern;

public class Server
{
	public void startSmtpConversation(Socket s) throws IOException
	{
		System.out.println("TEST");
		String httpRequest = readLine(s);
		String[] parts = httpRequest.split(Pattern.quote(" "), 2);
		if (parts.length < 2)
			throw new IOException(); // TODO
		String request = parts[0];

		System.out.println("REQUEST TYPE: " + request);

	}

	private String readLine(Socket from) throws IOException
	{
		// SMTP uses CRLF as (line/command) delimiter (CR = 13 decimal, LF = 10)
		// TODO globalise
		final char CR = (char) 0x0D;
		final char LF = (char) 0x0A;
		String line = "";

		while (true)
		{
			int c = from.getInputStream().read();

			if (c == -1)
				return line;
			if (c == CR)
			{
				c = from.getInputStream().read();
				if (c == -1)
					return line;
				else if (c == LF)
				{
					return line;
				} else
				{
					throw new IOException("Expected end of line, got '" + c + "'");
				}
			}

			line += (char) c;
		}
	}

	private void writeLine(String line, Socket to) throws IOException
	{
		// TODO globalise
		final char CR = (char) 0x0D;
		final char LF = (char) 0x0A;
		final String CRLF = "" + CR + LF;

		write(line + CRLF, to);
	}

	private void write(String s, Socket to) throws IOException
	{
		to.getOutputStream().write(s.getBytes());
	}
}
