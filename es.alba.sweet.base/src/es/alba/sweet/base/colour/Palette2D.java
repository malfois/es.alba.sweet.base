package es.alba.sweet.base.colour;

public enum Palette2D {

	NCD("Ncd");

	private int[]	colors	= new int[256];
	private String	name;

	private Palette2D(String name) {
		this.built();
		this.name = name;
	}

	/**
	 * Determines the RGB color for the gammaII palette
	 */
	private void built() {
		int i;
		int[] red = new int[256];
		int[] green = new int[256];
		int[] blue = new int[256];

		float a, b;

		// evaluates the value of the red pixel
		for (i = 0; i < 256; i++) {
			red[i] = 0;
			if ((i > 47) && (i < 65)) {
				a = (float) ((81. - 0.) / (64 - 47));
				b = 81 - a * 64;
				red[i] = (int) (a * i + b);
			}
			if ((i > 64) && (i < 80)) {
				a = (float) ((79. - 81.) / (79 - 65));
				b = 79 - a * 79;
				red[i] = (int) (a * i + b);
			}
			if ((i > 79) && (i < 111)) {
				a = (float) ((255. - 79.) / (110 - 79));
				b = 255 - a * 110;
				red[i] = (int) (a * i + b);
			}
			if ((i > 110) && (i < 163)) {
				red[i] = (255);
			}
			if ((i > 162) && (i < 175)) {
				a = (float) ((163. - 255.) / (174 - 162));
				b = 163 - a * 174;
				red[i] = (int) (a * i + b);
			}
			if ((i > 174) && (i < 193)) {
				a = (float) ((255. - 168.) / (192 - 175));
				b = 255 - a * 192;
				red[i] = (int) (a * i + b);
			}
			if ((i > 192) && (i < 256)) {
				red[i] = (255);
			}
		}

		// evaluates the value of the green pixel
		for (i = 0; i < 256; i++) {
			green[i] = 0;
			if ((i > 113) && (i < 146)) {
				a = (float) ((163. - 0.) / (145 - 113));
				b = 163 - a * 145;
				green[i] = (int) (a * i + b);
			}
			if ((i > 145) && (i < 177)) {
				green[i] = (int) (163.);
			}
			if ((i > 176) && (i < 192)) {
				a = (float) ((255. - 163.) / (191 - 176));
				b = 255 - a * 191;
				green[i] = (int) (a * i + b);
			}
			if (i > 191) {
				green[i] = (255);
			}
		}

		// evaluates the value of the blue pixel
		for (i = 0; i < 256; i++) {
			blue[i] = 0;
			if ((i < 50)) {
				a = (float) ((255. - 0.) / (49. - 0.));
				b = 255 - a * 49;
				blue[i] = (int) (a * i + b);
			}
			if ((i > 49) && (i < 97)) {
				a = (float) ((0. - 255.) / (96. - 49.));
				b = 0 - a * 96;
				blue[i] = (int) (a * i + b);
			}
			if ((i > 128) && (i < 146)) {
				a = (float) ((82. - 0.) / (145. - 128.));
				b = (float) (82. - a * 145.);
				blue[i] = (int) (a * i + b);
			}
			if ((i > 145) && (i < 160)) {
				a = (float) ((0. - 82.) / (159. - 145.));
				b = (float) (0. - a * 159.);
				blue[i] = (int) (a * i + b);
			}
			if (i > 176) {
				a = (float) ((255. - 0.) / (255. - 176.));
				b = (float) (255. - a * 255.);
				blue[i] = (int) (a * i + b);
			}
		}

		// writes the RGB values of the GAMMAII palette in a 256 elements array.
		for (i = 0; i < 256; i++) {
			this.colors[i] = ((red[i] & 0x0ff) << 16) | ((green[i] & 0x0ff) << 8) | (blue[i] & 0x0ff);
			// new Color(colRGB[0][i], colRGB[1][i], colRGB[2][i]);
		}

	}

}
