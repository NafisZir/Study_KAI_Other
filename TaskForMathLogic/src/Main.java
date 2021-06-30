public class Main {
    public static void main(String[] args) {
        // Информация о студенте и о задании
        System.out.println("----------------------------------------------------------");
        System.out.println("Хуснутдинов Н.Ф.    гр.4211    номер в списке-27");
        System.out.println("Вариант ф-ии одной переменной - 2 (-x)");
        System.out.println("Вариант ф-ии двух переменных - 3 (x=>y)");
        System.out.println("Вариант стандартного представления ф-ии - 2 (вторая форма)");
        System.out.println("----------------------------------------------------------\n");
        // Начало решения
        Solution solution = new SolutionFunction();
        solution.start();

        System.out.println("Завершение...");
    }
}
