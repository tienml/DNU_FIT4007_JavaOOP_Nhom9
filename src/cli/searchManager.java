package cli;

import java.util.List;
import java.util.stream.Collectors;

public class searchManager {

    public static List<String> fuzzySearch(List<String> names, String keyword) {
        return names.stream()
                .filter(name -> name.toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
