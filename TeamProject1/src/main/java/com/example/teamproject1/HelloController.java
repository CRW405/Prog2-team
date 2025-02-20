package com.example.teamproject1;

import java.awt.Desktop.Action;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.example.teamproject1.filters.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class HelloController {
    /*
     * TODO
     * Filter folders for filter classes - done
     * Abstract class for filter so that filters are consistent - done
     * Normalize blueshift logic with greyscale logic, eg: use same pixel system - done
     * that greyscale uses- done
     * 
     * Control Logic for buttons, etc
     * 
     * 
     * IDEAS:
     * Greyscale - done
     * Blue shift, - done
     * red shift, etc - done
     * Sepia - done
     * Sort pixels - done
     * inverse color - done
     * 
     * Overlay (add another image on top of the current image)
     * Data mosh like effect
     * Mirror
     * make error popup function
     */
    @FXML
    private ImageView inputImageView;
    @FXML
    private ImageView outputImageView;
    @FXML
    private Button loadImageButton;
    @FXML
    private Button applyGrayScaleButton;
    @FXML
    private Button applySepiaButton;
    private File inputImageFile;
    private BufferedImage outputImage;

    @FXML
    private void loadImage(ActionEvent event) {
        // Load image from file
        FileChooser fileChooser = new FileChooser(); // create file chooser object
        fileChooser.setTitle("Open Image File"); // set title of file chooser
        fileChooser.getExtensionFilters().addAll( // add filters to file chooser, we can only select these image files
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        // show file chooser and get selected file, the null indicates that the file
        // chooser is not a child of any window
        if (selectedFile != null) { // as long as the user selected a valid file
            Image inputImage = new Image(selectedFile.toURI().toString());
            // create image object from selected file by passing the file path
            inputImageView.setImage(inputImage); // set the input image view to the selected image
            inputImageFile = selectedFile; // set the input image file to the selected file
        } else {
            System.out.println("File is not valid"); // if the file cannot be selected
            // maybe have an error pop up
        }
    }

    @FXML
    private void saveImage(ActionEvent event) {
        if (outputImage != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                try {
                    String fileName = file.getName();
                    if (!fileName.contains(".")) {
                        file = new File(file.getAbsolutePath() + ".png");
                    }
                    javax.imageio.ImageIO.write(outputImage, "png", file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No output image to save");
            }
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        outputImageView.setImage(inputImageView.getImage());
    }

    @FXML
    private void loadOutput(ActionEvent event) {
        if (outputImage!=null) {
            try {
                File temFile = File.createTempFile("temp", ".png");
                ImageIO.write(outputImage, "png", temFile);
                inputImageFile = temFile;
                inputImageView.setImage(new Image(temFile.toURI().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No output image to load");
        }
    }

    @FXML
    private void applyGrayScale(ActionEvent event) {
        GrayScale grayScale = new GrayScale(); // create greyscale object
        try { // since the filters throw an IOException, we need to catch it
            outputImage = grayScale.applyFilter(inputImageFile); // apply greyscale filter to input image file
            outputImageView.setImage(GrayScale.convertBufferedToFx(outputImage));
            // set the output image view to the output image, converting it to a JavaFX
            // image
        } catch (Exception e) {
            e.printStackTrace(); // if therese an error, print it
            // maybe have an error pop up here too
        }
    }

    @FXML
    private void applySepia(ActionEvent event) {
        // Apply filter to image
        Sepia sepia = new Sepia();

        try {
            outputImage = sepia.applyFilter(inputImageFile);
            outputImageView.setImage(sepia.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // get selected filter and input image then apply filter
    }

    @FXML
    private void applyBlueShift(ActionEvent event) {
        BlueShift blueshift = new BlueShift();

        try {
            outputImage = blueshift.applyFilter(inputImageFile);
            outputImageView.setImage(BlueShift.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void applyRedShift(ActionEvent event) {
        RedShift redshift = new RedShift();

        try {
            outputImage = redshift.applyFilter(inputImageFile);
            outputImageView.setImage(RedShift.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void applyGreenShift(ActionEvent event) {
        GreenShift greenshift = new GreenShift();

        try {
            outputImage = greenshift.applyFilter(inputImageFile);
            outputImageView.setImage(GreenShift.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void applyInverse(ActionEvent event) {
        Inverse inverse = new Inverse();

        try {
            outputImage = inverse.applyFilter(inputImageFile);
            outputImageView.setImage(Inverse.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void applySort(ActionEvent event) {
        Sort sort = new Sort();

        try {
            outputImage = sort.applyFilter(inputImageFile);
            outputImageView.setImage(Sort.convertBufferedToFx(outputImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
