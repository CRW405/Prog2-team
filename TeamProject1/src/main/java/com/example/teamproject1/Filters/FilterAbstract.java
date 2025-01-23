package com.example.teamproject1.Filters;

import javafx.scene.image.Image;

public abstract class FilterAbstract {
    /*
     * TODO
     * Figure out how filters actually work
     * Make this useful
     */
    public abstract void applyFilter(Image image); // Apply filter to image

    public abstract void applyFilter(Image image, double intensity); // Apply filter to image with intensity
}
