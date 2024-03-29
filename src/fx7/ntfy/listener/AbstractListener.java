package fx7.ntfy.listener;

import org.bukkit.event.Listener;

import fx7.ntfy.Coordinator;
import fx7.ntfy.NtfyMessageTemplate;

public abstract class AbstractListener implements Listener
{
	protected final Coordinator coordinator;

	private NtfyMessageTemplate msgTmpl;

	protected AbstractListener(Coordinator coordinator)
	{
		this.coordinator = coordinator;
	}

	protected abstract String getMessageTemplateKey();

	protected NtfyMessageTemplate getMessageTemplate()
	{
		NtfyMessageTemplate msgTmpl = this.msgTmpl;
		if (msgTmpl == null)
		{
			msgTmpl = coordinator.getMessageTemplate();
			NtfyMessageTemplate evtMsgTmpl = coordinator.getMessageTemplate(getMessageTemplateKey());
			if (evtMsgTmpl != null)
				msgTmpl = msgTmpl.merge(evtMsgTmpl);
			this.msgTmpl = msgTmpl;
		}
		return this.msgTmpl;
	}
}
