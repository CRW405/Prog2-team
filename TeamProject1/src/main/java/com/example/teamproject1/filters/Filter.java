package com.example.teamproject1.filters;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

public abstract class Filter {
    public abstract Image applyFilter(File inputFile) throws IOException;
}

/* public abstract class BlueFilter {
    public abstract Image applyBlueFilter(File inputFile) throws I0Exception;
}

me no get :(
    */
