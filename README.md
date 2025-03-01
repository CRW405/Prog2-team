# UI
## Load Image Button
Loads input image by opening a file explorer prompt
## Save Image
Saves output image by opening a file explorer prompt
## Use Output
Saves output image in a temporary file then passes that temporary file as the input file
## Image Views
Both images are stored as JavaFX Image objects and passed in with ImageView.SetImage()
## Filter Buttons
Each filter is activated by a Button's OnAction() inside Titledpane's that section each filter into one of these categories 'old', 'colorshift', 'glitch', or 'other' 
## Reset Button
Simply sets the output imageView's image to the input imageView's Image
# Filters
## General
All filters loop through every pixel, store RGBA values as an int named pixel, creates a java Color object to store and simplify pixel setting and getting, the new pixel is an int value created from passing the modified values into a new Color object and using BufferedImage.setRGB(x,y,newPixel)
All filters extend an abstract Filter class that has an abstract applyFilter function taking a File and throwing an IOException. The Filter class also passes down a convertBufferedToFx function that takes a bufferedImage and uses a javaFX WritableImage and its included .getPixelWriter(), loops through the inputs height and width, and does pixelWriter.setArgb(x, y, bufferedImage.getRGB(x, y)) to copy every pixel over
## Color Shifts
Gets every pixel's RGB and performs this algorithm: mainColor*1.4, secondaryColor*.8, tertiaryColor*.4
For example, blueShift does: blue*1.4, green*.8, red*.4
## Grayscale
Averages the pixel color through this algorithm: gray=(red+green+blue) / 3
## Sepia
Algorithm sourced from: https://stackoverflow.com/questions/1061093/how-is-a-sepia-tone-created
filteredColor = red*0.393, green*0.769, blue*0.189 
## Inverse
Subtracts max color value by current color value
## Sort Pixels
Stores all pixels in an array and uses Array.Sort() to quicksort and passes the sorted array back into the image
## Tile Shuffle
Algorithm sourced from http://datamoshing.com/type/image/ 
By dividing the image into 100px by 100px tiles, the filter loops through two randomly selected tiles and swaps the pixels
## Channel Shift
Algorithm sourced from http://datamoshing.com/type/image/ 
Uses a temporary image to select one color value from RGB from every pixel and shifts that color channel in a random direction by replacing the working image's corresponding color value, runs multiple times over same working image
## Overlay
Algorithm sourced from: https://www.tutorialspoint.com/javafx/javafx_images.html
A file prompt selects an overlay image, the overlay image is scaled up with Image.getScaledInstance(), the Graphics2D library is used to draw the base image, reduce the overlay image's opacity, and then draws the new transparent overlay image over the base
# Other
## Error Handling
Every function handles errors by catching the error and displaying an error popup window by using the javaFX Alert object
