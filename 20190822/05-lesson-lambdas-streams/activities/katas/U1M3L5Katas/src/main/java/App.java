import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<Television> tvs = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Television> televisionJson;

            televisionJson = mapper.readValue(new File("televisions.json"), new TypeReference<List<Television>>(){});

            System.out.println("All the TVs in inventory greater than screen size of 60.");
            televisionJson.stream()
                    .filter(size -> size.getScreenSize() > 60).forEach(t -> {
                System.out.println("===============");
                System.out.println("Brand: " + t.getBrand());
                System.out.println("Model: " + t.getModel());
                System.out.println("Price: " + t.getPrice());
                System.out.println("Screensize: " + t.getScreenSize());
            });

            System.out.println("===========");
            System.out.println("Mapped by Brands:");
            Map<String, List<Television>> brandTvs =
                    televisionJson.stream().collect(Collectors.groupingBy(t -> t.getBrand()));
            Set<String> keys = brandTvs.keySet();
            for(String key : keys) {
                System.out.println(key);
            }

            System.out.println("===========");
            System.out.println("Average screen size of all tvs:");

            double avgTvScreenSize =
                    televisionJson.stream().mapToInt(t -> t.getScreenSize())
                    .average().getAsDouble();

            System.out.println(avgTvScreenSize);

            System.out.println("===========");
            System.out.println("Biggest TV screen size of all tvs:");
            int maxTvScreenSize =
                    televisionJson.stream().mapToInt(t -> t.getScreenSize())
                    .max().getAsInt();

            System.out.println(maxTvScreenSize);

//            System.out.println("===========");
//            System.out.println("Sorting by Screen Size:");

//            List<String> sortedList = televisionJson.stream()
//                    .sorted(Comparator.comparingInt(Television::getScreenSize))
//                    .collect(Collectors.toList());
//
//                    televisionJson.stream().sorted().forEach(t -> {
//                        System.out.println("===============");
//                        System.out.println("Screensize: " + t.getScreenSize());
//                        System.out.println("Brand: " + t.getBrand());
//                        System.out.println("Model: " + t.getModel());
//                        System.out.println("Price: " + t.getPrice());
//                            }
//                    );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
