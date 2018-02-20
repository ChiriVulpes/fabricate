package yuudaari.fabricate.api;

import java.util.function.Function;

public interface IApiWrapper {

	public void addEventHandler (int event, Function<Object, Void> handler);
}
