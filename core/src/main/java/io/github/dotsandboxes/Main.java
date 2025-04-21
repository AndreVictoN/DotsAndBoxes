package io.github.dotsandboxes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    //private Texture image;
    Dots dots;
    Text text;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //image = new Texture("libgdx.png");
        dots = new Dots();
        text = new Text();
    }

    @Override
    public void render() {
        ScreenUtils.clear(233f, 233f, 233f, 1f);
        batch.begin();
        //batch.draw(image, 140, 210);
        dots.drawDots(batch);
        text.drawText(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        dots.dispose();
        text.dispose();
        //image.dispose();
    }
}
