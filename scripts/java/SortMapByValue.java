import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;


public class SortMap {

    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(0, 15);
        map.put(1, 12);
        map.put(2, 30);
        map.put(3, 5);
        map.put(4, 22);
        map.put(5, 27);
        map.put(6, 4);
        map.put(7, 87);

        Map<Integer, Integer> sortedMap = sortMapByValue(map);

        System.out.println("Input: " + map.toString());
        System.out.println("Output: " + sortedMap.toString());
    }

    private static Map<Integer, Integer> sortMapByValue(Map<Integer, Integer> map)
    {
        Map<Integer, Integer> sortedMap =  map.entrySet().stream()
                .sorted(comparingInt(e -> -1 * e.getValue() )) // -1 to be sorted in descending way
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError();},
                        LinkedHashMap::new
                ));
        return sortedMap;
    }
}
