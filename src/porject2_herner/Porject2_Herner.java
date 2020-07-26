/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package porject2_herner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Tom Herner
 */
public class Porject2_Herner extends Application {

    private int x = 1;
    private int y = 1;
    private int correctAnswer = (x * y);
    private int answerStreak = 0;
    private int score = 0;
    private int wrongAnswers = 0;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        TextField input = new TextField();
        Text multiplicationProblem = new Text();

        Text playerScore = new Text(Integer.toString(score));
        Text incorrectAnswers = new Text(Integer.toString(wrongAnswers));

        Button checkBtn = new Button();
        checkBtn.setText("Check My Answer");
        checkBtn.setLayoutX(205);
        checkBtn.setLayoutY(275);

        // The trophy is a free image taken from https://pngtree.com/so/trophy
        FileInputStream imageInput = new FileInputStream("BeautifulTrophy.jpg");
        Image image = new Image("file:BeautifulTrophy.jpg", 100, 100, false, false);
        ImageView imageView = new ImageView(image);

        // The red cross is a free image taken from https://www.clipartmax.com/middle/m2H7N4H7N4b1b1H7_red-cross-clipart-wrong-answer-red-cross-clipart-wrong-answer/
        FileInputStream redCrossImage = new FileInputStream("redCross.png");
        Image redCross = new Image("file:redCross.png", 100, 100, false, false);
        ImageView imageViewRedCross = new ImageView(redCross);

        // The game over is a free image taken from https://dlpng.com/png/6837791
        FileInputStream gameOverImage = new FileInputStream("gameOver.png");
        Image gameOver = new Image("file:gameOver.png", 100, 100, false, false);
        ImageView imageViewGameOver = new ImageView(gameOver);

        // Alert dialog code is from https://code.makery.ch/blog/javafx-dialogs-official/
        // This was taken from the HelloWorldGUI in class exercise, it is repeated below with different title and contect text.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Welcome To The Multiplication Game!");
        alert.setHeaderText(null);
        alert.setContentText("Instructions: Once you have read the insructions please click \"OK\". \n In this game you will be given"
                + " a multiplication problem and asked solve it. Once you input the correct answer you will gain a point and move on to the next question. You will have all the time you need"
                + " to answer as many questions as you can. If you get 10 questions right in a row you will earn a trophy. Once you have answered five questions incorrectly the game will end and your score will be given. Each wrong answer will also decrease your score by one point. "
                + "Please only enter your answers in number format, letters and special characters will not be accepted in your answer.");
        alert.showAndWait();

        input.setOnAction((ActionEvent event) -> {
            if (input.getText().length() > 0) {
                if (input.getText().matches("[0-9]+")) {
                    int userAnswer = Integer.parseInt(input.getText());
                    if (userAnswer == correctAnswer) {
                        if (y < 11) {
                            y++;
                            input.clear();
                            multiplicationProblem.setText(x + " x " + (y));
                            correctAnswer = (x) * (y);
                            answerStreak++;
                            score++;
                            playerScore.setText(Integer.toString(score));
                        }
                        if (y == 11) {
                            y = 1;
                            x++;
                            input.clear();
                            multiplicationProblem.setText((x) + " x " + y);
                            correctAnswer = (x) * (y);
                            if (answerStreak == 10) {
                                hotStreak(imageView);
                                answerStreak = 0;
                            }
                        }
                    } else {
                        wrongAnswers++;
                        if (wrongAnswers < 5) {
                            wrongAnswer(imageViewRedCross, wrongAnswers);
                            score--;
                            playerScore.setText(Integer.toString(score));
                            answerStreak = 0;
                            incorrectAnswers.setText(Integer.toString(wrongAnswers));
                        } else {
                            if (wrongAnswers == 5) {
                                wrongAnswer2(imageViewRedCross);
                                score--;
                                playerScore.setText(Integer.toString(score));
                                answerStreak = 0;
                                incorrectAnswers.setText(Integer.toString(wrongAnswers));
                            }
                        }
                        if (wrongAnswers == 6) {
                            gameOver(imageViewGameOver, score);
                        }
                    }
                } else {
                    invalidAnswer();
                }
            } else {
                invalidAnswer();
            }
        });
        checkBtn.setOnAction((ActionEvent event) -> {
            if (input.getText().length() > 0) {
                if (input.getText().matches("[0-9]+")) {
                    int userAnswer = Integer.parseInt(input.getText());
                    if (userAnswer == correctAnswer) {
                        if (y < 11) {
                            y++;
                            input.clear();
                            multiplicationProblem.setText(x + " x " + (y));
                            correctAnswer = (x) * (y);
                            answerStreak++;
                            score++;
                            playerScore.setText(Integer.toString(score));
                        }
                        if (y == 11) {
                            y = 1;
                            x++;
                            input.clear();
                            multiplicationProblem.setText((x) + " x " + y);
                            correctAnswer = (x) * (y);
                            if (answerStreak == 10) {
                                hotStreak(imageView);
                                answerStreak = 0;
                            }
                        }
                    } else {
                        wrongAnswers++;
                        if (wrongAnswers < 5) {
                            wrongAnswer(imageViewRedCross, wrongAnswers);
                            score--;
                            playerScore.setText(Integer.toString(score));
                            answerStreak = 0;
                            incorrectAnswers.setText(Integer.toString(wrongAnswers));
                        } else {
                            if (wrongAnswers == 5) {
                                wrongAnswer2(imageViewRedCross);
                                score--;
                                playerScore.setText(Integer.toString(score));
                                answerStreak = 0;
                                incorrectAnswers.setText(Integer.toString(wrongAnswers));
                            }
                        }
                        if (wrongAnswers == 6) {
                            gameOver(imageViewGameOver, score);
                        }
                    }
                } else {
                    invalidAnswer();
                }
            } else {
                invalidAnswer();
            }
        });

