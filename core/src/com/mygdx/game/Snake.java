package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;



public class Snake {
    public Node head = null;

    private Direction _direction = Direction.RIGHT;

    public Snake(World world, float x, float y) {

        head = new Node(world, x, y);
        world.setContactListener(head);
    }

    public void getInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            System.out.println("Input Up");
            if (_direction != Direction.UP) _direction = Direction.UP;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            System.out.println("Input Down");
            if (_direction != Direction.DOWN) _direction = Direction.DOWN;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            System.out.println("Input Left");
            if (_direction != Direction.LEFT) _direction = Direction.LEFT;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            System.out.println("Input Right");
            if (_direction != Direction.RIGHT) _direction = Direction.RIGHT;
        }
    }

    public void update() {
        switch (_direction) {
            case UP:
                head.setPosition(head.body.getPosition().x, head.body.getPosition().y + Node.size*2 + 2);
                break;
            case DOWN:
                head.setPosition(head.body.getPosition().x, head.body.getPosition().y - Node.size*2 - 2);
                break;
            case LEFT:
                head.setPosition(head.body.getPosition().x - Node.size*2 - 2, head.body.getPosition().y);
                break;
            case RIGHT:
                head.setPosition(head.body.getPosition().x + Node.size*2 + 2, head.body.getPosition().y);
                break;
            default:
                break;
        }
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
    }
}
