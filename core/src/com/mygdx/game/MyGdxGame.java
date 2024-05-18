package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.physics.box2d.*;

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

		float height = Gdx.graphics.getHeight();
		float width = Gdx.graphics.getWidth();

		camera = new OrthographicCamera(width, height);

		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();

		snake = new Snake(world,22,22);

		Timer tSnake = new Timer();
		tSnake.schedule(new TimerTask() {
			@Override
			public void run() {
				snake.update();
			}
		}, 1000, 500);

		//Creating border
		//Left
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(-3, height/2f));
		Body groundBody = world.createBody(groundBodyDef);
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(1, height);
		groundBody.createFixture(groundBox, 0.0f);
		groundBox.dispose();
		//Right
		groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(width+3, height/2f));
		groundBody = world.createBody(groundBodyDef);
		groundBox = new PolygonShape();
		groundBox.setAsBox(1, height);
		groundBody.createFixture(groundBox, 0.0f);
		groundBox.dispose();
		//Upwards
		groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(width/2f, height+3));
		groundBody = world.createBody(groundBodyDef);
		groundBox = new PolygonShape();
		groundBox.setAsBox(width, 1);
		groundBody.createFixture(groundBox, 0.0f);
		groundBox.dispose();
		//Downwards
		groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(width/2f, -3));
		groundBody = world.createBody(groundBodyDef);
		groundBox = new PolygonShape();
		groundBox.setAsBox(width, 1);
		groundBody.createFixture(groundBox, 0.0f);
		groundBox.dispose();

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
