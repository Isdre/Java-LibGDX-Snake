package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Timer;
import java.util.TimerTask;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	SpriteBatch batch;
	Snake snake;

	World world;
	Box2DDebugRenderer debugRenderer;

	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();

		snake = new Snake(world,30,30);

		Timer tSnake = new Timer();
		tSnake.schedule(new TimerTask() {
			@Override
			public void run() {
				snake.update();
			}
		}, 1000, 500);
	}

	public void render () {
		snake.getInput();

		if (Manager.getInstance().death) {
			Gdx.app.exit();
		}

		ScreenUtils.clear(0, 0, 0, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.end();

		debugRenderer.render(world,camera.combined.cpy());

		world.step(1/60f, 6, 2);
	}

	public void dispose () {
		batch.dispose();
		Gdx.gl.glClearColor(Color.BLACK.r,Color.BLACK.g,Color.BLACK.b,Color.BLACK.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void resize (int width, int height) {
	}

	public void pause () {
	}

	public void resume () {
	}
}
