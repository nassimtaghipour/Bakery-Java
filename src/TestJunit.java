import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

public class TestJunit {

	Bakery bk = new Bakery();

	@Test
	public void testSampleOrders() {
		// Vegemite Scroll (VS5) order 10 pieces
		List<Integer> list = bk.dpShoppingBasketContent(new int[] { 5, 3 }, 10);
		Collections.sort(list);
		assertEquals(list, Arrays.asList(new Integer[] { 5, 5 }));

		// Blueberry muffin (MB11) order 14 pieces
		list.clear();
		list = bk.dpShoppingBasketContent(new int[] { 2, 5, 8 }, 14);
		Collections.sort(list);
		assertEquals(list, Arrays.asList(new Integer[] { 2, 2, 2, 8 }));

		// Croissant (CF) order 13 pieces
		list.clear();
		list = bk.dpShoppingBasketContent(new int[] { 3, 5, 9 }, 13);
		Collections.sort(list);
		assertEquals(list, Arrays.asList(new Integer[] { 3, 5, 5 }));
	}

	@Test
	public void testRandomOrders() {
		final int maxTestTimes = 100000;
		final int maxPacksCount = 100;
		final int maxEachPackSize = 20;
		final int maxOrderCount = 200;
		Random r = new Random();
		List<Integer> pack = new ArrayList<Integer>();

		int packLenght = 1 + r.nextInt(maxPacksCount);

		// test times loop
		for (int i = 0; i < maxTestTimes; i++) {

			// create pack
			for (int j = 0; j < packLenght; j++)
				pack.add(1 + r.nextInt(maxEachPackSize));
			// remove duplicates
			LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(pack);
			ArrayList<Integer> rnPackWithoutDuplicates = new ArrayList<>(hashSet);

			int orderCount = 1 + r.nextInt(maxOrderCount);
			List<Integer> ShoppingBasket = bk
					.dpShoppingBasketContent(rnPackWithoutDuplicates.stream().mapToInt(n -> n).toArray(), orderCount);
			if (ShoppingBasket != null) {
				Collections.sort(ShoppingBasket);

				assertTrue(rnPackWithoutDuplicates.containsAll(ShoppingBasket)
						&& orderCount == ShoppingBasket.stream().mapToInt(n -> n.intValue()).sum());

				/*
				 * System.out.println("bakeryPacks:"+rnPackWithoutDuplicates);
				 * System.out.println("orderCount:"+orderCount);
				 * System.out.println("ShoppingBasket:"+ShoppingBasket);
				 * System.out.println("__"+i+"________");
				 */

			}
			// clear for next round :)
			if (ShoppingBasket != null)
				ShoppingBasket.clear();
			hashSet.clear();
			rnPackWithoutDuplicates.clear();
			pack.clear();
		}

	}

}
