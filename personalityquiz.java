import java.util.*;
//Co2-Abstract Data Type..
class Question {
    String question;
    String[] options;
    String[] type;

    Question(String q, String[] op, String[] t) {
        question = q;
        options = op;
        type = t;
    }
}

public class PersonalityInsightPortal {

    static Scanner sc = new Scanner(System.in);

    // CO4 - HashMap for score tracking
    static HashMap<String, Integer> score = new HashMap<>();

    // CO3 - Queue to process questions
    static Queue<Question> quizQueue = new LinkedList<>();

    public static void main(String[] args) {

        login();

        loadQuestions();

        conductQuiz();

        showResult();
    }

    // LOGIN
    static void login() {

        System.out.println("===== Personality Insight Portal =====");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            System.out.println("Please fill all details");
            System.exit(0);
        }

        System.out.println("\nWelcome " + name);
    }

    // LOAD QUESTIONS
    static void loadQuestions() {
//Co4 Hashing
        score.put("thinker", 0);
        score.put("social", 0);
        score.put("adventurer", 0);

        quizQueue.add(new Question(
                "How do you prefer spending free time?",
                new String[]{"Thinking quietly", "With friends", "Exploring new places"},
                new String[]{"thinker", "social", "adventurer"}));

        quizQueue.add(new Question(
                "In a team, you usually:",
                new String[]{"Plan carefully", "Motivate others", "Take bold decisions"},
                new String[]{"thinker", "social", "adventurer"}));

        quizQueue.add(new Question(
                "Your friends describe you as:",
                new String[]{"Wise", "Friendly", "Fearless"},
                new String[]{"thinker", "social", "adventurer"}));

        quizQueue.add(new Question(
                "When faced with a problem:",
                new String[]{"Analyze deeply", "Discuss with others", "Try something new"},
                new String[]{"thinker", "social", "adventurer"}));

        quizQueue.add(new Question(
                "You enjoy places that are:",
                new String[]{"Quiet", "Lively", "Adventurous"},
                new String[]{"thinker", "social", "adventurer"}));
    }

    // CONDUCT QUIZ
    static void conductQuiz() {

        while (!quizQueue.isEmpty()) {

            Question q = quizQueue.poll();

            System.out.println("\n" + q.question);

            for (int i = 0; i < q.options.length; i++) {
                System.out.println((i + 1) + ". " + q.options[i]);
            }

            System.out.print("Choose option (1-3): ");
            int choice = sc.nextInt();

            String type = q.type[choice - 1];

            score.put(type, score.get(type) + 1);
        }
    }

    // RESULT
    static void showResult() {

        System.out.println("\nCalculating result...");

        // CO1 - Bubble Sort on scores
        List<Map.Entry<String, Integer>> list = new ArrayList<>(score.entrySet());

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).getValue() < list.get(j + 1).getValue()) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }

        String personality = list.get(0).getKey();

        // CO1 - Linear search for description
        HashMap<String, String> descriptions = new HashMap<>();
        descriptions.put("thinker", "You are calm, logical and analytical.");
        descriptions.put("social", "You are friendly, expressive and energetic.");
        descriptions.put("adventurer", "You are bold, curious and love challenges.");

        System.out.println("\n===== RESULT =====");
        System.out.println("Your Personality Type: " + personality.toUpperCase());
        System.out.println(descriptions.get(personality));
    }
}