# MC2ntfy

Minecraft PLugin for pushing some Mineraft Events to a ntfy Server.

**TL:DR** Start a server with the plugin activated. Config will be generated. Stop server, modify config for your needs, restart server.

For now the Plugin is listening for 4 Minecraft Events:

* PlayerJoinEvent
* PlayerQuitEvent
* PlayerDeathEvent
* AsyncPlayerChatEvent

For all these Events you can configure the content, the receiving host and the topic.
Also basic auth is possible.

Most common setup will be to configure a "mainMsgTmpl", which defines a host, topic and auth credentials. And then you can customize the message content for each of the events.

There are also some placeholders which will be replaced in the message texts. Placeholders are:

* PLAYER_NAME
* EVENT_MESSAGE
* PLAYER_EXP_LEVEL

Example config (which also will be generated on first start) under example.yml