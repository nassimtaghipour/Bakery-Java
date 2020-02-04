
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author nassim_taghipoor@yahoo.com
 * 
 *
 */

public class Bakery {

	 
	/**
	 * @param packs
	 * @param orderCount
	 * @return
	 * use coin change DYNAMIC PROGRAMMING algorithm 
	 * to minimize number of packs :)
	 */
	public List<Integer> dpShoppingBasketContent(int[] packs, int orderCount) {
		int n = packs.length;
		int[] count = new int[orderCount + 1];
		int[] from = new int[orderCount + 1];

		count[0] = 1;
		for (int i = 0; i < orderCount; i++)
			if (count[i] > 0)
				for (int j = 0; j < n; j++) {
					int p = i + packs[j];
					if (p <= orderCount) {
						if (count[p] == 0 || count[p] > count[i] + 1) {
							count[p] = count[i] + 1;
							from[p] = j;
						}
					}
				}

		// No solutions:
		if (count[orderCount] == 0)
			return null;

		// Build answer.
		int[] result = new int[count[orderCount] - 1];
		int k = orderCount;
		while (k > 0) {
			result[count[k] - 2] = packs[from[k]];
			k = k - packs[from[k]];
		}

		return Arrays.stream(result).boxed().collect(Collectors.toList());
	}

	public void printShoppingBasketContent(String code, List<Integer> list, List<Integer> packs,
			List<Double> packsPrice) {
        
		if (list.get(0) < 0) {
			System.out.println(
					"error, maybe it's impossible to complete your order with current packs sizes; please try another order");
			System.out.println("__________________");
			return;
		}
		//build output
		double totalAmount = 0;
		for (int p : packs) {
			long count = list.stream().filter(i -> i.equals(p)).count();
			if (count > 0) {
				totalAmount += count * packsPrice.get(packs.indexOf(p));

				System.out.println(count + " X " + p + ": $" + packsPrice.get(packs.indexOf(p)));

			}
		}

		System.out.println(code + ": " + totalAmount);
		System.out.println("__________________");
	}

	public static void main(String[] args) {

		Bakery bk = new Bakery();
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Hello dear customer :)");

			System.out.println("Please enter total Vegemite Scroll (VS5) that you want:");
			int vs5 = scanner.nextInt();

			System.out.println("Please enter total Blueberry muffin (MB11) that you want:");
			int mb11 = scanner.nextInt();

			System.out.println("Please enter total Croissant (CF) that you want:");
			int cf = scanner.nextInt();

			List<Integer> list = bk.dpShoppingBasketContent(Packs.vs5_PackList.stream().mapToInt(i -> i).toArray(),
					vs5);
			bk.printShoppingBasketContent("VS5", list, Packs.vs5_PackList, Packs.vs5_PacksPrice);

			list.clear();
			list = bk.dpShoppingBasketContent(Packs.mb11_PackList.stream().mapToInt(i -> i).toArray(), mb11);
			bk.printShoppingBasketContent("MB11", list, Packs.mb11_PackList, Packs.mb11_PacksPrice);

			list.clear();
			list = bk.dpShoppingBasketContent(Packs.cf_PackList.stream().mapToInt(i -> i).toArray(), cf);
			bk.printShoppingBasketContent("CF", list, Packs.cf_PackList, Packs.cf_PacksPrice);

			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}