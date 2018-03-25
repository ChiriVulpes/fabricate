package yuudaari.fabricate.api;

import java.util.function.Consumer;

public interface IApiWrapper {

	public void addEventHandler (Object event, Consumer<Object> handler);
}
