public class Sistema {
    public static void main(String[] args) {
        String n = "Carlos";
        double a = 8;
        double b = 7;
        double c = (a + b) / 2;
        System.out.println("Aluno: " + n);
        System.out.println("Media: " + c);
        if (c >= 6) {
            System.out.println("Aprovado");
        } else {
            System.out.println("Reprovado");
        }
    }
}
