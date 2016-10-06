package domain.basket;

public interface BasketControllerObserver {

	public void notifyUpdatedItems();

	public void notifyInvoiceCreated();
}
