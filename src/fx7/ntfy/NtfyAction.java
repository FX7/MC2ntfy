package fx7.ntfy;

public class NtfyAction
{
	public String toJson()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		// { "action": "view", "label": "Admin panel", "url":
		// "https://filesrv.lan/admin" }
		sb.append("}");
		return sb.toString();
	}

}
