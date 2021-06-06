package org.halim.dynamics.engine.mainpulator.impl;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;

public interface FileManipulator<T> {

    ByteArrayOutputStream generate(Iterator<T> data);

}
