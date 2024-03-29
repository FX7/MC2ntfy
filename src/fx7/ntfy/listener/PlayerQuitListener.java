package fx7.ntfy.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import fx7.ntfy.Coordinator;
import fx7.ntfy.MessageBuilder;
import fx7.ntfy.sender.MessageSender;

public class PlayerQuitListener extends AbstractListener
{
	public PlayerQuitListener(Coordinator coordinator)
	{
		super(coordinator);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		MessageBuilder builder = new MessageBuilder(getMessageTemplate());
		builder //
				.player(event.getPlayer()) //
				.eventMessage(event.getQuitMessage());

		MessageSender.getInstance().send(builder.toNtfyMessageI());
	}

	@Override
	protected String getMessageTemplateKey()
	{
		return PlayerQuitEvent.class.getSimpleName();
	}
}
