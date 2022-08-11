package com.mygdx.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;


public class MyGame extends ApplicationAdapter {
	private ShapeRenderer sRenderer;
	private int width;
	private int touchX;
	private int touchY;
	private OrthographicCamera cam;
	private Vector3 touchpos;
	boolean[][] grid;
	
	@Override
	public void create () {
		width = 10;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800, 600);
		sRenderer = new ShapeRenderer();
		sRenderer.setAutoShapeType(true);
		touchpos = new Vector3();
		grid = new boolean[30][40];
		for(int i=0; i<30; i++){
			for(int j=0; j<40; j++){
				grid[i][j] = false;
			}
		}
	}

	@Override
	public void render () {

		cam.unproject(touchpos.set(Gdx.input.getX(), Gdx.input.getY(), 0));

		touchX = (int)Math.floor(touchpos.x/width);
		touchY = (int)Math.floor(touchpos.y/width);
		touchX = MathUtils.clamp(touchX, 0, 39);
		touchY = MathUtils.clamp(touchY, 0, 29);

		if(Gdx.input.isTouched()){
			int selectX = (int)Math.floor(touchpos.x/width);
			int selectY = (int)Math.floor(touchpos.y/width);
			if(selectX >= 0 && selectX < 40 && selectY >= 0 && selectY < 30){
				grid[selectY][selectX] = true;
				System.out.println(selectX + ", " + selectY);
			}

		}

		// System.out.println(touchX + ", " + touchY);



		ScreenUtils.clear(0.2f, 0, 0, 1);
		sRenderer.begin(ShapeRenderer.ShapeType.Filled);
		for(int i=0; i<30; i++){
			for(int j=0; j<40; j++){
				if(j == touchX && i == touchY){
					sRenderer.setColor(1, 0, 0, 1);
				}
				else if(grid[i][j] == true){
					sRenderer.setColor(0, 1, 0, 1);
				}
				else{
					sRenderer.setColor(1, 1, 1, 1);
				}
				sRenderer.rect(j*width , i*width, width-1, width-1);
			}
		}
		sRenderer.end();
		
	}
	
	@Override
	public void dispose () {
	}


}
