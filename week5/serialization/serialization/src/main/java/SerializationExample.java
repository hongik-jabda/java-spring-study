import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationExample {
    public static void main(String[] args) {
        Person person = new Person("혜윤", 22);

        try (
                FileOutputStream fileOut = new FileOutputStream("person.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut)
        ) {
            out.writeObject(person);
            System.out.println("Serialized data is saved in person.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
