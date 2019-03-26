package com.baeldung.architecture.hexa.inside;

import java.util.List;

public class DummyShapeService implements ShapeService {

    private ShapeRepository ShapeRepository;

    public DummyShapeService(com.baeldung.architecture.hexa.inside.ShapeRepository shapeRepository) {
        ShapeRepository = shapeRepository;
    }

    @Override
    public List<Shape> findShapesByColor(String color) {
        return ShapeRepository.loadShapes(color);
    }

}
