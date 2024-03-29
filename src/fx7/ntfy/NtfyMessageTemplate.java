package fx7.ntfy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

@SerializableAs("NtfyMessageTemplate")
public class NtfyMessageTemplate implements ConfigurationSerializable
{
	private static final String PARAM_HOST = "host";
	private URL host;

	private static final String PARAM_TOPIC = "topic";
	private String topic;

	private static final String PARAM_MESSAGE_TEMPLATE = "message_template";
	private String message_template;

	private static final String PARAM_TITLE_TEMPLATE = "title_template";
	private String title_template;

	private static final String PARAM_PRIORITY = "priority";
	private NtfyPriority priority;

	private static final String PARAM_ICON = "icon";
	private URL icon;

	private static final String PARAM_TAGS = "tags";
	private List<String> tags = new ArrayList<>();

	private static final String PARAM_EMOJIS = "emojis";
	private List<NtfyEmoji> emojis = new ArrayList<>();

	private static final String PARAM_AUTH_USER = "auth_user";
	private String auth_user;

	private static final String PARAM_AUTH_PASSWORD = "auth_password";
	private String auth_password;

	// private final List<NtfyAction> actions = new ArrayList<>();

	private NtfyMessageTemplate()
	{
	}

	public URL getHost()
	{
		return host;
	}

	public String getTopic()
	{
		return topic;
	}

	public String getTitleTemplate()
	{
		return title_template;
	}

	public String getMessageTemplate()
	{
		return message_template;
	}

	public List<String> getAllTags()
	{
		if (Helper.isEmpty(tags) && Helper.isEmpty(emojis))
			return null;

		List<String> allTags = new ArrayList<String>(Helper.size(tags) + Helper.size(emojis));
		if (tags != null)
			allTags.addAll(tags);
		if (emojis != null)
			allTags.addAll(NtfyEmoji.toStringList(emojis));
		return allTags;
	}

	public NtfyPriority getPriority()
	{
		return priority;
	}

	public String getAuthUser()
	{
		return auth_user;
	}

	public String getAuthPassword()
	{
		return auth_password;
	}

	@Override
	public Map<String, Object> serialize()
	{
		Map<String, Object> serialized = new HashMap<String, Object>();

		if (this.host != null)
			serialized.put(PARAM_HOST, this.host.toString());
		if (Helper.isNotEmpty(this.topic))
			serialized.put(PARAM_TOPIC, this.topic);
		if (Helper.isNotEmpty(this.message_template))
			serialized.put(PARAM_MESSAGE_TEMPLATE, this.message_template);
		if (this.title_template != null)
			serialized.put(PARAM_TITLE_TEMPLATE, this.title_template);
		if (this.priority != null)
			serialized.put(PARAM_PRIORITY, this.priority.name());
		if (this.icon != null)
			serialized.put(PARAM_ICON, this.icon.toString());
		if (Helper.isNotEmpty(tags))
			serialized.put(PARAM_TAGS, this.tags);
		if (Helper.isNotEmpty(emojis))
			serialized.put(PARAM_EMOJIS, NtfyEmoji.toStringList(this.emojis));
		if (Helper.isNotEmpty(auth_user))
			serialized.put(PARAM_AUTH_USER, this.auth_user);
		if (Helper.isNotEmpty(auth_password))
			serialized.put(PARAM_AUTH_PASSWORD, auth_password);

		return serialized;
	}

	public static NtfyMessageTemplate deserialize(Map<String, Object> serialized) throws MalformedURLException
	{
		NtfyMessageTemplate tmpl = new NtfyMessageTemplate();

		String url = (String) serialized.get(PARAM_HOST);
		if (Helper.isNotEmpty(url))
			tmpl.host = new URL(url);

		String topic = (String) serialized.get(PARAM_TOPIC);
		if (Helper.isNotEmpty(topic))
			tmpl.topic = topic;

		String messageTemplate = (String) serialized.get(PARAM_MESSAGE_TEMPLATE);
		if (Helper.isNotEmpty(messageTemplate))
			tmpl.message_template = messageTemplate;

		String title_template = (String) serialized.get(PARAM_TITLE_TEMPLATE);
		if (Helper.isNotEmpty(title_template))
			tmpl.title_template = title_template;

		String priority = (String) serialized.get(PARAM_PRIORITY);
		if (Helper.isNotEmpty(priority))
			tmpl.priority = NtfyPriority.valueOf(priority);

		String icon = (String) serialized.get(PARAM_ICON);
		if (Helper.isNotEmpty(icon))
			tmpl.icon = new URL(icon);

		@SuppressWarnings("unchecked")
		List<String> tags = (List<String>) serialized.get(PARAM_TAGS);
		if (Helper.isNotEmpty(tags))
			tmpl.tags = tags;

		@SuppressWarnings("unchecked")
		List<String> emojis = (List<String>) serialized.get(PARAM_EMOJIS);
		if (Helper.isNotEmpty(emojis))
			tmpl.emojis = NtfyEmoji.fromStringCollection(emojis);

		String auth_user = (String) serialized.get(PARAM_AUTH_USER);
		if (Helper.isNotEmpty(auth_user))
			tmpl.auth_user = auth_user;

		String auth_password = (String) serialized.get(PARAM_AUTH_PASSWORD);
		if (Helper.isNotEmpty(auth_password))
			tmpl.auth_password = auth_password;

		return tmpl;
	}

	public NtfyMessageTemplate merge(NtfyMessageTemplate evtMsgTmpl)
	{
		NtfyMessageTemplate merged = new NtfyMessageTemplate();

		merged.host = evtMsgTmpl.host != null ? evtMsgTmpl.host : this.host;
		merged.topic = Helper.isNotEmpty(evtMsgTmpl.topic) ? evtMsgTmpl.topic : this.topic;
		merged.message_template = Helper.isNotEmpty(evtMsgTmpl.message_template) ? evtMsgTmpl.message_template
				: this.message_template;
		merged.title_template = Helper.isNotEmpty(evtMsgTmpl.title_template) ? evtMsgTmpl.title_template
				: this.title_template;
		merged.priority = evtMsgTmpl.priority != null ? evtMsgTmpl.priority : this.priority;
		merged.icon = evtMsgTmpl.icon != null ? evtMsgTmpl.icon : this.icon;
		merged.tags = Helper.isNotEmpty(evtMsgTmpl.tags) ? evtMsgTmpl.tags : this.tags;
		merged.emojis = Helper.isNotEmpty(evtMsgTmpl.emojis) ? evtMsgTmpl.emojis : this.emojis;
		merged.auth_user = Helper.isNotEmpty(evtMsgTmpl.auth_user) ? evtMsgTmpl.auth_user : this.auth_user;
		merged.auth_password = Helper.isNotEmpty(evtMsgTmpl.auth_password) ? evtMsgTmpl.auth_password
				: this.auth_password;

		return merged;
	}

	public static NtfyMessageTemplate defaultTemplate()
	{
		try
		{
			NtfyMessageTemplate def = new NtfyMessageTemplate();
			def.host = new URL("https://ntfy.sh/");
			def.topic = "minecraft";
			def.message_template = "Message from your minecraft server";
			return def;
		} catch (MalformedURLException e)
		{
			throw new RuntimeException("URL of default Template is malformed ...", e);
		}
	}
}
