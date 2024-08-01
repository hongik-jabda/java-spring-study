`직렬화` : 자바 객체를 일련의 바이트로 변환해 파일에 저장하거나 네트워크를 통해 전송할 수 있게 해주는 기능

`역직렬화` : 바이트 데이터를 다시 자바 객체로 복원하는 과정

객체 데이터를 통신하기 쉬운 포멧(Byte, CSV, Json.. ) 형태로 만들어주는 작업 → **직렬화**

포멧(Byte, CSV, Json ..) 형태에서 객체로 변환하는 과정 → **역직렬화**

```java
class Member{
		private String name;
	
		public Sample(String name) {
			this.name = name;
		}
}
```

위와 같은 클래스가 있을 때, Json 데이터 형식을 예로 들면

`Member member = new Member("혜윤");` 객체를 `{"name" : "혜윤"}` 과 같은 방식으로 변경하는 것을 직렬화, 

`{"name" : "혜윤"}` 데이터를 받아 `Member`  객체의 `name`  필드에 `"혜윤"` 을 할당하고 객체를 생성하는 것을 역직렬화라고 할 수 있다.

## 직렬화가 필요한 이유

1. 데이터 영속화
    
    객체를 파일에 저장함으로써 프로그램이 종료된 후에도 객체의 상태를 보존할 수 있다. 
    
2. 네트워크 전송
    
    객체를 네트워크를 통해 다른 JVM으로 전송할 때 사용된다. 예를 들어 분산 시스템에서 객체를 클라이언트와 서버 간에 주고받을 때 직렬화를 사용한다.
    
3. 캐시 및 큐
    
    객체를 캐시나 메시지 큐에 저장할 때, 객체의 상태를 그대로 유지한 채 저장하고 필요할 때 복원할 수 있다. 


## Serializable 인터페이스 구현

직렬화를 위해선 Serializable 인터페이스를 구현하거나, 혹은 Serializable 인터페이스를 구현한 클래스를 상속받으면 된다.

Serializable 인터페이스는 특별히 메소드를 구현할 필요 없는 마커 인터페이스로서, 직렬화를 고려하여 작성한 클래스인지를 판단하는 기준으로 사용됨

```java
class Sample implements Serializable {
{

class Sample2 extends Sample {
}
```

*마커 인터페이스 : 자바에서 특별한 기능이나 속성을 나타내기 위해 사용되는 인터페이스. 아무런 메소드도 정의하지 않고, 단지 인터페이스 이름만으로 특정 클래스가 어떤 속성을 가지고 있음을 표시

## transient

특정 필드를 직렬화하고 싶지 않은 경우에는 `transient` 키워드를 붙이면, 그 타입의 기본값(int인 경우 0, 객체인 경우 null)으로 직렬화 된다.

```java
class Sample implements Serializable {
		transient String name;
}
```

## 다른 객체를 멤버 변수로 가지고 있을 때

```java
public class Mamber implements Serializable {
		private String id;
		private transient String password;
		private String email;
		
		Obj1 obj1;
		Obj2 obj2;
		
		// ...
}
```

위 코드에서 `Obj1`, `Obj2` 클래스 중 하나라도 Serializable 인터페이스를 구현한 클래스가 없으면 `java.io.NotSerializableException` 예외가 발생한다.

다시 말해 멤버 변수 클래스 중 Serializable 을 구현하지 않은 클래스가 있을 때 직렬화를 할 수 없다.


## 예제

### Person 클래스

```java
import java.io.Serializable;

class Person implements Serializable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
```

### Serialization

```java
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializationExample {
    public static void main(String[] args) {
        Person person = new Person("혜윤", 22);

        try (FileOutputStream fileOut = new FileOutputStream("person.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(person);
            System.out.println("Serialized data is saved in person.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

- 파일 출력 스트림 생성
    - `FileOutputStream` : 파일에 바이트 데이터를 쓰기 위한 스트림을 생성. 여기선 “person.per” 라는 파일을 만듦
    - `ObjectOutputStream` : 객체를 직렬화하여 FileOutputStream을 통해 파일로 출력함
- 객체 직렬화
    - `out.writeObject(person);`
    - writeObject 메소드는 Person 객체를 직렬화하여 파일로 씀. 이 메소드가 호출되면, Person 객체는 일련의 바이트로 변환되어 person.ser 파일에 저장됨
- try-with-resources 구문
    
    자바 7에서 도입된 기능으로, 자원이 자동으로 닫히도록 보장해주는 try 구문.
    
    기존 `try-catch-finally` 블록에서는 `finally` 블록에서 자원을 명시적으로 닫아야 했지만, `try-with-resources` 구문을 사용하면 자원이 자동으로 닫힘
    
    ```java
    try (리소스 선언 및 초기화) {
        // 리소스를 사용하는 코드
    } catch (Exception e) {
        // 예외 처리 코드
    }
    ```
    
- 코드를 실행하면 Person.ser 파일이 생성된다. .txt 확장자를 사용해도 문제 없지만 직렬화된 파일이란 것을 명시하기 위해 .ser 이나 .obj 로 많이 지정한다.

### Deserialization

```java
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
```

- FileInputStream
    - person.ser 파일을 읽기 위해 FileInputStream 객체를 생성. 이 스트림은 파일로부터 바이트 데이터를 읽어들임
- ObjectInputStream
    - FileInputStream을 기반으로 ObjectInputStream 객체를 생성
    - ObjectInputStream은 바이트 데이터를 객체로 변환하는 기능을 제공
- readObject 메소드
    - in.readObject() 메소드를 호출해 파일로부터 객체를 읽어들임. 이 메소드는 직렬화된 데이터를 읽고, 원래의 자바 객체로 복원
    - 읽어들인 객체는 `Object` 타입으로 반환되므로, 해당 객체를 `Person` 클래스로 캐스팅함

위 코드를 실행시켜보면, 역직렬화가 잘 수행된 것을 확인할 수 있다.

이로써 언젠가 사라져버리는 인스턴스 상태에서 영속화가 되었다.

이렇게 역직렬화를 이용하게 되면, 직렬화된 외부 파일만 있다면 생성자로 객체를 초기화할 필요 없이 바로 객체에 정보를 가져와 인스턴스화하여 사용할 수 있게 된다.

## 직렬화 장점

1. 성능 이슈
    - 직렬화 및 역직렬화 과정에서 CPU와 메모리 사용량이 증가할 수 있다. 특히 큰 객체나 많은 데이터를 직렬화하는 경우 성능 저하가 발생할 수 있다.
2. 버전 관리 문제
    - 클래스의 구조가 변경될 경우 (필드 추가/삭제, 타입 변경 등), 이전에 직렬화한 데이터를 역직렬화하는 데 문제 발생할 수 있음. 이를 해결하기 위해 `serialVersionUID` 를 사용하여 클래스의 버전을 관리하지만, 여전히 구조가 많이 바뀌면 호환성 문제 발생할 수 있음
3. 보안 이슈
    - 직렬화된 데이터는 그대로 저장되기 때문에 직렬화된 객체를 악의적으로 수정한 수후 역직렬화할 경우 보안 취약점 생길 수 있음
4. 직렬화 가능 객체의 제한
    - 직렬화를 사용하기 위해서는 모든 객체가 Serializable 인터페이스를 구현해야한다.
  
## 참고
https://inkyu-yoon.github.io/docs/Language/Java/Serialization

https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%A7%81%EB%A0%AC%ED%99%94Serializable-%EC%99%84%EB%B2%BD-%EB%A7%88%EC%8A%A4%ED%84%B0%ED%95%98%EA%B8%B0
