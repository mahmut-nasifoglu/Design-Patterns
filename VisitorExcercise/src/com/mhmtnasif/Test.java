package com.mhmtnasif;

public class Test {
    public static void main(String[] args) {
        Visitor areaVisitor = new Area();
        Visitor moveVisitor = new Move(3,2);
        Visitor scaleVisitor = new Scale(3);
        Figure[] figures = {new Circle(2,2,2), new Square(2,2,2), new Triangle(2,2,2)};
        for (Figure f : figures) {
            f.call(areaVisitor);
            f.call(moveVisitor);
            f.call(scaleVisitor);
            f.call(areaVisitor);
        }
    }
}
