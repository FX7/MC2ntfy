package fx7.ntfy;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import fx7.ntfy.listener.PlayerChatListener;
import fx7.ntfy.listener.PlayerDeathListener;
import fx7.ntfy.listener.PlayerJoinListener;
import fx7.ntfy.listener.PlayerQuitListener;
import fx7.ntfy.sender.MessageSender;

public class MC2NtfyPlugin extends JavaPlugin implements Coordinator
{
	private static final String CONSOLE_MESSAGE_PREFIX = "[MC2Ntfy] ";

	private static final String STORE_KEY_MAIN_MESSAGE_TEMPLATE = "mainMsgTmpl";
	private NtfyMessageTemplate mainMsgTmpl;

	@Override
	public void onDisable()
	{
		storeConfigs();
	}

	private void storeConfigs()
	{
		getMessageTemplate(); // sicher stellen, dass ggf die default config
								// geladen wird
		getConfig().set(STORE_KEY_MAIN_MESSAGE_TEMPLATE, this.mainMsgTmpl);
		saveConfig();
	}

	@Override
	public void onEnable()
	{
		MessageSender.initInstance(this);

		PlayerJoinListener pjl = new PlayerJoinListener(this);
		getServer().getPluginManager().registerEvents(pjl, this);

		PlayerQuitListener pql = new PlayerQuitListener(this);
		getServer().getPluginManager().registerEvents(pql, this);
		
		PlayerDeathListener pdl = new PlayerDeathListener(this);
		getServer().getPluginManager().registerEvents(pdl, this);
		
		PlayerChatListener pcl = new PlayerChatListener(this);
		getServer().getPluginManager().registerEvents(pcl, this);
	}

	@Override
	public FileConfiguration getConfig()
	{
		// TODO why not in onEnable? tried it many times, but doesnt work
		registerConfigurationSerializables();
		return super.getConfig();
	}

	private void registerConfigurationSerializables()
	{
		ConfigurationSerialization.registerClass(NtfyMessageTemplate.class);
	}

	@Override
	public void sendConsoleMessage(String message)
	{
		if (message == null || message.isEmpty())
			return;

		Bukkit.getConsoleSender().sendMessage(CONSOLE_MESSAGE_PREFIX + message);
	}

	@Override
	public NtfyMessageTemplate getMessageTemplate()
	{
		NtfyMessageTemplate mainMsgTmpl = this.mainMsgTmpl;
		if (mainMsgTmpl == null)
		{
			mainMsgTmpl = getMessageTemplate(STORE_KEY_MAIN_MESSAGE_TEMPLATE);
			if (mainMsgTmpl == null)
			{
				mainMsgTmpl = NtfyMessageTemplate.defaultTemplate();
			}
			this.mainMsgTmpl = mainMsgTmpl;
		}
		return this.mainMsgTmpl;
	}

	@Override
	public NtfyMessageTemplate getMessageTemplate(String key)
	{
		NtfyMessageTemplate template = (NtfyMessageTemplate) getConfig().get(key);
		return template;
	}
}
