package smtp;

import java.util.Arrays;
import java.util.List;

// https://en.wikipedia.org/wiki/Email#Internet_Message_Format
// https://www.ietf.org/rfc/rfc2822.txt
final class HtmlEmailBuilder {
	EmailData email = new EmailData();

	{
		email.addAdditionalHeader("Mime-Version: 1.0;");
		email.addAdditionalHeader("Content-Type: text/html; charset=\"ISO-8859-1\";");
		email.addAdditionalHeader("Content-Transfer-Encoding: 7bit;");
	}

	public HtmlEmailBuilder from(String from) {
		email.setFrom(from);
		return this;
	}

	public HtmlEmailBuilder to(String to) {
		return to(Arrays.asList(to));
	}

	public HtmlEmailBuilder to(String... tos) {
		return to(Arrays.asList(tos));
	}

	public HtmlEmailBuilder to(List<String> tos) {
		for (String recipient : tos)
			email.addRecipient(recipient);
		return this;
	}

	public HtmlEmailBuilder subject(String subject) {
		email.setSubject(subject);
		return this;
	}

	public HtmlEmailBuilder htmlBody(String body) {
		email.setBody(body);
		return this;
	}

	public EmailData getEmail() {
		return email;
	}
}
