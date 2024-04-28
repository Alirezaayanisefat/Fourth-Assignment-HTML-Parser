import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.util.Collections.sort;
import static java.util.Collections.swap;

public class Parser {
    static List<Country> countries = new ArrayList<>();
    static ArrayList<String> countryName = new ArrayList<>();
    static  ArrayList<String> capital = new ArrayList<>();
    static  ArrayList<Integer> population = new ArrayList<>();
    static  ArrayList<Double> area = new ArrayList<>();
    public List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        Collections.sort(sortedByName, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return  sortedByName;
    }

    public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);

        Collections.sort(sortedByPopulation, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return Integer.compare(o1.getPopulation(), o2.getPopulation());
            }
        });
        Collections.reverse(sortedByPopulation);

        return sortedByPopulation;
    }

    public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        boolean swapped;
        int n = sortedByArea.size();
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedByArea.get(j).getArea() < sortedByArea.get(j + 1).getArea()) {
                    Country temp = sortedByArea.get(j);
                    sortedByArea.set(j, sortedByArea.get(j + 1));
                    sortedByArea.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
        }
        return sortedByArea;
    }


    public void setUp() throws IOException {

        //Parse the HTML file using Jsoup
        //TODO

        File htmlFile = new File("src/Resources/country-list.html");
        Document dc = Jsoup.parse(htmlFile, "UTF-8");

        // Extract data from the HTML
        //TODO

        Elements data = dc.select(".country");
        for (Element i : data)
        {
            countryName.add(i.select(".country-name").text());
            capital.add(i.select(".country-capital").text());
            population.add(Integer.parseInt(i.select(".country-population").text()));
            area.add(Double.parseDouble(i.select(".country-area").text()));
        }

        // Iterate through each country div to extract country data
        //TODO

        for (int i = 0; i < countryName.size(); i++)
        {
            Country country = new Country(countryName.get(i), capital.get(i), population.get(i), area.get(i));
            countries.add(country);
        }
    }

    public static void main(String[] args) throws IOException {
        //you can test your code here before you run the unit tests ;)
    }
}
