package com.example.teamproject1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {
    /*
     * TODO
     * Filter folders for filter classes
     * Abstract class for filter so that filters are consistent
     * Control Logic for buttons, etc
     * 
     * IDEAS:
     * Greyscale
     * Blue shift, red shift, etc
     * Sepia
     * Mirror 
     */

    @FXML
    private ImageView inputImageView;

    @FXML
    private ImageView outputImageView;

    private Image inputImage;

    private Image outputImage;

    private void loadimage() {
        // Load image from file
    }

    private void applyFilter() {
        // Apply filter to image

        // get selected filter and input image then apply filter
    }
}