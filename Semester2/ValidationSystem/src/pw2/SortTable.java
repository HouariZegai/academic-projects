package pw2;

public class SortTable {

    public int[] sortTable(int[] table) {
        for(int i = 0; i < table.length - 1; i++) {
            for(int j = i + 1; j < table.length; j++) {
                if(table[i] > table[j]) {
                    table[i] = table[i] + table[j];
                    table[j] = table[i] - table[j];
                    table[i] = table[i] - table[j];
                }
            }
        }

        return table;
    }

    public Float[] sortTable(Float[] table) {
        for(int i = 0; i < table.length - 1; i++) {
            for(int j = i; j < table.length; j++) {
                if(table[i] > table[j]) {
                    table[i] = table[i] + table[j];
                    table[j] = table[i] - table[j];
                    table[i] = table[i] - table[j];
                }
            }
        }
        return table;
    }

    public Double[] sortTable(Double[] table) {
        for(int i = 0; i < table.length - 1; i++) {
            for(int j = i; j < table.length; j++) {
                if(table[i] > table[j]) {
                    table[i] = table[i] + table[j];
                    table[j] = table[i] - table[j];
                    table[i] = table[i] - table[j];
                }
            }
        }
        return table;
    }
}
