
import java.util.*;
import java.util.regex.*;

abstract class ExamQuestion {
    String type;
    String correct, answer;
    double points;

    ExamQuestion(String type, String correct,
                 String answer, double points) {
        this.type = type;
        this.correct = correct;
        this.answer = answer;
        this.points = points;
    }

    abstract double grade();
}

class MCQ extends ExamQuestion {
    MCQ(String c, String a, double p) {
        super("MCQ", c, a, p);
    }

    double grade() {
        return answer.equalsIgnoreCase(correct) ? points : 0;
    }
}

class TrueFalse extends ExamQuestion {
    TrueFalse(String c, String a, double p) {
        super("TF", c, a, p);
    }

    double grade() {
        return answer.equalsIgnoreCase(correct) ? points : 0;
    }
}

class Essay extends ExamQuestion {
    Essay(String c, String a, double p) {
        super("ESSAY", c, a, p);
    }

    double grade() {
        String[] keywords = correct.split(",");
        int matches = 0;

        for (String keyword : keywords) {
            if (answer.toLowerCase().contains(
                    keyword.trim().toLowerCase())) {
                matches++;
            }
        }

        if (matches >= 2) {
            return points * 0.75;
        } else if (matches == 1) {
            return points * 0.50;
        }

        return 0;
    }
}

public class QuestionGrader {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        double total = 0;

        Pattern pattern = Pattern.compile(
                "\"([^\"]*)\"|(\\S+)");

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            Matcher matcher = pattern.matcher(line);
            ArrayList<String> tokens = new ArrayList<>();

            while (matcher.find()) {
                tokens.add(matcher.group(1) != null
                        ? matcher.group(1)
                        : matcher.group(2));
            }

            String type = tokens.get(0);
            String correct = tokens.get(2);
            String answer = tokens.get(3);
            double points = Double.parseDouble(
                    tokens.get(4));

            ExamQuestion q;

            switch (type) {
                case "MCQ":
                    q = new MCQ(correct, answer, points);
                    break;
                case "TF":
                    q = new TrueFalse(correct, answer, points);
                    break;
                default:
                    q = new Essay(correct, answer, points);
            }

            double score = q.grade();
            System.out.printf("%s: %.2f%n", type, score);
            total += score;
        }

        System.out.printf("Total Score: %.2f%n", total);
        sc.close();
    }
}

