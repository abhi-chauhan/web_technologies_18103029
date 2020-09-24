import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Assignment_3 {

    private static final HashSet<String> visitedLinks = new HashSet<>();
    private static final Queue<Link> linkQueue = new LinkedList<>();
    private static final List<String> facultyRelatedWordsList = new LinkedList<>();
    private static int maxDepth = 1;
    private static FileWriter textFile;
    private static FileWriter linksFile;
    private static FileWriter objectFile;
    private static String urlContains = "";
    private static int depth = 0;

    public static boolean validity(String url) {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static int frequency(String para, String substring) {

        if (para.length() == 0 || substring.length() == 0) {
            return 0;
        }
        return para.split(substring).length - 1;
    }

    public static boolean facultyRelated(final Document page) {

        if (page == null) {
            return false;
        }
        int numberOfFacultyRelatedWords = 0;
        String htmlPageString = page.toString().toLowerCase();
        for (String word :
                facultyRelatedWordsList) {
            numberOfFacultyRelatedWords += frequency(htmlPageString, word);
            if (numberOfFacultyRelatedWords >= 15) {
                return true;
            }
        }
        return false;

    }

    public static boolean facultyRelated(String str, int freqThreshold) {

        if (str.length() == 0) {
            return false;
        }
        str = str.toLowerCase();
        int numberOfFacultyRelatedWords;
        for (String word :
                facultyRelatedWordsList) {
            numberOfFacultyRelatedWords = frequency(str, word);
            if (numberOfFacultyRelatedWords > freqThreshold) {
                return true;
            }
        }
        return false;

    }

    public static void extractLinks(final Document page) {

        if (page == null) {
            return;
        }
        Elements links = page.getElementsByTag("a");
        for (Element link : links) {
            String newURL = link.attr("abs:href");

            if (!newURL.contains("#") && !visitedLinks.contains(newURL) && newURL.contains(urlContains)) {
                Link site = new Link();
                site.url = newURL;
                site.text = link.text();
                linkQueue.add(site);
            }
        }
    }

    public static void extractText(final Document page) {

        if (page == null) {
            return;
        }
        for (String tag :
                ("p blockquote").split(" ")) {
            Elements tagElementsList = page.getElementsByTag(tag);
            for (Element tagElement : tagElementsList) {
                if (facultyRelated(tagElement.text(), 3)) {
                    try {
                        String row = "<" + tag + ">,\"" + tagElement.text() + "\"\r\n";
                        textFile.write(row);
                    } catch (IOException e) {
                        System.out.println("Error writing to text.csv!");
                    }
                }
            }

        }

    }

    public static void crawl(String url, String text) {

        if (url.length() == 0) {
            return;
        }
        if (visitedLinks.contains(url)) {
            return;
        }

        visitedLinks.add(url);
        try {
            if (depth <= maxDepth) {
                Document page = Jsoup.connect(url).get();
                if (facultyRelated(url, 0) || facultyRelated(page)) {
                    extractText(page);
                    try {
                        String row = text + "," + url + "\r\n";
                        linksFile.write(row);
                    } catch (IOException e) {
                        System.out.println("Error writing to links.csv!");
                    }
                }
                extractLinks(page);
            } else if (facultyRelated(url, 0)) {
                Jsoup.connect(url).execute();
                try {
                    String row = text + "," + url + "\r\n";
                    linksFile.write(row);
                } catch (IOException e) {
                    System.out.println("Error writing to links.csv!");
                }
            }

        } catch (UnsupportedMimeTypeException type) {
            if (facultyRelated(url, 0)) {
                try {
                    objectFile.write(text + "," + url + "\r\n");
                } catch (IOException e) {
                    System.out.println("Error writing to object.csv!");
                }
            }
        } catch (IOException e) {
//            System.out.println("Unable to parse " + url);
        }

    }

    public static void main(String[] args) {

        try {
            if (!System.getProperty("user.dir").contains("Assignment_3")) {
                linksFile = new FileWriter("Assignment_3\\links.csv");
                textFile = new FileWriter("Assignment_3\\text.csv");
                objectFile = new FileWriter("Assignment_3\\object.csv");
            } else {
                linksFile = new FileWriter("links.csv");
                textFile = new FileWriter("text.csv");
                objectFile = new FileWriter("object.csv");
            }

            String row = "Tag ,Text\r\n";
            textFile.write(row);

            row = "Link Text,URL\r\n";
            linksFile.write(row);

            row = "Link Text,URL\r\n";
            objectFile.write(row);

        } catch (IOException e) {
            System.out.println("Unable To Create File(s)!");
            System.exit(1);
        }

        String url = "";
        try {
            System.out.print("Input URL: ");
            Scanner input = new Scanner(System.in);
            url = input.nextLine(); //"https://pec.ac.in/";
            System.out.print("Enter String which should be in every url: ");
            urlContains = input.nextLine(); // "pec.ac.in/
            System.out.print("Enter MAX Depth(BFS): ");
            maxDepth = input.nextInt(); // 4
            input.nextLine();
            input.close();
        } catch (Exception e) {
            System.out.println("Invalid Input!");
            System.exit(1);
        }

        long startTime = System.nanoTime();


        if (!validity(url)) {
            System.out.println("INVALID URL!");
            return;
        }


        facultyRelatedWordsList.addAll(Arrays.asList(("dr faculty professor prof assistant publications qualification books published projects").split(" ")));


        Link temp = new Link();
        temp.url = url;
        temp.text = " ---ROOT--- ";
        linkQueue.add(temp);
        linkQueue.add(null);
        while (!linkQueue.isEmpty()) {
            temp = linkQueue.remove();
            if (temp == null) {
                depth++;
                if (!linkQueue.isEmpty()) {
                    linkQueue.add(null);
                    System.out.println("Level " + (depth - 1) + " Complete");
                }
            } else {
                crawl(temp.url, temp.text);
            }
        }

        try {

            textFile.close();
            linksFile.close();
            objectFile.close();

        } catch (IOException e) {
            System.out.println("Unable to close files!");
        }

        long endTime = System.nanoTime();
        double timeElapsed = (endTime - startTime) / 1000000000.0;
        System.out.println("Time Elapsed: " + timeElapsed / 60.0 + " minutes");

    }


    private static class Link {
        public String url = "";
        public String text = "";
    }
}