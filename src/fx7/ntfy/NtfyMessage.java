package fx7.ntfy;

import java.net.URL;
import java.util.Map;

public interface NtfyMessage
{
	public URL getHost();
	
	public Map<String, String> getHeader();

	public String getBody();
}
