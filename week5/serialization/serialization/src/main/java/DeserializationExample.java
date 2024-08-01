import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeserializationExample {
    public static void main(String[] args) {
        // 파일 경로 지정
        String filename = "person.ser";

        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            // 객체를 역직렬화
            Person person = (Person) in.readObject();

            System.out.println("Deserialized Person: " + person);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
