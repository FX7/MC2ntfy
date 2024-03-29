package fx7.ntfy;

import java.util.Collection;
import java.util.stream.Collectors;

public class Helper
{
	public static String toString(Collection<String> collection)
	{
		if (collection == null)
			return null;

		if (collection.isEmpty())
			return null;

		return collection.stream().collect(Collectors.joining("\",\"", "\"", "\""));
	}

	public static int size(Collection<?> collection)
	{
		if (collection == null)
			return 0;
		return collection.size();
	}

	public static boolean isEmpty(Collection<?> collection)
	{
		return collection == null || collection.isEmpty();
	}

	public static boolean isNotEmpty(Collection<?> collection)
	{
		return !isEmpty(collection);
	}

	public static boolean isEmpty(String s)
	{
		return s == null || s.length() == 0 || s.trim().length() == 0;
	}

	public static boolean isNotEmpty(String s)
	{
		return !isEmpty(s);
	}
}
