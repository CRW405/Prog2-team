package com.example.teamproject1;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import com.example.teamproject1.filters.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/*
 * TODO
 * Filter folders for filter classes - done
 * Abstract class for filter so that filters are consistent - done
 * Normalize blueshift logic with greyscale logic, eg: use same pixel system
 * that greyscale uses- done
 * 
 * User input for select filters
 * 
 * 
 * IDEAS:
 * Greyscale - done
 * Blue shift, - done
 * red shift, etc - done
 * Sepia - done
 * Sort pixels - done
 * inverse color - done
 * TileShuffle - done, by accident
 * make error popup function - done
 * 
 * Data mosh like effect - ended up being channel shift, could revisit
 * 
 * Overlay (add another image on top of the current image) - jay's job and in progress
 * Mirror
 */

public class HelloController {
    //////////////////////////// FXML VARIABLES ////////////////////////////
    @FXML
    private ImageView inputImageView;
    @FXML
    private ImageView outputImageView;
    private File inputImageFile;
    private BufferedImage outputImage;
    //////////////////////////// FXML VARIABLES ////////////////////////////

    //////////////////////////// FILE OPERATIONS ///////////////////////////
    
    private void showError(String errorMessage) { // shows that should be inputted 
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR MESSAGE");
        alert.setContentText(errorMessage);
        alert.showAndWait(); // this shows up as an alert if you don't input a file 
        // so proud of this fr fr
    }

    @FXML
    private void loadImage(ActionEvent event) { // open file chooser to select input image
        FileChooser fileChooser = new FileChooser(); // create file chooser object
        
        // setup //
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")); // allowed file types
        File selectedFile = fileChooser.showOpenDialog(null); // open file picker
        // setup //

        if (selectedFile != null) { // if a file was selected
            try {
                Image inputImage = new Image(selectedFile.toURI().toString()); // create image object from selected file
                inputImageView.setImage(inputImage); // set the input image view to the selected image
                inputImageFile = selectedFile; // set the input image file to the selected file
            } catch (Exception e) {
                System.out.println("File is invalid. PLease choose another valid image file: " + e.getMessage()); // if no file was selected, print error
                showError("File is invalid. Please choose another valid image file."); // error popup
            }
        } else {
            System.out.println("No file was selected. Please select a image file."); // if no file was selected, print error
            showError("No file was selected. Please select a image file."); 
        }
    }




    @FXML
    private void saveImage(ActionEvent event) { // save output image
        if (outputImage != null) { // if there is an output image

            // setup //
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            File file = fileChooser.showSaveDialog(null);
            // setup //

            if (file != null) { // if a file was selected
                try {
                    String fileName = file.getName(); // get the name of the file
                    if (!fileName.contains(".")) { // if the file name does not contain a file extension
                        file = new File(file.getAbsolutePath() + ".png"); // add a .png extension to the file
                    }
                    javax.imageio.ImageIO.write(outputImage, "png", file); // write the output image to the file
                } catch (Exception e) { // catch errors
                    System.out.println("Error saving image: " + e.getMessage());
                    showError("Error saving image. Please try again.");
                }
            } else {
                System.out.println("No output image to save");
                showError("No output image to save.");
            }
        }
    }
    //////////////////////////// FILE OPERATIONS ////////////////////////////

    //////////////////////////// FILTER OPERATIONS ////////////////////////////
    @FXML
    private void reset(ActionEvent event) { // reset output image to input image
        outputImageView.setImage(inputImageView.getImage());
    }

    @FXML
    private void loadOutput(ActionEvent event) { // load output image to input image
        if (outputImage != null) { // if there is an output image
            try {
                File tempFile = File.createTempFile("temp", ".png"); // create a temporary file
                ImageIO.write(outputImage, "png", tempFile); // write the output image to the temporary file
                inputImageFile = tempFile; // set the input image file to the temporary file
                inputImageView.setImage(new Image(tempFile.toURI().toString())); // set the input image view to the temporary file
            } catch (Exception e) {
                System.out.println("Error loading output image: " + e.getMessage());
                showError("Error loading output image.");
                
            }
        } else {
            System.out.println("No output image to load");
            showError("No output image to load.");
        }
    }
    //////////////////////////// FILTER OPERATIONS ////////////////////////////

