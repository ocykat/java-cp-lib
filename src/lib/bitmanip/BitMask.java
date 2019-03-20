package lib.bitmanip;

import static lib.bitmanip.BitManip.INT_MASK_SIZE;

public class BitMask {
    public int[] mask;

    public BitMask(int size) {
        int maskLength = (int) Math.ceil((double) size / INT_MASK_SIZE);
        this.mask = new int[maskLength];
    }

    public void shiftLeft() {
        mask[mask.length - 1] <<= 1;

        int msb = 1 << (INT_MASK_SIZE - 1);

        for (int i = mask.length - 2; i > -1; i--) {
            if ((mask[i] & msb) == msb) {
                ++mask[i + 1];
            }
            mask[i] <<= 1;
        }
    }

    public void shiftRight() {
        mask[0] >>= 1;

        int msb = 1 << (INT_MASK_SIZE - 1);

        for (int i = 1; i < mask.length; i++) {
            if ((mask[i] & 1) == 1) {
                mask[i - 1] |= msb;
            }
            mask[i] >>= 1;
        }
    }

    public void setBit(int i) {
        int x = i / INT_MASK_SIZE;
        int y = i % INT_MASK_SIZE;
        mask[x] = BitManip.setBit(mask[x], y);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = mask.length - 1; i > -1; i--) {
            stringBuilder.append(BitManip.toString(mask[i]));
        }

        return stringBuilder.toString();
    }
}
