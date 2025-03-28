package quiz.application;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;

public class api {

    public static String[][][] fetchTriviaQuestions(int numQuestions, String difficulty, String category) {
        try {
            // Construct API URL with filters
            String apiUrl = "https://the-trivia-api.com/v2/questions?";
            apiUrl += "limit=" + numQuestions;

            if (!difficulty.isEmpty()) {
                apiUrl += "&difficulties=" + URLEncoder.encode(difficulty, "UTF-8");
            }
            if (!category.isEmpty()) {
                apiUrl += "&categories=" + URLEncoder.encode(category, "UTF-8");
            }

//            System.out.println(apiUrl);

            // Initialize question and answer arrays
            String[][] questions = new String[numQuestions][5];
            String[][] answers = new String[numQuestions][2];

            // API Request
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code : " + conn.getResponseCode());
            }

            // Read response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();

            // Parse JSON response
            JSONArray jsonArray = new JSONArray(response.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject questionObj = jsonArray.getJSONObject(i);

                // Extract question text
                JSONObject questionTextObj = questionObj.getJSONObject("question");
                questions[i][0] = questionTextObj.getString("text"); // Store question in first column

                // Extract correct and incorrect answers
                String correctAnswer = questionObj.getString("correctAnswer");
                JSONArray incorrectAnswers = questionObj.getJSONArray("incorrectAnswers");

                // Store correct answer
                answers[i][0] = correctAnswer;

                // Store all options (correct + incorrect) randomly
                String[] options = new String[4];
                for (int j = 0; j < incorrectAnswers.length(); j++) {
                    options[j] = incorrectAnswers.getString(j);
                }
                options[incorrectAnswers.length()] = correctAnswer; // Add correct answer at last

                // Shuffle options (basic shuffle logic)
                for (int j = 0; j < options.length; j++) {
                    int randIndex = (int) (Math.random() * options.length);
                    String temp = options[j];
                    options[j] = options[randIndex];
                    options[randIndex] = temp;
                }

                // Store options in questions array
                for (int j = 0; j < 4; j++) {
                    questions[i][j + 1] = options[j];
                }

                // Store correct answer index for verification
                for (int j = 0; j < 4; j++) {
                    if (questions[i][j + 1].equals(correctAnswer)) {
                        answers[i][1] = String.valueOf(j + 1); // Store correct option index (1-4)
                        break;
                    }
                }
            }
//            for(String[] q:questions) System.out.println(q[0]);
//            System.out.println("--------");
//            for(String[] a:answers) System.out.println(a[0]);

            return new String[][][]{questions, answers}; // Return both arrays together

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if an error occurs
        }
    }

//    public static void main(String[] args) {
//        fetchTriviaQuestions(10,"easy","science");
//    }
}
