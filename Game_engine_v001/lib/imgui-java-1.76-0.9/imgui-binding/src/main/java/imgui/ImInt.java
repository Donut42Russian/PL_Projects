package imgui;

import java.util.Objects;

public final class ImInt {
    final int[] data = new int[]{0};

    public ImInt() {
    }

    public ImInt(final int value) {
        set(value);
    }

    public int get() {
        return this.data[0];
    }

    public void set(final int value) {
        this.data[0] = value;
    }

    @Override
    public String toString() {
        return String.valueOf(get());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ImInt imInt = (ImInt) o;
        return data[0] == imInt.data[0];
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data[0]);
    }
}
