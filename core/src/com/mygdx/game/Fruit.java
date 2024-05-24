package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

public class Fruit{
    public FixtureDef fixtureDef;
    public BodyDef bodyDef;

    public Fruit(float x, float y) {
        bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Node.size, Node.size);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

    }

    @Override
    public String toString() {
        return "Fruit";
    }
}
