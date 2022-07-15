// The code uses the previous LFSR to pseudo randomize the colors of pixels
// in a picture and thus can encrypt and decrypt a picture

import java.awt.Color;

public class PhotoMagic {

    // returns a new picture that has a transformed copy of the given picture, using the given lfsr.
    public static Picture transform(Picture picture, LFSR lfsr) {
        Picture pic = picture;

        LFSR lfsr3 = lfsr;

        for (int col = 0; col < pic.width(); col++)
            for (int row = 0; row < pic.height(); row++) {
                Color color = pic.get(col, row);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int pseudo_random_number = lfsr3.generate(8);
                int changedRed = red ^ pseudo_random_number;

                pseudo_random_number = lfsr3.generate(8);
                int changedGreen = green ^ pseudo_random_number;

                pseudo_random_number = lfsr3.generate(8);
                int changedBlue = blue ^ pseudo_random_number;

                Color c1 = new Color(changedRed, changedGreen, changedBlue);

                pic.set(col, row, c1);
            }

        return pic;
    }

    // takes the name of an image file and a description of an lfsr as command-line arguments;
    // displays a copy of the image that is "encrypted" using the LFSR.
    public static void main(String[] args) {
        Picture pic = new Picture(args[0]);
        LFSR lfsr3 = new LFSR(args[1], Integer.parseInt(args[2]));

        transform(pic, lfsr3);

        pic.show();
    }
}
