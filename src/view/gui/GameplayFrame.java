package view.gui;

import model.questions.Category;
import model.questions.Difficulty;
import javax.swing.*;

public abstract class GameplayFrame extends JFrame implements UI {
    protected JLabel gamemode;
    protected JLabel questionTextLabel;
    protected JLabel categoryLabel;
    protected JLabel roundLabel;
    protected boolean hasTimer;
    protected int count = 10000;
    protected final Timer timer;
    protected JLabel timerLabel;
    protected JLabel difficultyLabel;

    public GameplayFrame() /*throws HeadlessException*/ {
        gamemode = UtilGUI.getLabelInstance("");
        questionTextLabel = UtilGUI.getLabelInstance("");
        categoryLabel = UtilGUI.getLabelInstance("");
        roundLabel = UtilGUI.getLabelInstance("");
        hasTimer = false;
        timerLabel = UtilGUI.getLabelInstance("");
        difficultyLabel = UtilGUI.getLabelInstance("");
        timer =  new Timer(100, e -> {
            if(count<=0) {
                ((Timer) e.getSource()).stop();
            } else {
                count -= 100;
            }
            if(hasTimer)
                GameplayFrame.this.timerLabel.setText((count/1000.0)+" seconds");
            else
                GameplayFrame.this.timerLabel.setText("");
        });
    }

    @Override
    public void updateGamemode(String gamemodeName) {
        this.gamemode.setText(gamemodeName);
    }

    @Override
    public void updateQuestion(String question) {
        this.questionTextLabel.setText("<html>"+question+"</html>");
    }

    @Override
    public void updateCategory(Category category) {
        categoryLabel.setText("Category : "+category.toString());
    }

    @Override
    public void updateRoundId(String id) {
        roundLabel.setText("Round : "+id);
    }

    @Override
    public void setHasTimer(boolean b) { this.hasTimer = b; }

    @Override
    public void restartCount() {
        this.count = 10000;
    }

    @Override
    public void stopTimer() {
        this.timer.stop();
    }

    @Override
    public void startTimer() { this.timer.start(); }

    @Override
    public void updateDifficulty(Difficulty difficulty) {
        this.difficultyLabel.setText(difficulty.toString());
    }
}
