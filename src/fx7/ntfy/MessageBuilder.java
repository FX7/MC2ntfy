package fx7.ntfy;

import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

public class MessageBuilder
{
	private final NtfyMessageTemplate template;
	private String title;
	private String message;

	public MessageBuilder(NtfyMessageTemplate template)
	{
		this.template = template;
		String title = template.getTitleTemplate();
		String message = template.getMessageTemplate();
		this.title = title == null ? "" : title;
		this.message = message == null ? "" : message;
	}

	public NtfyMessage toNtfyMessageI()
	{
		return new NtfyMessage()
		{

			@Override
			public URL getHost()
			{
				return template.getHost();
			}

			@Override
			public Map<String, String> getHeader()
			{
				Map<String, String> headers = new HashMap<>();

				if (Helper.isNotEmpty(template.getAuthUser()) && Helper.isNotEmpty(template.getAuthPassword()))
				{
					String credentials = template.getAuthUser() + ":" + template.getAuthPassword();
					headers.put("Authorization", "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes()));
				}

				return headers;
			}

			@Override
			public String getBody()
			{
				StringBuilder sb = new StringBuilder();
				sb.append("{");
				sb.append("\"topic\":").append("\"").append(template.getTopic()).append("\",");
				sb.append("\"message\":").append("\"").append(message).append("\"");
				if (Helper.isNotEmpty(title))
					sb.append(",\"title\":").append("\"").append(title).append("\"");
				List<String> tags = template.getAllTags();
				if (Helper.isNotEmpty(tags))
					sb.append(",\"tags\":[").append(Helper.toString(tags)).append("]");
				if (template.getPriority() != null)
					sb.append(",\"priority\":").append(template.getPriority().toId());

				sb.append("}");
				return sb.toString();
			}
		};
	}

	public MessageBuilder eventMessage(String eventMessage)
	{
		this.title = eventMessage(title, eventMessage);
		this.message = eventMessage(message, eventMessage);
		return this;
	}

	private String eventMessage(String templateText, String eventMessage)
	{
		return templateText.replaceAll("\\[EVENT_MESSAGE\\]", eventMessage);
	}

	public MessageBuilder player(Player player)
	{

		this.title = player(title, player);
		this.message = player(message, player);
		return this;
	}

	private String player(String templateText, Player player)
	{
		templateText = playerName(templateText, player.getName());
		templateText = playerExpLevel(templateText, player.getLevel());
		return templateText;
	}

	public MessageBuilder playerName(String playerName)
	{
		this.title = playerName(title, playerName);
		this.message = playerName(message, playerName);
		return this;
	}

	private String playerName(String templateText, String playerName)
	{
		return templateText.replaceAll("\\[PLAYER_NAME\\]", playerName);
	}

	public MessageBuilder playerExpLevel(int expLevel)
	{
		this.title = playerExpLevel(title, expLevel);
		this.message = playerExpLevel(message, expLevel);
		return this;
	}

	private String playerExpLevel(String templateText, int expLevel)
	{
		return templateText.replaceAll("\\[PLAYER_EXP_LEVEL\\]", Integer.toString(expLevel));
	}
}
