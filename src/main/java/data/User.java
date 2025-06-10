package data;

public class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Метод для генерации случайного пользователя
    public static User generateRandomUser() {
        return new User(
                DataForUsers.generateRandomName(),
                DataForUsers.generateRandomMail(),
                DataForUsers.generateRandomPass()
        );
    }
}