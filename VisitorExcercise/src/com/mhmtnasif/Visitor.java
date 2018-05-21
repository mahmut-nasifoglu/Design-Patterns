package com.mhmtnasif;

public abstract class Visitor {

    abstract void visit(Circle circle);

    abstract void visit(Square square);

    abstract void visit(Triangle triangle);
}

class Area extends Visitor {

    @Override
    public void visit(Circle circle) {
        circle.setRadius(circle.getRadius()*circle.getScale());
        System.out.println("Circle area is :"+Math.PI*circle.getRadius()*circle.getRadius());
    }

    @Override
    public void visit(Square square) {
        square.setEdge(square.getEdge()*square.getScale());
        System.out.println("Square area is :"+square.getEdge()*square.getEdge());
    }

    @Override
    public void visit(Triangle triangle) {
        triangle.setEdge(triangle.getEdge()*triangle.getScale());
        System.out.println("Triangle area is :"+(triangle.getEdge()*triangle.getEdge()*Math.sqrt(3))/4);
    }
}

class Move extends Visitor {

    private Integer positionX;
    private Integer positiony;

    public Move(Integer positionX, Integer positiony) {
        this.positionX = positionX;
        this.positiony = positiony;
    }

    @Override
    public void visit(Circle circle) {
        circle.setPositionX(positionX);
        circle.setPositionY(positiony);
        System.out.println("Circle moved to ("+circle.getPositionX()+","+circle.getPositionY()+")");
    }

    @Override
    public void visit(Square square) {
        square.setPositionX(positionX);
        square.setPositionY(positiony);
        System.out.println("Square moved to ("+square.getPositionX()+","+square.getPositionY()+")");
    }

    @Override
    public void visit(Triangle triangle) {
        triangle.setPositionX(positionX);
        triangle.setPositionY(positiony);
        System.out.println("Triangle moved to ("+triangle.getPositionX()+","+triangle.getPositionY()+")");
    }
}



class Scale extends Visitor {
    private Integer dimention;

    public Scale(Integer dimention) {
        this.dimention = dimention;
    }

    @Override
    public void visit(Circle circle) {
        circle.setScale(dimention);
        System.out.println("Circle Scaled to "+circle.getScale());
    }

    @Override
    public void visit(Square square) {
        square.setScale(dimention);
        System.out.println("Square Scaled to "+square.getScale());
    }

    @Override
    public void visit(Triangle triangle) {
        triangle.setScale(dimention);
        System.out.println("Triangle Scaled to "+triangle.getScale());
    }
}