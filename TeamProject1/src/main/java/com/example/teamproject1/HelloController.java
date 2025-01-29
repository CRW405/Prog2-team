package com.example.teamproject1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {
    /*
     * TODO
     * Filter folders for filter classes - done
     * Abstract class for filter so that filters are consistent - done
     * Control Logic for buttons, etc
     * 
     * IDEAS:
     * Greyscale - in proress
     * Blue shift, red shift, etc
     * Sepia
     * Mirror
     * Overlay (add another image on top of the current image)
     * Sort pixels
     * Data mosh like effect
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