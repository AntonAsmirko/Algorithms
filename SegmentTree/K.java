import java.io.*;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;

public class Parking {
    public static void main(String[] args) throws IOException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("parking.out")));
            TreeSet<Integer> parking = new TreeSet<>();
            Scanner scanner = new Scanner(new FileReader(new File("parking.in")));
            int n = scanner.nextInt(), m = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                parking.add(i + 1);
            }
            int pos;
            for (int i = 0; i < m; i++) {
                switch (scanner.next()) {
                    case "enter":
                        pos = scanner.nextInt();
                        Optional<Integer> higher = Optional.ofNullable(parking.ceiling(pos));
                        if (higher.isEmpty()) {
                            Optional<Integer> lower = Optional.ofNullable(parking.higher(0));
                            parking.remove(lower.orElse(Integer.MAX_VALUE));
                            bw.write(lower.orElse(Integer.MAX_VALUE)+"\n");
                        } else {
                            parking.remove(higher.orElse(Integer.MAX_VALUE));
                            bw.write(higher.orElse(Integer.MAX_VALUE)+"\n");
                        }
                        break;
                    case "exit":
                        pos = scanner.nextInt();
                        parking.add(pos);
                        break;
                }
            }
            bw.close();
        } catch (IOException e) {
            throw e;
        }
    }
}