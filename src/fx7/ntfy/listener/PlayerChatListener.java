package fx7.ntfy.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fx7.ntfy.Coordinator;
import fx7.ntfy.MessageBuilder;
import fx7.ntfy.sender.MessageSender;

public class PlayerChatListener extends AbstractListener
{

	public PlayerChatListener(Coordinator coordinator)
	{
		super(coordinator);
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		MessageBuilder builder = new MessageBuilder(getMessageTemplate());
		builder //
				.player(event.getPlayer()) //
				.eventMessage(event.getMessage());

		MessageSender.getInstance().send(builder.toNtfyMessageI());
	}

	@Override
	protected String getMessageTemplateKey()
	{
		return AsyncPlayerChatEvent.class.getSimpleName();
	}

}
