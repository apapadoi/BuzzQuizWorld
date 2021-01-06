package resources.utilResources;

import javax.swing.*;

public class ImageFactory {
    public static ImageIcon createImage(Image image) {
        ImageIcon imageIcon = null;

        switch (image) {
            case INTRO_PAGE_LOGO:
                imageIcon = new ImageIcon(Constants.INTRO_PAGE_IMG_URL);
                break;
            case APP_ICON:
                imageIcon = new ImageIcon(Constants.APP_ICON_IMG_URL);
                break;
            case INTRO_PAGE_BACKGROUND_IMG:
                imageIcon = new ImageIcon(Constants.INTRO_PAGE_BACKGROUND_IMG_URL);
                break;
            case OPTIONS_PAGE_BACKGROUND_IMG:
                imageIcon = new ImageIcon(Constants.OPTIONS_PAGE_BACKGROUND_IMG_URL);
                break;
            case ENGLISH_FLAG:
                imageIcon = new ImageIcon(Constants.ENGLISH_FLAG_IMG_URL);
                break;
            case GREEK_FLAG:
                imageIcon = new ImageIcon(Constants.GREEK_FLAG_IMG_URL);
                break;
            case SCORES_PAGE_BACKGROUND_IMG:
                imageIcon = new ImageIcon(Constants.SCORES_PAGE_IMG_URL);
                break;
            case PLAY_PAGE_BACKGROUND_IMG:
                imageIcon=new ImageIcon(Constants.PLAY_PAGE_BACKGROUND_IMG_URL);
                break;
            case ONE_PLAYER_SELECTION_PAGE_BACKGROUND_IMG:
                imageIcon=new ImageIcon(Constants.ONE_PLAYER_SELECTION_PAGE_BACKGROUND_IMG_URL);
                break;
            case LOADING_SCREEN_PAGE_IMG:
                imageIcon=new ImageIcon(Constants.LOADING_SCREEN_PAGE_IMG_URL);
                break;
            case TWO_PLAYERS_GAMEMODE_BACKGROUND_IMG:
                imageIcon = new ImageIcon(Constants.TWO_PLAYERS_GAMEMODE_BACKGROUND_IMG_URL);
                break;
            case W_KEY_IMG:
                imageIcon = new ImageIcon(Constants.W_KEY_IMG_URL);
                break;
            case A_KEY_IMG:
                imageIcon = new ImageIcon(Constants.A_KEY_IMG_URL);
                break;
            case S_KEY_IMG:
                imageIcon = new ImageIcon(Constants.S_KEY_IMG_URL);
                break;
            case D_KEY_IMG:
                imageIcon = new ImageIcon(Constants.D_KEY_IMG_URL);
                break;
            case UP_KEY_IMG:
                imageIcon = new ImageIcon(Constants.UP_KEY_IMG_URL);
                break;
            case LEFT_KEY_IMG:
                imageIcon = new ImageIcon(Constants.LEFT_KEY_IMG_URL);
                break;
            case DOWN_KEY_IMG:
                imageIcon = new ImageIcon(Constants.DOWN_KEY_IMG_URL);
                break;
            case RIGHT_KEY_IMG:
                imageIcon = new ImageIcon(Constants.RIGHT_KEY_IMG_URL);
                break;
            case QUESTION_IMG_TEST_IMG:
                imageIcon = new ImageIcon(Constants.QUESTION_IMG_TEST_IMG_URL);
                break;
            case FINISH_PAGE_BACKGROUND_IMG:
                imageIcon = new ImageIcon(Constants.FINISH_PAGE_BACKGROUND_IMG_URL);
                break;
            case ONE_PLAYER_PAGE_BACKGROUND_IMG:
                imageIcon=new ImageIcon(Constants.ONE_PLAYER_PAGE_BACKGROUND_IMG_URL);
                break;
            case ONE_PLAYER_BETTING_PAGE_BACKGROUND_IMG:
                imageIcon=new ImageIcon(Constants.ONE_PLAYER_BETTING_PAGE_BACKGROUND_IMG_URL);
        }
        return imageIcon;
    }
}
