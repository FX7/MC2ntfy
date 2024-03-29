package fx7.ntfy.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import fx7.ntfy.Coordinator;
import fx7.ntfy.MessageBuilder;
import fx7.ntfy.sender.MessageSender;

public class PlayerDeathListener extends AbstractListener
{

	public PlayerDeathListener(Coordinator coordinator)
	{
		super(coordinator);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		MessageBuilder builder = new MessageBuilder(getMessageTemplate());
		builder //
				.player(event.getEntity()) //
				.eventMessage(event.getDeathMessage());

		MessageSender.getInstance().send(builder.toNtfyMessageI());
	}

	@Override
	protected String getMessageTemplateKey()
	{
		return PlayerDeathEvent.class.getSimpleName();
	}

}
