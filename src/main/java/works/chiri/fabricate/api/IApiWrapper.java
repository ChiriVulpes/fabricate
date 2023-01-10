package works.chiri.fabricate.api;

import java.util.function.Consumer;

public interface IApiWrapper {

	public void addEventHandler (String event, Consumer<Object> handler);
}
