package pw2;

public class SortTester {

    public boolean isSortedTable(int[] sortedTable) {
        for(int i = 1; i < sortedTable.length; i++) {
            assert sortedTable[i - 1] > sortedTable[i];
        }

        return true;
    }

    public boolean isSortedTable(Float[] sortedTable) {
        for(int i = 1; i < sortedTable.length; i++) {
            if(sortedTable[i - 1] > sortedTable[i])
                return false;
        }

        return true;
    }

    public boolean isSortedTable(Double[] sortedTable) {
        for(int i = 1; i < sortedTable.length; i++) {
            if(sortedTable[i - 1] > sortedTable[i])
                return false;
        }

        return true;
    }
}
