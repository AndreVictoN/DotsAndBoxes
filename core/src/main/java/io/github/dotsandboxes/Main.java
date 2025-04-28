package io.github.dotsandboxes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    Dots dots;
    Text text;
    Lines lines;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        batch = new SpriteBatch();
        //image = new Texture("libgdx.png");
        dots = new Dots();
        text = new Text();
        lines = new Lines();
    }

    @Override
    public void render() {
        ScreenUtils.clear(233f, 233f, 233f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        dots.draw(batch);
        text.draw(batch);
        lines.draw(batch);

        Vector3 mousePos3 = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos3);

        Vector2 mousePos = new Vector2(mousePos3.x, mousePos3.y);
        lines.checkIfMouseIsHovering(mousePos);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        dots.dispose();
        text.dispose();
        lines.dispose();
    }
}
