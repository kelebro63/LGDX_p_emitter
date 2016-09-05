package com.kelebro63.emitter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {

//	public static final int WIDTH = Gdx.graphics.getWidth(); ;
//	public static final int HEIGHT = Gdx.graphics.getHeight();

	SpriteBatch batch;
	ParticleEffect peBlueSmoke;
	ParticleEffect peRedSmoke;
	ParticleEffect peYellowSmoke;

	//OrthographicCamera camera;
	
	@Override
	public void create () {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, Game.WIDTH / 2, Game.HEIGHT / 2);
//		camera.position.x = (Game.WIDTH / 2);//220
//		camera.position.y = (Game.HEIGHT / 2);//400;
//		camera.update();


		peBlueSmoke = new ParticleEffect();
		peBlueSmoke.load(Gdx.files.internal("smoke1.p"),Gdx.files.internal(""));
		peBlueSmoke.getEmitters().first().setPosition(0, height / 6 * 4);
		setHeightForPE(peBlueSmoke, height).start();

		peRedSmoke = new ParticleEffect();
		peRedSmoke.load(Gdx.files.internal("smoke2.p"),Gdx.files.internal(""));
		peRedSmoke.getEmitters().first().setPosition(width, height / 6 * 2);
		setHeightForPE(peRedSmoke, height).start();

		peYellowSmoke = new ParticleEffect();
		peYellowSmoke.load(Gdx.files.internal("smoke3.p"),Gdx.files.internal(""));
		peYellowSmoke.getEmitters().first().setPosition(0, 0);
		setHeightForPE(peYellowSmoke, height).start();
	}

	@Override
	public void render () {
		float value_r = 33.0f;
		float value_g = 36.0f;
		float value_b = 67.0f;
		Gdx.gl.glClearColor(value_r/255f, value_g/255f, value_b/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		peBlueSmoke.update(Gdx.graphics.getDeltaTime());
		peRedSmoke.update(Gdx.graphics.getDeltaTime());
		peYellowSmoke.update(Gdx.graphics.getDeltaTime());

		//batch.setProjectionMatrix(camera.combined);
		batch.begin();
		peBlueSmoke.draw(batch);
		peRedSmoke.draw(batch);
		peYellowSmoke.draw(batch);
		batch.end();

		if (peBlueSmoke.isComplete()) {
			peBlueSmoke.reset();
		}

		if (peRedSmoke.isComplete()) {
			peRedSmoke.reset();
		}

		if (peYellowSmoke.isComplete()) {
			peYellowSmoke.reset();
		}

	}

	private ParticleEmitter setHeightForPE(ParticleEffect effect, int height) {
		ParticleEmitter particleEmitter = effect.getEmitters().get(0);
		ParticleEmitter.SpawnShapeValue shape = particleEmitter.getSpawnShape();//
		shape.setShape(ParticleEmitter.SpawnShape.line);
		particleEmitter.getSpawnHeight().setHigh(height / 4);
		particleEmitter.getSpawnWidth().setHigh(0f);
		//particleEmitter.getSpawnShape().

		return particleEmitter;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