    //////////////////////////////////////////////////////// FILTERS ////////////////////////////////////////////////////////
    @FXML
    private void applyGrayScale(ActionEvent event) {
        GrayScale grayScale = new GrayScale(); // create greyscale object
        try { // since the filters throw an IOException, we need to catch it
            outputImage = grayScale.applyFilter(inputImageFile); // apply greyscale filter to input image file
            outputImageView.setImage(Filter.convertBufferedToFx(outputImage));
            // set the output image view to the output image, converting it to a JavaFX
            // image
        } catch (Exception e) {
            System.out.println("Error applying grey scale filter: " + e.getMessage());
            showError("Error applying grey scale filter.");
        }
    }

    @FXML
    private void applySepia(ActionEvent event) {
        Sepia sepia = new Sepia();

        try {
            outputImage = sepia.applyFilter(inputImageFile);
            outputImageView.setImage(Filter.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            System.out.println("Error applying sepia filter: " + e.getMessage());
            showError("Error applying sepia filter.");
        }
    }

    @FXML
    private void applyBlueShift(ActionEvent event) {
        BlueShift blueshift = new BlueShift();

        try {
            outputImage = blueshift.applyFilter(inputImageFile);
            outputImageView.setImage(Filter.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            System.out.println("Error applying blue shift filter: " + e.getMessage());
            showError("Error applying blue shift filter.");
        }
    }

    @FXML
    private void applyRedShift(ActionEvent event) {
        RedShift redshift = new RedShift();

        try {
            outputImage = redshift.applyFilter(inputImageFile);
            outputImageView.setImage(Filter.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            System.out.println("Error applying red shift filter: " + e.getMessage());
            showError("Error applying red shift filter.");
        }
    }

    @FXML
    private void applyGreenShift(ActionEvent event) {
        GreenShift greenshift = new GreenShift();

        try {
            outputImage = greenshift.applyFilter(inputImageFile);
            outputImageView.setImage(Filter.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            System.out.println("Error applying green shift filter: " + e.getMessage());
            showError("Error applying green shift filter.");
        }
    }

    @FXML
    private void applyInverse(ActionEvent event) {
        Inverse inverse = new Inverse();

        try {
            outputImage = inverse.applyFilter(inputImageFile);
            outputImageView.setImage(Filter.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            System.out.println("Error applying inverse filter: " + e.getMessage());
            showError("Error applying inverse filter.");
        }
    }

    @FXML
    private void applySort(ActionEvent event) {
        Sort sort = new Sort();

        try {
            outputImage = sort.applyFilter(inputImageFile);
            outputImageView.setImage(Filter.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            System.out.println("Error applying sort filter: " + e.getMessage());
            showError("Error applying sort filter.");
        }
    }

    @FXML
    private void applyShuffleTile(ActionEvent event) {
        ShuffleTile shuffleTile = new ShuffleTile();

        try {
            outputImage = shuffleTile.applyFilter(inputImageFile);
            outputImageView.setImage(Filter.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            System.out.println("Error applying shuffle tile filter: " + e.getMessage());
            showError("Error applying shuffle tile filter.");
        }
    }

    @FXML
    private void applyChannelShift(ActionEvent event) {
        ChannelShift channelShift = new ChannelShift();

        try {
            outputImage = channelShift.applyFilter(inputImageFile);
            outputImageView.setImage(Filter.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            System.out.println("Error applying data mosh filter: " + e.getMessage());
            showError("Error applying data mosh filter.");
        }
    }

    @FXML
    private void applyOverlay(ActionEvent event) {
        Overlay overlay = new Overlay();

        try {
            outputImage = overlay.applyFilter(inputImageFile);
            outputImageView.setImage(Filter.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            System.out.println("Error applying overlay image: " + e.getMessage());
            showError("Error applying overlay filter.");
        }
    }
    //////////////////////////////////////////////////////// FILTERS ////////////////////////////////////////////////////////
}
