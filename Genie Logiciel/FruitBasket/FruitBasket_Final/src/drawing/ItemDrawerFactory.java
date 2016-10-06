package drawing;

import domain.basket.BasketItem;

public interface ItemDrawerFactory {

	public ItemDrawer getItemDrawer(BasketItem fruit);

}
