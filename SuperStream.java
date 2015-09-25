import java.io.ByteArrayInputStream;

public class SuperStream extends ByteArrayInputStream {

	public SuperStream(final byte abyte0[]) {
		super(abyte0);
	}

	@Override
	public int read() {
		int i = super.read();
		if (i == -1) {
			reset();
			i = super.read();
		}
		return i;
	}

	@Override
	public int read(final byte abyte0[], final int i, final int j) {
		int k;
		for (k = 0; k < j;) {
			final int l = super.read(abyte0, i + k, j - k);
			if (l >= 0)
				k += l;
			else
				reset();
		}

		return k;
	}
}
