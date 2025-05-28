package io.github.dotsandboxes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.dotsandboxes.utils.Screen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Screen currentScreen = Screen.MAIN_MENU;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    Dots dots;
    Text text;
    Text enterText;
    Text textResults;
    Text pointsText;
    Lines lines;
    SquarePlayer squaresPlayer;
    SquareEnemy squaresEnemy;
    Bot bot;
    Music song;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        batch = new SpriteBatch();
        //image = new Texture("libgdx.png");
        dots = new Dots();
        text = new Text();
        enterText = new Text(Screen.MAIN_MENU);
        textResults = new Text(Screen.RESULTS_MENU);
        pointsText = new Text(Screen.GAME);
        lines = new Lines();
        squaresPlayer = new SquarePlayer();
        squaresEnemy = new SquareEnemy();
        bot = new Bot(lines, squaresEnemy, squaresPlayer);
        currentScreen = Screen.MAIN_MENU;
        song = Gdx.audio.newMusic(Gdx.files.internal("song/Wacuka_Instrumental.mp3"));
        song.setLooping(true);
        song.setVolume(0.5f);
    }

    @Override
    public void render() {
        batch.begin();

        if(currentScreen == Screen.GAME)
        {
            screenGame();
        }
        else if(currentScreen == Screen.MAIN_MENU || currentScreen == Screen.RESULTS_MENU)
        {
            menus();
        }

        batch.end();
    }

    public void screenGame()
    {
        pointsText.setResults(currentScreen, lines.playerPoints, lines.enemyPoints);
        if(!song.isPlaying()) song.play();
        ScreenUtils.clear(233f, 233f, 233f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    
        dots.draw(batch);
        text.draw(batch);
        lines.draw(batch);
        squaresPlayer.draw(batch);
        squaresEnemy.draw(batch);
        pointsText.draw(batch);
    
        Vector3 mousePos3 = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos3);
    
        Vector2 mousePos = new Vector2(mousePos3.x, mousePos3.y);
        lines.checkIfMouseIsHovering(mousePos, squaresPlayer, bot);
        if(bot.turn)
        {
            bot.playTurn();
        }
        squaresPlayer.setIsActive(squaresEnemy.getIsActive());

        if(lines.playerPoints + lines.enemyPoints == 25)
        {
            currentScreen = Screen.RESULTS_MENU;
        }
    }

    public void menus()
    {
        if(song.isPlaying()) song.stop();
        ScreenUtils.clear(233f, 233f, 233f, 1f);

        if(currentScreen == Screen.MAIN_MENU){
            text.draw(batch);
            enterText.draw(batch);
            enterText.animateText();
        }else if(currentScreen == Screen.RESULTS_MENU){
            text.draw(batch);
            textResults.setResults(currentScreen, lines.playerPoints, lines.enemyPoints);
            textResults.draw(batch);
            textResults.animateText();
        }

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode){
                if((currentScreen == Screen.MAIN_MENU || currentScreen == Screen.RESULTS_MENU) && keyCode == Input.Keys.ENTER){
                    lines.resetGame();
                    squaresPlayer.resetGame();
                    squaresEnemy.resetGame();
                    currentScreen = Screen.GAME;
                }

                return true;
            }
        });
    }

    @Override
    public void dispose() {
        batch.dispose();
        dots.dispose();
        text.dispose();
        enterText.dispose();
        textResults.dispose();
        pointsText.dispose();
        lines.dispose();
        squaresPlayer.dispose();
        squaresEnemy.dispose();
    }
}
