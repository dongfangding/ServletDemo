package training.servlet.cookie;

import java.util.LinkedHashMap;
import java.util.Map;

public class CookieGoodsDao {
	private static Map<String, Goods> goodsList = new LinkedHashMap<String, Goods>();
	static {
		goodsList.put("1", new Goods("1", "华为Mate9", "3600"));
		goodsList.put("2", new Goods("2", "华为Mate9 Pro", "5200"));
		goodsList.put("3", new Goods("3", "华为P9", "3300"));
		goodsList.put("4", new Goods("4", "华为P10", "4000"));
	}
	
	public Map<String, Goods> getAll() {
		return goodsList;
	}
	
	public Goods getGoodsById(String id) {
		return goodsList.get(id);
	}
}
