public class FunctionOneVar implements Function{
    int k = -1;        // k-значность логики
    int[] table;
    int constant; // функция-константа
    byte command;

    public FunctionOneVar(int k, byte command, int constant) {
        this.k = k;
        this.command = command;
        this.constant = constant;
    }

    public void buildTable() {
        switch (command) {
            case 1 -> {
                table = new int[k];
                for (int i = 0; i < k; i++) {
                    table[i] = constant;
                }
            }
            case 2 -> {
                table = new int[k];
                for (int i = 0; i < k; i++) {
                    table[i] = (i + 1) % k;
                }
            }
        }
    }

    public void outputTable(){
        System.out.println("\n-----");
        System.out.println("Таблица значений:");
        System.out.println("+-------------+");
        System.out.println("| x |  result |");
        System.out.println("+-------------+");
        for (int i = 0; i < k; i++) {
            System.out.println("| " + i + " |    " + table[i] + "    |");
        }
        System.out.println("+-------------+");
    }

    public void outputSecondForm(){
        System.out.println("\n-----");
        System.out.println("Вторая форма:");
        int temp;
        for (int i = 0; i < k; i++) {
            temp = table[i];

            if (temp != 0) {
                if(i > 0){
                    System.out.print(" + ");
                }
                if (temp == 1) { System.out.print("i_" + i + "(x)");
                    }
                else{
                    System.out.print(temp + "*i_" + i + "(x)");
                }
            }
        }
        System.out.println("\n");
    }

    public void getAnswer(int[] set){
        int var;       // Элемент из таблицы значений
        boolean success = false; // Есть совпадение или нет

        for (int value : set) {
            success = false;
            var = table[value];

            for (int i : set) {
                if (var == i) {
                    success = true;
                    break;
                }
            }

            if (success == false) {
                System.out.println("Множество не принадлежит функции.");
                break;
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
