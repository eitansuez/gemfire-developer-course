package io.pivotal.bookshop.buslogic;
import java.util.ArrayList;
import java.util.Date;

import io.pivotal.bookshop.domain.BookOrder;
import io.pivotal.bookshop.domain.BookOrderItem;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

// TODO-02: Start locator and one server, enabling REST support. Use port 7071 as the HTTP port. Then Run this OrderLoader class.
// TODO-03: Open a browser to http://localhost:7071/gemfire-api/v1 and verify you see 4 regions listed.
public class OrderLoader
{
	public static void main(String[] args) {
		ClientCache cache = new ClientCacheFactory().create();
		populateBookOrders(cache);
		cache.close();
	}

	private static void populateBookOrders(ClientCache cache)
	{
		Region<String, BookOrder> orders = cache.getRegion("BookOrder");
		// Order for Kari Powell for book: A Treatise of Treatises
		BookOrder order1 = new BookOrder(17699, new Date(), (float)5.99, new Date(), new ArrayList<>(), 5598, (float)40.98);
		order1.addOrderItem(BookOrderItem.builder().orderLine(1).itemNumber(123).build());

		orders.put("17699", order1);

		// Order for Lula Wax   book: A Treatise of Treatises & Clifford the Big Red Dog
		BookOrder order2 = new BookOrder(17700, new Date(), (float)5.99, new Date(), new ArrayList<>(), 5543, (float)52.97);
		order2.addOrderItem(BookOrderItem.builder().orderLine(1).itemNumber(123).build());
		order2.addOrderItem(BookOrderItem.builder().orderLine(2).itemNumber(456).build());
		orders.put("17700", order2);
	}

}
