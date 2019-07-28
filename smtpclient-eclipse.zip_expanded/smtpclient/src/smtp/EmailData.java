package smtp;

import java.util.*;
import java.util.stream.Collectors;

class EmailData {
	// SMTP uses CRLF as (line/command) delimiter (CR = 13 decimal, LF = 10)
	private static final String CRLF = "" + (char) 0x0D + (char) 0x0A;

	private String from;
	private ArrayList<String> recipients;
	private String subject;
	private String date = new Date().toString();
	private ArrayList<String> additionalHeaders;
	private String body;

	EmailData() {
	}

	public EmailData(String from, Collection<String> recipients, String subject, Collection<String> additionalHeaders,
			String body) {
		this.from = from;
		setRecipients(recipients);
		setAdditionalHeaders(additionalHeaders);
		this.subject = subject;
		this.body = body;
	}

	public List<String> getRecipients() {
		return Collections.unmodifiableList(recipients);
	}

	public void setRecipients(Collection<String> recipients) {
		if (recipients == null)
			throw new IllegalArgumentException();
		recipients.forEach(this::addRecipient);
	}

	public void addRecipient(String recipient) {
		if (recipient == null)
			throw new IllegalArgumentException();
		if (recipients == null)
			recipients = new ArrayList<>();
		recipients.add(recipient);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<String> getAdditionalHeaders() {
		return Collections.unmodifiableList(additionalHeaders);
	}

	public void setAdditionalHeaders(Collection<String> headers) {
		if (headers == null)
			throw new IllegalArgumentException();
		additionalHeaders.forEach(this::addAdditionalHeader);
	}

	public void addAdditionalHeader(String header) {
		if (header == null)
			throw new IllegalArgumentException();
		if (additionalHeaders == null)
			additionalHeaders = new ArrayList<>();
		additionalHeaders.add(header);
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public String toString() {
		// See https://www.ietf.org/rfc/rfc2822.txt

		final String from = this.from != null ? this.from : "";

		// address-list = (address *("," address))
		final String to = recipients.stream().collect(Collectors.joining(","));

		String headers = "";
		for (int i = 0; i < additionalHeaders.size(); i++) {
			String h = additionalHeaders.get(i);
			headers += h;
			if (i + 1 < additionalHeaders.size())
				headers += CRLF;
		}

		final String body = this.body != null ? this.body : "";

		return "From: " + from + CRLF + "To: " + to + CRLF + "Date: " + date + CRLF + "Subject: " + subject + CRLF
				+ headers + CRLF + CRLF + // empty line to separate headers from body
				body + CRLF + "." + CRLF;
	}
}
