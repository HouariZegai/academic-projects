package pw2;

public class SortTester {

    public static void main(String[] args) {
        int[] table = {1, 2, 3, 4, 5, 10, 100, 300};
        int[] table2 = {1, 10, 3, 4, 5, 110 -100, 300};

        System.out.println(isSortedTable(table));
        System.out.println(isSortedTable(table2));
    }

    private static boolean isSortedTable(int[] sortedTable) {
        for(int i = 1; i < sortedTable.length; i++) {
            if(sortedTable[i - 1] > sortedTable[i])
                return false;
        }

        return true;
    }
}
