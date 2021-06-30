import java.util.Scanner;

public class SolutionFunction implements Solution{
    int k = -1;        // k-значность логики
    int n = -1;        // число существенных переменных (1 или 2)
    byte command;
    int constant;

    public void start(){
        initValues();
        getFunction();

        Function function;
        if(n == 1){
            function = new FunctionOneVar(k, command, constant);
        } else {
            function = new FunctionTwoVar(k);
        }

        function.startSolve();
    }

    private void initValues(){
        getNumberK();
        getNumberN();
    }

    public void getNumberK(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число k: ");
        while(true) {
            if (sc.hasNextInt()) {
                k = sc.nextInt();
            }
            if (k < 1 | k > 15) {
                System.out.println("Некорректное значение. Должно быть число от 1 до 15. Повторите ввод: ");
            }
            else {
                break;
            }
        }
    }

    public void getNumberN(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число существенных переменных: ");
        while(true) {
            if (sc.hasNextInt()) {
                n = sc.nextInt();
            }
            if (n != 1 && n != 2) {
                System.out.println("Некорректный ввод. Надо ввести 1 или 2. Повторите ввод:");
            }
            else {
                break;
            }
        }
    }

    private boolean processFunc(int n, String func){
        String regex; // Используем регулярные выражения, так как буквы переменных могут быть разными

        if (n == 1) {
            boolean b;
            regex = "(-)([a-z]{1})";

            // Проверяем, является ли функция константой
            try {
                constant = Integer.parseInt(func);
                b = true;
            } catch (NumberFormatException e) {
                b = false;
            }

            if (b && (constant >= 0) && (constant < k)) {
                command = 1;
                return true;
            } else if (func.matches(regex)) {
                command = 2;
                return true;
            }
            else {
                return false;
            }
        }
        else {
            regex = "([a-z]{1})(=)(>)([a-z]{1})";
            if (func.matches(regex)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private void getFunction(){
        String func;
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите функцию: ");
        while(true) {
            func = sc.nextLine();
            func = func.replace(" ", "");

            if (processFunc(n, func)) { break; }
            else { System.out.println("Некорректная функция. Повторите ввод: "); }
        }
    }
}
