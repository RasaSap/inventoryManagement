package inventoryManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Warehouse {

    public static List<Item> readItemsFile(String fileName) throws IOException, ParseException {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] duomenys = new String[22];
        List<Item> itemList = new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(fileName));
            br.readLine(); //read the first line and throw it away
            while ((line = br.readLine()) != null) {
                duomenys = line.split(cvsSplitBy);

                itemList.add(new Item(duomenys[0],
                        Long.parseLong(duomenys[1]),
                        Integer.parseInt(duomenys[2]),
                        new SimpleDateFormat("yyyy-MM-dd").parse(duomenys[3])));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return itemList;
    }


    public static List<Item> checkForDublicates(List<Item> itemList) {
        sortItemsByName(itemList);
        List<Item> copy = new ArrayList<>();
        Set<Item> checkedList = new HashSet<>();
        for (int i = 0; i < itemList.size(); i++) {
            for (int j = 0; j < itemList.size(); j++) {

                if (itemList.get(i).getItemName().equals(itemList.get(j).getItemName()) &&
                        itemList.get(i).getCode() == itemList.get(j).getCode() &&
                        itemList.get(i).getExpirationDate().compareTo(itemList.get(j).getExpirationDate()) == 0 &&
                        itemList.indexOf(itemList.get(i)) != (itemList.indexOf(itemList.get(j)))) {

                    int kiekis = itemList.get(i).getQuantity() + itemList.get(j).getQuantity();
                    itemList.get(i).setQuantity(kiekis);
                    checkedList.add(itemList.get(i));

                    itemList.remove(itemList.get(j));

                } else {
                    checkedList.add(itemList.get(i));
                }
            }
        }

        copy.addAll(checkedList);
        sortItemsByName(copy);

        return copy;
    }

    public static void printList(List<Item> list) {
        for (Item val : list) {
            System.out.println(val);
        }
    }

    public static void sortItemsByName(List<Item> itemList) {
        Collections.sort(itemList, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {

                if (i1.getItemName().compareToIgnoreCase(i2.getItemName()) == 0) {
                    return 0;
                } else if (i1.getItemName().compareToIgnoreCase(i2.getItemName()) < 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
    }

    public static void checkStock(int amount) {
        List<Item> itemList = new ArrayList<>();
        try {
            itemList = checkForDublicates(readItemsFile("/Users/lenovo/desktop/testasJunior/sample.csv"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getQuantity() < amount) {
                System.out.println(itemList.get(i));
            }
        }
    }

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static void checkDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(inDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Item> itemList = new ArrayList<>();
        try {
            itemList = checkForDublicates(readItemsFile("/Users/lenovo/desktop/testasJunior/sample.csv"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < itemList.size(); i++) {
            if ((itemList.get(i).getExpirationDate().compareTo(date)) < 0 ||
                    (itemList.get(i).getExpirationDate().equals(date))) {
                System.out.println(itemList.get(i));
            }
        }
    }
}
