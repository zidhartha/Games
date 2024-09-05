import java.util.Arrays;

public class Donut {
    public static void main(String[] args) {
        int counter;
        double x_Rotation = 0, y_rotation = 0; // basically rotation angles.

        double[] z = new double[1760]; // An array to store the depth values of each point
        char[] Donut = new char[1760]; // This will actually store the ASCII structure of the donut

        System.out.println("\u001b[2J"); //This clears the console since we need console to run this code.
        //Intelij cant handle Donuts.

        //This is an infinite loop.
        for (; ; ) {
            Arrays.fill(Donut, 0, 1760, ' '); // Fill the arrays with initial values
            Arrays.fill(z, 0, 1760, 0);   // Each step of the depth will start from 0


            //In each loop we loop up to 6.28 or 2 * pi as in 360 degrees.
            for (double phi = 0; phi < 2 * 6.28; phi += 0.011)
                for (double theta = 0; theta < 6.28; theta += 0.04) {
                    double  sintheta = Math.sin(theta),
                            cosphi = Math.cos(phi),
                            sinx = Math.sin(x_Rotation),
                            sinphi = Math.sin(phi),
                            cosx = Math.cos(x_Rotation),
                            shift = cosphi + 2,
                            Depth = 1 / (sintheta * sinx * shift + sinphi * cosx + 5),
                            costheta = Math.cos(theta),
                            cosy = Math.cos(y_rotation),
                            siny = Math.sin(y_rotation),
                            temp = sintheta * shift * cosx - sinx * sinphi;



                      /*
                        x, y = coordinates of pixel relative to top-left of terminal window
                        z, b = arrays for depth and character representation at
                        Index = index of the pixel
                        Current_Pixel = character that represents current pixel
                     */

                    int x = (int) (40 + 30 * Depth * (costheta * shift * cosy - temp * siny)),
                            y = (int) (12 + 15 * Depth * (costheta * shift * siny + temp * cosy)),
                            Index = x + 80 * y,
                            Current_Pixel = (int) (8 * ((sinphi * sinx - sintheta * cosphi * cosx) * cosy -
                                    sintheta * cosphi * sinx - sinphi * cosx - costheta * cosphi * siny));


                    Current_Pixel = Math.max(Current_Pixel, 0);
                    Current_Pixel = Math.min(Current_Pixel, 11);


                    if (22 > y && y > 0 && x > 0 && 80 > x && Depth > z[Index]) {
                        z[Index] = Depth;
                        Donut[Index] = new char[]{'.', ',', '-', '~', ':', ';', '=', '!', '*', '#', '$', '@'}[Math.max(Current_Pixel,0)];
                    }
                }
            System.out.print("\u001b[H"); //
            for (counter = 0; 1761 > counter; counter++)
                System.out.print(counter % 80 > 0 ? Donut[counter] : 10);


            x_Rotation += 0.04; // Increase the rotation angle of x faster
            y_rotation += 0.02; // Increase the rotation angle of y faster
        }
    }
}
