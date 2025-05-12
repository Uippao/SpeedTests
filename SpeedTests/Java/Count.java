public class Count {
    public static void main(String[] args) {
        long start = System.nanoTime();
        for (int i = 1; i <= 10000; i++) System.out.println(i);
        long elapsed = System.nanoTime() - start;
        System.out.printf("Elapsed: %.9f seconds\n", elapsed / 1e9);
    }
}
