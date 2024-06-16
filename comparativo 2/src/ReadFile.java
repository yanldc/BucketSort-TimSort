import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public int[] readArrayFromFile(String filename) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split("\\s+");
                for (String value : values) {
                    if (!value.isEmpty()) {
                        list.add(Integer.parseInt(value.replaceAll("[^0-9-]", "")));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
