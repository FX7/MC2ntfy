package fx7.ntfy.sender;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import fx7.ntfy.Coordinator;
import fx7.ntfy.NtfyMessage;

public class MessageSender
{
	private final Coordinator communicator;

	private static MessageSender instance;

	public static void initInstance(Coordinator communicator)
	{
		instance = new MessageSender(communicator);
	}

	public static MessageSender getInstance()
	{
		if (instance == null)
			throw new RuntimeException("You must call MessageSender.initInstance(...) before you can use getInstance!");
		return instance;
	}

	private MessageSender(Coordinator communicator)
	{
		this.communicator = communicator;
	}

	public void send(NtfyMessage message)
	{
		if (message == null)
			return;

		try
		{
			URL url = message.getHost();
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");

			Map<String, String> header = message.getHeader();
			if (header != null)
				header.entrySet().stream().forEach((h -> con.setRequestProperty(h.getKey(), h.getValue())));

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(message.getBody());
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			if (responseCode == 200)
			{

			} else
			{
				communicator.sendConsoleMessage("Error Response Code : " + responseCode);

				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null)
				{
					response.append(inputLine);
				}
				in.close();

				communicator.sendConsoleMessage("Error Response : " + response.toString());
			}

		} catch (Exception e)
		{
			communicator.sendConsoleMessage("Exception : " + e.getMessage());
		}
	}
}
