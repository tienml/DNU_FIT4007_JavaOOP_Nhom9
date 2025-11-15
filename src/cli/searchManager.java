package cli;

import java.util.List;
import java.util.stream.Collectors;

public class searchManager {

    public static List<String> fuzzySearch(List<String> items, String keyword) {
        String key = keyword.toLowerCase();
        return items.stream()
                .filter(item -> item.toLowerCase().contains(key))
                .collect(Collectors.toList());
    }
}
