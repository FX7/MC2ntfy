package fx7.ntfy;

public interface Coordinator
{
	public NtfyMessageTemplate getMessageTemplate();

	public NtfyMessageTemplate getMessageTemplate(String key);

	public void sendConsoleMessage(String message);
}
