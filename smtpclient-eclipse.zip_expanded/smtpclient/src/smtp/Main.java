package smtp;

import smtp.util.IpChecker;

class Main
{
	public static void main(String[] args) throws Exception
	{
		final String smtpServer = "141.32.28.113";

		final String clientIp = IpChecker.getMyIp();
		final SmtpCredentials smtpLoginCredentials = new SmtpCredentials(clientIp);

		final EmailData email = new HtmlEmailBuilder()//
				.from("fgr@fh-zwickau.de")//
				.to("eldiyar98@gmail.com") //
				.subject("Test 1")//
				.htmlBody(//
						"<html><body><h1>Überschrift</h1>" + //
								"<p>Text</p><p>Lorem ipsum dolor sit amet...</p>" + //
								"<body></html>")
				.getEmail();

		final SmtpClient smtpClient = new SmtpClient();

		smtpClient.sendEmail(smtpServer, 25, smtpLoginCredentials, //
				email.getFrom(), email.getRecipients(), email.toString());
	}
}
