public class Main {

    public static void main(String[] args) {
        String browserName = (args.length >= 1) ? args[0] : "chrome";
        String testScope = (args.length == 2) ? args[1] : "all";

        System.out.println("Hello world " + browserName);
    }
}
