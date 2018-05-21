package com.mhmtnasif;


public abstract class Figure {
    abstract void call(Visitor visitor);
}


class Circle extends Figure {

    private Integer radius;
    private Integer positionX;
    private Integer positionY;
    private Integer scale;

    public Circle(Integer radius, Integer positionX, Integer positionY) {
        this.radius = radius;
        this.positionX = positionX;
        this.positionY = positionY;
        this.scale = 1;
    }

    @Override
    void call(Visitor visitor) {
        visitor.visit(this);
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }
}

class Square extends Figure {

    private Integer edge;
    private Integer positionX;
    private Integer positionY;
    private Integer scale;

    public Square(Integer edge, Integer positionX, Integer positionY) {
        this.edge = edge;
        this.positionX = positionX;
        this.positionY = positionY;
        this.scale = 1;
    }

    public Integer getEdge() {
        return edge;
    }

    public void setEdge(Integer edge) {
        this.edge = edge;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    @Override
    void call(Visitor visitor) {
        visitor.visit(this);
    }
}

class Triangle extends Figure {

    private Integer edge;
    private Integer positionX;
    private Integer positionY;
    private Integer scale;

    public Triangle(Integer edge, Integer positionX, Integer positionY) {
        this.edge = edge;
        this.positionX = positionX;
        this.positionY = positionY;
        this.scale = 1;
    }

    public Integer getEdge() {
        return edge;
    }

    public void setEdge(Integer edge) {
        this.edge = edge;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    @Override
    void call(Visitor visitor) {
        visitor.visit(this);
    }
}