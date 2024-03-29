package fx7.ntfy.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import fx7.ntfy.Coordinator;
import fx7.ntfy.MessageBuilder;
import fx7.ntfy.sender.MessageSender;

public class PlayerJoinListener extends AbstractListener
{
	public PlayerJoinListener(Coordinator coordinator)
	{
		super(coordinator);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		MessageBuilder builder = new MessageBuilder(getMessageTemplate());
		builder //
				.player(event.getPlayer()) //
				.eventMessage(event.getJoinMessage());

		MessageSender.getInstance().send(builder.toNtfyMessageI());
	}

	@Override
	protected String getMessageTemplateKey()
	{
		return PlayerJoinEvent.class.getSimpleName();
	}
}
