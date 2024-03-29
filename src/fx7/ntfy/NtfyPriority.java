package fx7.ntfy;

public enum NtfyPriority
{
	/**
	 * No vibration or sound. The notification will be under the fold in "Other
	 * notifications".
	 */
	min, //
	/**
	 * No vibration or sound. Notification will not visibly show up until
	 * notification drawer is pulled down.
	 */
	low, //
	/**
	 * Short default vibration and sound. Default notification behavior.
	 */
	def, //
	/**
	 * Long vibration burst, default notification sound with a pop-over
	 * notification.
	 */
	high, //
	/**
	 * Really long vibration bursts, default notification sound with a pop-over
	 * notification.
	 */
	max, //
	;

	public int toId()
	{
		return ordinal() + 1;
	}
}
