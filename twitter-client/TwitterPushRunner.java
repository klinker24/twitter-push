public class TwitterPushRunner {

    public static void main(String[] args) {
        System.out.println("---- Twitter Push Server ----");

        new UserListener().start();
    }
}
