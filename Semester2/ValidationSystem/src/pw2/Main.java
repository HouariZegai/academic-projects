package pw2;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        int[] tab = {1, 2, 3, 4, 5, 100, 10, 300};
//        Double[] tab2 = {1.1, 10.3, 3.99, 4.7, 5.8, 110.15 -100.30, 300d};
//        Float[] tab3 = {1f, 7f, -30.3f, 2.45f, 15.6f, 0f, 0f, 300.9f};

        int[] tab = generateTable(120);
        int[] tab2 = generateTable(500);
        int[] tab3 = generateTable(90);

        System.out.println("\nBefore sort:");
        printTable(tab);
        printTable(tab2);
        printTable(tab3);

        SortTable sortTable = new SortTable();
        SortTester sortTester = new SortTester();

        // sort tables
        sortTable.sortTable(tab);
        sortTable.sortTable(tab2);
        //sortTable.sortTable(tab3);

        System.out.println("\nAfter sort:");
        printTable(tab);
        printTable(tab2);
        printTable(tab3);

        // test if tables is sorted
        System.out.println("\nResult of Oracle test:");
        System.out.println(sortTester.isSortedTable(tab));
        System.out.println(sortTester.isSortedTable(tab2));
        System.out.println(sortTester.isSortedTable(tab3));
        System.out.println();
    }

    private static int[] generateTable(int tableLength) {
        if(tableLength < 1)
            return null;

        int[] table = new int[tableLength];
        Random random = new Random();
        for(int i = 0; i < table.length; i++) {
            table[i] = random.nextInt(1000);
        }

        return table;
    }

    private static void printTable(int tab[]) {
        System.out.print("[");
        for(int item : tab)
            System.out.print(item + ", ");

        System.out.println("]");
    }
}
