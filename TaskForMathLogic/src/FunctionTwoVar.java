public class FunctionTwoVar implements Function{
    int k = -1;        // k-значность логики
    int[][] table;

    public FunctionTwoVar(int k) {
        this.k = k;
    }

    public void buildTable(){
        table = new int[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (j > i) {
                    table[i][j] = k - 1;
                }
                else {
                    table[i][j] = k - 1 - i + j;
                }
            }
        }
    }

    public void outputTable(){
        System.out.println("\n-----");
        System.out.println("Таблица значений:");
        System.out.println("+-----------------+");
        System.out.println("| x | y |  result |");
        System.out.println("+-----------------+");
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                System.out.println("| " + i + " | " + j + " |    " + table[i][j] + "    |");
            }
            System.out.println("+-----------------+");
        }
    }

    public void outputSecondForm(){
        System.out.println("\n-----");
        System.out.println("Вторая форма:");
        int temp;
        int flag = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                temp = table[i][j];
                if(temp != 0) {
                    if (flag != 0) {
                        System.out.print(" + ");
                    }
                    if (temp == 1) {
                        System.out.print("i_" + i + "(x)" + "*i_" + j + "(y)");
                    } else {
                        System.out.print(temp + "*i_" + i + "(x)" + "*i_" + j + "(y)");
                    }
                    ++flag;
                }
            }
        }
        System.out.println("\n");
    }

    public void getAnswer(int[] set){
        int var;       // Элемент из таблицы значений
        boolean success = false; // Есть совпадение или нет

        // Аргументы для первой переменной
        finish:
        for (int value : set) {
            // Аргументы для второй переменной
            for (int item : set) {
                success = false;
                var = table[value][item];

                // Проверяем, входит ли значение аргумента из таблицы в заданное множество
                for (int j : set) {
                    if (var == j) {
                        success = true;
                        break;
                    }
                }

                if (success == false) {
                    System.out.println("Множество не принадлежит функции.");
                    break finish;
                }
            }
        }
        if(success){
            System.out.println("Множество принадлежит функции.");
        }
    }

    public void startSolve(){
        buildTable();
        outputTable();
        outputSecondForm();
        checkSet();
    }
}
