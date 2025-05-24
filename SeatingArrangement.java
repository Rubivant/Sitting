import java.util.*;

public class SeatingArrangement {

    // Класс для хранения информации об ученике
    static class Student {
        String name;
        Set<String> likes;
        Set<String> dislikes;

        Student(String name) {
            this.name = name;
            this.likes = new HashSet<>();
            this.dislikes = new HashSet<>();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Student> students = new HashMap<>();

        System.out.println("=== Регистрация учеников ===");

        // Ввод информации об учениках
        while (true) {
            System.out.print("Введите имя ученика (или пустую строку для завершения): ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) break;

            if (students.containsKey(name)) {
                System.out.println("Ученик с таким именем уже зарегистрирован.");
                continue;
            }

            Student student = new Student(name);

            System.out.print("Кого " + name + " любит (через запятую): ");
            String likesInput = scanner.nextLine();
            for (String like : likesInput.split(",")) {
                like = like.trim();
                if (!like.isEmpty()) student.likes.add(like);
            }

            System.out.print("Кого " + name + " не любит (через запятую): ");
            String dislikesInput = scanner.nextLine();
            for (String dislike : dislikesInput.split(",")) {
                dislike = dislike.trim();
                if (!dislike.isEmpty()) student.dislikes.add(dislike);
            }

            students.put(name, student);
            System.out.println("Ученик " + name + " зарегистрирован.\n");
        }

        // Вывод нейтральных пар
        System.out.println("\n=== Нейтральные пары ===");
        List<String> names = new ArrayList<>(students.keySet());

        boolean foundNeutral = false;

        for (int i = 0; i < names.size(); i++) {
            for (int j = i + 1; j < names.size(); j++) {
                String nameA = names.get(i);
                String nameB = names.get(j);

                Student A = students.get(nameA);
                Student B = students.get(nameB);

                // Симпатия или антипатия хотя бы с одной стороны
                boolean isLiked = A.likes.contains(nameB) || B.likes.contains(nameA);
                boolean isDisliked = A.dislikes.contains(nameB) || B.dislikes.contains(nameA);

                // Нейтральная пара — без симпатий и антипатий с обеих сторон
                if (!isLiked && !isDisliked) {
                    System.out.println(nameA + " - " + nameB);
                    foundNeutral = true;
                }
            }
        }

        if (!foundNeutral) {
            System.out.println("Нейтральных пар не найдено.");
        }

        scanner.close();
    }
}