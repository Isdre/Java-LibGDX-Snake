package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;


public class Snake {
    Node head = null;


    public Snake(World world, float x, float y) {
        head = new Node(world, x, y);
    }

    public void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            System.out.println("Input Up");
            head.setPosition(head.body.getPosition().x, head.body.getPosition().y + 22);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            System.out.println("Input Down");
            head.setPosition(head.body.getPosition().x, head.body.getPosition().y - 22);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            System.out.println("Input Left");
            head.setPosition(head.body.getPosition().x - 22, head.body.getPosition().y);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            System.out.println("Input Right");
            head.setPosition(head.body.getPosition().x + 22, head.body.getPosition().y);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Node.addNode = true;

        }
    }
}
