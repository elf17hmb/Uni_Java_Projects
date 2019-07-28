package smtp;

import smtp.util.Base64;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.regex.Pattern;

class SmtpClient {
	public void sendEmail(String serverFqdn, int port, SmtpCredentials credentials, String sender,
			Collection<String> recipients, String email) throws IOException {
		InetAddress ip = InetAddress.getByName(serverFqdn);

		sendEmail(ip, port, credentials, sender, recipients, email);
	}

	public void sendEmail(InetAddress server, int port, SmtpCredentials credentials, String sender,
			Collection<String> recipients, String email) throws IOException {
		Socket s = new Socket(server, port);

		startSmtpConversation(s);
		sayHelo(credentials.clientFqdnOrIp, s);
		// login(credentials.username, credentials.password, s);
		sendSender(sender, s);
		sendRecipients(recipients, s);
		sendEmailContent(email, s);
		disconnectFromServer(s);
	}

	private void startSmtpConversation(Socket s) throws IOException {
		String greeting = readLine(s);
		String[] parts = greeting.split(Pattern.quote(" "), 2);
		if (parts.length < 2)
			throw new IOException(); // TODO
		String status = parts[0];

		if ("220".equals(status) == false)
			throw new IOException(); // TODO
	}

	private void sayHelo(String clientFqdnOrIp, Socket s) throws IOException {
		// TODO these checks also belong to the SmtpCredentials ctor
		assert (clientFqdnOrIp != null);
		assert (clientFqdnOrIp.length() > 0);

		writeLine("HELO " + clientFqdnOrIp, s);

		String reply = readLine(s);
		String[] parts = reply.split(Pattern.quote(" "), 2);
		if (parts.length < 1)
			throw new IOException(); // TODO
		String status = parts[0];

		if ("250".equals(status) == false)
			throw new IOException(); // TODO
	}

	private void login(String username, String password, Socket s) throws IOException {
		{
			// initiate login procedure
			writeLine("AUTH LOGIN", s);

			// verify server' reply
			String reply = readLine(s);
			String[] parts = reply.split(Pattern.quote(" "), 2);
			if (parts.length < 2)
				throw new IOException(); // TODO
			String status = parts[0];

			if ("334".equals(status) == false)
				throw new IOException(); // TODO

			String statusInfo = parts[1];
			if (statusInfo.equals(Base64.base64Encode("Username:")) == false)
				throw new IOException(); // TODO
		}

		{
			// send username as Base64 encoded string
			writeLine(Base64.base64Encode(username), s);

			// verify server's reply
			String reply = readLine(s);
			String[] parts = reply.split(Pattern.quote(" "), 2);
			if (parts.length < 2)
				throw new IOException(); // TODO
			String status = parts[0];

			if ("334".equals(status) == false)
				throw new IOException(); // TODO

			String statusInfo = parts[1];
			if (statusInfo.equals(Base64.base64Encode("Password:")) == false)
				throw new IOException(); // TODO
		}

		{
			// send password as Base64 encoded string
			writeLine(Base64.base64Encode(password), s);

			// verify server's reply
			String reply = readLine(s);
			String[] parts = reply.split(Pattern.quote(" "), 2);
			if (parts.length < 2)
				throw new IOException(); // TODO
			String status = parts[0];

			if ("334".equals(status) == false)
				throw new IOException(); // TODO

			String statusInfo = parts[1];
			if (statusInfo.equals(Base64.base64Encode("Password:")) == false)
				throw new IOException(); // TODO
		}
	}

	private void sendSender(String sender, Socket s) throws IOException {
		writeLine("MAIL FROM:" + sender, s);

		String reply = readLine(s);
		if (reply.startsWith("250") == false)
			throw new IOException(); // TODO
	}

	private void sendRecipients(Collection<String> recipients, Socket s) throws IOException {
		assert (recipients.isEmpty() == false);

		// TODO Do it with style (use functional style): recipients.forEach();
		for (String recipient : recipients) {
			writeLine("RCPT TO:" + recipient, s);

			String reply = readLine(s);
			if (reply.startsWith("250") == false)
				throw new IOException(); // TODO
		}
	}

	private void sendEmailContent(String emailContent, Socket s) throws IOException {
		{
			writeLine("DATA", s);

			String reply = readLine(s);
			if (reply.startsWith("354") == false)
				throw new IOException();
		}

		{
			writeLine(emailContent, s);
			writeLine(".", s);

			String reply = readLine(s);
			if (reply.startsWith("250") == false)
				throw new IOException();
		}
	}

	private void disconnectFromServer(Socket s) throws IOException {
//		{
//			writeLine("QUIT", s);
//
//			String reply = readLine(s);
//			System.out.println(reply);
//			if (reply.startsWith("221") == false)
//				throw new IOException();
//		}

		assert (s.isClosed() == false);
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace(); // TODO use proper logging
		}
	}

	private String readLine(Socket from) throws IOException {
		// SMTP uses CRLF as (line/command) delimiter (CR = 13 decimal, LF = 10)
		// TODO globalise
		final char CR = (char) 0x0D;
		final char LF = (char) 0x0A;
		String line = "";

		while (true) {
			int c = from.getInputStream().read();

			if (c == -1)
				return line;
			if (c == CR) {
				c = from.getInputStream().read();
				if (c == -1)
					return line;
				else if (c == LF) {
					return line;
				} else {
					throw new IOException("Expected end of line, got '" + c + "'");
				}
			}

			line += (char) c;
		}
	}

	private void writeLine(String line, Socket to) throws IOException {
		// TODO globalise
		final char CR = (char) 0x0D;
		final char LF = (char) 0x0A;
		final String CRLF = "" + CR + LF;

		write(line + CRLF, to);
	}

	private void write(String s, Socket to) throws IOException {
		to.getOutputStream().write(s.getBytes());
	}
}
