import java.util.Scanner;

public interface Function {
    void buildTable();
    void outputTable();
    void outputSecondForm();
    void startSolve();
    void getAnswer(int[] set);

    default void checkSet(){
        String str;      // строка от пользователя
        String[] strSet; // содержит элементы заданного множества в виде строк
        int[] set;       // содержит элементы заданного множества в целочисленном виде
        System.out.println("-----");
        System.out.println("Проверка принадлежности к множеству.");
        while (true) {
            // Получаем целочисленный массив элементов множества
            System.out.println("Введите элементы множества через запятую:");
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            str = str.replace(" ", ""); // Удаляем пробелы
            strSet = str.split(",");               // Разделяем значения
            set = new int[strSet.length];

            for (int i = 0; i < set.length; i++) {
                set[i] = Integer.parseInt(strSet[i]);
            }

            // Проверяем
            getAnswer(set);

            // Условие выхода из цикла
            System.out.println("Хотите продолжить? 0/1");
            int finish = sc.nextInt();
            if(finish == 0){
                break;
            }
        }
    }
}