        // I learned about creating Text Nodes and setting font from this website: https://www.tutorialspoint.com/javafx/javafx_text.htm
        // The next 4 lines dictate the font and text of the multiplication problem shown, as well as the location in the GUI that is it shown.
        multiplicationProblem.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, 100));
        multiplicationProblem.setText(x + " x " + y);
        multiplicationProblem.setX(150);
        multiplicationProblem.setY(160);

        // The next five lines dictate where the answer label is placed within the GUI.
        Label answerLabel = new Label("Answer:");
        VBox answerLabelPlacement = new VBox();
        answerLabelPlacement.setLayoutX(235);
        answerLabelPlacement.setLayoutY(224);
        answerLabelPlacement.getChildren().add(answerLabel);

        // The next four lines dictate where the answer field for the user to enter their answer is placed within the GUI.
        HBox answerField = new HBox();
        answerField.setLayoutX(185);
        answerField.setLayoutY(244);
        answerField.getChildren().add(input);

        // The next four lines dictate where the score label and tracker is placed within the GUI.
        Label scoreLabel = new Label("Correct Answers:");
        scoreLabel.setLayoutX(0);
        scoreLabel.setLayoutY(0);
        playerScore.setLayoutX(95);
        playerScore.setLayoutY(14);

        // The next three lines dictate where the incorrect answers label and tracker will be placed within the GUI. 
        Label incorrectLabel = new Label("Incorrect Answers:");
        incorrectLabel.setLayoutX(0);
        incorrectLabel.setLayoutY(20);
        incorrectAnswers.setLayoutX(100);
        incorrectAnswers.setLayoutY(34);

        Group root = new Group(multiplicationProblem);
        root.getChildren().add(checkBtn);
        root.getChildren().addAll(answerLabelPlacement, answerField, scoreLabel, playerScore, incorrectLabel, incorrectAnswers);

        Scene scene = new Scene(root, 500, 400);

        primaryStage.setTitle("The Multiplication Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * hotStreak creates an alert to tell the player that they have answered 10
     * questions correctly
     *
     * @param imageView
     */
    public static void hotStreak(ImageView imageView) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You're on a hot streak!");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations, you correctly answered 10 questions in a row!");
        alert.setGraphic(imageView);
        alert.showAndWait();
    }

    /**
     * wrongAnswer creates an alert to tell the layer that they have given the
     * wrong answer. It also indicates to the user how many wrong answers they
     * have left before game over
     *
     * @param imageViewRedCross
     * @param wrongAnswers
     */
    public static void wrongAnswer(ImageView imageViewRedCross, int wrongAnswers) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Incorrect!");
        alert.setHeaderText(null);
        if (wrongAnswers > 0) {
            alert.setContentText("Sorry, that was the incorrect answer. Why don't you try again!" + "\n" + "Remember, you only have " + (5 - wrongAnswers) + " wrong answers left before game over!");
        }
        if (wrongAnswers < 0) {
            alert.setContentText("Sorry, that was the incorrect answer. Why don't you try again!" + "\n" + "Remember, your next wrong answer will be your last.");
        }
        alert.setGraphic(imageViewRedCross);
        alert.showAndWait();
    }

    /**
     * wrongAnswer2 creates an alert to tell the layer that they have given the
     * wrong answer. It also indicates to the user how many wrong answers they
     * have left before game over
     *
     * @param imageViewRedCross
     * @param wrongAnswers
     */
    public static void wrongAnswer2(ImageView imageViewRedCross) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Incorrect!");
        alert.setHeaderText(null);
        alert.setContentText("Sorry, that was the incorrect answer. Why don't you try again!" + "\n" + "Remember, your next wrong answer will be your last.");
        alert.setGraphic(imageViewRedCross);
        alert.showAndWait();
    }

    /**
     * gameOver creates an alert to let the user know that the game has ended.
     * The user's score is given, and the game is ended
     *
     * @param imageViewGameOver
     * @param score
     */
    public static void gameOver(ImageView imageViewGameOver, int score) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME OVER!");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations! You answered " + score + " questions correctly before you answered 5 incorrectly! Your score is " + score + "!");
        alert.setGraphic(imageViewGameOver);
        alert.showAndWait();
        Platform.exit();
    }

    /**
     * invalidAnswer creates an alert to tell the user that they have given an
     * invalid answer input.
     */
    public static void invalidAnswer() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Answer");
        alert.setHeaderText(null);
        alert.setContentText("You have entered an invalid answer. Please enter your answer in the answer box");
        alert.showAndWait();
    }
}
