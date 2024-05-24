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

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	SpriteBatch batch;
	Snake snake;

	Manager manager;
	World world;
	Box2DDebugRenderer debugRenderer;
	private Queue<Runnable> actionQueue;

	public void create () {
		batch = new SpriteBatch();

		float height = Gdx.graphics.getHeight();
		float width = Gdx.graphics.getWidth();

		camera = new OrthographicCamera(width, height);

		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		world = new World(new Vector2(0, 0), true);

		debugRenderer = new Box2DDebugRenderer();

		manager = Manager.getInstance();
		manager.game = this;
		manager.world = world;
		manager.x = width;
		manager.y = height;

		manager.SpawnFruit();

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

		actionQueue = new LinkedList<>();
	}

	public void render () {
		snake.getInput();

		if (Manager.getInstance().death) {
			Gdx.app.exit();
		}

		manager.update();

		while (!actionQueue.isEmpty()) {
			actionQueue.poll().run();
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

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	public void dispose () {
		batch.dispose();
		Gdx.gl.glClearColor(Color.BLACK.r,Color.BLACK.g,Color.BLACK.b,Color.BLACK.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void scheduleDestroyBody(final Body body) {
		actionQueue.add(new Runnable() {
			@Override
			public void run() {
				world.destroyBody(body);
				manager.SpawnFruit();
			}
		});
	}
}
